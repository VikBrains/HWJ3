package model;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parsers {
    public static LocalDate parseBirthDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        catch (DateTimeParseException e) {
            throw new DateTimeParseException("Неверный формат даты рождения", dateStr, e.getErrorIndex());
        }
    }

    public static long parsePhoneNumber(String phoneNumberStr) {
        int digits = 0;
        for (int i = 0; i < phoneNumberStr.length(); i++) {
            if (Character.isDigit(phoneNumberStr.charAt(i)))
                digits++;
        }
        if (digits == 11) {
            return Long.parseLong(phoneNumberStr);
        }
        else {
            throw new NumberFormatException("Неверный формат номера телефона");
        }
    }

    public static char parseGender(String genderStr) {
        char gender = genderStr.charAt(0);
        if (gender != 'm' && gender != 'f') {
            throw new IllegalArgumentException("Неверное значение пола");
        }
        return gender;
    }

}
