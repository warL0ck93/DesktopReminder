package com.organizer.projektorganizer.model;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Przedmiot {

    private final StringProperty nazwaPrzedmiotu;
    private final StringProperty kategoriaPrzedmiotu;
    private final DoubleProperty cenaPrzedmiotu;
    private final StringProperty miejsceZakupu;
    private final ObjectProperty<LocalDate> dataZakupu;
    private final ObjectProperty<LocalDate> terminPrzydatnosci;
    private final ObjectProperty<LocalDate> waznoscGwarancji;
    private final IntegerProperty ocenaPrzedmiotu;
    private int IdPrzedmiotu;
    /**
     * Default constructor.
     */
    public Przedmiot() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Przedmiot(String nazwa, String kategoria) {
        this.nazwaPrzedmiotu = new SimpleStringProperty(nazwa);
        this.kategoriaPrzedmiotu = new SimpleStringProperty(kategoria);
        this.IdPrzedmiotu = 0;
        // Some initial dummy data, just for convenient testing.
        this.cenaPrzedmiotu = new SimpleDoubleProperty();
        this.miejsceZakupu = new SimpleStringProperty("");
        this.dataZakupu = new SimpleObjectProperty<LocalDate>(null);
        this.terminPrzydatnosci = new SimpleObjectProperty<LocalDate>(null);
        this.waznoscGwarancji = new SimpleObjectProperty<LocalDate>(null);  
        this.ocenaPrzedmiotu = new SimpleIntegerProperty(0);
    }

	//gettery Property
	
        public StringProperty getNazwaPrzedmiotuProperty() {
		return nazwaPrzedmiotu;
	}

	public StringProperty getKategoriaPrzedmiotuProperty() {
		return kategoriaPrzedmiotu;
	}

	public DoubleProperty getCenaPrzedmiotuProperty() {
		return cenaPrzedmiotu;
	}

	public StringProperty getMiejsceZakupuProperty() {
		return miejsceZakupu;
	}

	public ObjectProperty<LocalDate> getDataZakupuProperty() {
		return dataZakupu;
	}

	public ObjectProperty<LocalDate> getTerminPrzydatnosciProperty() {
		return terminPrzydatnosci;
	}

	public ObjectProperty<LocalDate> getWaznoscGwarancjiProperty() {
		return waznoscGwarancji;
	}

	//gettery
	
	public String getNazwaPrzedmiotu() {
		return nazwaPrzedmiotu.get();
	}
    

	public String getKategoriaPrzedmiotu() {
		return kategoriaPrzedmiotu.get();
	}

	public Double getCenaPrzedmiotu() {
		return cenaPrzedmiotu.get();
	}

	public String getMiejsceZakupu() {
		return miejsceZakupu.get();
	}

	public LocalDate getDataZakupu() {
		return dataZakupu.get();
	}

	public LocalDate getTerminPrzydatnosci() {
		return terminPrzydatnosci.get();
	}

	public LocalDate getWaznoscGwarancji() {
		return waznoscGwarancji.get();
	}
	
	//settery
	public void setNazwaPrzedmiotu(String nazwa)
	{
		this.nazwaPrzedmiotu.set(nazwa);
	}
	
	public void setKategoriaPrzedmiotu(String kategoria)
	{
		this.kategoriaPrzedmiotu.set(kategoria);
	}
	
	public void setCenaPrzedmiotu(double cena)
	{
		this.cenaPrzedmiotu.set(cena);
	}

	public void setMiejsceZakupu(String miejsce)
	{
		this.miejsceZakupu.set(miejsce);
	}
	
	public void setDataZakupu(LocalDate dataKupna)
	{
		this.dataZakupu.set(dataKupna);
	}
	
	public void setDataPrzydatnosci(LocalDate terminPrzyd)
	{
		this.terminPrzydatnosci.set(terminPrzyd);
	}
	
	public void setWaznoscGwarancji(LocalDate waznoscGwar)
	{
		this.waznoscGwarancji.set(waznoscGwar);
	}
        
        public void setIdPrzedmiotu(int IdPrzedmiotu){
            this.IdPrzedmiotu = IdPrzedmiotu;
        }
        
        public int getIdPrzedmiot(){
            return IdPrzedmiotu;
        }
        
        public void setOcena(int ocena){
            this.ocenaPrzedmiotu.set(ocena);
        }
        
        public int getOcenaPrzedmiotu(){
            return ocenaPrzedmiotu.get();
        }
        
        public IntegerProperty getOcenaPrzedmiotuProperty(){
            return ocenaPrzedmiotu;
        } 
}