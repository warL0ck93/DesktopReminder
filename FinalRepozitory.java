/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.model;

import com.google.inject.Inject;
import com.organizer.projektorganizer.abstract1.RepozitoryInterface;

/**
 *
 * @author Grzegorz
 */
public class FinalRepozitory {
    
    private RepozitoryInterface repoInterface;
    @Inject //wstrzykiwanie zaleznosci
    public FinalRepozitory(RepozitoryInterface repoInterface)
    {
        this.repoInterface = repoInterface;
    }
   
    public RepozitoryInterface method(){
        return repoInterface;
    }
}
