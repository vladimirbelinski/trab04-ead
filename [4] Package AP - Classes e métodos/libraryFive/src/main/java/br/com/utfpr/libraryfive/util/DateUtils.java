package br.com.utfpr.libraryfive.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateUtils {

    public LocalDateTime getActualDate(){
        return LocalDateTime.now();
    }

    public LocalDateTime calculateDateToReturn(Integer days){
        return LocalDateTime.now().plusDays(days);
    }
}
