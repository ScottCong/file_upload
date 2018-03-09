package com.demo.springdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MyControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder){

    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("author", "Scott Cong");
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public void errorHandler(Exception e, HttpServletResponse response){

        response.setHeader("status","Server Error");
        response.setHeader("project manager","Scott");
        e.printStackTrace();
        return;

    }
}
