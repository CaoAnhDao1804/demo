package com.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component

public class PhoneValidator implements ConstraintValidator<PhoneValid, String> {

   @Override
   public boolean isValid(String numberphone, ConstraintValidatorContext context) {

      String firstNumber = numberphone.substring(0,1);
      if(firstNumber == "0") {
         if (numberphone.length() == 10) {
            return true;
         }
         else return false;
      } else {
         return false;
      }
      }
}
