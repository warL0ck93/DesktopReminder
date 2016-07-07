/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.abstract1;

import com.organizer.projektorganizer.model.Przedmiot;
import java.util.List;

/**
 *
 * @author Grzegorz
 * http://code.makery.ch/library/javafx-8-tutorial/
 */
public interface RepozitoryInterface {
     public boolean insertPrzedmiot(Przedmiot przedmiot);
     //private boolean createTables();
     public List<Przedmiot> selectPrzedmioty();
     public boolean updatePrzedmiot(Przedmiot przedmiot);
     public boolean deletePrzedmiot(Przedmiot przedmiot);
}
