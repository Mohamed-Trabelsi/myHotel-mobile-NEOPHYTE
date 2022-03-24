/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serv.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import static com.serv.gui.HomeForm.Menu;

/**
 *
 * @author wiem
 */
public class HomeServForm extends Form {
       Form current;
    public HomeServForm() {
        current=this; //Back 
        setTitle("Gestion des services");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddCateg = new Button("Ajouter catégorie");
        Button btnListCateg = new Button("Liste des catégories");
         Button btnaddserv = new Button("ajouter service");
            Button btnlistserv = new Button("liste des services");
  Resources res = null;
        btnAddCateg.addActionListener(e-> new addCategForm(current,res).show());
        btnListCateg.addActionListener(e-> new listCategForm(current).show());
        btnaddserv.addActionListener(e-> new addServForm(current).show());
btnlistserv.addActionListener(e-> new listServForm(current).show());

    
    


        addAll(btnAddCateg,btnListCateg,btnaddserv,btnlistserv);
         Menu(this);
        
    } 
    
}
