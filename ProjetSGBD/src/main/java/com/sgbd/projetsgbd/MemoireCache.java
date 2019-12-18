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
public class MemoireCache {
    
private List<Buffer> b;
private int capacite;


    public MemoireCache(List<Buffer> b, int capacite) {
        this.b = b;
        this.capacite = capacite;
    }

    public List<Buffer> getB() {
        return b;
    }

    public void setB(List<Buffer> b) {
        this.b = b;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }



}
