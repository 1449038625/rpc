package com.zbz.example.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Classname: ExampleServiceImplTest
 * Package: com.zbz.example.consumer
 * Decription:
 * @Author: 爱可尼科
 * @Create: 2025/2/13 - 17:40
 * @Version: v1.0
 */
@SpringBootTest
class ExampleServiceImplTest {
    @Resource
    private ExampleServiceImpl exampleService;
    @Test
    void test1() {
        exampleService.test();
    }
}