/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.Abs.showAbsForm;
import com.mycompany.gui.User.showUserForm;


/**
 *
 * @author WiiWii
 */
public class HomeResForm extends Form {
    Form current;
    public HomeResForm() {
        current=this; //Back 
        setTitle("Gestion des réservations");
        setLayout(BoxLayout.y());
        
        Container add = add(new Label("Choose an option"));
       
        Button btnAdd = new Button("Ajouter une Reservation");
   Button btnRechReservations = new Button("Rechercher Reservations");
   Button btnListReservations = new Button("Afficher Reservations");
      Button btnListChambres = new Button("Chambres"); 
    
   //  Button btnListRes = new Button("Liste des Reservations ");
  Resources res = null;
        btnAdd.addActionListener(e-> new AjoutResForm(current).show());
        //btnListReservations.addActionListener(e-> new ModifReservationForm(current).show());
        //   btnListRes.addActionListener(e-> new ListResForm(current).show());
              btnListReservations.addActionListener(e-> new ListRes(current).show());
              btnRechReservations.addActionListener(e-> new ListResForm(current).show());
        
             
         
             
     
       // btnListChamb.addActionListener(e-> new ListChambForm(current).show());
        
    
    


        addAll( btnAdd  ,btnListReservations,btnRechReservations);
        
   Menu(this);      
        
    } 

          public static void Menu(Form f) {

  
       Resources res = null;
         f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des évenements", " ".charAt(0),e ->new HomeEventFront().show() );
          f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new homeagencefront() .show());
           f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new homeAhmed().show());
            f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des services", " ".charAt(0),e -> new HomeServForm(f) .show());
                        f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservations", " ".charAt(0),e -> new HomeResForm() .show());
          // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new listCategForm(f,res) .show());
  // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new listCategForm(f,res) .show());
   // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservation", " ".charAt(0),e -> new listCategForm(f,res) .show());
         // f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new listCategForm(f) .show());
         //  f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new statistique().getF().show());

    } 
    
}
