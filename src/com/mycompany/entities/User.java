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
public class User {
    private int idUser ;
    private String nom;
    private String prenom;
    private int age;
    private int cin;
    private int tel_user;
    private String email_user;
    private String password;
    private String role;
    private int etat;
    private String genre;
    
    public User() {
    }

    public User(String nom, String prenom, int age, int cin, int tel_user, String email_user, String password, String role, String genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.cin = cin;
        this.tel_user = tel_user;
        this.email_user = email_user;
        this.password = password;
        this.role = role;
        this.genre = genre;
    }

    public User(int idUser, String nom, String prenom, int age, int cin, int tel_user, String email_user, String password, String role, int etat, String genre) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.cin = cin;
        this.tel_user = tel_user;
        this.email_user = email_user;
        this.password = password;
        this.role = role;
        this.etat = etat;
        this.genre = genre;
    }
    

    public int getId_user() {
        return idUser;
    }

    public void setId_user(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getTel_user() {
        return tel_user;
    }

    public void setTel_user(int tel_user) {
        this.tel_user = tel_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "User{" + "nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", cin=" + cin + ", tel_user=" + tel_user + ", email_user=" + email_user + ", password=" + password + ", role=" + role + ", etat=" + etat + ", genre=" + genre + '}';
    }
   
}


