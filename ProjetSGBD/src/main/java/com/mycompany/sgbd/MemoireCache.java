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
    protected int pos_attr_jointR;
    protected int pos_attr_jointS;

    public MemoireCache() {
        this.M= 4;
        this.indiceS = 0;
        this.indiceR = 0;
        this.buffersR =new ArrayList<Buffer>();
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
        for(int i =0 ; i<this.getM()-1;i++){  // M
            for(int j =0 ;j<this.bufferS.getCapacite();j++){ //capacite en bloc du buffer = 3 on suppose que le bufferS a la meme capacite que les buffersS
                if(this.indiceR<R.getBlocs().size()){
                    this.buffersR.add(new Buffer());
                    this.getBuffersR().get(i).getB().add(R.getBlocs().get(this.indiceR));
                    this.indiceR = this.indiceR+1;
                }
            }                          
        }        
    }
    
    public void chargeDernierBuffer(Table S)
    {
        System.out.println("Chargement buffer S ");
        for(int i =0 ;i<this.bufferS.getCapacite();i++){ //parcours blocs
            if(this.indiceS<S.getBlocs().size()){
                this.bufferS.getB().add(S.getBlocs().get(this.indiceS));
                this.indiceS = this.indiceS+1;
            }           
        }
        
    }
    
    public void parcoursMem()
    {
        
        for(int i=0; i<bufferS.getB().size();i++) //bufferR
        {
            for(int j=0; j<buffersR.size();j++) //taille buffersR
            {
                
                for(int k=0; k<buffersR.get(j).getB().size();k++) //nombre de blocs de buffersR.get(i)
                {
                    parcoursLignes(bufferS.getB().get(i) , buffersR.get(j).getB().get(k));
                }
            }
        }
    }
    
    public void parcoursLignes(Bloc s, Bloc r) //on mettra les indices
    {
        for(int i=0; i< s.getLignes().size();i++)
        {
            Ligne ls = s.getLignes().get(i);
            for(int j=0; j< r.getLignes().size();j++)
            {
                Ligne lr = r.getLignes().get(j);
                parcoursCase(ls, lr, this.pos_attr_jointS, this.pos_attr_jointR);
            }
        }
    }
    
    // il faut que les 2 tables aient la meme structure
    //c.à.d l'attribut R.a et S.a ont meme pos dans la table R et S
    public void parcoursCase(Ligne ls, Ligne lr, int pos_attr_jointS, int pos_attr_jointR)
    {
        if (ls.getAttributs().get(pos_attr_jointS) == lr.getAttributs().get(pos_attr_jointR))
        {
            List attr_res = new ArrayList<String>();
            for(int i=0; i< ls.getAttributs().size(); i++)
            {
                 attr_res.add(ls.getAttributs().get(i));
                 System.out.print(ls.getAttributs().get(i)+" | ");
            }
            
            for(int j=0; j< lr.getAttributs().size(); j++)
            {
                if(lr.getAttributs().get(pos_attr_jointR) != lr.getAttributs().get(j))
                {
                    attr_res.add(lr.getAttributs().get(j));
                    System.out.print(lr.getAttributs().get(j)+" | ");
                }                   
            }
            System.out.println("");           
            //return Ligne res 
        }
        
        //return null
    }
    
    
    
    public String toString()
    {
        String s="----MEMOIRE---- \n";
        for(int i=0;i<this.getBuffersR().size(); i++)
            s+= "Buffer "+i+" (taille : "+this.getBuffersR().get(i).getB().size()+ ") : "+this.getBuffersR().get(i).toString()+"\n";
            s+= "DernierBuffer S (taille : "+this.bufferS.getB().size()+") : "+this.bufferS.toString()+"\n";
        return s;
    }


}
