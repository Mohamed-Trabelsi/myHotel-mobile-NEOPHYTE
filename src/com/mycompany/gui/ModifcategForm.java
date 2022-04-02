/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.io.Preferences;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.categ;
import com.mycompany.services.ServiceCateg;
/**
 *
 * @author wiem
 */
public class ModifcategForm extends Form{
    static TextField tfName = new TextField();


    static TextField tfId = new TextField();
    categ current;

    public ModifcategForm(Form previous) {

        setTitle("Modifier catégorie");
        setLayout(BoxLayout.y());

        Button btnValider = new Button("Modifier catégorie");
       btnValider.setUIID("SkipButton");
        addAll(tfName, btnValider);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                     categ f = new categ(tfName.getText());
                        if (ServiceCateg.getInstance().modifcateg(f, Integer.parseInt(tfId.getText()))) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            Preferences.clearAll();
                              refreshTheme();
                             
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente

    }
    
}
