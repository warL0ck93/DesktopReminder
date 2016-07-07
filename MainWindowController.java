package com.organizer.projektorganizer.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.organizer.projektorganizer.MainApp;
import com.organizer.projektorganizer.model.FinalRepozitory;
import com.organizer.projektorganizer.model.Przedmiot;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainWindowController {
    @FXML
    private TableView<Przedmiot> przedmiotTable;
    @FXML
    private TableColumn<Przedmiot, String> nazwaPrzedmiotuColumn;
    @FXML
    private TableColumn<Przedmiot, String> kategoriaPrzedmiotuColumn;

    @FXML
    private Label cenaLabel;
    @FXML
    private Label miejsceZakupuLabel;
    @FXML
    private Label dataZakupuLabel;
    @FXML
    private Label terminPrzydLabel;
    @FXML
    private Label waznoscGwarLabel;
    @FXML
    private Label ocenaLabel;
   

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainWindowController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nazwaPrzedmiotuColumn.setCellValueFactory(cellData -> cellData.getValue().getNazwaPrzedmiotuProperty());
        kategoriaPrzedmiotuColumn.setCellValueFactory(cellData -> cellData.getValue().getKategoriaPrzedmiotuProperty());
        
        pokazSzczegolyPrzedmiotu(null);
        przedmiotTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue)->pokazSzczegolyPrzedmiotu(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        przedmiotTable.setItems(mainApp.getPrzedmiotData());
    }
    
    private void pokazSzczegolyPrzedmiotu(Przedmiot przedmiot){
        if(przedmiot != null){
            cenaLabel.setText(Double.toString(przedmiot.getCenaPrzedmiotu()));
            miejsceZakupuLabel.setText(przedmiot.getMiejsceZakupu());
            dataZakupuLabel.setText(przedmiot.getDataZakupu().toString());
            terminPrzydLabel.setText(przedmiot.getTerminPrzydatnosci().toString());
            waznoscGwarLabel.setText(przedmiot.getWaznoscGwarancji().toString());
            ocenaLabel.setText(Integer.toString(przedmiot.getOcenaPrzedmiotu()));
        }
        else{
            cenaLabel.setText("");
            miejsceZakupuLabel.setText("");
            dataZakupuLabel.setText("");
            terminPrzydLabel.setText("");
            waznoscGwarLabel.setText("");
        }
    }
    @FXML
    void handleDeletePrzedmiot(){
        int selectedIndex = przedmiotTable.getSelectionModel().getSelectedIndex();
        Przedmiot selectedPrzedmiot = przedmiotTable.getSelectionModel().getSelectedItem();
        FinalRepozitory repo = mainApp.getRepozytorium();
        if(selectedIndex >= 0){
            repo.method().deletePrzedmiot(selectedPrzedmiot);
            przedmiotTable.getItems().remove(selectedIndex);
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("Nie wybrano przedmiotu");
            alert.setContentText("Wybierz przedmiot do usuniecia");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleNewPrzedmiot() {
        Przedmiot tempPrzedmiot = new Przedmiot();
        FinalRepozitory repo = mainApp.getRepozytorium();
        boolean okClicked = mainApp.showPrzedmiotEditDialog(tempPrzedmiot);
        if (okClicked) {
            mainApp.getPrzedmiotData().add(tempPrzedmiot);
            repo.method().insertPrzedmiot(tempPrzedmiot);
            mainApp.setDataFromDatabase();
            pokazSzczegolyPrzedmiotu(tempPrzedmiot);
            //testListy(); //metoda do przetesotwania listy z bazy
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPrzedmiot() {
        przedmiotTable.setItems(mainApp.getPrzedmiotData());
        Przedmiot selectedPrzedmiot = przedmiotTable.getSelectionModel().getSelectedItem();
        FinalRepozitory repo = mainApp.getRepozytorium();
        if (selectedPrzedmiot != null) {
            boolean okClicked = mainApp.showPrzedmiotEditDialog(selectedPrzedmiot);
            if (okClicked) {
                
                pokazSzczegolyPrzedmiotu(selectedPrzedmiot);
                repo.method().updatePrzedmiot(selectedPrzedmiot);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Brak przedmiotu");
            alert.setHeaderText("Brak wybranego przedmiotu");
            alert.setContentText("Prosze wybrac przedmiot do edycji.");
            alert.showAndWait();
        }
    }
    
    /*
    void testListy(){
        Repozytorium repo = mainApp.getRepozytorium();
        List<Przedmiot> listaPrzed = repo.selectPrzedmioty();
        for(Przedmiot przedmiot: listaPrzed){
            System.out.println(przedmiot.getNazwaPrzedmiotu());
        }
    }
    */
    
    @FXML
    private void handleTrayButton(){
        mainApp.goToTray();
    }
}
