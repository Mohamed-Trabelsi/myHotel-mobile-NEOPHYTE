/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
//import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Engagements;
import com.mycompany.myapp.entities.Evenements;
import com.mycompany.myapp.entities.Sponsors;
import com.mycompany.myapp.services.ServiceEngagements;
import com.mycompany.myapp.services.ServiceEvenements;
import com.mycompany.myapp.services.ServiceSponsors;
import java.util.ArrayList;

/**
 *
 * @author trabelssi
 */
public class AjoutEngagement extends Form{
    Label CategorieLabel,eventLabel,SponsorLabel;
    Button btnAdd;
    ComboBox eventBox,sponsorBox;
        Form previous;

    public AjoutEngagement(Form previous) {

      
         super(new BoxLayout(BoxLayout.Y_AXIS));
        
        this.previous = previous;
        
        addGUIs();
        addActions();
      
        //super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> home.showBack());
    
    }
    private void addGUIs() {
                Container EventContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        CategorieLabel = new Label("Engagements");
        CategorieLabel.setUIID("defaultLabel");
       eventLabel = new Label("Event");
        eventLabel.setUIID("defaultLabel");
        eventBox = new ComboBox();
       
        for (Evenements u : ServiceEvenements.getInstance().getAllEvenementssP()) {
            eventBox.addItem(u.getLibelleE());   
        }
         SponsorLabel = new Label("sponsor");
        SponsorLabel.setUIID("defaultLabel");
         sponsorBox = new ComboBox();
        ArrayList<Sponsors> s = ServiceSponsors.getInstance().getAllSponsor();
        
        for (Sponsors t : s) {
            sponsorBox.addItem(t.getNomS());
        }
        
        btnAdd = new Button("Ajouter");
        btnAdd.setUIID("actionButton");
               EventContainer.addAll(CategorieLabel,eventLabel,eventBox,SponsorLabel,sponsorBox,btnAdd);

this.addAll( EventContainer);
    }
        private void addActions() {

        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               /*if ((tfDuree.getText().length() == 0)  ) {
                    System.out.println("d");
                    
                  //Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                  Dialog.show("Alert", "Please fill all the fields", "OK", "CANCEL");
          
                 
                } else {*/
                    try {
                         Engagements e = new Engagements((int)eventBox.getSelectedItem(),(int)sponsorBox.getSelectedItem());
                         System.out.println(e);
 
                        if (ServiceEngagements.getInstance().AddEngagements(e)) {
                                 //Dialog.show("Success", "Connection accepted", new Command("OK"));
                                 Dialog.show("Success", "Ajouté avec succès", "OK", "CANCEL");
                            previous.showBack();
                        } else {
                            //Dialog.show("ERROR", "Server error", new Command("OK"));
                            Dialog.show("Alerte", "Server error", "OK", "CANCEL");
                        }
                    } catch (NumberFormatException e) {
                       // Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            

           
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

    }
}
