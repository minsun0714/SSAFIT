package com.ssafy.ssafit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.ssafy.exam.model.dao")
@Configuration
public class MyBatisConfig {

}
