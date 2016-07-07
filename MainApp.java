package com.organizer.projektorganizer;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.organizer.projektorganizer.view.MainWindowController;
import com.organizer.projektorganizer.view.PowiadomienieDialogController;
import com.organizer.projektorganizer.view.PrzedmiotEditDialogController;
import com.organizer.projektorganizer.model.FinalRepozitory;
import com.organizer.projektorganizer.model.Przedmiot;
import com.organizer.projektorganizer.model.RepozitoryInjector;
import com.organizer.projektorganizer.model.Task;
import com.organizer.projektorganizer.model.TaskService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private TaskService taskService;
    private Thread threadTaskService;
    private static final String iconImageLoc =
            "http://icons.iconarchive.com/icons/scafer31000/bubble-circle-3/16/GameCenter-icon.png";

    /**
     *Dane jako obserwowalna lista przedmiotów
     */
    private ObservableList<Przedmiot> przedmiotData;
    //private Repozytorium repozytorium;
    private FinalRepozitory repozytorium;
    /**
     * Konstruktor
     */
    public MainApp() {
        //repozytorium = new Repozytorium();
        //Injector injector = Guice.createInjector(new RepozitoryInjector());
        Injector injector = Guice.createInjector(RepozitoryInjector.getInstance()); //Singleton
        repozytorium = injector.getInstance(FinalRepozitory.class);
        setDataFromDatabase();

    }
    public void setDataFromDatabase(){
        przedmiotData =  FXCollections.observableArrayList();
        List<Przedmiot> tmpListaPrzedmiot = repozytorium.method().selectPrzedmioty();
        for(Przedmiot przedmiot :tmpListaPrzedmiot){
            przedmiotData.add(przedmiot);
        }
    }
    /**
     * Returns the data as an observable list of Przedmiots.
     * @return
     */
    public ObservableList<Przedmiot> getPrzedmiotData() {
        return przedmiotData;
    }

    public FinalRepozitory getRepozytorium(){
        return repozytorium;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Okno Główne Oceniarki");
        Platform.setImplicitExit(false);
        javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
        initRootLayout();
        startTaskService(); //chyba tutaj?
       // sets up the tray icon (using awt code run on the swing thread).
        showMainWindow();
    }

    /**
     * Initializes the root layout.
     */

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showMainWindow() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/MainWindow.fxml"));
            AnchorPane mainWindow = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(mainWindow);
            MainWindowController controller = loader.getController();
            controller.setMainApp(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean showPowiadomienieDialog(Task task){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/PowiadomienieDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage powiadomienieStage = new Stage();
            powiadomienieStage.setTitle("Przypomnienie");
            powiadomienieStage.initModality(Modality.WINDOW_MODAL);
            //powiadomienieStage.initOwner(pri);
            Scene scene = new Scene(page);
            powiadomienieStage.setScene(scene);

            //Set controller
            PowiadomienieDialogController controller = loader.getController();
            controller.setPowiadomienieStage(powiadomienieStage);
            controller.setTask(task);
            System.out.println("PUSTY : " + task.getMessage());
            //controller.setPrzedmiot(new Przedmiot("Przedmioy", "Testow"));

            powiadomienieStage.showAndWait();


            return true;
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showPrzedmiotEditDialog(Przedmiot przedmiot) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/PrzedmiotEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edytuj Przedmiot");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        PrzedmiotEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPrzedmiot(przedmiot);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void addAppToTray(){
       try{
            java.awt.Toolkit.getDefaultToolkit(); //pobieram zestaw narzedzi
            if (!java.awt.SystemTray.isSupported()) {
                System.out.println("No system tray support, application exiting.");
                Platform.exit();
            }

            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray(); //dosaje obiekt tray
            URL imageLoc = new URL(iconImageLoc); //zrodlo ikony
            java.awt.Image image = ImageIO.read(imageLoc); //obraz ikony
            java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(image); //ikana tray

            // if the user double-clicks on the tray icon, show the main app stage.

            trayIcon.addActionListener(event -> Platform.runLater(this::goToForeground));

            // the convention for tray icons seems to be to set the default icon for opening
            // the application stage in a bold font.
            java.awt.Font defaultFont = java.awt.Font.decode(null);
            java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
            ///openItem.setFont(boldFont);


            java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
            java.awt.MenuItem showItem = new java.awt.MenuItem("Show");

            exitItem.addActionListener(event -> {
                //threadTaskService.destroy(); nie dziala
                threadTaskService.stop(); //dziala chyba dobrze?
                Platform.exit();
                tray.remove(trayIcon);
            });

            showItem.addActionListener(event->Platform.runLater(this::goToForeground));

            final java.awt.PopupMenu popup = new java.awt.PopupMenu();
            popup.add(showItem);
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);

            tray.add(trayIcon);
       }catch (java.awt.AWTException | IOException e) {
            System.out.println("Unable to init system tray");
            e.printStackTrace();
       }
    }

    public void goToTray(){
        primaryStage.hide();
    }

    private void goToForeground(){
        if(primaryStage != null){
            primaryStage.show();
            primaryStage.toFront();
        }
    }

    void startTaskService(){

        /*********************TEST *****************/
        //Przedmiot przedmiot = new TestowyP

        /*******************************************/
        taskService = new TaskService();
        taskService.setMainApp(this);
        threadTaskService = new Thread(taskService);
        threadTaskService.start();
    }

}
