/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.User;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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
public class showNUser extends Form{
    public static User evenementActuelle = null;
        User user;
    public static Resources theme = UIManager.initFirstTheme("/theme");
    
     public showNUser(Form perevious) {
         super("Utilisateurs Non Approuvés", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
       // getToolbar().hideToolbar();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            perevious.showBack();
        });  
    }
     
      public void refresh() {
        this.removeAll();
        addGUIs();  
        this.refreshTheme();
    }
      
      private void addGUIs() {
  
        ArrayList<User> users = ServiceUser.getInstance().getNUsers();
        for (int i = 0; i < users.size(); i++) {
            this.add(creerUser(users.get(i)));
        }
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
        
         Button btnConf = new Button();
        btnConf.setUIID("actionButton");
        FontImage.setMaterialIcon(btnConf, FontImage.MATERIAL_CHECK);
        
        btnConf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setMessage("User Confirmé");
                status.show();

                
                ServiceUser su = new ServiceUser();
                su.getInstance().Confirmer(user.getId_user());
                refresh();
          
                

            }
        });
        
        
        btnsContainer.addAll( btnConf);
         evenementModel.addAll(labelNom, labelPrenom,labelAge,labelCin ,labelTelUser
                ,labelEmailUser, labelRole, labelGenre,btnsContainer);
        return evenementModel;
        
       }
      
   
}
