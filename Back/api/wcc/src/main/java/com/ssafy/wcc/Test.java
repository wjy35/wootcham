package com.ssafy.wcc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/")
    public String test() {
        return "hi";
    }
}
