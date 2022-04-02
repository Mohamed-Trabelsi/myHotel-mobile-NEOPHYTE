/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.util.Resources;

/**
 *
 * @author wiem
 */
public class HomeForm extends Form {
   Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
   /*     add(new Label("Choose an option"));
        Button btnAddCateg = new Button("Ajouter catégorie");
        Button btnListCateg = new Button("Liste des catégories");
         Button btnaddserv = new Button("ajouter service");
            Button btnlistserv = new Button("liste des services");
  Resources res = null;
        btnAddCateg.addActionListener(e-> new addCategForm(current,res).show());
        btnListCateg.addActionListener(e-> new listCategForm(current).show());
        btnaddserv.addActionListener(e-> new addServForm(current).show());
btnlistserv.addActionListener(e-> new listServForm(current).show());
*/
    
    


       // addAll(btnAddCateg,btnListCateg,btnaddserv,btnlistserv);
         Menu(this);
        
    } 
       public static void Menu(Form f) {

  
        Resources res = null;
          //   f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des utilisateurs", " ".charAt(0),e -> new listCategForm(f,res) .show());
         //f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des services", " ".charAt(0),e -> new HomeServForm().show());
        //f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des évenements", " ".charAt(0),e -> new listCategForm(f,res) .show());
          // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new listCategForm(f,res) .show());
  // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new listCategForm(f,res) .show());
   // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservation", " ".charAt(0),e -> new listCategForm(f,res) .show());
         // f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new listCategForm(f) .show());
         //  f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new statistique().getF().show());

    }
       
    void addSideMenu(Resources res, Form f) {
    }
  
}
