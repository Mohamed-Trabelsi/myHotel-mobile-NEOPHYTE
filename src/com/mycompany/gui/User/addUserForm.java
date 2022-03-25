/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.User;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author dell
 */
public class addUserForm extends Form {

    public addUserForm(Form previous) {

        setTitle("Add a new User");
        setLayout(BoxLayout.y());

        TextField tfNom = new TextField("", "Nom");
        TextField tfPrenom = new TextField("", "Prenom");
        TextField tfAge = new TextField("", "Age");
        TextField tfCin = new TextField("", "CIN");
        TextField tfTelUser = new TextField("", "N°Télephone");
        TextField tfEmailUser = new TextField("", "E-mail");
        TextField tfPassword = new TextField("", "Password");
        tfPassword.setConstraint(TextField.PASSWORD);
        //TextField tfRole = new TextField("", "role");
        ComboBox cbRole = new ComboBox ();
        cbRole.addItem ("Admin");
        cbRole.addItem ("RH");
        cbRole.addItem ("Staff");
        //TextField tfGenre = new TextField("", "Genre");
        ComboBox cbGenre = new ComboBox ();
        cbGenre.addItem ("Homme");
        cbGenre.addItem ("Femme");
        cbGenre.addItem ("Autres");
        
        Button btnAdd = new Button("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfPrenom.getText().length() == 0) 
                     || (tfEmailUser.getText().length() == 0) || (tfPassword.getText().length() == 0)) {
                    System.out.println("d");
                    
                  //Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                  Dialog.show("Alert", "Please fill all the fields", "OK", "CANCEL");
          
                 
                } else {
                    try {
                         User u = new User(tfNom.getText(),tfPrenom.getText(),Integer.parseInt(tfAge.getText())
                                 ,Integer.parseInt(tfCin.getText()),Integer.parseInt(tfTelUser.getText())
                                 ,tfEmailUser.getText(),tfPassword.getText(), (String) cbRole.getSelectedItem(), (String) cbGenre.getSelectedItem());
 
                        if (ServiceUser.getInstance().AddUser(u)) {
                                 //Dialog.show("Success", "Connection accepted", new Command("OK"));
                                 Dialog.show("Success", "Ajouté avec succès", "OK", "CANCEL");
                            previous.showBack();
                        } else {
                            //Dialog.show("ERROR", "Server error", new Command("OK"));
                            Dialog.show("Alerte", "Server error", "OK", "CANCEL");
                        }
                    } catch (NumberFormatException e) {
                       // Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });
        addAll(tfNom, tfPrenom,cbGenre, tfAge,tfCin, tfTelUser, tfEmailUser,tfPassword, cbRole,btnAdd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

    }

}
