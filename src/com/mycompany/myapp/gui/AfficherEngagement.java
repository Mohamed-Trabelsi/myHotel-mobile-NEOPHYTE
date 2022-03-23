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
import com.mycompany.myapp.entities.Engagements;
import com.mycompany.myapp.entities.Evenements;
import com.mycompany.myapp.entities.Sponsors;
import com.mycompany.myapp.services.ServiceEngagements;
import java.util.ArrayList;

/**
 *
 * @author trabelssi
 */
public class AfficherEngagement extends Form {
    
     public static Engagements evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

   
    Button btnAjouter;

    public AfficherEngagement(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        //addActions();
       // getToolbar().hideToolbar();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            //perevious.showBack();
        });
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        //addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        btnAjouter = new Button("Ajouter");
        btnAjouter.setUIID("newButton");
        getToolbar().addMaterialCommandToRightBar(" ",FontImage.MATERIAL_ADD_CIRCLE,(evt1)-> {
            //new AjoutEvenement(this).show();
        });

        //this.add(btnAjouter);
     
        ArrayList<Engagements> en = ServiceEngagements.getInstance().getEngagement();
        for (int i = 0; i < en.size(); i++) {
            this.add(creerEngagement(en.get(i)));
        }
    }

    /*private void addActions() {
         btnAjouter.addActionListener(action -> {
            new AjoutEvenement(this).show();
        });
    }*/

    private Component creerEngagement(Engagements eng) {
        
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("engagementContainer");
       /*Evenements e = new Evenements();
       Sponsors s = new Sponsors();*/
       SpanLabel spEvent = new SpanLabel();
       spEvent.setText("Evenement : " + eng.getEvent());
       
       SpanLabel spSponsor = new SpanLabel();
       spSponsor.setText("Sponsor : " + eng.getSponsor());

        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);

       Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);
       
        /*btnModifier.addActionListener(action -> {

           evenementActuelle = sponsor;
            new ModifierSponsor(this).show();
            ServiceSponsors.getInstance().updateSponsor(sponsor);

            addGUIs();
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

        });*/

        btnsContainer.addAll(btnModifier,btnSupprimer);
      evenementModel.addAll(spEvent, spSponsor,btnsContainer);     
        return evenementModel;


    }
}
