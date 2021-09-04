package com.cpprac;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

    public static Date addSecondsToCurrentDate(int numberOfSeconds) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, numberOfSeconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSZ");
        String dateTime = dateFormat.format(new Date());
        System.out.println(dateTime);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(addSecondsToCurrentDate(7200));
    }
}
