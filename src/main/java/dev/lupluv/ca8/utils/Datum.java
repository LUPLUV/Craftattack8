package dev.lupluv.ca8.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Datum {

    public static Date date = Calendar.getInstance().getTime();
    public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy" + " " + "HH:mm:ss");
    public static String datum = format.format(date);

}
