package com.example.demo.dao;

import com.example.demo.domain.Timetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-17
 */
public interface TimetableMapper extends BaseMapper<Timetable> {
    List<Timetable> getStartToEnd(int start, int end);
}
