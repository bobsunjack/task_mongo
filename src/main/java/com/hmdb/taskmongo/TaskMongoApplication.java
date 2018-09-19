package com.hmdb.taskmongo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hmdb.taskmongo.mysql.dao")
public class TaskMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskMongoApplication.class, args);
    }
}
