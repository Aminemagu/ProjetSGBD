/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sgbd;


import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tb985403
 */
public class Index {
    private int pos_i; //index sur attribut en pos_i
    private HashMap<String, List<Integer> > index; // un attribut correspond à des addresses de blocs
    

    public Index(int pos_i) {
        this.pos_i = pos_i;
        index = new HashMap<String, List<Integer>>();        
    }

    public int getPos_i() {
        return pos_i;
    }

    public void setPos_i(int pos_i) {
        this.pos_i = pos_i;
    }

    public HashMap<String, List<Integer>> getIndex() {
        return index;
    }

    public void setIndex(HashMap<String, List<Integer>> index) {
        this.index = index;
    }
    


    public String toString()
    {
        String s="AFFICHAGE DE L'INDEX sur pos_attr : "+this.pos_i;
        for (String i : this.getIndex().keySet()) 
        {
            s+="\n clef: " + i + " adresse: ";
            for(int j=0;j<this.index.get(i).size(); j++)
                s+= this.getIndex().get(i).get(j)+";";
        }
        s+="\n";
        return s;
    }
    
    
    
    
}
