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
import com.mycompany.entities.Agences;
import com.mycompany.services.ServiceAgences;
import java.util.ArrayList;
public class AfficherAgencefront extends Form {
     public static Agences evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

   
    Button btnAjouter;
    public AfficherAgencefront(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        addActions();
        //getToolbar().hideToolbar();
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
     
        ArrayList<Agences> evenements = ServiceAgences.getInstance().getAllEvenementssP();
        for (int i = 0; i < evenements.size(); i++) {
            this.add(creerEvenement(evenements.get(i)));
        }
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
            new AjoutAgence(this).show();
        });
    }
    private Component creerEvenement(Agences event) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("agenceContainer");

        Label labelLibelle = new Label((String) event.getNom());
        labelLibelle.setUIID("Nom");
        
        
        Label labelDateD = new Label((String) event.getEmail());
        labelDateD.setUIID("email");
        
        Label labelDateF = new Label((int)event.getNum()+"");
        labelDateF.setUIID("num");
        
        
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
       
        btnModifier.addActionListener(action -> {

           evenementActuelle = event;
            new ModifierAgence(this).show();
            ModifierAgence.tfId.setText(String.valueOf(event.getId())); 
        new ModifierAgence(this).show(); 
        });
   

        btnsContainer.addAll(btnModifier);
      evenementModel.addAll(labelLibelle, labelDateD,labelDateF, btnsContainer);     
        return evenementModel;


    }
        
}
