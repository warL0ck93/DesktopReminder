/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.organizer.projektorganizer.model.Przedmiot;
import com.organizer.projektorganizer.model.Repozytorium;
import java.time.LocalDate;
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
public class thirdTest {
    
    public thirdTest() {
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
    public void TestRepoUpdate()
    {
        Repozytorium repo = new Repozytorium();
        
        Przedmiot przedmiot = new Przedmiot();
        przedmiot.setNazwaPrzedmiotu("trzecitestowyPrzedmiot");
        przedmiot.setCenaPrzedmiotu(50);
        przedmiot.setDataPrzydatnosci(LocalDate.MIN);
        przedmiot.setDataZakupu(LocalDate.MIN);
        przedmiot.setIdPrzedmiotu(3);
        przedmiot.setKategoriaPrzedmiotu("AGD");
        przedmiot.setOcena(3);
        przedmiot.setWaznoscGwarancji(LocalDate.MIN);
        assertTrue(repo.updatePrzedmiot(przedmiot));
    }
    
    
}
