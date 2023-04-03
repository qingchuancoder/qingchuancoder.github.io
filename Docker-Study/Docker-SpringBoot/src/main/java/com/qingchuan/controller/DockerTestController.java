package com.qingchuan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 @author 清川
 @date 2022-07-27
 */
@RestController
@RequestMapping("/docker-test")
public class DockerTestController {

    private Logger logger = LoggerFactory.getLogger(DockerTestController.class);
    @GetMapping
    public String testGet() {
        logger.info("Get Successfully");
        return "Get Successfully";
    }

    @PostMapping
    public String testPost() {
        logger.info("Post Successfully");
        return "Post Successfully";
    }
}
