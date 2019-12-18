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
public class Bloc {
    private int taille;
    private List<Ligne> l;

    public Bloc(int taille, List<Ligne> l) {
        this.taille = taille;
        this.l = l;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public List<Ligne> getL() {
        return l;
    }

    public void setL(List<Ligne> l) {
        this.l = l;
    }
    
    
}
