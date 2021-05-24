package com.ponder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ponder.mapper")
public class AutoGenerationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoGenerationServerApplication.class, args);
    }

}
