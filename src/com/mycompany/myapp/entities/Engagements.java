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
    private int id,event,sponsor;
private String e,s;

    public Engagements(int id, String e, String s) {
        this.id = id;
        this.e = e;
        this.s = s;
    }

    public String getE() {
        return e;
    }

    public String getS() {
        return s;
    }

    public void setE(String e) {
        this.e = e;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Engagements() {
    }

    public Engagements(int event, int sponsor) {
        this.event = event;
        this.sponsor = sponsor;
    }

    public Engagements(int id, int event, int sponsor) {
        this.id = id;
        this.event = event;
        this.sponsor = sponsor;
    }

    public int getId() {
        return id;
    }

    public int getEvent() {
        return event;
    }

    public int getSponsor() {
        return sponsor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public void setSponsor(int sponsor) {
        this.sponsor = sponsor;
    }

    @Override
    public String toString() {
        return "Engagements{" + "event=" + event + ", spons=" + sponsor + '}';
    }
}
