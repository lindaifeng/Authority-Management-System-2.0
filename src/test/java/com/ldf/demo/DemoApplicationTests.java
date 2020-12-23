package com.ldf.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        Date departureTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String departureTimeStr = dateFormat.format(departureTime);
        System.out.println(departureTimeStr);
    }

}
