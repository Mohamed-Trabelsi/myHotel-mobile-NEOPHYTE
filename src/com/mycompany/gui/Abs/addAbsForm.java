/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.Abs;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Pointage;
import com.mycompany.entities.User;
import com.mycompany.services.ServicePointage;
import com.mycompany.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class addAbsForm extends Form {
    public addAbsForm(Form previous) {

        setTitle("Add a new Abs");
        setLayout(BoxLayout.y());
        
        Label CategorieLabel = new Label("Utilisateur");
       ServiceUser serviceTask = new ServiceUser();
        ArrayList<User> Users = serviceTask.getAllUsers();
        ComboBox userBox = new ComboBox();
        userBox.addItem("f");
        for (User u : Users) {
            
            userBox.addItem(u.getId_user());
            
            
        }
        Container categorieContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        categorieContainer.add(CategorieLabel);
        categorieContainer.add(userBox);

        TextField tfDuree = new TextField("", "Duree");
        ComboBox cbType = new ComboBox ();
        cbType.addItem ("absence justifie");
        cbType.addItem ("absence non justifie");
        
       
        Picker tfdateD = new Picker();
        tfdateD .setType(Display.PICKER_TYPE_DATE);
       tfdateD .setUIID("TextFieldBlack");
        
        
        Button btnAdd = new Button("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDuree.getText().length() == 0)  ) {
                    System.out.println("d");
                    
                  //Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                  Dialog.show("Alert", "Please fill all the fields", "OK", "CANCEL");
          
                 
                } else {
                    try {
                         Pointage p = new Pointage(tfdateD.getText(), (String) cbType.getSelectedItem()
                                 ,Integer.parseInt(tfDuree.getText())
                                 , (int) userBox.getSelectedItem());
                         System.out.println(p);
 
                        if (ServicePointage.getInstance().addAbs(p)) {
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
        addAll(tfdateD, tfDuree,cbType, categorieContainer,btnAdd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

    }
    
}
