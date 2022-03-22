/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serv.entities;

import java.util.Date;

/**
 *
 * @author wiem
 */
public class service {
    int idServ;
String db, df;
    String id_categ;
       public service() {
   
    }
    
    public service( int idServ,String db, String df, String id_categ) {
      this.idServ = idServ;
        this.db = db;
        this.df = df;
        this.id_categ = id_categ;
    }
 public service(String db, String df, String id_categ) {
  
        this.db = db;
        this.df = df;
        this.id_categ = id_categ;
    }
  

    public int getIdServ() {
        return idServ;
    }

    public String getDb() {
        return db;
    }

    public String getDf() {
        return df;
    }

    public String getId_categ() {
        return id_categ;
    }

    public void setIdServ(int idServ) {
        this.idServ = idServ;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public void setId_categ(String id_categ) {
        this.id_categ = id_categ;
    }

  

 

 

    
    
    
    
}
