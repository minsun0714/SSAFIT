package com.ssafy.ssafit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.ssafy.ssafit.model.dao")
@Configuration
public class MyBatisConfig {

}
