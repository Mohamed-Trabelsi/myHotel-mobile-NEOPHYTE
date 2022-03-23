/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Evenements;
import com.mycompany.myapp.services.ServiceEvenements;

/**
 *
 * @author trabelssi
 */
public class ModifierEvenement extends Form {
    static TextField tfId = new TextField();
    Evenements current;
    public ModifierEvenement(Form previous) {
        setTitle("Modifier un evenement");
        setLayout(BoxLayout.y());
            
TextField tfLibelle = new TextField("","Libelle");
Picker dateDPicker = new Picker();
dateDPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
Picker dateFPicker = new Picker();
dateFPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
/*dateDPicker.setUIID("TextFieldBlack");
addStringValue("Date debut",dateDPicker);
TextField tfDateD= new TextField("","Date début");
TextField tfDateF = new TextField("","Date fin");*/
TextField tfDescription = new TextField("","Description");
TextField tfEspace = new TextField("","Espace");
TextField tfCapacite = new TextField("","Capacite");
Button btnAdd = new Button("Modifier");


btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLibelle.getText().length()==0)||(tfEspace.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    Evenements t = new Evenements(tfLibelle.getText(),dateDPicker.getText(),dateFPicker.getText(),tfDescription.getText(),tfEspace.getText(),Integer.parseInt(tfCapacite.getText()));

                        if( ServiceEvenements.getInstance().updateEvent(t, Integer.parseInt(tfId.getText())))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           //previous.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }                  
                }               
            }  
        });
addAll(tfLibelle,dateDPicker,dateFPicker,tfDescription,tfEspace,tfCapacite,btnAdd);
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});                 
    }

   
}
