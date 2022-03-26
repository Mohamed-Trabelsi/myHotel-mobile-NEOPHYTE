/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.mycompany.myapp.entities.Evenements;
import com.mycompany.myapp.services.ServiceEvenements;

import java.util.ArrayList;


/**
 *
 * @author trabelssi
 */

    public class AfficherEvenement extends Form {
        
                public static Evenements evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

    static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    Button btnAjouter;

    public AfficherEvenement(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        
        addGUIs();
        addActions();
       // getToolbar().hideToolbar();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            //perevious.showBack();
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
        getToolbar().addMaterialCommandToRightBar(" ",FontImage.MATERIAL_ADD_CIRCLE,(evt1)-> {
            new AjoutEvenement(this).show();
        });

        //this.add(btnAjouter);
     
        ArrayList<Evenements> evenements = ServiceEvenements.getInstance().getAllEvenementssP();
        for (int i = 0; i < evenements.size(); i++) {
            this.add(creerEvenement(evenements.get(i)));
        }
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
            new AjoutEvenement(this).show();
        });
    }

    Component creerEvenement(Evenements event) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("evenementContainer");
Button btnlib = new Button((String) event.getLibelleE());
btnlib.addActionListener(action -> {

        });
        Label labelLibelle = new Label((String) event.getLibelleE());
        labelLibelle.setUIID("Libelle");
        
        SpanLabel spanLabelDescription = new SpanLabel(event.getDescriptionE());
        spanLabelDescription.setTextUIID("description");
        
        Label labelDateD = new Label((String) event.getDateDE());
        labelDateD.setUIID("Date debut");
        
        Label labelDateF = new Label((String) event.getDateFE());
        labelDateF.setUIID("Date fin");
        
        Label labelEspace = new Label(event.getEspaceE());
        labelEspace.setUIID("Espace");
        
       Label sp = new Label();
       sp.setText("Capacite : " + event.getCapaciteE());
       
       Label spetat = new Label();
       
       if(event.getEtatEv()==0)
       {  
       spetat.setText("Non validé");
       }
       else
       {
        spetat.setText("Validé");
       }
           
       

        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);

       Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
            
              Button btnValider = new Button();
              btnValider.setUIID("actionButton");
              FontImage.setMaterialIcon(btnValider, FontImage.MATERIAL_CHECK_CIRCLE);
              
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);
                
       
        btnModifier.addActionListener(action -> {
 evenementActuelle = event;
            new ModifierSponsor(this).show();
            ModifierEvenement.tfId.setText(String.valueOf(event.getId())); 
        new ModifierEvenement(this).show();
        });
       
 btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceEvenements.getInstance().deleteEvent(event.getId());
                evenementActuelle = null;
                dlg.dispose();
                this.removeAll();
                addGUIs();
                this.refreshTheme();
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            //Dimension pre = dlg.getContentPane().getPreferredSize();
            //dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
            dlg.show(1000, 1000, 10, 10);

        });
 btnValider.addActionListener(action -> {
      ServiceEvenements.getInstance().validerEvent(event.getId());
evenementActuelle = event;
this.removeAll();
 addGUIs();
            this.refreshTheme();
        });
 

      btnsContainer.addAll(btnModifier,btnSupprimer,btnValider);
      evenementModel.addAll(btnlib,labelLibelle, labelDateD,labelDateF, spanLabelDescription,labelEspace,sp,spetat, btnsContainer);     
        return evenementModel;


    }
   
}

