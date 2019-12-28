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
public class Indexes {
    Hashtable<Integer, String> hm; 
    
    public Indexes() {
        this.hm = new Hashtable<Integer, String>();
    }

    public Hashtable<Integer, String> getHm() {
        return hm;
    }

    public void setHm(Hashtable<Integer, String> hm) {
        this.hm = hm;
    }
    
    
    
}
