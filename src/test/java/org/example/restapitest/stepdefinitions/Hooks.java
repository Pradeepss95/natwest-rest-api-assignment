package org.example.restapitest.stepdefinitions;


import io.cucumber.spring.CucumberContextConfiguration;
import org.example.restapitest.SpringContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = SpringContextConfiguration.class)
public class Hooks {
}
