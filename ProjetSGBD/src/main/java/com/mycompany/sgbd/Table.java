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
public class Table {
    private List<Bloc> blocs;
    private String nom;
    private Index indext;
    private List<String> attributs;
    

    public Table(String n,List<String> l) {
        this.attributs = l;
        this.nom = n;
        this.blocs = new ArrayList<Bloc>();
        this.blocs.add(new Bloc());
    }

    public List<Bloc> getBlocs() {
        return blocs;
    }

    public void setLignes(List<Bloc> blocs) {
        this.blocs = blocs;
    }

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getAttribut() {
        return attributs;
    }

   
    
    //méthode pour insérer les lignes dans les blocs selon leurs capacitées
    void insertLigne(List<String> l1) {
        Ligne l = new Ligne(l1);
        if (l1.size() != this.attributs.size()){
            System.out.println("La ligne n'a pas le bon nombre d'attribut");
        }else{
            if(blocs.get(blocs.size()-1).getFreespace() > l.getTaille()){ //si dernier bloc n'est pas plein
                    blocs.get(blocs.size()-1).getLignes().add(l);
            }else{
                blocs.add(new Bloc());
                blocs.get(blocs.size()-1).getLignes().add(l);
                
            }
        }
    }

    @Override
    public String toString() {
        String res = "Table {\n" ;
        for (int i =0 ; i<blocs.size();i++){
           res += "\t"+i + " : " + blocs.get(i).toString() +"\n";
        } 
        res +='}';
        
        return res;
    }

    int count() {
        int nb = 0 ;
        for (int i =0 ; i<blocs.size();i++){
          nb += blocs.get(i).getLignes().size();
        } 
      
        
        return nb;
    }
    
    
    //remplir l'index de posiition pos_i:
    public void remplirIndex(int pos)
    {
        
    }
    
    
}
