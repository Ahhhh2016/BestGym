package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.domain.Card;
import com.example.demo.domain.Timetable;
import com.example.demo.domain.User;
import com.example.demo.service.TimetableService;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/timetable")
@Api(tags = "TimetableController")
public class TimetableController {
    @Resource
    private TimetableService timetableService;

    @ApiOperation(value = "获取当前器械的时间表")
    @GetMapping("/getTimetable")
    public Result getTimetable(@RequestParam(value = "facilityId") Integer facilityId){
//        Timetable tt = timetableService.getOne(new QueryWrapper<Timetable>().eq("facilityId", facilityId));
//        return  Result.success(tt);
        List<Timetable> TimetableList = timetableService.list();

        List<Timetable> res = new ArrayList<>();

        for (Timetable t : TimetableList) {
            if (Objects.equals(t.getFacilityid(), facilityId)) {
                res.add(t);
            }
        }

        if(res.size() == 0){
            return Result.error("-1", "返回时间表失败！");
        }

        return Result.success(res, "返回时间表成功！");
    }

}

