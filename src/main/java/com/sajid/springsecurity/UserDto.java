package com.sajid.springsecurity;

public record UserDto(String username, String fullname, String mobile, String email, String password, String confirmPassword, String agreeToTerms) {

}
