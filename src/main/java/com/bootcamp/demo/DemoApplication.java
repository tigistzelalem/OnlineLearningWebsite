package com.bootcamp.demo;

import java.io.File;

import com.bootcamp.demo.Controller.Coursescontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new File(Coursescontroller.uploadDirectory).mkdir();
		SpringApplication.run(DemoApplication.class, args);
	}

}
