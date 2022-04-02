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
import com.mycompany.entities.Produits;
import com.mycompany.services.ServiceProduits;
import java.util.ArrayList;
/**
 *
 * @author asus
 */
public class AfficherProduit extends Form {
     public static Produits evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

   
    Button btnAjouter;
    public AfficherProduit(Form previous) {
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
     
        ArrayList<Produits> evenements = ServiceProduits.getInstance().getAllEvenementssP();
        for (int i = 0; i < evenements.size(); i++) {
            this.add(creerEvenement(evenements.get(i)));
        }
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
            new AjoutProduit(this).show();
        });
    }
    private Component creerEvenement(Produits event) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("produitContainer");

        Label labelLibelle = new Label((String) event.getLibelle());
        labelLibelle.setUIID("libelle");
        
        
        Label labelDateD = new Label((String) event.getType());
        labelDateD.setUIID("type");
        
        Label labelDateF = new Label((int)event.getQuantite()+"");
        labelDateF.setUIID("quantite");
        
        
       // Label labelCapacite = new Label(Int.parseInt (event.getCapaciteE().toString()));
        //labelCapacite.setUIID("Capacite");

        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);

              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
                FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
              
       
       
    btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceProduits.getInstance().deleteEvent(event.getId());
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

        btnsContainer.addAll(btnSupprimer);
      evenementModel.addAll(labelLibelle, labelDateD,labelDateF, btnsContainer);     
        return evenementModel;


    }
        
}
