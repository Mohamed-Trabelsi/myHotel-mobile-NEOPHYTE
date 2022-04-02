/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Agences;
import com.mycompany.entities.Contrats;
import com.mycompany.services.ServiceAgences;
import com.mycompany.services.ServiceContrats;
import java.util.ArrayList;
/**
 *
 * @author asus
 */
public class AjoutContrat extends Form {
    
     public AjoutContrat(Form previous) {
        setTitle("Ajouter un contrat");
        setLayout(BoxLayout.y());
            
        Label CategorieLabel = new Label("Agence");
       ServiceAgences serviceTask = new ServiceAgences();
        ArrayList<Agences> lescategories = serviceTask.getAllEvenementssP();
        ComboBox categorieBox = new ComboBox();
        for (Agences temp : lescategories) {
            categorieBox.addItem(temp.getNom());       
        }
         Label categorieEror = new Label("*");
        categorieEror.getAllStyles().setFgColor(0xff0000);
        categorieEror.setVisible(false);
        Container categorieContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        categorieContainer.add(CategorieLabel);
        categorieContainer.add(categorieBox);
        categorieContainer.add(categorieEror);
         
        
TextField tfLibelle = new TextField("","titre");
TextField tfDescription = new TextField("","contenu");
TextField tfDateD = new TextField("","Date debut");
TextField tfDateF = new TextField("","Date fin");
TextField tfCapacite = new TextField("","prix");


//TextField tfEspace = new TextField("","Agence");
Button btnAdd = new Button("Add");
 btnAdd.setUIID("SkipButton");
    btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLibelle.getText().length()==0)||(tfDescription.getText().length()==0)||(tfDateD.getText().length()==0)||(tfDateF.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                         
     Contrats t = new Contrats(tfDateD.getText(),tfDateF.getText(),tfDescription.getText(),tfLibelle.getText(),Integer.parseInt(tfCapacite.getText()),(String)categorieBox.getSelectedItem());

                         System.out.println(t);
 
                        if (ServiceContrats.getInstance().AddEvenements(t)) {
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
            }  
        });
    addAll(categorieContainer,tfLibelle,tfDescription,tfDateD,tfDateF,tfCapacite,btnAdd);
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
previous.refreshTheme();
});                 
    }
}
