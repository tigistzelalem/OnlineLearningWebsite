package com.bootcamp.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordencoder {

  public static void main(String[] arg) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawpassword = "1234";
    String encodedPassword = encoder.encode(rawpassword);

    System.out.println(encodedPassword);

  }

}
