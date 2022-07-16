package com.pathan;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.validator.routines.DateValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageDate {

    boolean isDate(String date) {
        String yyyyMMdd_ = "yyyy-MM-dd";
        String yyyyMMdd = "yyyy/MM/dd";
        String ddMMyyyy_ = "dd-MM-yyyy";
        String ddMMyyyy = "dd/MM/yyyy";
        DateValidator validator = DateValidator.getInstance();
        if (validator.isValid(date)) {
            return true;
        } else if (validator.isValid(date, yyyyMMdd_)) {
            return true;
        } else if (validator.isValid(date, yyyyMMdd)) {
            return true;
        } else if (validator.isValid(date, ddMMyyyy)) {
            return true;
        } else if (validator.isValid(date, ddMMyyyy_)) {
            return true;
        } else return false;
    }
    String stringToDate(String dateStr) throws ParseException {
        Date date = DateUtils.parseDateStrictly(dateStr,
                new String[]{ "yyyy-MM-dd", "yyyy/MM/dd", "dd-MM-yyyy","dd/MM/yyyy", });
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

}
