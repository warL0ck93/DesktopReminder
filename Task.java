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
public abstract class Task {
    //private final ObjectProperty<LocalDate> dateRun;
    protected Przedmiot przedmiot;
    protected String message;
    
    public Task(Przedmiot przedmiot){
        this.przedmiot = przedmiot;
    }
    
    public Przedmiot getPrzedmiot(){
        return przedmiot;
    }
    
    public boolean compare(){
        return false;
    }
    
    public String getMessage(){
        return message;
    }
}
