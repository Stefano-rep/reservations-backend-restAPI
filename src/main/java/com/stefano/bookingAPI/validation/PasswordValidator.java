package com.stefano.bookingAPI.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint,String>{

    private int min;
    private int max;


    @Override
    public void initialize(PasswordConstraint constraint){
        this.min = constraint.min();
        this.max = constraint.max();
    }

    @Override
    public boolean isValid (String password, ConstraintValidatorContext context){
        if(password == null || password.isBlank()){
            return false;
        }

        if(password.length() < min || password.length() > max){
            return false;
        } 

        return password.matches("^(?=.*[A-Za-z])(?=.*\\d).+$");
    }
}
