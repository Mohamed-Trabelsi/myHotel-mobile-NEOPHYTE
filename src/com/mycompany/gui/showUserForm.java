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
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class showUserForm extends Form {

    public static User evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

    Button btnAjouter;

    public showUserForm(Form perevious) {
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
        getToolbar().addMaterialCommandToRightBar(" ",FontImage.MATERIAL_ADD_CIRCLE,(evt1)-> {
            new addUserForm(this).show();
        });
        
      
       // this.add(btnAjouter);

        ArrayList<User> users = ServiceUser.getInstance().getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            this.add(creerUser(users.get(i)));
        }
    }
     
     private void addActions() {
        btnAjouter.addActionListener(action -> {
            new addUserForm(this).show();
        });
    }
     
     private Component creerUser(User user) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("evenementContainer");

        Label labelNom = new Label("Nom : "+user.getNom());
        
        Label labelPrenom = new Label("Prenom : "+user.getPrenom());
 
        Label labelAge = new Label("Age : "+user.getAge());
        //labelAge.setUIID("Age");
        
        Label labelCin = new Label("CIN : "+user.getCin());
        
        Label labelTelUser = new Label("N°tel : "+user.getTel_user()); 
        
        Label labelEmailUser = new Label("Email : "+user.getEmail_user());
        
        Label labelRole = new Label("Role : "+user.getRole());
        
        Label labelGenre = new Label("Genre : "+user.getGenre());

        
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
        updateUser.tfId.setText(String.valueOf(user.getId_user()));
        new updateUser(this).show();
        });

        btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceUser.getInstance().deleteUser(user.getId_user());
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

        btnsContainer.addAll( btnSupprimer,btnModifier);
        evenementModel.addAll(labelNom, labelPrenom,labelAge,labelCin ,labelTelUser
                ,labelEmailUser, labelRole, labelGenre, btnsContainer);
        return evenementModel;

    }

}
