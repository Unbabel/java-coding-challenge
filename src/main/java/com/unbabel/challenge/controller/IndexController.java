package com.unbabel.challenge.controller;


import com.unbabel.challenge.model.Message;
import com.unbabel.challenge.repositories.MessageRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    MessageRepository carRepository;

    @Value("${java.challenge.message}")
    private String msg;

    private List<String> tasks = Arrays.asList("Translation 1","Translation 2","Translation 3");


    @GetMapping("/")
    public String main(Model model) {

        //saving a random message at each request
        carRepository.save(new Message(RandomStringUtils.randomAlphabetic(10)));

        //set variables to be used in thymeleaf template
        model.addAttribute("message", msg);
        model.addAttribute("tasks", tasks);

        return "index"; //thymeleaf template name (index -> index.html)
    }



}
