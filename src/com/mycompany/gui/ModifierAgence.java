/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.mycompany.entities.Agences;
import com.mycompany.services.ServiceAgences;
/**
 *
 * @author asus
 */
public class ModifierAgence extends Form{
    static TextField tfId = new TextField();
    Agences current;
    
     public ModifierAgence(Form previous) {
        setTitle("Modifier Agence");
        setLayout(BoxLayout.y());
            
TextField tfLibelle = new TextField("","nom");
TextField tfDescription = new TextField("","email");
TextField tfDateD = new TextField("","num");

Button btnAdd = new Button("Modifier");
  btnAdd .setUIID("SkipButton");
    btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLibelle.getText().length()==0)||(tfDescription.getText().length()==0)|| (tfDateD.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
                else
                {
                    try {
                    Agences s = new Agences(Integer.parseInt(tfDateD.getText()),tfLibelle.getText(),tfDescription.getText());
                    

                        if( ServiceAgences.getInstance().updateAgence(s,Integer.parseInt(tfId.getText())))
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
addAll(tfLibelle,tfDateD,tfDescription,btnAdd);
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
    
previous.showBack();
});                 
    }
}
