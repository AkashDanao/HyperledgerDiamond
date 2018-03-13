package com.globant.akashdanao.hyperledgerdiamond.utils;

import java.util.regex.Pattern;

/**
 * Created by akash.danao on 13/03/18.
 */

public class Utility {

    public static boolean isValidEmail(String email){
        String  emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Pattern pattern = Pattern.compile(emailPattern);
        return pattern.matcher(email).matches();
    }
}
