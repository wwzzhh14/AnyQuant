package com.anyquant.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HP on 2016/3/28.
 */
public class TimeUtil {
    public static long getTimeLong(String date){
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d=sdf.parse(date);
            c.setTime(d);
            long result=c.getTimeInMillis();
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static String getNowDate(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String result=sdf.format(date);
        return result;
    }
    public static String getPassedDate(double days,String nowDate){
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = sdf.parse(nowDate);
            c.setTime(date);
            long offset=(long)days*24*60*60*1000;
            long time=c.getTimeInMillis()-offset;
            c.setTimeInMillis(time);
            Date resultDate=c.getTime();
            String result=sdf.format(resultDate);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String getAddDate(double days,String nowDate){
        Calendar c=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = sdf.parse(nowDate);
            c.setTime(date);
            long offset=(long)days*24*60*60*1000;
            long time=c.getTimeInMillis()+offset;
            c.setTimeInMillis(time);
            Date resultDate=c.getTime();
            String result=sdf.format(resultDate);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
