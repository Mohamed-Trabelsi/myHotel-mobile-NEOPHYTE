/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Command;
import com.mycompany.entities.Produits;
import com.mycompany.services.ServiceProduits;
import com.codename1.ui.util.Resources;
/**
 *
 * @author Administrator
 */
public class AjoutProduit extends Form{
    public AjoutProduit(Form previous) {
        setTitle("Ajouter un Produit ");
        setLayout(BoxLayout.y());
            
TextField tfLibelle = new TextField("","libelle");
TextField tfDateD = new TextField("","quantite");
TextField tfDescription = new TextField("","type");
Button btnAdd = new Button("Add");
 btnAdd.setUIID("SkipButton");
    btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLibelle.getText().length()==0)||(tfDescription.getText().length()==0)|| (tfDateD.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    Produits t = new Produits(tfLibelle.getText(),tfDescription.getText(),Integer.parseInt(tfDateD.getText()));

                        if( ServiceProduits.getInstance().AddProduits(t))
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
