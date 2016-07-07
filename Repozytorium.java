/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.model;

import com.google.inject.Singleton;
import com.organizer.projektorganizer.abstract1.RepozitoryInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Grzegorz
 */
@Singleton
public class Repozytorium implements RepozitoryInterface{
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:bazaPrzedmiotow.db";
    private Connection connection; //poloczenie do bazy
    private Statement statement; //konkretne repozytorium 
    
    public Repozytorium() {
        try {
            Class.forName(Repozytorium.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
        createTables();
    }
     
    private boolean createTables(){
        String createPrzedmiot = "CREATE TABLE IF NOT EXISTS Przedmiot (PrzedmiotID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nazwaPrzedmiotu varchar(255), "
                + "kategoriaPrzedmiotu varchar(255), "
                + "cenaPrzedmiotu int, "
                + "miejsceZakupu varchar(255), "
                + "dataZakupu varchar(255), "
                + "terminPrzydatnosci varchar(255), "
                + "waznoscGwarancji varchar(255), "
                + "ocenaPrzedmiotu int)";
        try {
            statement.execute(createPrzedmiot);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean insertPrzedmiot(Przedmiot przedmiot) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into Przedmiot values (NULL, ?, ?, ?, ?, ?, ?, ?,?);");
            prepStmt.setString(1, przedmiot.getNazwaPrzedmiotu());
            prepStmt.setString(2, przedmiot.getKategoriaPrzedmiotu());
            prepStmt.setString(3, przedmiot.getCenaPrzedmiotu().toString());
            prepStmt.setString(4, przedmiot.getMiejsceZakupu());
            prepStmt.setString(5, przedmiot.getDataZakupu().toString());
            prepStmt.setString(6, przedmiot.getTerminPrzydatnosci().toString());
            prepStmt.setString(7, przedmiot.getWaznoscGwarancji().toString());
            prepStmt.setString(8, Integer.toString(przedmiot.getOcenaPrzedmiotu()));
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu przedmiotu");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public List<Przedmiot> selectPrzedmioty() {
        List<Przedmiot> przedmioty = new LinkedList<Przedmiot>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM Przedmiot");
            int cenaPrzedmiotu,IdPrzedmiotu,ocenaPrzedmiotu;
            String nazwaPrzedmiotu, kategoriaPrzedmiotu, miejsceZakupu,dataZakupu,terminPrzydatnosci,waznoscGwarancji;
           
            while(result.next()) {
                IdPrzedmiotu = result.getInt("PrzedmiotID");
                cenaPrzedmiotu = result.getInt("cenaPrzedmiotu");
                nazwaPrzedmiotu = result.getString("nazwaPrzedmiotu");
                miejsceZakupu = result.getString("miejsceZakupu");
                kategoriaPrzedmiotu = result.getString("kategoriaPrzedmiotu");
                dataZakupu = result.getString("dataZakupu");
                terminPrzydatnosci = result.getString("terminPrzydatnosci");
                waznoscGwarancji = result.getString("waznoscGwarancji");
                ocenaPrzedmiotu = result.getInt("ocenaPrzedmiotu");
                Przedmiot przedmiot = new Przedmiot();
                przedmiot.setIdPrzedmiotu(IdPrzedmiotu);
                przedmiot.setCenaPrzedmiotu(cenaPrzedmiotu);
                przedmiot.setNazwaPrzedmiotu(nazwaPrzedmiotu);
                przedmiot.setKategoriaPrzedmiotu(kategoriaPrzedmiotu);
                przedmiot.setMiejsceZakupu(miejsceZakupu);
                przedmiot.setDataZakupu(LocalDate.parse(dataZakupu));
                przedmiot.setDataPrzydatnosci(LocalDate.parse(terminPrzydatnosci));
                przedmiot.setWaznoscGwarancji(LocalDate.parse(waznoscGwarancji));
                przedmiot.setOcena(ocenaPrzedmiotu);
                przedmioty.add(przedmiot);
                
                //czytelnicy.add(new Czytelnik(id, imie, nazwisko, pesel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return przedmioty;
    }
    
    public boolean updatePrzedmiot(Przedmiot przedmiot){
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "update Przedmiot SET nazwaPrzedmiotu = ?, "
                            + "kategoriaPrzedmiotu = ?, "
                            + "cenaPrzedmiotu = ?, "
                            + "miejsceZakupu = ?, "
                            + "dataZakupu = ?, "
                            + "terminPrzydatnosci = ?, "
                            + "waznoscGwarancji = ?, "
                            + "ocenaPrzedmiotu = ?"
                            + "where PrzedmiotID = ?");
            //UPDATE COMPANY SET ADDRESS = 'Texas' WHERE ID = 6;
            prepStmt.setString(1, przedmiot.getNazwaPrzedmiotu());
            prepStmt.setString(2, przedmiot.getKategoriaPrzedmiotu());
            prepStmt.setString(3, przedmiot.getCenaPrzedmiotu().toString());
            prepStmt.setString(4, przedmiot.getMiejsceZakupu());
            prepStmt.setString(5, przedmiot.getDataZakupu().toString());
            prepStmt.setString(6, przedmiot.getTerminPrzydatnosci().toString());
            prepStmt.setString(7, przedmiot.getWaznoscGwarancji().toString());
            prepStmt.setString(8, Integer.toString(przedmiot.getOcenaPrzedmiotu()));
            prepStmt.setInt(9, przedmiot.getIdPrzedmiot());
            prepStmt.executeUpdate();
                    
            System.out.println("NASTAPIL UPDATE ID = " + przedmiot.getIdPrzedmiot());
        } catch (SQLException e) {
            System.err.println("Blad przy edytowaniu przedmiotu");
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deletePrzedmiot(Przedmiot przedmiot){
        try{
            PreparedStatement prepStmt = connection.prepareStatement(
                    "DELETE from Przedmiot where PrzedmiotID = ?");
            prepStmt.setInt(1, przedmiot.getIdPrzedmiot());
            prepStmt.executeUpdate();
        }catch(SQLException e){
            System.err.println("Blad przy edytowaniu przedmiotu");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
