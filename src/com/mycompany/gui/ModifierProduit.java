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
import com.mycompany.entities.Produits;
import com.mycompany.services.ServiceProduits;
/**
 *
 * @author asus
 */
public class ModifierProduit extends Form{
    static TextField tfId = new TextField();
    Produits current;
    
     public ModifierProduit(Form previous) {
        setTitle("Modifier Produits");
        setLayout(BoxLayout.y());
            
TextField tfLibelle = new TextField("","libelle");
TextField tfDescription = new TextField("","type");
TextField tfDateD = new TextField("","quantite");

Button btnAdd = new Button("Modifier");
    btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLibelle.getText().length()==0)||(tfDescription.getText().length()==0)|| (tfDateD.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
                else
                {
                    try {
                    Produits s = new Produits(tfLibelle.getText(),tfDescription.getText(),Integer.parseInt(tfDateD.getText()));
                    

                        if( ServiceProduits.getInstance().updateProduit(s,Integer.parseInt(tfId.getText())))
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
addAll(tfLibelle,tfDescription,tfDateD,btnAdd);
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
    
previous.showBack();
});                 
    }
}
