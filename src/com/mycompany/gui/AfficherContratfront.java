/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Contrats;
import com.mycompany.services.ServiceContrats;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author hurb
 */
public class AfficherContratfront extends Form{
    public static Contrats evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

   
    Button btnAjouter;

    public AfficherContratfront(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        addActions();
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
          previous.showBack();
        });
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }
     private void addGUIs() {
        btnAjouter = new Button("Ajouter");
        btnAjouter.setUIID("newButton");

        this.add(btnAjouter);
     
        ArrayList<Contrats> evenements = ServiceContrats.getInstance().getAllEvenementssP();
        for (int i = 0; i < evenements.size(); i++) {
            this.add(creerEvenement(evenements.get(i)));
        }
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
                new AjoutContrat(this).show();
        });
    }
    
    private Component creerEvenement(Contrats event) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("contratContainer");
        
        SpanLabel labelLibelle = new SpanLabel("titre : " + event.getTitre());
        SpanLabel spanLabelDescription = new SpanLabel("description : " + event.getContenu());
        SpanLabel labelCapacite = new SpanLabel("prix : " + event.getPrix());
        
        SpanLabel sp3 = new SpanLabel("date de debut : " + event.getDated());
        SpanLabel sp4 = new SpanLabel("date de fin : " + event.getDatef());
        SpanLabel sp5 = new SpanLabel("agence : " + event.getAgence());
        
        
        
       // Label labelCapacite = new Label(Int.parseInt (event.getCapaciteE().toString()));
        //labelCapacite.setUIID("Capacite");

        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);

       Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);
       
     
      evenementModel.addAll(labelLibelle,  spanLabelDescription,labelCapacite,sp5,sp3,sp4);     
        return evenementModel;


    }
}
