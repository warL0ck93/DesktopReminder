/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizer.projektorganizer.model;

import com.google.inject.AbstractModule;
import com.organizer.projektorganizer.abstract1.RepozitoryInterface;

/**
 *
 * @author Grzegorz
 */
public class RepozitoryInjector extends AbstractModule{
   
    private RepozitoryInjector(){}
    private static RepozitoryInjector repozitoryInjector = null;
    
    public static RepozitoryInjector getInstance(){
        if(repozitoryInjector == null){
            repozitoryInjector = new RepozitoryInjector();
        }
        
        return repozitoryInjector;
    }
    
    @Override
    protected void configure(){
        bind(RepozitoryInterface.class).to(Repozytorium.class);
    }
}
