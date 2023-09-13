package com.example.demo.service.serviceImpl;

import com.example.demo.domain.Employee;
import com.example.demo.dao.EmployeeMapper;
import com.example.demo.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职工 服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-13
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
