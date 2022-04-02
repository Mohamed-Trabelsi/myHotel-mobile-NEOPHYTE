/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.entities.categ;
import com.mycompany.services.ServiceCateg;
import java.util.ArrayList;

import com.codename1.ui.Button;
import static com.codename1.ui.CN.addNetworkErrorListener;
import static com.codename1.ui.CN.updateNetworkThreadCount;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.Collections;
/**
 *
 * @author wiem
 */
public class listCategForm extends Form {
    public ArrayList<categ> categs;
    private Resources theme;
    Form current;
    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    private Label createForFont(Font fnt, String s) {
        Label l = new Label(s);
        l.getUnselectedStyle().setFont(fnt);
        return l;
    }
    public listCategForm(Form previous,Resources res) {
         
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
            
           //  SpanLabel sp2 = new SpanLabel ("id : " + obj.getId());
                  // SpanLabel a = new SpanLabel("Nom de la catégorie : " + obj.getName());
                MultiButton multiButton = new MultiButton();  
          multiButton.setTextLine1("  " +obj.getName());
            //  multiButton.setTextLine1("id de la catégorie : " +obj.getId());
             
          
           /*     Button delete = new Button("delete");
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

            }); */
   multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
           multiButton.addActionListener(l -> new OnecategForm(previous, obj,res).show());
            add(multiButton);

            addAll(spTitle);
        }
       

    
       
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Trier par nom", null, (evt) -> {
            removeAll();
             Collections.sort(categs, categ.nameComparator);
                fillData(previous,res);
        });
      

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
        public void fillData(Form previous, Resources res) {
        for (categ obj : categs) {
            
            MultiButton multiButton = new MultiButton();
       
               multiButton.setTextLine1("  " +obj.getName());
                 multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
           
            multiButton.addActionListener(l -> new  OnecategForm(previous, obj,res).show());
            add(multiButton);
        }
    }

    
    
}
