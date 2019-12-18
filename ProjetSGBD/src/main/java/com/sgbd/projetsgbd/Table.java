/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgbd.projetsgbd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tb985403
 */
public class Table {
    private List<Bloc> blocs;
    private List<Indexes> indexes;
    private List<String> atribut;
    

    public Table(List<String> l) {
        this.atribut = l;
        this.blocs = new ArrayList<Bloc>();
        this.blocs.add(new Bloc());
        this.indexes = new ArrayList<Indexes>();
    }

    public List<Bloc> getBlocs() {
        return blocs;
    }

    public void setLignes(List<Bloc> blocs) {
        this.blocs = blocs;
    }

    public List<Indexes> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Indexes> indexes) {
        this.indexes = indexes;
    }

    void insertLigne(List<String> l1) {
        Ligne l = new Ligne(l1);
        if (l1.size() != this.atribut.size()){
            System.out.println("La ligne n'a pas le bon nombre d'attribut");
        }else{
            if(blocs.get(blocs.size()-1).getFreespace() > l.getTaille()){//si dernier bloc n'est pas plien
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
    
    
}
