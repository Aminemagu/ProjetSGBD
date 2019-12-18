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
    private List<Ligne> lignes;
    private List<Indexes> indexes;
    

    public Table(List<Ligne> lignes, List<Indexes> indexes) {
        this.lignes = lignes;
        this.indexes = indexes;
    }

    public Table() {
        this.lignes = new ArrayList<Ligne>();
        this.indexes = new ArrayList<Indexes>();
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }

    public List<Indexes> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<Indexes> indexes) {
        this.indexes = indexes;
    }
    
}
