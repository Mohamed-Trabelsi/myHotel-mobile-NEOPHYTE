/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author asus
 */
public class Contrats {
    private int etat,id,prix;
    private String dated,datef,contenu,titre;
    private Agences agence;
    
public Contrats() {
        
    }
    public Contrats(String dated, String datef, String contenu, String titre,int prix) {
        this.dated = dated;
        this.datef = datef;
        this.contenu = contenu;
        this.titre = titre;
        this.prix = prix;
    }

    public Contrats(int id,int etat, String dated, String datef, String contenu, String titre,int prix, Agences agence) {
        this.etat = etat;
        this.id = id;
        this.dated = dated;
        this.datef = datef;
        this.contenu = contenu;
        this.titre = titre;
        this.prix = prix;
        this.agence = agence;
    }

    public Contrats(int etat, String dated, String datef, String contenu, String titre,int prix,Agences agence) {
        this.etat = etat;
        this.dated = dated;
        this.datef = datef;
        this.contenu = contenu;
        this.titre = titre;
        this.prix = prix;
        this.agence = agence;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Contrats(String valueOf, String valueOf0){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getDatef() {
        return datef;
    }

    public void setDatef(String datef) {
        this.datef = datef;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Agences getAgence() {
        return agence;
    }

    public void setAgence(Agences agence) {
        this.agence = agence;
    }
    
}
