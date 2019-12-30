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
    
    private List<Buffer> buffersR;
    private Buffer bufferS;
    private int M;
    protected int indiceR;
    protected int indiceS;

    public MemoireCache() {
        this.M= 4;
        this.indiceS = 0;
        this.indiceR = 0;
        this.buffersR =new ArrayList<Buffer>();
        for(int i = 0 ;i<this.M-1;i++){
            buffersR.add(new Buffer());
        }
        this.bufferS = new Buffer();
        
    }


    public List<Buffer> getBuffersR() {
        return buffersR;
    }

    public void setBuffersR(List<Buffer> buffers) {
        this.buffersR = buffers;
    }
    
    public Buffer getBufferS(){
        return bufferS;
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
        for(int i =0 ; i<this.getM();i++){  // M
            
            if( !this.getBuffersR().isEmpty() )
            {
                System.out.println("i="+i);
                for(int j =0 ;j<3;j++){ //capacite en bloc du buffer
                    
                    if(this.indiceR<R.getBlocs().size()){
                        this.getBuffersR().get(i).getB().add(R.getBlocs().get(this.indiceR));
                        this.indiceR = this.indiceR+1;
                    }                   
                }               
            }           
        }        
    }
    
    public void chargeDernierBuffer(Table S)
    {
        System.out.println("Chargement buffer S ");
        for(int i =0 ;i<3;i++){ //parcours blocs
            System.out.println("i="+i);
            if(this.indiceS<S.getBlocs().size()){
                this.bufferS.getB().add(S.getBlocs().get(this.indiceS));
                this.indiceS = this.indiceS+1;
            }           
        }
        
    }
    
    public void videDernierBuffer()
    {
        System.out.println("Flush buffer S ");
        
    }
    
    
    /*public void chargeTousLesBuffer(Table R, Table S)
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
                this.toString();
            }
        }
        
        
        //chargeBuffer(R)
        //chargeDernierBuffer(S)
    }
    */
    
    public String toString()
    {
        String s="----MEMOIRE---- \n";
        for(int i=0;i<this.getBuffersR().size(); i++)
            s+= "Buffer "+i+" (taille : "+this.getBuffersR().get(i).getB().size()+ ") : "+this.getBuffersR().get(i).toString()+"\n";
            s+= "DernierBuffer S (taille : "+this.bufferS.getB().size()+") : "+this.bufferS.toString()+"\n";
        return s;
    }


}
