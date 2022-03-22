/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serv.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

import java.util.ArrayList;

import com.codename1.ui.Button;
import com.codename1.ui.layouts.BoxLayout;
import com.serv.entities.service;
import com.serv.services.ServiceServ;
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
           /* Button Delete = new Button("D");
            Button Modif = new Button("M");*/
            
                SpanLabel sp2 = new SpanLabel ("id : " + obj.getIdServ());
                   SpanLabel a = new SpanLabel("catégorie du service : " + obj.getId_categ());
                  SpanLabel sp3 = new SpanLabel("date d'ouverture : " + obj.getDb());
                   SpanLabel sp4 = new SpanLabel("date de fermeture : " + obj.getDf());
    

            addAll(spTitle,sp2,a,sp3,sp4);
        }
        

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    
}
