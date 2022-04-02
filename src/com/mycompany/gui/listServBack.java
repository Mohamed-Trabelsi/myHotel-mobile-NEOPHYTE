/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.categ;
import com.mycompany.entities.service;
import com.mycompany.services.ServiceServ;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author wiem
 */
public class listServBack extends Form {
      public ArrayList<service> categs;
    Form current;
    public listServBack(Form previous) {
        
        setTitle("Liste des services"); 
        categs = ServiceServ.getInstance().getAllCategs();
        for (service obj : categs) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
                    MultiButton multiButton = new MultiButton();  
          multiButton.setTextLine1("catégorie :" +obj.getId_categ());
              multiButton.setTextLine2("id :" +obj.getIdServ());
                multiButton.setTextLine3("date ouerture :" +obj.getDb());
                      multiButton.setTextLine4("date fermeture :" +obj.getDf());
           /* Button Delete = new Button("D");
            Button Modif = new Button("M");*/
            
             /*   SpanLabel sp2 = new SpanLabel ("id : " + obj.getIdServ());
                   SpanLabel a = new SpanLabel("catégorie du service : " + obj.getId_categ());
                  SpanLabel sp3 = new SpanLabel("date d'ouverture : " + obj.getDb());
                   SpanLabel sp4 = new SpanLabel("date de fermeture : " + obj.getDf());
    

            addAll(sp2,a,sp3,sp4);*/
;  Toolbar tb = new Toolbar(true);
        setToolbar(tb);
              Button btnSupprimer = new Button();
              btnSupprimer.setUIID("actionButton");
            
              Button Modif = new Button();
              Modif.setUIID("actionButton");
 multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_ADS_CLICK, "", 5.0f));
    FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(Modif, FontImage.MATERIAL_UPDATE);
                        Modif.addActionListener(action -> {
                             ModifServForm.tfId.setText(String.valueOf(obj.getIdServ()));
            
        new  ModifServForm(this).show();

        });
              
                             btnSupprimer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       
                        ServiceServ.getInstance().deleteCateg(obj.getIdServ()) ;
                      new listServBack(previous).show();
                            //LocalNotification n = new LocalNotification();
   
                      
                    }

           
                });
                                  getToolbar().addCommandToOverflowMenu("nombre des étoiles selon les services", null, (evt) -> {
         new nbServ(current).show();
        });
        getToolbar().addCommandToOverflowMenu("recherche des services", null, (evt) -> {
         new listTrifiForm(current).show();
        });
         // multiButton.addActionListener(l -> new ModifServForm(previous).show());
           addAll(multiButton,Modif,btnSupprimer);

           // addAll(spTitle);
        }
        

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
    
}
