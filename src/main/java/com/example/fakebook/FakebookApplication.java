package com.example.fakebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.ArrayList;

@EntityScan(basePackages = {"com.example.fakebook.model.entity"})
@SpringBootApplication
public class FakebookApplication {
   public static void main(String[] args) {
      SpringApplication.run(FakebookApplication.class, args);
   }
}
