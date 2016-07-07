/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.model;

import java.time.LocalDate;

/**
 *
 * @author Grzegorz
 */
public class TaskWithDataWaznosci extends Task{
    public TaskWithDataWaznosci(Przedmiot przedmiot){
        super(przedmiot);
        setMessage();
    }
    
    private void setMessage(){
        message = "Data waznosci skonczyla sie " + przedmiot.getWaznoscGwarancji().toString()
                + " dla przedmiotu " + przedmiot.getNazwaPrzedmiotu();
    }
    
    public String getMeessage(){
        return message;
    }
    
     public boolean compare(){
        if(przedmiot.getTerminPrzydatnosci().equals(LocalDate.now())){
            //DateUtiL.
            return true;
        }
        return false;
    }
}
