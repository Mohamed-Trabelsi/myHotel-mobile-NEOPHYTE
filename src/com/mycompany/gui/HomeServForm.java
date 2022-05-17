/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import static com.mycompany.gui.HomeForm.Menu;

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
         Toolbar tb = this.getToolbar();
         tb.addCommandToOverflowMenu("Déconnecter", null,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean yes = Dialog.show("Alerte", "Voulez-vous vraiment se déconnecter ?", "oui", "non");
                if (yes) {
                    current.show();
                }
            }
        });
        add(new Label("Choisir une option"));
       // Button btnAddCateg = new Button("Ajouter catégorie");
        //Button btnListCateg = new Button("Liste des catégories");
         Button btnaddserv = new Button("ajouter service");
           // Button btnlistserv = new Button("liste des services");
                    Button bstat = new Button("liste des services ");
                         Button l = new Button("map");
                          // Button rec = new Button("rechercher un service ");
  Resources res = null;
        //btnAddCateg.addActionListener(e-> new addCategForm(current,res).show());
        //btnListCateg.addActionListener(e-> new listCategForm(current,res).show());
        btnaddserv.addActionListener(e-> new addServForm(current).show());
//btnlistserv.addActionListener(e-> new listServForm(current).show());
bstat.addActionListener(e-> new listServForm(current).show());
//rec.addActionListener(e-> new listTrifiForm(current).show());


l.addActionListener(e-> new detail());
//l.addActionListener(e-> new detail());

       //bstat.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new listCategForm(bstat) .show());
           //
          // this.current.getToolbar().addMaterialCommandToLeftSideMenu("------>Statistique services", " ".charAt(0),e -> new statistique().getF().show());
    


        addAll(btnaddserv,bstat,l);
         Menu(this);
        
    } 
     public static void Menu(Form f) {

  
        Resources res = null;
            // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des utilisateurs", " ".charAt(0),e -> new showUserForm(f).show());
              // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des pointages", " ".charAt(0),e -> new showAbsForm(f).show());
         //f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des services", " ".charAt(0),e -> new HomeServForm().show());
       // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des évenements", " ".charAt(0),e -> new listCategForm(f,res) .show());
         f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des évenements", " ".charAt(0),e ->new HomeEventFront().show() );
          f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new homeagencefront() .show());
           f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new homeAhmed().show());
            f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des services", " ".charAt(0),e -> new HomeServForm() .show());
                        f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservations", " ".charAt(0),e -> new HomeResForm() .show());
          // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new listCategForm(f,res) .show());
  // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new listCategForm(f,res) .show());
   // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservation", " ".charAt(0),e -> new listCategForm(f,res) .show());
         // f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new listCategForm(f) .show());
         //  f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new statistique().getF().show());

    }
    
}
