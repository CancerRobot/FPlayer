package com.wozipa.android.study.util;

/**
 * Created by Wozipa on 2017/5/23.
 */

public class StringUtils {

    public static boolean isEmpty(String string)
    {
        if(string==null || string.isEmpty())
        {
            return true;
        }
        return false;
    }
}
