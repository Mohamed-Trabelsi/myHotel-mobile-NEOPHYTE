/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
/**
 *
 * @author asus
 */



public class home extends Form{
     Form current;
     Form a;
   
    private Resources theme;

    public home() {
        
        
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
       btnMap.addActionListener(e->new MapForm());
       statProduit.addActionListener(e-> new StatistiquePieForm(theme).show()); 
       
       //btnAdd.addActionListener(e-> new AjoutProduit(current).show());
        addAll(btnListEvenements,btnListSponsors,btnMap,statProduit);
    }
    
}
