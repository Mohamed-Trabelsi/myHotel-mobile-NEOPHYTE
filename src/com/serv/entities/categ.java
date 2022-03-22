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
public class categ {
    private int id;
    private String name;
    
    public categ() {
    }

    public categ( String name) {
       
        this.name = name;
       
    
    }

    public categ(int id, String name) {
        this.id = id;
        this.name = name;
     
       
    }

    @Override
    public String toString() {
        return "categorie{" + "id=" + id + ", nom=" + name + '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


 

}
