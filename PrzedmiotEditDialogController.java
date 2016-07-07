/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.view;

import com.organizer.projektorganizer.address.DateUtiL;
import com.organizer.projektorganizer.model.Przedmiot;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Grzegorz
 */
public class PrzedmiotEditDialogController {
    @FXML
    private TextField nazwaTextField;
    @FXML
    private TextField kategoriaTextField;
    @FXML
    private TextField cenaTextField;
    @FXML
    private TextField miejsceZakupuTextField;
    @FXML
    private TextField dataZakupuTextField;
    @FXML
    private TextField terminPrzydTextField;
    @FXML
    private TextField waznoscGwarTextField;
    @FXML
    private RadioButton r1; 
    @FXML
    private RadioButton r2;
    @FXML
    private RadioButton r3; 
    @FXML
    private RadioButton r4;
    @FXML
    private RadioButton r5;
    @FXML
    private ToggleGroup toggleGroup;
    
    private Stage dialogStage;
    private Przedmiot przedmiot;
    private boolean okClicked = false;
    
    @FXML
    void initialize(){
        //inicializeRadioBox();
    }
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    
    public void setPrzedmiot(Przedmiot przedmiot){
        //inicializeRadioBox();
        this.przedmiot = przedmiot;
        nazwaTextField.setText(przedmiot.getNazwaPrzedmiotu());
        kategoriaTextField.setText(przedmiot.getKategoriaPrzedmiotu());
        cenaTextField.setText(Double.toString(przedmiot.getCenaPrzedmiotu()));
        miejsceZakupuTextField.setText(przedmiot.getMiejsceZakupu());
        
        if(przedmiot.getDataZakupu() == null){
            dataZakupuTextField.setText( "" );
        }else{
            dataZakupuTextField.setText( przedmiot.getDataZakupu().toString() );
        }
            
        if(przedmiot.getDataZakupu() == null){
            terminPrzydTextField.setText( "" );
        }else{
            terminPrzydTextField.setText(przedmiot.getTerminPrzydatnosci().toString());
        }
        if(przedmiot.getDataZakupu() == null){
            waznoscGwarTextField.setText( "" );
        }else{
           waznoscGwarTextField.setText(przedmiot.getWaznoscGwarancji().toString());
        }
        
          
      
        
    }
    
    public boolean isOkClicked(){
        return okClicked;
    }
    
    @FXML
    private void handleOkClicked(){
        
        if(isInputValid()){
            przedmiot.setNazwaPrzedmiotu(nazwaTextField.getText());
            przedmiot.setKategoriaPrzedmiotu(kategoriaTextField.getText());
            przedmiot.setCenaPrzedmiotu(Double.parseDouble(cenaTextField.getText()));
            przedmiot.setMiejsceZakupu(miejsceZakupuTextField.getText());
            //przedmiot.setDataZakupu(DateUtiL.parse(dataZakupuTextField.getText()));
            //przedmiot.setDataPrzydatnosci(DateUtiL.parse(terminPrzydTextField.getText()));
            //przedmiot.setWaznoscGwarancji(DateUtiL.parse(waznoscGwarTextField.getText()));
            przedmiot.setDataZakupu(LocalDate.parse(dataZakupuTextField.getText()));
            przedmiot.setDataPrzydatnosci(LocalDate.parse(terminPrzydTextField.getText()));
            przedmiot.setWaznoscGwarancji(LocalDate.parse(waznoscGwarTextField.getText()));
           
            if(r1.isSelected()){
                System.out.println("1");
                przedmiot.setOcena(1);
            }else if(r2.isSelected()){
                System.out.println("2");
                przedmiot.setOcena(2);
            }else if(r3.isSelected()){
                System.out.println("3");
                przedmiot.setOcena(3);
            }else if(r4.isSelected()){
                System.out.println("4");
                przedmiot.setOcena(4);
            }else if(r5.isSelected()){
                System.out.println("5");
                przedmiot.setOcena(5);
            }
            
            okClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
    
    private boolean isInputValid(){
        String errorMessage = "";

        if (nazwaTextField.getText() == null || nazwaTextField.getText().length() == 0) {
            errorMessage += "Niepoprawna nazwa przedmiotu!\n";
        }
        if (kategoriaTextField.getText() == null || kategoriaTextField.getText().length() == 0) {
            errorMessage += "Niepoprawny rodzaj kategorii!\n";
        }
        if (cenaTextField.getText() == null || cenaTextField.getText().length() == 0) {
            errorMessage += "Niepoprawna cena!\n";
        }else{
            try{
                Double.parseDouble(cenaTextField.getText());
            }catch(NumberFormatException e){
                errorMessage += "Cena musi byc liczba! \n";
            }
        }
        
        if (dataZakupuTextField.getText() == null || dataZakupuTextField.getText().length() == 0) {
            errorMessage += "Musisz podać date zakupu!\n";
        } else {
            if (!DateUtiL.validDate(dataZakupuTextField.getText())) {
                errorMessage += "Niepoprawny format daty. Poprawny format to: yyyy-mm-dd!\n";
            }
        }
        
        if (terminPrzydTextField.getText() == null || terminPrzydTextField.getText().length() == 0) {
            errorMessage += "Pole terminu przydatności nie moze byc puste!\n";
        } else {
            if (!DateUtiL.validDate(terminPrzydTextField.getText())) {
                //System.out.println("POdana data to : " + terminPrzydTextField.getText());
                errorMessage += "Niepoprawny format daty. Poprawny format: yyyy-mm-dd!\n";
            }
        }
        
        if (waznoscGwarTextField.getText() == null || waznoscGwarTextField.getText().length() == 0) {
            errorMessage += "Pole ważności gwarancji nie moze byc puste!\n";
        } else {
            if (!DateUtiL.validDate(waznoscGwarTextField.getText())) {
                errorMessage += "Niepoprawny format daty. Poprawny format: yyyy-mm-dd!\n";
            }
        }
       
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Niepoprawne pola");
            alert.setHeaderText("Prosze popraw wymienione pola");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    private void inicializeRadioBox(){
        r1 = new RadioButton("1");
        r2 = new RadioButton("2");
        r3 = new RadioButton("3");
        r4 = new RadioButton("4");
        r5 = new RadioButton("5");
        toggleGroup = new ToggleGroup();
        r1.setToggleGroup(toggleGroup);
        r2.setToggleGroup(toggleGroup);
        r3.setToggleGroup(toggleGroup);
        r4.setToggleGroup(toggleGroup);
        r5.setToggleGroup(toggleGroup);
    }
}
