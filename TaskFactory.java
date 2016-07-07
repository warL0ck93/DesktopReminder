/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.model;

/**
 *
 * @author Grzegorz
 */
public class TaskFactory {
    
    public Task getTask(Przedmiot przedmiot, String type){
        if(type == "gwarancja"){
            return new TaskWithGwarancja(przedmiot);
        }
        else{
            return new TaskWithDataWaznosci(przedmiot);
        }
    }
}
