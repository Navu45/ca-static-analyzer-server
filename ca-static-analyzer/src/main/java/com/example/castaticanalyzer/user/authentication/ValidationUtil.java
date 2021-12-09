package com.example.castaticanalyzer.user.authentication;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ValidationUtil {

    public void getErrors(BindingResult bindingResult, Model model) {
        for (Object object : bindingResult.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                model.addAttribute(fieldError.getField(), fieldError.getCode());
            }
        }
    }
}
