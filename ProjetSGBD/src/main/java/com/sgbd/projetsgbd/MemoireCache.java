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
public class MemoireCache {
    
    private List<Buffer> buffers;
    private int M;

    public MemoireCache() {
        this.M= 4;
        this.buffers = new ArrayList<Buffer>();
        for(int i = 0 ;i<this.M;i++){
            buffers.add(new Buffer());
        }
        
    }


    public List<Buffer> getBuffers() {
        return buffers;
    }

    public void setBuffers(List<Buffer> buffers) {
        this.buffers = buffers;
    }

    public int getM() {
        return M;
    }

    public void setM(int M) {
        this.M = M;
    }

    
    
    // J AI FAIT COMME SI UN BUFFER CHARGEAIT UN BLOC ALORS QU'IL PEUT EN CHARGER 3 NORMALEMENT
    
    public void chargeBuffer(Table R, int indice)
    {
        //NE PAS OUBLIER DE VIDER LES LISTES
        for(int i=0+indice; i < ( M+indice) -1 ;i++ ) //M-1 car on charge 3 buffer de la table R
        {
            if( R.getBlocs().size() < i ) 
                this.buffers.get(i).getB().add(R.getBlocs().get(i));
        }
        
    }
    
    public void chargeDernierBuffer(Table S, int indice)
    {
        if( S.getBlocs().size() < indice )
        {
            this.buffers.get(this.M-1).getB().add(S.getBlocs().get(0));
        }
    }
    
    public void chargeTousLesBuffer()
    {
        //chargeBuffer(R, 15)
        //chargeDernierBuffer(S)
    }


}
