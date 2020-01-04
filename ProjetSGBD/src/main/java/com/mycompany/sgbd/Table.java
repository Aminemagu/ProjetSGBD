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
public class Table {
    private List<Bloc> blocs;
    private String nom;
    private Index indext;
    private List<String> attributs;
    

    public Table(String n,List<String> l) {
        this.attributs = l;
        this.nom = n;
        this.blocs = new ArrayList<Bloc>();
        this.blocs.add(new Bloc());
        this.indext = new Index(1);
    }

    public List<Bloc> getBlocs() {
        return blocs;
    }

    public void setLignes(List<Bloc> blocs) {
        this.blocs = blocs;
    }    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getAttribut() {
        return attributs;
    }

    public void setIndext(Index indext) {
        this.indext = indext;
        this.remplirIndex(indext.getPos_i());
    }

    public Index getIndext() {
        return indext;
    }

    
   
    
    //méthode pour insérer les lignes dans les blocs selon leurs capacitées
    void insertLigne(List<String> l1) {
        Ligne l = new Ligne(l1);
        if (l1.size() != this.attributs.size()){
            System.out.println("La ligne n'a pas le bon nombre d'attribut");
        }else{
            if(blocs.get(blocs.size()-1).getFreespace() > l.getTaille()){ //si dernier bloc n'est pas plein
                    blocs.get(blocs.size()-1).getLignes().add(l);
            }else{
                blocs.add(new Bloc());
                blocs.get(blocs.size()-1).getLignes().add(l);
                
            }
        }
    }

    @Override
    public String toString() {

        String res = "Table {\n" ;
        for (int i =0 ; i<blocs.size();i++){
           res += "\t"+i + " : " + blocs.get(i).toString() +"\n";
        } 
        res +='}';
        
        if(this.indext.getIndex().size() != 0)
        {
            res+="\n"+this.indext.toString();
        }  
        
        return res;
    }

    int count() {
        int nb = 0 ;
        for (int i =0 ; i<blocs.size();i++){
          nb += blocs.get(i).getLignes().size();
        } 
      
        
        return nb;
    }
    
    
    //remplir l'index de posiition pos_i:
    public void remplirIndex(int pos)
    {
        this.indext = new Index(pos);
        
        for(int i=0; i<this.getBlocs().size();i++) //parcours blocs
        {
            for(int j=0; j<this.getBlocs().get(i).getLignes().size();j++) //parcours lignes
            {
                for(int k=0; k<this.getBlocs().get(i).getLignes().get(j).getAttributs().size();k++) //parcours attributs
                {
                    if(k == pos ) //pour parcourir uniquement l'attribut d'index
                    {
                        String attr = this.getBlocs().get(i).getLignes().get(j).getAttributs().get(k);
                        if( indext.getIndex().get(attr) == null) //si attribut pas dans l'index on l'ajoute
                        {
                            ArrayList l = new ArrayList<Integer>();
                            l.add(i); //on ajoute l'indice du bloc correspondant a l'attribut
                            indext.getIndex().put(attr, l);                            
                        }
                        else // l'attribut est deja present dans l'index, il faut ajouter l'indice du bloc
                        {
                            int t = 0;
                            for(int l =0; l<indext.getIndex().get(attr).size();l++)
                            {
                                if(indext.getIndex().get(attr).get(l) == i) 
                                    t=t+1;
                            }
                            if( t==0 )
                                indext.getIndex().get(attr).add(i); // si bloc pas présent dans la liste on ajoute l'indice du bloc 
                        }
                    }
                }
            }
            
        }
        
    }
    
 
    
}
