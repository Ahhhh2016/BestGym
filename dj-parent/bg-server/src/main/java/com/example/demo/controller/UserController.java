package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.domain.User;
import com.example.demo.domain.Employee;
import com.example.demo.service.UserService;
import com.example.demo.service.EmployeeService;
import com.example.demo.utils.Result;
import com.example.demo.utils.SecurityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 鐢ㄦ埛 前端控制器
 * </p>
 *
 * @author
 * @since 2022-04-08
 */

@Api(tags = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private EmployeeService employeeService;

//    @Resource
//    private HttpServletRequest  request;

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/info")
    public Result info() {
        UserDetails currentUser = SecurityUtils.getCurrentUser();
        return Result.success(currentUser);
    }


//    @ApiOperation(value = "登录之后返回token")
//    @PostMapping("/login")
//    public Result login(@RequestBody User user){
//
//
//
//        Employee e = employeeService.getOne(new QueryWrapper<Employee>().eq("username", user.getUsername()));
//        User u=userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
//
//
//        if(u!=null && !user.getPassword().equals(u.getPassword())){
//            return Result.error("-1","用户登录，账号或密码不正确");
//        }
//        if(e!=null && !user.getPassword().equals(e.getPassword())){
//            return Result.error("-1","员工登录，账号或密码不正确");
//        }
//
//
//        if(u == null && e != null) {
//            return Result.success(e, "验证通过， 员工登录");
//        }
//        else if(u != null && e == null) return Result.success(u, "验证通过， 用户登录");
//        else return Result.error("-1", "用户不存在");
//    }

//    @ApiOperation(value = "登录之后返回token")
//    @PostMapping("/login")
//    public Result login(String username, String password,String code, HttpServletRequest request){
////        String captcha = (String) request.getSession().getAttribute("captcha");
////        System.out.println(captcha + "!!!");
////        if (StringUtils.isBlank(code) || !captcha.equals(code)) {
////            return Result.error("-1","验证码填写错误！");
////        }
//        System.out.println("username:"+username);
//        Employee e = employeeService.getOne(new QueryWrapper<Employee>().eq("username", username));
//        User u=userService.getOne(new QueryWrapper<User>().eq("username", username));
//
//
//        if(u!=null && !password.equals(u.getPassword())){
//            return Result.error("-1","用户登录，账号或密码不正确");
//        }
//        if(e!=null && !password.equals(e.getPassword())){
//            return Result.error("-1","员工登录，账号或密码不正确");
//        }
//
//
//        if(u == null && e != null) {
//            return Result.success(e, "验证通过， 员工登录");
//        }
//        else if(u != null && e == null) return Result.success(u, "验证通过， 用户登录");
//        else return Result.error("-1", "用户不存在");
//    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public Result logout(@RequestBody User user){
//        User u=userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
//
//        if(u==null){
//            return Result.error("-1","用户不存在");
//        }
//        if(!user.getPassword().equals(u.getPassword())){
//            return Result.error("-1","账号或密码不正确");
//        }
        return Result.success(null,"登录已退出");
    }

    @PostMapping("/register")
    public Result userRegister(@RequestBody User user){
        System.out.println(user);
        userService.registerUser(user);
        return Result.success();
    }


    @ApiOperation(value = "注册时添加新用户")
    @PostMapping("/add")
    public Result regist(@RequestBody User user){
        User us=userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if(us!=null){
            return  Result.error("-1","This username exists!");
        }
        User us2 = userService.getOne(new QueryWrapper<User>().eq("email", user.getEmail()));
        if (us2 != null) {
            return Result.error("-1", "This email exists!");
        }
        boolean u=userService.save(user);

        if(u){
            return  Result.success();
        }else{
            System.out.println("err");
            return  Result.error("-1","失败");
        }
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("/modify")
    public Result modify(@RequestBody User user){
//        User us=userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
//        if(us!=null){
//            return  Result.error("-1","This username exists!");
//        }
//        User us2 = userService.getOne(new QueryWrapper<User>().eq("email", user.getEmail()));
//        if (us2 != null) {
//            return Result.error("-1", "This email exists!");
//        }
//        boolean u=userService.save(user);

        User us = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();

        updateWrapper.set("password", user.getPassword());
        updateWrapper.set("email", user.getEmail());
        updateWrapper.set("phoneNumber", user.getPhoneNumber());
        updateWrapper.eq("username", user.getUsername());
        boolean u = userService.update(us, updateWrapper);
        // System.out.println(u);

        if(u){
            return  Result.success(us);
        }else{
            return  Result.error("-1","失败");
        }
    }

//    @GetMapping("/getUser")
//    public Result test(){
//        return  Result.success(userService.getUserOne(1));
//    }

    @GetMapping("/getUser")
    public Result getUser(@RequestParam String username) {
        User us = userService.getOne(new QueryWrapper<User>().eq("username", username));

        if(us != null){
            return  Result.success(us);
        }else{
            return  Result.error("-1","失败");
        }
    }

    @RequestMapping("/error")
    public Result error(){
        return  Result.error("-1","验证码错误"   );
    }
}

