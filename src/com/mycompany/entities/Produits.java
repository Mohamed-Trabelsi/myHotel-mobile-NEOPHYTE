/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Administrator
 */
public class Produits {
    
    private int id;
    private String libelle,type;
    private int quantite;

    public Produits() {
    }

    public Produits(int id, String libelle, String type, int quantite) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.quantite = quantite;
    }

    public Produits(String libelle, String type, int quantite) {
        this.libelle = libelle;
        this.type = type;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    
    
    

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", libelle=" + libelle + ", type=" + type + ", quantite=" + quantite + '}';
    }
    
    
    
    
    
    
}
