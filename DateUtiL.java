/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.address;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 *
 * @author Grzegorz
 */
public class DateUtiL {
   private static final String DATE_PATTERN = "YYYY-mm-dd";
   private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
   
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    public static boolean validDate(String dateString){
      
        try{
           return DATE_FORMATTER.parse(dateString) != null;
       }catch(Exception e){
           return false;
       }
    }
    /*
    public static boolean compare(LocalDate date1, LocalDate date2){
        try 
    }
    */
}