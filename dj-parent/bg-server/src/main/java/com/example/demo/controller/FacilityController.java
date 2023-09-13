package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.domain.*;
import com.example.demo.service.ActivityService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.FacilityService;
import com.example.demo.service.TimetableService;
import com.example.demo.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 *  Facility
 * </p>
 *
 * @author
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/facility")
@Api(tags = "FacilityController")
public class FacilityController {
    @Resource
    private FacilityService facilityService;

    @Autowired
    private EmployeeService employeeService;

    @Resource
    private TimetableService timetableService;

    @Resource
    private ActivityService activityService;

    // 返回所有器械信息
    @ApiOperation(value = "get all facilities")
    @GetMapping("/findAllFacility")
    public Result findAllFacility(){
        List<Facility> f_List = facilityService.list();

        if(f_List==null){
            return  Result.error("-1", "Find Failed");
        }

        return Result.success(f_List, "Find Success!");
    }

    // 传参数：facility的type，返回指定facility
    @ApiOperation(value = "return facility detail")
    @GetMapping("/detail")
    public Result detail(@RequestParam int facilityId){
        Facility fs = facilityService.getOne(new QueryWrapper<Facility>().eq("id", facilityId));

        if(fs == null){
            return Result.error("-1", "Failed");
        }

        return Result.success(fs, "Find Success!");
    }

    // 传参数：facility的type，返回指定facility
    @ApiOperation(value = "return all activities")
    @GetMapping("/getActivities")
    public Result getActivities(@RequestParam int facilityId){
        Activity aa = activityService.getOne(new QueryWrapper<Activity>().eq("facilityId", facilityId));
        if(aa == null){
            return Result.error("-1", "Failed");
        }

        return Result.success(aa, "Find Success!");
    }

//    @ApiOperation(value = "return facility timetable")
//    @GetMapping("/detailtimetable")
//    public Result detailTimetable(@RequestParam int facilityId){
////
//        Timetable tt = timetableService.getOne(new QueryWrapper<Timetable>().eq("facilityId", facilityId));
//        if(tt == null){
//            return Result.error("-1", "Failed");
//        }
//
//        return Result.success(tt, "Find Success!");
//    }


}

