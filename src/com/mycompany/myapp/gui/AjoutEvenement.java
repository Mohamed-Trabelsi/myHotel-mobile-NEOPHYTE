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
import com.mycompany.myapp.entities.Evenements;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Command;

import com.mycompany.myapp.services.ServiceEvenements;


/**
 *
 * @author trabelssi
 */
public class AjoutEvenement extends Form {
    public AjoutEvenement(Form previous) {
        setTitle("Ajouter un evenement");
        setLayout(BoxLayout.y());
            
TextField tfLibelle = new TextField("","Libelle");
TextField tfDateD = new TextField("","Description");
TextField tfDescription = new TextField("","Date début");
TextField tfDateF = new TextField("","Date fin");
TextField tfEspace = new TextField("","Espace");
TextField tfCapacite = new TextField("","Capacite");
Button btnAdd = new Button("Add");


btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfLibelle.getText().length()==0)||(tfEspace.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    Evenements t = new Evenements(tfLibelle.getText(),tfDescription.getText(),tfDateD.getText(),tfDateF.getText(),tfEspace.getText(),Integer.parseInt(tfCapacite.getText()));

                        if( ServiceEvenements.getInstance().AddEvenements(t))
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
addAll(tfLibelle,tfDescription,tfDateD,tfDateF,tfEspace,tfCapacite,btnAdd);
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});                 
    }
    
    /*Label labelLibelle, labelDateD,labelDateF,labelDescription,labelCapacite,labelEspace;
    TextField tfLibelle,tfEspace,tfDateD,tfDateF,tfCapacite;
    TextArea tfDescription;
    Button btnAjouter;
    Form previous;
    
    public AjoutEvenement(Form previous) {
         super(new BoxLayout(BoxLayout.Y_AXIS));
        
        this.previous = previous;
        
        addGUIs();
        addActions();
      
        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        labelLibelle = new Label("Libelle");
        labelLibelle.setUIID("defaultLabel");
        tfLibelle = new TextField();
        labelCapacite = new Label("Capacite");
        labelCapacite.setUIID("defaultLabel");
        tfCapacite = new TextField();
        labelEspace = new Label("Espace");
        labelEspace.setUIID("defaultLabel");
        tfEspace = new TextField();
        labelDescription = new Label("Description : ");
        labelDescription.setUIID("defaultLabel");
        tfDescription = new TextArea();
        labelDateD = new Label("Date : ");
        Container picker = new Container( new BoxLayout(BoxLayout.Y_AXIS));
        labelDateD.setUIID("defaultLabel");
        tfDateD = new TextField();
        
        labelDateF.setUIID("defaultLabel");
        tfDateF = new TextField();
       

        btnAjouter = new Button("Ajouter");

        btnAjouter.setUIID("buttonSuccess");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        container.setUIID("EvenementContainer");
        container.addAll(labelLibelle, tfLibelle, labelDateD,tfDateD,labelDateF,tfDateF, labelDescription, tfDescription,labelEspace,tfEspace,labelCapacite,tfCapacite, btnAjouter);

        this.addAll(container);
    }

    private boolean controleDeSaisie() {
        if (tfLibelle.getText().equals("")) {
            Dialog.show("Objet vide", "", new Command("Ok"));
            return false;
        }
        if (tfDescription.getText().equals("")) {
            Dialog.show("Description vide", "", new Command("Ok"));
            return false;
        }

        return true;
    }

    private void addActions() {

        btnAjouter.addActionListener((action) -> {
             
            if (controleDeSaisie()) {
                System.out.println("11");

                int responseCode = ServiceSponsors.getInstance().AddSponsor(new Sponsors(tfLibelle.getText(), tfDateD.getText(), tfDateF.getText(), tfDescription.getText(),tfEspace.getText()));
                System.out.println("22");
                 
                if (responseCode == 200) {
                    Dialog.show("SuccÃ©s", "Reparation ajoutÃ© avec succes", new Command("Ok"));
                } else {
                    Dialog.show("Erreur", "Erreur d'ajout de Reparation. Code d'erreur : " + responseCode, new Command("Ok"));
                }
                
                  ((ShowReparation) previous).refresh();
                previous.showBack();
            }
        });

    }*/
  
}
