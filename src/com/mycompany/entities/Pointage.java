/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author dell
 */
public class Pointage {
    
    private int idPtg ;
    private String dateDepart;
    private String typePtg;
    private int duree;
    private int etatConge;
    private int id_user;
    private String userNom;
    private String userPrenom;

    public Pointage() {
    }

    public Pointage(int idPtg, String dateDepart, String typePtg, int duree, int etatConge, int id_user) {
        this.idPtg = idPtg;
        this.dateDepart = dateDepart;
        this.typePtg = typePtg;
        this.duree = duree;
        this.etatConge = etatConge;
        this.id_user = id_user;
    }

    public Pointage(String dateDepart, String typePtg, int duree, int etatConge, int id_user) {
        this.dateDepart = dateDepart;
        this.typePtg = typePtg;
        this.duree = duree;
        this.etatConge = etatConge;
        this.id_user = id_user;
    }

    public Pointage(int idPtg, String dateDepart, String typePtg, int duree, int id_user) {
        this.idPtg = idPtg;
        this.dateDepart = dateDepart;
        this.typePtg = typePtg;
        this.duree = duree;
        this.id_user = id_user;
    }

    public Pointage(String dateDepart, String typePtg, int duree, int id_user) {
        this.dateDepart = dateDepart;
        this.typePtg = typePtg;
        this.duree = duree;
        this.id_user = id_user;
    }
    

    public int getIdPtg() {
        return idPtg;
    }

    public void setIdPtg(int idPtg) {
        this.idPtg = idPtg;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getTypePtg() {
        return typePtg;
    }

    public void setTypePtg(String typePtg) {
        this.typePtg = typePtg;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getEtatConge() {
        return etatConge;
    }

    public void setEtatConge(int etatConge) {
        this.etatConge = etatConge;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Pointage(String dateDepart, String typePtg, int duree, int id_user, String userNom, String userPrenom) {
        this.dateDepart = dateDepart;
        this.typePtg = typePtg;
        this.duree = duree;
        this.id_user = id_user;
        this.userNom = userNom;
        this.userPrenom = userPrenom;
    }

    public String getUserNom() {
        return userNom;
    }

    public void setUserNom(String userNom) {
        this.userNom = userNom;
    }

    public String getUserPrenom() {
        return userPrenom;
    }

    public void setUserPrenom(String userPrenom) {
        this.userPrenom = userPrenom;
    }
    
    

    @Override
    public String toString() {
        return "Pointage{" + "dateDepart=" + dateDepart + ", typePtg=" + typePtg + ", duree=" + duree + ", etatConge=" + etatConge + ", id_user=" + id_user + '}';
    }
    
    
    
}
