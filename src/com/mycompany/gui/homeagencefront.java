/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author hurb
 */
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

public class homeagencefront extends Form{
     Form a;
   
    private Resources theme;

    public homeagencefront() {
        
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
    
        a=this; 
       
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        //Button btnAdd = new Button("add");
        Button btnListEvenements = new Button("Liste des contrats");
        Button btnListSponsors = new Button("Liste des Agences");
         Button statAgence = new Button("stat des contrats");
          Button mapAgence = new Button("map");
           Button imagea = new Button("capture");
        
           imagea.addActionListener(e-> new imagea());
          mapAgence.addActionListener(e-> new MapAgence()); 
        statAgence.addActionListener(e-> new StatAgence()); 
       btnListSponsors.addActionListener(e-> new AfficherAgencefront(a).show());
        btnListEvenements.addActionListener(e-> new AfficherContratfront(a).show());
        
        addAll(btnListEvenements,btnListSponsors,statAgence,mapAgence,imagea);
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
          // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des produits", " ".charAt(0),e -> new listCategForm(f,res) .show());
  // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des agences", " ".charAt(0),e -> new listCategForm(f,res) .show());
   // f.getToolbar().addMaterialCommandToLeftSideMenu("Gestion des réservation", " ".charAt(0),e -> new listCategForm(f,res) .show());
         // f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new listCategForm(f) .show());
         //  f.getToolbar().addMaterialCommandToLeftSideMenu("Statistique services", " ".charAt(0),e -> new statistique().getF().show());

    }
    
    
}

