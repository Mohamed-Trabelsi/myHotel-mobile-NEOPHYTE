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
public class Fournisseurs {
    
    private int id;
    private String nom,adresse;
    private int num;

    public Fournisseurs() {
    }

    public Fournisseurs(int id, String nom, String adresse, int num) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.num = num;
    }

    public Fournisseurs(String nom, String adresse, int num) {
        this.nom = nom;
        this.adresse = adresse;
        this.num = num;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

        public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    
    @Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", nom=" + nom + ", num=" + num + ", adresse=" + adresse + '}';
    }
    
}
