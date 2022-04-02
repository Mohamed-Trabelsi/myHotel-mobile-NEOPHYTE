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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dell
 */
public class updateAbs extends Form {

    Object L;
    Map<String, Object> userll = new HashMap<>();
        
    static TextField tfDureeAbs = new TextField();
    static TextField tfId = new TextField();
    static ComboBox cbTypeAbs = new ComboBox();

    public updateAbs(Form previous) {
        setTitle("Modifier Abs");
        setLayout(BoxLayout.y());

        TextField tfDuree  = tfDureeAbs;
        ComboBox cbType = cbTypeAbs;

         Picker tfdateD = new Picker();
        tfdateD.setType(Display.PICKER_TYPE_DATE);
        tfdateD.setUIID("TextFieldBlack");
        
        //TextField tfRole = new TextField("", "role");
        cbType.addItem("absence justifie");
        cbType.addItem("absence non justifie");

        //TextField tfGenre = new TextField("", "Genre");
        Label UserLabel = new Label("Utilisateur");
        ServiceUser serviceTask = new ServiceUser();
        ArrayList<User> Users = serviceTask.getAllUsers();
        ComboBox userBox = new ComboBox();

        for (User u : Users) {
            userll.put(u.getNom(), u.getId_user());
            userBox.addItem(u.getNom());
        }
        Label userEror = new Label("*");
        userEror.getAllStyles().setFgColor(0xff0000);
        userEror.setVisible(false);
        Container userContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        userContainer.add(UserLabel);
        userContainer.add(userBox);
        userContainer.add(userEror);

        Button btnUpdate = new Button("Modifier");
         btnUpdate.setUIID("SkipButton");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDuree.getText().length() == 0)){
                    //Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    Dialog.show("Alert", "Please fill all the fields", "OK", "CANCEL");
                } else {
                    try {
                       L = userll.get(userBox.getSelectedItem().toString());
                        System.out.println("userid "+L);
                        float duree = Float.parseFloat(L.toString());
                        int idu = (int) duree;
                        Pointage p = new Pointage(tfdateD.getText(), (String) cbType.getSelectedItem(),
                                Integer.parseInt(tfDuree.getText()),
                                idu);
                        System.out.println("ABS : "+p);
                        
                        if (ServicePointage.getInstance().updateAbs(p, Integer.parseInt(tfId.getText()))) {
                              
                            Dialog.show("Success", "Modifié avec succès", "OK", "CANCEL");
                            previous.showBack();
                        } else {
                            //Dialog.show("ERROR", "Server error", new Command("OK"));
                            //Dialog.show("Alerte", "Server error", "OK", "CANCEL");
                            System.out.println("ok");
                        }
                    } catch (NumberFormatException e) {
                        //Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        System.out.println("ok2");
                    }
                }
            }
        });
         addAll(tfdateD, tfDuree, cbType, userContainer, btnUpdate);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
    }

}
