/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;
import java.util.Date;
/**
 *
 * @author WiiWii
 */
public class Reservation {

    public static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void add(Reservation e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int id;
    private String Client;
    private  Date DateD,DateF;
    private int id_Chambre; 

    public Reservation() {
    }
    
    

    public Reservation(int id, String Client, Date DateD, Date DateF, int id_Chambre) {
        this.id = id;
        this.Client = Client;
        this.DateD = DateD;
        this.DateF = DateF;
        this.id_Chambre = id_Chambre;
    }

    public Reservation(String Client, Date DateD, Date DateF, int id_Chambre) {
        this.Client = Client;
        this.DateD = DateD;
        this.DateF = DateF;
        this.id_Chambre = id_Chambre;
    }

    public Reservation(String Client, Date DateD, Date DateF) {
        this.Client = Client;
        this.DateD = DateD;
        this.DateF = DateF;
    }


   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String Client) {
        this.Client = Client;
    }

    public Date getDateD() {
        return DateD;
    }

    public void setDateD(Date DateD) {
        this.DateD = DateD;
    }

    public Date getDateF() {
        return DateF;
    }

    public void setDateF(Date DateF) {
        this.DateF = DateF;
    }

    public int getId_Chambre() {
        return id_Chambre;
    }

    public void setId_Chambre(int id_Chambre) {
        this.id_Chambre = id_Chambre;
    }

    public void setReservation(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    }
