package com.dungnt.healthclinic.service.impl;

import com.dungnt.healthclinic.dto.SignUpRequest;

public class ValidationService {
    public static void validateCredentials(SignUpRequest signUpRequest) throws Exception {
        if (signUpRequest == null) {
            throw new Exception("Khong co thong tin dang ky");
        }
        if (signUpRequest.getUsername() == null) {
            throw new Exception("Khong co thong tin tai khoan");
        }
        if (signUpRequest.getPassword() == null) {
            throw new Exception("Khong co thong tin mat khau");
        }
        String username = signUpRequest.getUsername();
        String password = signUpRequest.getPassword();
        if (!username.matches("[0-9]+") || username.length() < 10) {
            throw new Exception("Tai khoan phai la so dien thoai");
        }
        if (password.length() < 8) {
            throw new Exception("Mat khau phai co toi thieu 8 ky tu");
        }
        boolean checkUppercase = false;
        boolean checkDigit = false;
        boolean checkLetter = false;
        for (int i = 0; i< password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isLetter(ch)) {
                checkLetter = true;
            }
            if (Character.isDigit(ch)) {
                checkDigit = true;
            }
            if (Character.isUpperCase(ch)) {
                checkUppercase = true;
            }
            if (checkLetter && checkDigit && checkUppercase) {
                break;
            }
        }
        if (!checkLetter || !checkDigit || !checkUppercase) {
            throw new Exception("Mat khau khong du manh");
        }
    }
}
