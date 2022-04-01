/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author trabelssi
 */
public class HomeEventFront extends Form{
    Form current;

   public HomeEventFront() {
       
        current=this; 
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        //Button btnAdd = new Button("add");
        Button btnListEvenements = new Button("Évènements");
        Button btnListSponsors = new Button("Sponsors");
        Button btnListEngagement = new Button("Engagements");
        
       
       btnListSponsors.addActionListener(e-> new AfficherSponsor(current).show());
       btnListEvenements.addActionListener(e-> new AfficherEvenementFront(current).show());
       
       btnListEngagement.addActionListener(e-> new AfficherEngagement(current).show());
       //btnAdd.addActionListener(e-> new AjoutEvenement(current).show());
        addAll(btnListEvenements,btnListSponsors,btnListEngagement);
    }

    HomeEventFront(AfficherEvenement aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
