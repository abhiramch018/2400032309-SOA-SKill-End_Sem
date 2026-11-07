package com.example.ms1;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {
    @GetMapping(value = "/ms1/add", produces = MediaType.TEXT_PLAIN_VALUE)
    public String add(@RequestParam("a") int a, @RequestParam("b") int b) {
        return "MS 1.1 - Additon = " + ((long) a + b);
    }
}
