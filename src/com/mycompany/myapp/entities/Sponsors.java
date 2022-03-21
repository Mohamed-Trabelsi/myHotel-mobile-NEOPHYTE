/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author trabelssi
 */
public class Sponsors {
    private int id,telS;
    private String adresseS,nomS;

    public Sponsors() {
    }

    public Sponsors(int id, int telS, String adresseS, String nomS) {
        this.id = id;
        this.telS = telS;
        this.adresseS = adresseS;
        this.nomS = nomS;
    }


    public Sponsors(int telS, String adresseS, String nomS) {
        this.telS = telS;
        this.adresseS = adresseS;
        this.nomS = nomS;
    }

    
   

    public int getId() {
        return id;
    }

    public int getTelS() {
        return telS;
    }

    public String getAdresseS() {
        return adresseS;
    }

    public String getNomS() {
        return nomS;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelS(int telS) {
        this.telS = telS;
    }

    public void setAdresseS(String adresseS) {
        this.adresseS = adresseS;
    }

    public void setNomS(String nomS) {
        this.nomS = nomS;
    }

    @Override
    public String toString() {
        return "Sponsors{" + "telS=" + telS + ", adresseS=" + adresseS + ", nomS=" + nomS + '}';
    }
    
public Object get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }  

}
