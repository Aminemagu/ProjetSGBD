/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sgbd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tb985403
 */
public class Buffer {
    private int capacite;
    private List<Bloc> b;
    

    public Buffer(){
        this.capacite=3;
        this.b = new ArrayList<Bloc>();
        for(int i = 0 ;i<this.capacite;i++){
            b.add(new Bloc());
        }
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Bloc> getB() {
        return b;
    }

    public void setB(List<Bloc> b) {
        this.b = b;
    }
    
    
}