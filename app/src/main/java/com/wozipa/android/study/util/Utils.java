package com.wozipa.android.study.util;

/**
 * Created by Wozipa on 2017/5/21.
 */

public class Utils {

    public static boolean checkString(String string)
    {
        if(string==null ||string.isEmpty())
        {
            return false;
        }
        return true;
    }

    public static boolean checkStrings(String... strings)
    {
        for(String string:strings)
        {
            if(!checkString(string))
            {
                return false;
            }
        }
        return true;
    }
}
