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
import static com.mycompany.gui.HomeForm.Menu;

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
    HomeEventFront(AfficherEvenement aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
