/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

import java.util.ArrayList;

import com.codename1.ui.Button;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.service;
import com.mycompany.services.ServiceServ;
/**
 *
 * @author wiem
 */
public class listServForm extends Form {
    public ArrayList<service> categs;
    Form current;
    public listServForm(Form previous) {
        
        setTitle("Liste des services"); 
        categs = ServiceServ.getInstance().getAllCategs();
        for (service obj : categs) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
                    MultiButton multiButton = new MultiButton();  
          multiButton.setTextLine1("catégorie :" +obj.getId_categ());
           /* Button Delete = new Button("D");
            Button Modif = new Button("M");*/
            
          /*      SpanLabel sp2 = new SpanLabel ("id : " + obj.getIdServ());
                   SpanLabel a = new SpanLabel("catégorie du service : " + obj.getId_categ());
                  SpanLabel sp3 = new SpanLabel("date d'ouverture : " + obj.getDb());
                   SpanLabel sp4 = new SpanLabel("date de fermeture : " + obj.getDf());
    

            addAll(spTitle,sp2,a,sp3,sp4);
*/   multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
           multiButton.addActionListener(l -> new OneservForm(previous, obj).show());
            add(multiButton);

            addAll(spTitle);
        }
        

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    
}
