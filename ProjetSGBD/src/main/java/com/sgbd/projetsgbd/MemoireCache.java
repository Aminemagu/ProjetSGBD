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

    /*void chargeBufferCart(Table R, Table S) {
        
        for(int i=0; i<this.M;i++)
        {//on ajoute tous les blocs de R 
            this.buffers.add(R.getBlocs().get(i));
                    }
        //le dernier 
    }*/
    
    


}
