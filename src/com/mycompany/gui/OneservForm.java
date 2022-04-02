/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.*;
import com.mycompany.entities.service;
import com.mycompany.services.ServiceServ;
import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;  
//import java.sql.Date; 


/**
 *
 * @author wiem
 */
public class OneservForm extends Form {
      Form current;
   ServiceServ reservationService = new ServiceServ();
      public OneservForm(Form previous ,service c) {
          
            
     
        setTitle("information detaillé");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        /* *THIS CODE USED TO DISPLAY IMAGE* */
      

        Label idLabel = new Label("id: "+ c.getIdServ());
        Label nameLabel = new Label("nom: "+c.getId_categ());
        Label dbLabel = new Label("Date ouverture: "+c.getDb());
        Label dfLabel = new Label("Date fermeture: "+c.getDf());
/*LocalDateTime dateTime = LocalDateTime.parse(c.getDf());

 LocalDateTime a=LocalDateTime.now();          
                  System.out.println((int)(c.getDb().compareTo(c.getDf())));
                  //int rec =t.getDate().compareTo(datec);
         boolean tr= (dateTime.isAfter(a));
                if(tr=true )
                  {
                       Label compler = new Label("service fermé");
                   compler.getAllStyles().setFgColor(0xff0000);
                    add(compler); 
                  }*/
      
             Button delete = new Button("delete");
              delete.setUIID("SkipButton");
      delete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       
                        ServiceServ.getInstance().deleteCateg(c.getIdServ()) ;
                      
                            //LocalNotification n = new LocalNotification();
    new listServBack(previous).show();
                      
                    }

           
                });
   
    

              Button Modif = new Button("Modifier");
               Modif.setUIID("SkipButton");
             Modif.addActionListener((evt) -> {
              
            
            ModifServForm.tfId.setText(String.valueOf(c.getIdServ()));
            
        new  ModifServForm(this).show();
                     

                //new ModifcategForm(previous).show();

            }); 
        addAll(idLabel,nameLabel,dbLabel,dfLabel,delete,Modif);
        /* *** *BACK BUTTON* *** */
      
        /* *** *OVERFLOW MENU* *** */
      /* *** *BACK BUTTON* *** */
      
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVE}RFLOW MENU* *** */
   
          
      }
}
