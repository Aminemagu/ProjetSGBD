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
public class MemoireCache {
    
    private List<Buffer> buffers; 
    private int M;
    protected int indiceR;
    protected int indiceS;

    public MemoireCache() {
        this.M= 4;
        this.indiceS = 0;
        this.indiceR = 0;
        this.buffers =new ArrayList<Buffer>();
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

    
    public void chargeBuffer(Table R)
    {
        System.out.println("Chargement buffer R ");
        for(int i =0 ; i<(this.getM()-1);i++){  // M-1 car on ne veut pas charger le dernier buffer
            
            if( !this.getBuffers().isEmpty() )
            {
                System.out.println("i="+i);
                for(int j =0 ;j<3;j++){ //capacite en bloc du buffer
                    
                    if(this.indiceR<R.getBlocs().size()){
                        this.getBuffers().get(i).getB().add(R.getBlocs().get(this.indiceR));
                        this.indiceR = this.indiceR+1;
                    }
                    
                }
                //System.out.println(this.getBuffers().get(i).getB().toString());
            }
            
        }
        
    }
    
    public void chargeDernierBuffer(Table S)
    {
        System.out.println("Chargement buffer S ");
        for(int i =0 ;i<3;i++){
            System.out.println("i="+i);
            if(this.indiceS<S.getBlocs().size()){
                this.getBuffers().get(this.getM()-1).getB().add(S.getBlocs().get(this.indiceS));
                this.indiceS = this.indiceS+1;
            }           
        }
        //System.out.println(this.getBuffers().get(this.getM()-1).getB().toString());
    }
    
    
    public void chargeTousLesBuffer(Table R, Table S)
    {
        //NE PAS OUBLIER DE VIDER LA LISTE (= BUFFER) APRES CHAQUE ITERATION
        this.getBuffers().clear();
        int taille_R = R.getBlocs().size();
        int taille_S = S.getBlocs().size();

        while( (this.indiceR != taille_R) && (this.indiceS !=taille_S))
        {
            for(int i=0; i<taille_R; i++) //parcourir table R
            {
                this.getBuffers().clear();
                chargeBuffer(R);
                chargeDernierBuffer(S);
            }
        }
        
        
        //chargeBuffer(R)
        //chargeDernierBuffer(S)
    }
    
    public String toString()
    {
        String s="";
        for(int i=0;i<this.getBuffers().size(); i++)
            s+= "Buffer "+i+" : "+this.getBuffers().get(i).toString()+"\n";
        return s;
    }


}
