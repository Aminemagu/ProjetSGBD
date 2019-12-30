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
    private List<String> attributs ;
    private int taille;

    public Ligne(List<String> attribut) {
        this.attributs = attribut;
        this.taille = 2000;
    }

    public List<String> getAttributs() {
        return attributs;
    }

    public void setAttributs(List<String> attribut) {
        this.attributs = attribut;
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
        for (int i =0 ; i<attributs.size();i++){
           res +="\t\t\t" +i + " : " + attributs.get(i)+"\n" ;
        } 
        res +="\t\t}";
        
        return res;
    }
    
}
