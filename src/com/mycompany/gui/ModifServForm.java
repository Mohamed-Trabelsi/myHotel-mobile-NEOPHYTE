/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
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
import com.mycompany.entities.categ;
import com.mycompany.entities.service;
//import static com.serv.gui.ModifcategForm.tfId;
import com.mycompany.services.ServiceCateg;
import com.mycompany.services.ServiceServ;
import java.util.ArrayList;

/**
 *
 * @author wiem
 */
public class ModifServForm extends Form {
    static TextField tfId = new TextField();
    service current;
 
    public ModifServForm (Form previous) {

          setTitle("Modifier le service");
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
       //tfdb .setUIID("TextFieldBlack");
 
        Picker tfdf = new Picker();
        tfdf.setType(Display.PICKER_TYPE_DATE_AND_TIME);
       // tfdf.setUIID("TextFieldBlack");
 
       
        
     ;
         Button btnValider = new Button("Modifier le service");
          btnValider.setUIID("SkipButton");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              
                    try {
                 
                       service t = new service(tfdb.getText(),tfdf.getText(),categorieBox.getSelectedItem().toString());
                       if (ServiceServ.getInstance().modifcateg(t, Integer.parseInt(tfId.getText())))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
               }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                    
                }
                
                
            

          

           
        });
           Form f = new Form("", BoxLayout.yBottom());      
Button showNotification = new Button("Besoin d'aide ?");



showNotification.addActionListener(e -> ToastBar.showMessage("veuillez modifier les dates selon le planning d'hotel .", FontImage.MATERIAL_INFO));



        add(showNotification);
  

f.show();
        
        addAll(categorieContainer, tfdb, tfdf,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }  
}
