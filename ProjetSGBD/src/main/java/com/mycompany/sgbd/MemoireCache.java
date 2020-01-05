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
    protected Table tableCache;

    public MemoireCache() {
        this.M= 4;
        this.indiceS = 0;
        this.indiceR = 0;
        this.pos_attr_jointR = 0; 
        this.pos_attr_jointS = 0;
        this.buffersR = new ArrayList<Buffer>();
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
        this.getBuffersR().clear(); //flush des buffersR
        
        System.out.println("Chargement buffer R ");
        for(int i =0 ; i<this.getM()-1;i++){  // M
            if(this.indiceR<R.getBlocs().size())
                this.buffersR.add(new Buffer());
            
            for(int j =0 ;j<this.bufferS.getCapacite();j++){
                //capacite en bloc du buffer = 3 on suppose que le bufferS a la meme capacite que les buffersS
                if(this.indiceR<R.getBlocs().size()){
                    this.getBuffersR().get(i).getB().add(R.getBlocs().get(this.indiceR));
                    this.indiceR = this.indiceR+1;
                }
            }                          
        }        
    }
    
    public void chargeDernierBuffer(Table S)
    {
        this.getBufferS().getB().clear(); // flush du bufferS
        
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
        
        for(int i=0; i<bufferS.getB().size();i++) //bufferS
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
    

    public void parcoursCase(Ligne ls, Ligne lr, int pos_attr_jointS, int pos_attr_jointR)
    {
        List attr_res = new ArrayList<String>();
        if (ls.getAttributs().get(pos_attr_jointS) == lr.getAttributs().get(pos_attr_jointR))
        {
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
            this.tableCache.insertLigne(attr_res);
            
        }
        
    }
    
   
    
    public boolean chargeUnBufferR(Bloc br)
    {
            
            if (this.buffersR.size() == this.getM()-1 ) // si completement rempli on le clear
            {
                if(this.buffersR.get(this.M-2).getB().size() == this.bufferS.getCapacite() ) 
                {
                    this.buffersR.clear();
                }
            }
            
            if(this.buffersR.size() == 0) //si completement vide on ajoute un buffer à buffersR
            {
                this.buffersR.add(new Buffer());
            } 
                
                    
            
            if(this.buffersR.size() <= this.M -1 ) //si les buffersR ne sont pas remplis et que le dernier buffer de R n'est pas rempli
            {
               
               //allocation d'un buffer si le precdent est plein et que la taille du BuffersR n'excede pas M-1
               if( ( this.buffersR.get(buffersR.size()-1).getB().size() == this.bufferS.getCapacite() ) && (this.buffersR.size() != this.M-1 ))
               {
                   this.buffersR.add(new Buffer());
               }
               
               //si dernier element pas rempli et que ce n'est pas le dernier buffer
               if( (this.buffersR.get(buffersR.size()-1).getB().size() < this.bufferS.getCapacite() ) && (this.buffersR.size() != this.M-1 ) )
               {
                   this.buffersR.get(buffersR.size()-1).getB().add(br);
                   return true;
               }
               
            }
            
            return false;
    }
    
    //parcours bufferS
    
    public void chargeBufferKeylook(Table R) // Table R avec INDEX!
    {
        for(int i=0; i<bufferS.getB().size();i++) //bufferS
        {
            Bloc s =bufferS.getB().get(i);
            for(int j=0; j<s.getLignes().size(); j++)
            {
                Ligne ls = s.getLignes().get(j);
                for(int k=0; k< ls.getAttributs().size();k++)
                {
                    if(R.getIndext().getPos_i() == k) //critere de jointure
                    {
                        String attr = ls.getAttributs().get(k);
                       
                        List al = R.getIndext().getIndex().get(attr);
                        if(al == null)
                           System.out.println(attr);
                        if(al != null) //si il existe une jointure
                        {
                            for(int l=0;l< al.size();l++)
                            {
                                if( chargeUnBufferR(R.getBlocs().get(l)) ) //si succes de charge buffer 
                                {
                                    //on enleve le bloc de l'index car traité
                                    enleveBlocIndex(R, l); // et il faut aussi enlever le bloc dans "chaque clef"
                                }

                            }
                        }
                        
                    }  
                    
                }
                
            }
        }
    }
    
    public void enleveBlocIndex(Table R, int p)
    {
        for (String i : R.getIndext().getIndex().keySet()) 
        {
            List<Integer> rl = R.getIndext().getIndex().get(i);
            
            for(int j=0;j<rl.size(); j++)
            {
                if(rl.get(j) == p )
                {
                    rl.remove(p);
                }
            }
            
            R.getIndext().getIndex().put(i,rl); //suppr de la valeur de la clef correspondant au bloc 

        }
        
        for (int i=0;  i < R.getIndext().getIndex().size(); i++) 
        {
        if( R.getIndext().getIndex().get(R.getIndext().getIndex().keySet().toArray()[i]).size() == 0 ) //si la valeur de la clef correspond a une liste vide
            {            
                R.getIndext().getIndex().remove(R.getIndext().getIndex().values().toArray()[i]);; //suppr de la clef                
            }
        }
        
    }
    
    
    public int Cout()
    {
        int c = this.bufferS.getB().size(); //ajout des blocs de S dans le buffer
        
        for(int i=0; i<this.buffersR.size();i++)
            c+= this.buffersR.get(i).getB().size();
        
        return c;
    }
    
    
    
    public String toString()
    {
        String s="----MEMOIRE---- \n";
        s+= "chargeBuffers R (NbrBuffers : "+this.buffersR.size()+") : \n";
        for(int i=0;i<this.getBuffersR().size(); i++)
        {
            s+= "Buffer "+i+" (NbrBlocs : "+this.getBuffersR().get(i).getB().size()+ ") : "+this.getBuffersR().get(i).toString()+"\n";
        }
        
        s+= "DernierBuffer S (NbrBlocs : "+this.bufferS.getB().size()+") : "+this.bufferS.toString()+"\n";
        
        return s;
    }


}
