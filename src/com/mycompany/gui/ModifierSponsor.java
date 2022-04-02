/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Sponsors;
import com.mycompany.services.ServiceSponsors;

/**
 *
 * @author trabelssi
 */
public class ModifierSponsor extends Form {
    static TextField tfId = new TextField();
    Sponsors current;

    public ModifierSponsor(Form previous) {
        setTitle("Modifier Sponsor");
        setLayout(BoxLayout.y());
            
 TextField tfNom = new TextField("","Nom");
  TextField tfAdresse = new TextField("","Adresse");
  TextField tfTel = new TextField("","Tel");
Button btnAdd = new Button("Modifier");
btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfAdresse.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    Sponsors s = new Sponsors(Integer.parseInt(tfTel.getText()),tfAdresse.getText(),tfNom.getText());
System.out.println("test");
                        if( ServiceSponsors.getInstance().updateSponsor(s, Integer.parseInt(tfId.getText())))
                            
                        {
                           
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           previous.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }                  
                }               
            }  
        });
addAll(tfNom,tfAdresse,tfTel,btnAdd);
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});                 
    }
}
