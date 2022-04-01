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
public class homeagence extends Form{
     Form a;
   
    private Resources theme;

    public homeagence() {
        
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
        statAgence.addActionListener(e-> new StatAgence(theme).show()); 
       btnListSponsors.addActionListener(e-> new AfficherAgence(a).show());
        btnListEvenements.addActionListener(e-> new AfficherContrat(a).show());
        
        addAll(btnListEvenements,btnListSponsors,statAgence,mapAgence,imagea);
    }
    
}
