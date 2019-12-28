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
        System.out.println("Chargement buffer R ");
        for(int i =0 ;i<this.M-1;i++){
            for(int j =0 ;j<this.buffers.get(i).getCapacite();j++){
                if(indice<R.getBlocs().size()){
                    System.out.println(""+indice);
                    this.buffers.get(this.M-1).getB().add(R.getBlocs().get(indice));
                    indice = indice+1;
                    System.out.println("blocs chrager");
             }
            }
        }
        System.out.println("buffer charger");
        
    }
    
    public void chargeDernierBuffer(Table S, int indice)
    {
        System.out.println("Chargement buffer S ");
        for(int i =0 ;i<this.buffers.get(M-1).getCapacite();i++){
            
            if(indice<S.getBlocs().size()){
                System.out.println(""+indice);
                this.buffers.get(this.M-1).getB().add(S.getBlocs().get(indice));
                indice = indice+1;
                System.out.println("blocs chrager");
            }
        }
        System.out.println("buffer charger");
    }
    
    public void chargeTousLesBuffer(Table R, Table S)
    {
        //NE PAS OUBLIER DE VIDER LA LISTE (= BUFFER) APRES CHAQUE ITERATION
        this.buffers.clear();
        int taille_R = R.getBlocs().size();
        int taille_S = S.getBlocs().size();
        int indice_R = 0;
        int indice_S = 0;
        
        while( (indice_R != taille_R) && (indice_S !=taille_S))
        {
            for(int i=0; i<taille_R; i++) //parcourir table R
            {
                this.buffers.clear();
                chargeBuffer(R, indice_R );
                chargeDernierBuffer(S,indice_S);
            }
        }
        
        
        //chargeBuffer(R)
        //chargeDernierBuffer(S)
    }


}
