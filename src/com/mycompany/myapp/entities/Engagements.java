/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author trabelssi
 */
public class Engagements {
    private int id,event,spons;

    public Engagements() {
    }

    public Engagements(int event, int spons) {
        this.event = event;
        this.spons = spons;
    }

    public Engagements(int id, int event, int spons) {
        this.id = id;
        this.event = event;
        this.spons = spons;
    }

    public int getId() {
        return id;
    }

    public int getEvent() {
        return event;
    }

    public int getSpons() {
        return spons;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public void setSpons(int spons) {
        this.spons = spons;
    }

    @Override
    public String toString() {
        return "Engagements{" + "event=" + event + ", spons=" + spons + '}';
    }
}
