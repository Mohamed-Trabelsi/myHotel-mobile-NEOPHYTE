/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

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
public class updateUser extends Form {
    static TextField tfId = new TextField();

    public updateUser(Form previous) {
        setTitle("Modifier User");
        setLayout(BoxLayout.y());

         TextField tfNom = new TextField("", "nom");
        TextField tfPrenom = new TextField("", "prenom");
        TextField tfAge = new TextField("", "age");
        TextField tfCin = new TextField("", "cin");
        TextField tfTelUser = new TextField("", "telUser");
        TextField tfEmailUser = new TextField("", "emailUser");
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
        
        Button btnUpdate = new Button("Modifier");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length() == 0) || (tfPrenom.getText().length() == 0) 
                     || (tfEmailUser.getText().length() == 0)) {
                    //Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    Dialog.show("Alert", "Please fill all the fields", "OK", "CANCEL");
                } else {
                    try {
                       User u = new User(tfNom.getText(),tfPrenom.getText(),Integer.parseInt(tfAge.getText())
                                 ,Integer.parseInt(tfCin.getText()),Integer.parseInt(tfTelUser.getText())
                                 ,tfEmailUser.getText(), (String) cbRole.getSelectedItem(), (String) cbGenre.getSelectedItem());
                       u.setPassword("omar");
                        System.out.println(u);

                        if (ServiceUser.getInstance().updateUser(u,Integer.parseInt(tfId.getText()))) {
                           // Dialog.show("Success", "Connection accepted", new Command("OK"));
                           Dialog.show("Success", "Modifié avec succès", "OK", "CANCEL");
                            previous.showBack();
                        } else {
                            //Dialog.show("ERROR", "Server error", new Command("OK"));
                            Dialog.show("Alerte", "Server error", "OK", "CANCEL");
                        }
                    } catch (NumberFormatException e) {
                        //Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });
        addAll(tfNom, tfPrenom, tfAge,cbGenre,tfCin, tfTelUser, tfEmailUser, cbRole, btnUpdate);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
    }

}
