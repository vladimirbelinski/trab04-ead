package br.com.utfpr.libraryfive.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class DateUtils {

    public LocalDateTime getActualDate(){
        return LocalDateTime.now();
    }

    public LocalDateTime calculateDateToReturn(Integer days){
        return LocalDateTime.now().plusDays(days);
    }

    public Date convertDate(final String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
