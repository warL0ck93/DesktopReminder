/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.organizer.projektorganizer.model.Przedmiot;
import com.organizer.projektorganizer.model.Repozytorium;
import com.organizer.projektorganizer.model.TaskWithDataWaznosci;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Grzegorz
 */
public class fourthTest {
    
    public fourthTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    
    @Test
    public void compareTest()
    {
        Przedmiot przedmiot = new Przedmiot();
        przedmiot.setDataPrzydatnosci(LocalDate.now());
        przedmiot.setDataZakupu(LocalDate.now());
        przedmiot.setNazwaPrzedmiotu("zadanie na dzis");
        przedmiot.setWaznoscGwarancji(LocalDate.now());
        TaskWithDataWaznosci zadanieNaDzis = new TaskWithDataWaznosci(przedmiot);
        assertTrue(zadanieNaDzis.compare());
        
    }
}
