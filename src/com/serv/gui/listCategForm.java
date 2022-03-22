/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serv.gui;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.serv.entities.categ;
import com.serv.services.ServiceCateg;
import java.util.ArrayList;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
/**
 *
 * @author wiem
 */
public class listCategForm extends Form {
    public ArrayList<categ> categs;
    Form current;
    public listCategForm(Form previous) {
        setTitle("Liste des catégories"); 
        Style s = UIManager.getInstance().getComponentStyle("Title");
     
   

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
           ActionListener callback = null;
      tb.addSearchCommand(e->
            new HomeForm().showBack(), CENTER);
     

        categs = ServiceCateg.getInstance().getAllCategs();
        for (categ obj : categs) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            
             SpanLabel sp2 = new SpanLabel ("id : " + obj.getId());
                   SpanLabel a = new SpanLabel("Nom de la catégorie : " + obj.getName());
                 
          
             
          
                Button delete = new Button("delete");
      delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       
                        ServiceCateg.getInstance().deleteCateg(obj.getId()) ;
                      
                            LocalNotification n = new LocalNotification();
   
                      
                    }

           
                });
   
    

              Button Modif = new Button("Modifier");
             Modif.addActionListener((evt) -> {
              
                ModifcategForm.tfId.setText(String.valueOf(obj.getId()));
          
           
                ModifcategForm.tfName.setText(obj.getName());

                new ModifcategForm(previous).show();

            });
 

            addAll(spTitle, sp2,a,delete,Modif);
        }
      

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
