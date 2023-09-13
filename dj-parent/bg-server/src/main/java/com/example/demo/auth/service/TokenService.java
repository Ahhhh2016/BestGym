package com.example.demo.auth.service;

import com.example.demo.auth.security.UserDetailsImpl;
import com.example.demo.constants.CommonConstant;
import com.example.demo.utils.IdGen;
import com.example.demo.utils.RSAUtil;
import com.example.demo.utils.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
 * token验证处理
 *
 *
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class TokenService {

    /**
     * token请求头名称
     */
    private String tokenHeader;

    /**
     * 用于signature（签名）部分解密
     */
    private String primaryKey;

    /**
     * 签发者
     */
    private String IIS;

    /**
     * token有效期3600秒，既是1个小时
     */
    private Integer accessTokenValidity;

    /**
     * 刷新token有效期为7天
     */
    private Integer refreshTokenValidity;

    @Autowired
    private RedisService redisService;

    @Value("${private.key}")
    private String privateKey;

    @Value("${public.key}")
    private String publicKey;

    /**
     * 生成token 对称加密
     *
     * @param userDetails 用户信息
     * @return token
     */
    public String getToken(UserDetailsImpl userDetails) {
        PrivateKey rsaPrivatekey = null;
        try {
            rsaPrivatekey = RSAUtil.getPrivateKey(this.privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 加入ID确保生成的 Token 都不一致
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", IdGen.uuid());
        String token = Jwts.builder()
                //设置发证人
                .setIssuer(IIS)
                //.signWith(SignatureAlgorithm.HS512, primaryKey) // 采用HS512算法对JWT进行的签名,PRIMARY_KEY是我们的密钥
                .signWith(SignatureAlgorithm.RS256, rsaPrivatekey)
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .compact();
        return token;
    }

    /**
     * 获取token 存入redis
     *
     * @param userDetails 用户信息
     * @return token
     */
    public String createToken(UserDetailsImpl userDetails) {
        // 对token 进行转换,生成一个uuid.
        String internalToken = UUID.randomUUID().toString();
        Map<String, Object> tokenAndDetails = this.restTokenList(userDetails);
        redisService.setHashAndExpire(CommonConstant.ACCESS_TOKEN_KEY + internalToken, tokenAndDetails, this.accessTokenValidity);
        redisService.setHashAndExpire(CommonConstant.REFRESH_TOKEN_KEY + internalToken, tokenAndDetails, this.refreshTokenValidity);
        return internalToken;
    }

    /**
     * 封装token
     *
     * @param userDetails 用户信息
     * @return list
     */
    public Map<String, Object> restTokenList(UserDetailsImpl userDetails) {
        String token = this.getToken(userDetails);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("info", userDetails);

        List<Object> tokenAndDetails = new ArrayList();
        tokenAndDetails.add(token);
        tokenAndDetails.add(userDetails);
        return map;
    }

    /**
     * 刷新token
     *
     * @param userDetails 用户信息
     * @return token
     */
    public String refreshToken(UserDetailsImpl userDetails) {
        //生成一个uuid
        String internalToken = UUID.randomUUID().toString();
        Map<String, Object> tokenAndDetails = this.restTokenList(userDetails);
        redisService.setHashAndExpire(CommonConstant.ACCESS_TOKEN_KEY + internalToken, tokenAndDetails, this.accessTokenValidity);
        return internalToken;
    }
    /**
     * 删除token
     */
    public void removeToken(String accessToken, String refreshToken) {
        if (StringUtils.isNotEmpty(accessToken) && StringUtils.isNotEmpty(refreshToken)) {
            String accessTokenKey = CommonConstant.ACCESS_TOKEN_KEY + accessToken;
            String refreshTokenKey = CommonConstant.REFRESH_TOKEN_KEY + refreshToken;
            redisService.remove(accessTokenKey);
            redisService.remove(refreshTokenKey);
        }
    }

    /**
     * 从token中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token) {
        RSAPublicKey publicKey = null;
        try {
            publicKey = RSAUtil.getPublicKey(this.publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Jwts.parser()
                //.setSigningKey(this.primaryKey)
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public UserDetailsImpl getLoginUser(HttpServletRequest request) {
        String header = request.getHeader(this.tokenHeader);
        String token = "";
        if (StringUtils.isNotEmpty(header) && header.startsWith(CommonConstant.TOKEN_PREFIX)) {
            token = header.replace(CommonConstant.TOKEN_PREFIX, "");
        }
        if (StringUtils.isNotEmpty(token)) {
            Claims claims = parseToken(token);
            // 解析对应的权限以及用户信息
            String s = (String) claims.get(CommonConstant.ACCESS_TOKEN_KEY);
            UserDetailsImpl user = redisService.getObject(CommonConstant.ACCESS_TOKEN_KEY + s);
            return user;
        }
        return null;
    }
}
