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
 * @author asus
 */
public class AfficherContrat extends Form{
    public static Contrats evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

   
    Button btnAjouter;

    public AfficherContrat(Form previous) {
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
       
        btnModifier.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog(" etes vous sure ");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment changer l'etat du contrat?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                try {
                    ServiceContrats.getInstance().validercontrat(event.getId());
                } catch (IOException ex) {
                   
                }
                
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
       
 btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceContrats.getInstance().deleteEvent(event.getId());
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

        btnsContainer.addAll(btnModifier,btnSupprimer);
      evenementModel.addAll(labelLibelle,  spanLabelDescription,labelCapacite,sp5,sp3,sp4,btnsContainer);     
        return evenementModel;


    }
}
