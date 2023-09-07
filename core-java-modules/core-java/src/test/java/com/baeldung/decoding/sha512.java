package com.baeldung.decoding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class sha512 {
    private static String HASH = "1a+ASQGsoFIIf7z9t2BNx1MT6tUbVV5pSCi+cWcpJ3/O+0IkQ1aeZenFOtCURlfH5h2nxOqtb9YNXyV+cQUHYg==";
    private static String SALT = "MIYqENzHybA3X5M0M6wX6g==";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String plaintext = HASH + SALT;

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] hash = messageDigest.digest(plaintext.getBytes());

        System.out.println("Result: " + new String(hash));
    }
}
