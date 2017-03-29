package com.company.utility;

import java.time.LocalDate;
import java.util.Date;

public class Converter {

    public static LocalDate toLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    public static Date toDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

}
