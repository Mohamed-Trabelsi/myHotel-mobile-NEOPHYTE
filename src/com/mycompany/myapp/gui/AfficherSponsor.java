/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.mycompany.myapp.entities.Sponsors;
import com.mycompany.myapp.services.ServiceSponsors;
import java.util.ArrayList;

/**
 *
 * @author trabelssi
 */
public class AfficherSponsor extends Form{

                     public static Sponsors evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

   
    Button btnAjouter;

    public AfficherSponsor(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        addActions();
       // getToolbar().hideToolbar();
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
        getToolbar().addMaterialCommandToRightBar(" ",FontImage.MATERIAL_ADD_CIRCLE,(evt1)-> {
            new AjoutSponsor(this).show();
        });

        //this.add(btnAjouter);
     
        ArrayList<Sponsors> evenements = ServiceSponsors.getInstance().getAllSponsor();
        for (int i = 0; i < evenements.size(); i++) {
            this.add(creerEvenement(evenements.get(i)));
        }
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
            new AjoutSponsor(this).show();
        });
    }

    private Component creerEvenement(Sponsors sponsor) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("evenementContainer");

        Label labelNom = new Label((String) sponsor.getNomS());
        labelNom.setUIID("Nom");
        
        Label LabelAdresse = new Label((String)sponsor.getAdresseS());
        LabelAdresse.setUIID("Adresse");
        
        SpanLabel sp = new SpanLabel();
       sp.setText("Numero : " + sponsor.getTelS());

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

           evenementActuelle = sponsor;
            new ModifierSponsor(this).show();
            /*ServiceSponsors.getInstance().updateSponsor(sponsor);

            addGUIs();*/
            ModifierSponsor.tfId.setText(String.valueOf(sponsor.getId())); 
        new ModifierSponsor(this).show();             
        });
       
 btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceSponsors.getInstance().deleteSponsor(sponsor.getId());
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
      evenementModel.addAll(labelNom, LabelAdresse,sp,btnsContainer);     
        return evenementModel;


    }
}
