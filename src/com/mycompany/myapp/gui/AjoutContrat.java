/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Command;
import com.mycompany.entities.Contrats;
import com.mycompany.myapp.services.ServiceContrats;
/**
 *
 * @author asus
 */
public class AjoutContrat extends Form {
    
     public AjoutContrat(Form previous) {
        setTitle("Ajouter un contrat");
        setLayout(BoxLayout.y());
            
TextField tfLibelle = new TextField("","titre");
TextField tfDescription = new TextField("","contenu");
TextField tfDateD = new TextField("","Date début");
TextField tfDateF = new TextField("","Date fin");
TextField tfCapacite = new TextField("","prix");
//TextField tfEspace = new TextField("","Agence");
Button btnAdd = new Button("Add");
    btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLibelle.getText().length()==0)||(tfDescription.getText().length()==0)||(tfDateD.getText().length()==0)||(tfDateF.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    Contrats t = new Contrats(tfDateD.getText(),tfDateF.getText(),tfDescription.getText(),tfLibelle.getText(),Integer.parseInt(tfCapacite.getText()));

                        if( ServiceContrats.getInstance().AddEvenements(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           previous.showBack();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "prix must be a number", new Command("OK"));
                    }                  
                }               
            }  
        });
    addAll(tfLibelle,tfDescription,tfDateD,tfDateF,tfCapacite,btnAdd);
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
previous.refreshTheme();
});                 
    }
}
