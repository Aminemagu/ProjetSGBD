/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgbd.projetsgbd;

import java.util.List;

/**
 *
 * @author tb985403
 */
public class Buffer {
    private int capacite;
    private List<Bloc> b;
    
    public Buffer(int capacite, List<Bloc> b) {
        this.capacite = capacite;
        this.b = b;
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
