/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.Abs.showAbsForm;
import com.mycompany.gui.User.showUserForm;

/**
 *
 * @author trabelssi
 */
public class HomeEvenement extends Form{
    Form current;

   public HomeEvenement() {
       
        current=this; 
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        //Button btnAdd = new Button("add");
        Button btnListEvenements = new Button("Évènements");
        Button btnListSponsors = new Button("Sponsors");
        Button btnListEngagement = new Button("Engagements");
        
       
       btnListSponsors.addActionListener(e-> new AfficherSponsor(current).show());
       btnListEvenements.addActionListener(e-> new AfficherEvenement(current).show());
       
       btnListEngagement.addActionListener(e-> new AfficherEngagement(current).show());
       //btnAdd.addActionListener(e-> new AjoutEvenement(current).show());
        addAll(btnListEvenements,btnListSponsors,btnListEngagement);
        Menu(this);
    }
  public static void Menu(Form f) {

  
        Resources res = null;
             f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des utilisateurs", " ".charAt(0),e -> new showUserForm(f).show());
               f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des pointages", " ".charAt(0),e -> new showAbsForm(f).show());
                 f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des évenements", " ".charAt(0),e ->new HomeEvenement().show() );
      
          f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new homeagence() .show());
         f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new homeAhmedBack().show());
        f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des services", " ".charAt(0),e -> new HomeServBack() .show());
      f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique catégorie", " ".charAt(0),e -> new statistique().getF().show());
                  f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservations", " ".charAt(0),e -> new HomeResForm() .show());
          // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new listCategForm(f,res) .show());
  // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new listCategForm(f,res) .show());
   // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservation", " ".charAt(0),e -> new listCategForm(f,res) .show());
         // f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new listCategForm(f) .show());
         //  f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new statistique().getF().show());

    }

}
