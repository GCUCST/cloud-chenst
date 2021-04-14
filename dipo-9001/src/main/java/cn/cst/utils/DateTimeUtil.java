package cn.cst.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtil {
    public String getNowDateTime(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
         String localTime = df.format(time);
//         LocalDateTime ldt = LocalDateTime.parse("2018-01-12 17:07:05",df);
         return  localTime;
    }


}
