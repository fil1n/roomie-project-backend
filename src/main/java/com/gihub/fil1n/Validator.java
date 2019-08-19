package com.gihub.fil1n;

import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;

public class Validator {

    private static EmailValidator validator = EmailValidator.getInstance();

    public static boolean isEmailValid(@NotNull String email) {
        return validator.isValid(email);
    }

//    public static boolean isPhoneNumberValid(@NotNull String number) {
//
//    }



    private Validator() {}
}
