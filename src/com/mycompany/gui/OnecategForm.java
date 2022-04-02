/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.p;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.categ;
import com.mycompany.entities.service;
import com.mycompany.services.ServiceCateg;
import com.mycompany.services.ServiceServ;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.messaging.Message;
/**
 *
 * @author wiem
 */
public class OnecategForm extends Form {
        Form current;
   ServiceCateg reservationService = new ServiceCateg();
      public OnecategForm(Form previous ,categ c,Resources res) {
          
            
      setTitle("detail de : " +c.getName());
      //  setLayout(new FlowLayout(CENTER));
       // setTitle("information detaillé");
       // setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        /* *THIS CODE USED TO DISPLAY IMAGE* */
      
  Container d= new Container(BoxLayout.y());
     //  Label idLabel = new Label("id: "+ c.getId());
       // Label nameLabel = new Label("nom: "+c.getName());
          d.add(new Label("identifiant= "+c.getId()));
         d.add(new Label("Nom de la catégorie="+c.getName()));
      
      
              Button delete = new Button("delete");
       delete.setUIID("SkipButton");
      delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       
                        ServiceCateg.getInstance().deleteCateg(c.getId()) ;
                    
                            Dialog.show("Success", "suppression de la catégorie ! ", "Ok", null);
                          new  listCategForm(previous,res).show(); 
//                            f.setTransitionOutAnimator(CommonTransitions.createEmpty());
//                            f.show();
                     

                         
   
                      
                    }

           
                });
   
    

              Button Modif = new Button("Modifier");
         Modif.setUIID("SkipButton");
             Modif.addActionListener((evt) -> {
              
                ModifcategForm.tfId.setText(String.valueOf(c.getId()));
          
           
                ModifcategForm.tfName.setText(c.getName());

                new ModifcategForm(previous).show();

            }); 
              //TextField recherche =new TextField();
    
//        tb.addSearchCommand(e -> 
//        new SearchFilm(res,rechercheTexte).show()
//);
  
        addAll(d,delete,Modif);
        /* *** *BACK BUTTON* *** */
      
        /* *** *OVERFLOW MENU* *** */
      /* *** *BACK BUTTON* *** */
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVE}RFLOW MENU* *** */
   
          
      }
   
    
}
