package com.example.demo.service;

import com.example.demo.domain.Activity;
import com.example.demo.domain.Timetable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-04-17
 */
public interface TimetableService extends IService<Timetable> {
    List<Timetable> getStartToEnd(int start, int end);
}
