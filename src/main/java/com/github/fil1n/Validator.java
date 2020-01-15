package com.github.fil1n;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.apache.commons.validator.routines.EmailValidator;
import org.jetbrains.annotations.NotNull;

public class Validator {

    private static EmailValidator validator = EmailValidator.getInstance();
    private static PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static boolean isEmailValid(@NotNull String email) {
        return validator.isValid(email);
    }

    public static boolean isPhoneNumberValid(@NotNull String number, @NotNull String code) throws Exception {

        try {
            Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(number, code);
            return phoneUtil.isValidNumber(swissNumberProto);
        } catch (NumberParseException e) {
            e.printStackTrace();
        }

        throw new Exception();
    }



    private Validator() {}
}
