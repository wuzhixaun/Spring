package com.wuzx.springboot.controller;


import com.wuzx.springboot.config.MyService;
import com.wuzx.springboot.pojo.MyProperties;
import com.wuzx.springboot.pojo.Person;
import com.wuzx.springboot.pojo.RandomProperties;
import com.wuzx.springboot.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/demo")
    public String demo() {
        return "hello spring boot1";
    }


    @Autowired
    Person person;
    @GetMapping("/person")
    public Person person() {
        System.out.println(person);
        return person;
    }

    @Autowired
    Student student;
    @GetMapping("/student")
    public Student student() {
        System.out.println(student);
        return student;
    }


    @Autowired
    private MyProperties myProperties;

    @GetMapping("/myProperties")
    public MyProperties myProperties() {
        return myProperties;
    }

    @Autowired
    private MyService service;
    @GetMapping("/myservice")
    public String myservice() {
        return service.name;
    }


    @Autowired
    private RandomProperties randomProperties;
    @GetMapping("/randomProperties")
    public RandomProperties randomProperties() {
        return randomProperties;
    }

}
