/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Abs;

import com.codename1.components.InteractionDialog;
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
import com.mycompany.entities.Pointage;
import com.mycompany.entities.User;
import com.mycompany.gui.Abs.addAbsForm;
import static com.mycompany.gui.Abs.updateAbs.tfId;
import static com.mycompany.gui.User.showUserForm.evenementActuelle;
import com.mycompany.gui.User.updateUser;
import com.mycompany.services.ServicePointage;
import com.mycompany.services.ServiceUser;

import java.util.ArrayList;


/**
 *
 * @author dell
 */
public class showAbsForm extends Form {

    public static Pointage evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

    Button btnAjouter;

    public showAbsForm(Form perevious) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        addActions();
        // getToolbar().hideToolbar();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            perevious.showBack();
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
        getToolbar().addMaterialCommandToRightBar(" ", FontImage.MATERIAL_ADD_CIRCLE, (evt1) -> {
            new addAbsForm(this).show();
        });

        // this.add(btnAjouter);
        ArrayList<Pointage> Abs = ServicePointage.getInstance().getAllAbs();
        for (int i = 0; i < Abs.size(); i++) {
            this.add(creerAbs(Abs.get(i)));
        }
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
            new addAbsForm(this).show();
        });
    }

    private Component creerAbs(Pointage abs) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("evenementContainer");

        //Label labelDateDepart = new Label("Date Depart : " + abs.getDateDepart());
        Label labelTypePtg = new Label("Type d'absence : " + abs.getTypePtg());

        Label labelDuree = new Label("Duree : " + abs.getDuree());

        Label labelUtilisateur = new Label("utilisateur : " + abs.getUserPrenom() + " " + abs.getUserNom());

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

            /* evenementActuelle = user;
            new updateUser(this).show();
            ServiceUser.getInstance().updateUser(user);

            addGUIs();*/
           
            updateAbs.tfDureeAbs.setText(String.valueOf(abs.getDuree()));
            System.out.println("duree : "+abs.getDuree());
            updateAbs.tfId.setText(String.valueOf(abs.getIdPtg()));
            System.out.println("iddddd : "+abs.getIdPtg());

        

            new updateAbs(this).show();
        });
        btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServicePointage.getInstance().deleteAbs(abs.getIdPtg());
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

        btnsContainer.addAll(btnSupprimer, btnModifier);
        evenementModel.addAll(labelTypePtg, labelDuree, labelUtilisateur, btnsContainer);
        return evenementModel;
    }
}
