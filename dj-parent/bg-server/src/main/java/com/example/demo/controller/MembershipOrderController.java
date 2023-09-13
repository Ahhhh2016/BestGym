package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.domain.MembershipOrder;
import com.example.demo.domain.User;
import com.example.demo.service.MembershipOrderService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Result;
import com.fasterxml.jackson.annotation.JsonFormat;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.sql.Timestamp;

/**
 * <p>
 *  会员入会办理
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/membershipOrder")
public class MembershipOrderController {
    @Resource
    private MembershipOrderService membershipOrderService;
    @Autowired
    private UserService userService;


    /**
     * 加入会员
     * 1：月会员
     * 2：年会员
    **/
    @GetMapping("/enter")
    public Result enter(@RequestBody User user, @RequestParam Integer type){
        Float money;
        //从user表中找到要入会的会员
        User u = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if(u==null){
            return Result.error("-1", "没有找到该用户！");
        }
        if(u.getMembership()!=0){
            return Result.error("-1", "该用户已办理会员！");
        }

        // 判断价格
        if(type == 1) {
            money = 500.00F;
        }else if(type == 2) {
            money = 2000.00F;
        }else{
            return Result.error("-1", "会员类型不存在");
        }

        Date date = new Date();

        System.out.println("入会日期："+ date);

        MembershipOrder membership = new MembershipOrder();
        membership.setMembershipType(type);
        membership.setPaymentMoney(money);
        membership.setUserId(u.getId());
        membership.setPaymentDate(date);

        boolean add = membershipOrderService.save(membership);
        if(add){
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username", u.getUsername());
            updateWrapper.set("membership", type);

            boolean update = userService.update(u, updateWrapper);
            if(update){
                return Result.success(membership, "入会成功!");
            }else{
                return Result.error("-1", "用户会员信息更改失败!");
            }
        }else{
            return Result.error("-1", "入会失败!");
        }

    }

    /**
     * 取消会员
     */
    @GetMapping("/cancel")
    public Result enter(@RequestBody User user){

        try{
            //查找用户
            User u = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
            if(u==null){
                return Result.error("-1", "没有找到该用户！");
            }
            //匹配会员信息
            MembershipOrder membership = membershipOrderService.getOne(new QueryWrapper<MembershipOrder>().eq("user_id", u.getId()));
            if(membership==null){
                return Result.error("-1", "无该用户会员信息！");
            }

            //删除会员信息
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username", u.getUsername());
            boolean deleteMembership = membershipOrderService.remove(new QueryWrapper<MembershipOrder>().eq("user_id", u.getId()));
            if(deleteMembership){
                updateWrapper.set("membership", 0);
                boolean update = userService.update(u, updateWrapper);
                if(!update){
                    return Result.error("-1", "会员信息更新失败！");
                }
            }else {
                return Result.error("-1", "删除失败！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return Result.success("删除成功！");
        }


    }

}

