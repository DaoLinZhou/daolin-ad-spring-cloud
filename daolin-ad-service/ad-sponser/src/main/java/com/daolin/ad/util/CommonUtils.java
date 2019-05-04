package com.daolin.ad.util;


import com.daolin.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Daolin
 * @date 2019/04/26
 */
public class CommonUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    public static String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseStringDate(String dateString) throws AdException{
        try{
            return DateUtils.parseDate(dateString, parsePatterns);
        } catch (ParseException e) {
            throw new AdException(e.getMessage());
        }
    }


}
