/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.model;

import com.organizer.projektorganizer.MainApp;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author Grzegorz
 */
public class TaskService implements Runnable {
    private List<Task> taskList;
    private MainApp mainApp; ///moze zadzialac ale nie koniecznie
    private boolean flaga = false;
    private TaskFactory taskFactory;
    public TaskService() {
        taskList = new LinkedList<Task>();
        taskFactory = new TaskFactory();
    }
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        setList();
    }
    
    private void setList(){
        for(Przedmiot przedmiot : mainApp.getPrzedmiotData()){
            taskList.add(taskFactory.getTask(przedmiot, "gwarancja"));
            taskList.add(taskFactory.getTask(przedmiot, "data_waznosci"));
        }
    }
     
    public void run(){
        while(true){
            
            if(flaga == false){
                for(Task task: taskList){
                    if(task.compare()){
                        flaga = true;
                        Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                         //javaFX operations should go here
                         mainApp.showPowiadomienieDialog(task);
                         taskList.remove(task);
                         flaga = false;
                        }
                        }); 
                       
                    }
                }
            }            
             
            try{
                Thread.sleep(60000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
                 
       }
    }
    
}
