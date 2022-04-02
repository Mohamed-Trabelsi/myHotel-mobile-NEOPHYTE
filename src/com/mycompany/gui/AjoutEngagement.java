package com.mycompany.gui;

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
import com.mycompany.services.ServiceEngagements;
import com.mycompany.services.ServiceEvenements;
import com.mycompany.services.ServiceSponsors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author trabelssi
 */
public class AjoutEngagement extends Form{
    
    Label CategorieLabel,eventLabel,SponsorLabel;
    Button btnAdd;
    ComboBox eventBox,sponsorBox;
    Form previous;

    Object E;
    Object S;
    Map<String, Object> userll = new HashMap<>();

    private Map<String, Object> createListEntry(String name, int id) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", id);
        return entry;
    }
    
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
        ArrayList<Evenements> e = ServiceEvenements.getInstance().getAllEvenementssP();
        /*for (Evenements u : ServiceEvenements.getInstance().getAllEvenementssP()) {
            eventBox.addItem(u.getId());   
        }*/
        for (Evenements u : e) {
            userll.put(u.getLibelleE(), u.getId());
            eventBox.addItem(u.getLibelleE());
        }
         SponsorLabel = new Label("sponsor");
        SponsorLabel.setUIID("defaultLabel");
         sponsorBox = new ComboBox();
        ArrayList<Sponsors> s = ServiceSponsors.getInstance().getAllSponsor();
        
        for (Sponsors i : s) {
            userll.put(i.getNomS(), i.getId());
            sponsorBox.addItem(i.getNomS());
        }
        
        /*for (Sponsors t : s) {
            sponsorBox.addItem(t.getId());
        }*/
        
        btnAdd = new Button("Ajouter");
      btnAdd.setUIID("SkipButton");
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
                         E = userll.get(eventBox.getSelectedItem().toString());
                         S = userll.get(sponsorBox.getSelectedItem().toString());
                         float nomE = Float.parseFloat(E.toString());
                         int ide = (int)nomE;
                         float nomS = Float.parseFloat(S.toString());
                         int ids = (int)nomS;
                         Engagements e = new Engagements(ide,ids);
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