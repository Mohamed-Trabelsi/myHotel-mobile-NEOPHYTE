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
 * @author asus
 */
public class home extends Form{
     Form current;

    public home() {
       
        current=this; 
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        //Button btnAdd = new Button("add");
        Button btnListEvenements = new Button("Liste des contrats");
        Button btnListSponsors = new Button("Liste des Agences");
       
       btnListSponsors.addActionListener(e-> new AfficherAgence(current).show());
        btnListEvenements.addActionListener(e-> new AfficherContrat(current).show());
       
       //btnAdd.addActionListener(e-> new AjoutEvenement(current).show());
        addAll(btnListEvenements,btnListSponsors);
    }
    
}
