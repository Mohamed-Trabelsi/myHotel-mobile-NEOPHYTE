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
public class Agences {
     private int id,num;
    private String nom,email;

    public Agences(int id, int num, String nom, String email) {
        this.id = id;
        this.num = num;
        this.nom = nom;
        this.email = email;
    }

    public Agences() {
    }

    public Agences(int num, String nom, String email) {
        this.num = num;
        this.nom = nom;
        this.email = email;
    }

    public Agences(String valueOf, String valueOf0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   @Override
    public String toString() {
        return "Engagements{" + "nom=" + nom + ", num =" + num + ", email=" + email + '}';
    }
    
}
