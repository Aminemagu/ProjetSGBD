/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sgbd;

import java.util.List;

/**
 *
 * @author tb985403
 */
public class Ligne {
    private List<String> attribut ;
    private int taille;

    public Ligne(List<String> attribut) {
        this.attribut = attribut;
        this.taille = 2000;
    }

    public List<String> getAttribut() {
        return attribut;
    }

    public void setAttribut(List<String> attribut) {
        this.attribut = attribut;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        String res = "\tLigne{\n" ;
        for (int i =0 ; i<attribut.size();i++){
           res +="\t\t\t" +i + " : " + attribut.get(i)+"\n" ;
        } 
        res +="\t\t}";
        
        return res;
    }
    
}
