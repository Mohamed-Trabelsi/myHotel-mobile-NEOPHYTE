/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.User;

import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.User;

/**
 *
 * @author dell
 */
public class detailUser extends Form{
    public detailUser(User user, Form precedent) {
        setTitle("Profil :" + user.getPrenom()+" "+ user.getNom());
        setLayout(BoxLayout.y());
        
        
         Toolbar tb = this.getToolbar();
         tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            precedent.showBack();
        });  
         Label labelNom = new Label("Nom : " + user.getNom());

        Label labelPrenom = new Label("Prenom : " + user.getPrenom());

        Label labelAge = new Label("Age : " + user.getAge());
        //labelAge.setUIID("Age");

        Label labelCin = new Label("CIN : " + user.getCin());

        Label labelTelUser = new Label("N°tel : " + user.getTel_user());

        Label labelEmailUser = new Label("Email : " + user.getEmail_user());

        Label labelRole = new Label("Role : " + user.getRole());

        Label labelGenre = new Label("Genre : " + user.getGenre());
        
        addAll(labelNom, labelPrenom, labelAge, labelCin, labelTelUser,
                labelEmailUser, labelRole, labelGenre);
         
    }
    
    
}
