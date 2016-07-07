/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.view;

import com.organizer.projektorganizer.model.Przedmiot;
import com.organizer.projektorganizer.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Grzegorz
 */
public class PowiadomienieDialogController {
    
    @FXML
    private Label message;
    
    private Stage powiadomienieStage;
    private Przedmiot przedmiot;
    private Task task;
    
    @FXML
    void initialize(){
       
      
    }
    
    public void setPowiadomienieStage(Stage stage){
        this.powiadomienieStage = stage;
    }
    
    @FXML
    private void handleOkButton(){
       powiadomienieStage.close();
    }
    
    public void setPrzedmiot(Przedmiot przedmiot){
       this.przedmiot = przedmiot;
        
         message.setText("Dnia " + przedmiot.getTerminPrzydatnosci() + " skonczyl sie termin przydatnosci"
          + " produktu " + przedmiot.getNazwaPrzedmiotu());
    }
    
    public void setTask(Task task){
        this.task = task;
        message.setText(task.getMessage());
    }   
}
