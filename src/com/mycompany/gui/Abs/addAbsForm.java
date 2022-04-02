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
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Pointage;
import com.mycompany.entities.User;
import com.mycompany.services.ServicePointage;
import com.mycompany.services.ServiceUser;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dell
 */
public class addAbsForm extends Form {

    Object L;
    Map<String, Object> userll = new HashMap<>();

    public addAbsForm(Form previous) {

        setTitle("Add a new Abs");
        setLayout(BoxLayout.y());

        Label UserLabel = new Label("Utilisateur");
        ServiceUser serviceTask = new ServiceUser();
        ArrayList<User> Users = serviceTask.getAllUsers();
        ComboBox userBox = new ComboBox();

        for (User u : Users) {
            userll.put(u.getNom(), u.getId_user());
            userBox.addItem(u.getNom());
        }
        System.out.println("uuuuuuuu" + userll);
        Label userEror = new Label("*");
        userEror.getAllStyles().setFgColor(0xff0000);
        userEror.setVisible(false);
        Container userContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        userContainer.add(UserLabel);
        userContainer.add(userBox);
        userContainer.add(userEror);

        TextField tfDuree = new TextField("", "Duree");
        ComboBox cbType = new ComboBox();
        cbType.addItem("absence justifie");
        cbType.addItem("absence non justifie");

        Picker tfdateD = new Picker();
        tfdateD.setType(Display.PICKER_TYPE_DATE);
        tfdateD.setUIID("TextFieldBlack");

        Button btnAdd = new Button("Add");
         btnAdd.setUIID("SkipButton");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((tfDuree.getText().length() == 0)) {
                    System.out.println("d");

                    //Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    Dialog.show("Alert", "Please fill all the fields", "OK", "CANCEL");

                } else {
                    try {
                        L = userll.get(userBox.getSelectedItem().toString());
                        System.out.println(L);
                        float duree = Float.parseFloat(L.toString());
                        int idu = (int) duree;
                        Pointage p = new Pointage(tfdateD.getText(), (String) cbType.getSelectedItem(),
                                Integer.parseInt(tfDuree.getText()),
                                idu);
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
        addAll(tfdateD, tfDuree, cbType, userContainer, btnAdd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

    }

}
