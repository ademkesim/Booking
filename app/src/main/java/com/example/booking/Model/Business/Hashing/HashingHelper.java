package com.example.booking.Model.Business.Hashing;

public class HashingHelper {

    private static int workload = 12;

    public static String hashPassword(String password_plaintext){
        String salt = BCrypt.gensalt(workload);

        return (BCrypt.hashpw(password_plaintext, salt));
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash){
        boolean password_verified = true;

        if(null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return(password_verified);
    }
}
