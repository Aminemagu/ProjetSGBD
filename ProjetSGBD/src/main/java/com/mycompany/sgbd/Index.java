/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sgbd;

import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author tb985403
 */
public class Index {
    private int pos_i; //index sur attribut en pos_i
    private Hashtable<String, List<Integer> > index; // un attribut correspond à des addresses de blocs
    
    
    public Index(int pos_i) {
        this.pos_i = pos_i;
        index = new Hashtable<String, List<Integer>>();        
    }

    
    
    
    
    
}
