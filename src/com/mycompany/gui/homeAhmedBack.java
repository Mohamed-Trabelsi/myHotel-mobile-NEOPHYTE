/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.Abs.showAbsForm;
import com.mycompany.gui.User.showUserForm;
/**
 *
 * @author asus
 */



public class homeAhmedBack extends Form{
     Form current;
     Form a;
   
    private Resources theme;

    public homeAhmedBack() {
        
        
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        }); 
       
        current=this; 
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        Button btnListSponsors = new Button("Liste des Produits");
        Button btnListEvenements = new Button("Liste des Fournisseurs");
        Button btnMap = new Button("Show Map");
        Button statProduit = new Button("stat des produits");
        
       
       btnListSponsors.addActionListener(e-> new AfficherProduit(current).show());
       btnListEvenements.addActionListener(e-> new AfficherFournisseur(current).show());
       btnMap.addActionListener(e->new MapAgence());
       statProduit.addActionListener(e-> new StatistiquePieForm(theme).show()); 
       
       //btnAdd.addActionListener(e-> new AjoutProduit(current).show());
        addAll(btnListSponsors,btnMap,statProduit,btnListEvenements);
        Menu(this);
    }
     public static void Menu(Form f) {

  
        Resources res = null;
             f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des utilisateurs", " ".charAt(0),e -> new showUserForm(f).show());
               f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des pointages", " ".charAt(0),e -> new showAbsForm(f).show());
                 f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des évenements", " ".charAt(0),e ->new HomeEvenement().show() );
      
          f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new homeagence() .show());
         f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new homeAhmed().show());
        f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des services", " ".charAt(0),e -> new HomeServBack() .show());
      f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique catégorie", " ".charAt(0),e -> new statistique().getF().show());
          // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new listCategForm(f,res) .show());
  // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new listCategForm(f,res) .show());
   // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservation", " ".charAt(0),e -> new listCategForm(f,res) .show());
         // f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new listCategForm(f) .show());
         //  f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new statistique().getF().show());

    }
    
}
