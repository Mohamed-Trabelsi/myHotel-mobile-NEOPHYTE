/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serv.gui;
import com.codename1.messaging.Message;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.serv.entities.categ;
import com.serv.entities.service;
import com.serv.services.ServiceCateg;
import com.serv.services.ServiceServ;
import java.util.ArrayList;

import java.util.Date;


/**
 *
 * @author wiem
 */
public class addServForm extends Form{
     public addServForm(Form previous) {
        setTitle("Ajouter un nouveau service");
        setLayout(BoxLayout.y());
    
      Label CategorieLabel = new Label("Categorie");
       ServiceCateg serviceTask = new ServiceCateg();
        ArrayList<categ> lescategories = serviceTask.getAllCategs();
        ComboBox categorieBox = new ComboBox();
        for (categ temp : lescategories) {
            categorieBox.addItem(temp.getId()+"-"+temp.getName());
            
        }
         Label categorieEror = new Label("*");
        categorieEror.getAllStyles().setFgColor(0xff0000);
        categorieEror.setVisible(false);
        Container categorieContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        categorieContainer.add(CategorieLabel);
        categorieContainer.add(categorieBox);
        categorieContainer.add(categorieEror);
         service tfcateg = null;
 
     
      Picker tfdb = new Picker();
        tfdb .setType(Display.PICKER_TYPE_DATE_AND_TIME);
       tfdb .setUIID("TextFieldBlack");
 
        Picker tfdf = new Picker();
        tfdf.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        tfdf.setUIID("TextFieldBlack");
 
       
        
     ;
        Button btnValider = new Button("Ajouter service");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                    try {
                 
                       service t = new service(tfdb.getText(),tfdf.getText(),categorieBox.getSelectedItem().toString());
                     if( ServiceServ.getInstance().addCateg(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
               }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                    
                }
                
                
            

          

           
        });
        
        addAll(categorieContainer, tfdb, tfdf,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }

 
    
}
