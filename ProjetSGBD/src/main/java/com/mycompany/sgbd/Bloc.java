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
public class Bloc {
    private int taille;
    private List<Ligne> lignes;

    public Bloc(int taille, List<Ligne> l) {
        this.taille = taille;
        this.lignes = l;
    }

    public Bloc() {
        this.taille = 8192;
        this.lignes = new ArrayList<Ligne>();
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(List<Ligne> l) {
        this.lignes = l;
    }
    public int getNbLigneParBloc(){
        //int nb = (int)taille/this.lignes.get(0).getTaille();
        return lignes.size();
    }

    public int getFreespace() {
        int used_space = 0;
        for(int i =0 ;i< lignes.size();i++){
            used_space += lignes.get(i).getTaille();
        }
        return this.taille-used_space;
    }

    @Override
    public String toString() {
       
        String res = "bloc {\n" ;
        for (int i =0 ; i<lignes.size();i++){
           res += "\t\t"+ i + " : " + lignes.get(i).toString()+ "\n";
        } 
        res +="\t}";
        
        return res+"\n";
    }
    
    
}
