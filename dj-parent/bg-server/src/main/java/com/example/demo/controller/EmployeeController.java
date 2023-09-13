package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.demo.domain.*;
import com.example.demo.service.ActivityService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.FacilityService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 职工 前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private ActivityService activityService;


    @GetMapping("/showAllEmployee")
    public Result showAllEmployee() {
        List<Employee> e_list = employeeService.list();

        if (e_list == null) {
            return Result.error("-1", " Employee list find Failed.");
        }

        return Result.success(e_list, "Find Success");
    }

    @PostMapping("/findUser")
    public Result findUser(@RequestParam(value = "username") String username) {
        List<User> u_list;
        // 返回所有y
        if ("".equals(username)) {
            u_list = userService.list();
        } else {
            u_list = userService.list(new QueryWrapper<User>().eq("username", username));
        }
        if (u_list == null) {
            return Result.error("-1", "The user is not exist!");
        }

        return Result.success(u_list, "Find Success!");
    }

    @GetMapping("/listUser")
    public Result listUser(){
        List<User> userList = userService.list();
        if(userList == null){
            return Result.error("-1", "返回用户信息失败！");
        }

        return Result.success(userList, "返回用户信息成功！");
    }

    @GetMapping("/deleteUser")
    public Result deleteUser(@RequestParam int userId){
        System.out.println("delete user:" + userId);
        QueryWrapper<User> c= new QueryWrapper<User>().eq("id", userId);
        if(c==null){
            return  Result.error("-1","This card does not exist!");
        }

        boolean u=userService.remove(c);

        if(u){
            return Result.success();
        }else{
            System.out.println(c);
            System.out.println("err");
            return  Result.error("-1","失败");
        }
    }

    /**
     * 对于场地的操作
     **/
    // 查看所有器械信息
    @GetMapping("/showFacility")
    public Result showFacility() {
        List<Facility> f_List = facilityService.list();

        if (f_List == null) {
            return Result.error("-1", "Find Failed");
        }

        return Result.success(f_List, "Find Success!");
    }

    @GetMapping("/deleteFacility")
    public Result deleteFacility(@RequestParam int facilityId){
        System.out.println("delete facility:" + facilityId);
        QueryWrapper<Facility> c= new QueryWrapper<Facility>().eq("id", facilityId);
        if(c==null){
            return  Result.error("-1","This card does not exist!");
        }

        boolean u=facilityService.remove(c);

        if(u){
            return Result.success();
        }else{
            System.out.println(c);
            System.out.println("err");
            return  Result.error("-1","失败");
        }
    }

    // 查看场地的活动
    @GetMapping("/showActivity")
    public Result showActivity(@RequestParam String type) {
        Facility facility = facilityService.getOne(new QueryWrapper<Facility>().eq("facilityType", type));
        Integer facility_id = facility.getId();
        List<Activity> act_List = activityService.list(new QueryWrapper<Activity>().
                eq("activity_to_which_facility", facility_id));
        if(act_List==null) return Result.error("-1", "failed");

        return Result.success(act_List, "成功返回对应场地的活动");
    }


    /**
     经理登录
     **/
    //修改场地信息
    @PostMapping("/modifyFacility")
    public Result modifyFacility(@RequestBody Facility facility){
        System.out.println("facility_type: " + facility.getFacilityType());
        Facility facility1 = facilityService.getOne(new QueryWrapper<Facility>().eq("facilityType", facility.getFacilityType()));
        if(facility1==null){
            return Result.error("-1", "无此场地！");
        }
        UpdateWrapper<Facility> updateFacility = new UpdateWrapper<>();
        updateFacility.eq("facilityType",facility.getFacilityType());
        updateFacility.set("maxCapacity", facility.getMaxCapacity());
        updateFacility.set("numFacility", facility.getNumFacility());
        
        boolean f = facilityService.update(facility1, updateFacility);

        if(f){
            System.out.println("capacity"+facility1.getMaxCapacity());
            System.out.println("num"+facility1.getNumFacility());
            return Result.success();
        }else{
            return Result.error("-1", "更新失败");
        }
    }

    //查看场地的活动信息并修改
    @GetMapping("/ModifyActivity")
    public Result ModifyActivity(@RequestBody Activity activity){
        Activity activity1 = activityService.getOne(new QueryWrapper<Activity>().eq("activity_name", activity.getActivityName()));
        UpdateWrapper<Activity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("activity_name", activity.getActivityName());
        updateWrapper.set("capacity", activity1.getCapacity());
        updateWrapper.set("price", activity1.getPrice());
        boolean flag = activityService.update(activity1, updateWrapper);

        if(flag){
            return Result.success(activity1, "修改信息成功");
        }else{
            return Result.error("-1", "修改失败");
        }
    }

    
}



