/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author trabelssi
 */
public class Evenements {
    private int id;
    private String libelleE,dateDE,dateFE,descriptionE,espaceE;
    private int capaciteE;

    public Evenements(int id, String libelleE, String dateDE, String dateFE, String descriptionE, String espaceE, int capaciteE) {
        this.id = id;
        this.libelleE = libelleE;
        this.dateDE = dateDE;
        this.dateFE = dateFE;
        this.descriptionE = descriptionE;
        this.espaceE = espaceE;
        this.capaciteE = capaciteE;
    }

    public Evenements() {
    }

    public Evenements(String libelleE, String dateDE, String dateFE, String descriptionE, String espaceE, int capaciteE) {
        this.libelleE = libelleE;
        this.dateDE = dateDE;
        this.dateFE = dateFE;
        this.descriptionE = descriptionE;
        this.espaceE = espaceE;
        this.capaciteE = capaciteE;
    }

    public int getId() {
        return id;
    }

    public String getLibelleE() {
        return libelleE;
    }

    public String getDateDE() {
        return dateDE;
    }

    public String getDateFE() {
        return dateFE;
    }

    public String getDescriptionE() {
        return descriptionE;
    }

    public String getEspaceE() {
        return espaceE;
    }

    public int getCapaciteE() {
        return capaciteE;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelleE(String libelleE) {
        this.libelleE = libelleE;
    }

    public void setDateDE(String dateDE) {
        this.dateDE = dateDE;
    }

    public void setDateFE(String dateFE) {
        this.dateFE = dateFE;
    }

    public void setDescriptionE(String descriptionE) {
        this.descriptionE = descriptionE;
    }

    public void setEspaceE(String espaceE) {
        this.espaceE = espaceE;
    }

    public void setCapaciteE(int capaciteE) {
        this.capaciteE = capaciteE;
    }

    @Override
    public String toString() {
        return "Evenements{" + "libelleE=" + libelleE + ", dateDE=" + dateDE + ", dateFE=" + dateFE + ", descriptionE=" + descriptionE + ", espaceE=" + espaceE + '}';
    }

    public Object get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
}
