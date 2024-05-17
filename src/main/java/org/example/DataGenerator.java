package org.example;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
   public static String generateDate(int addDays , String pattern){
       return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
   }
}