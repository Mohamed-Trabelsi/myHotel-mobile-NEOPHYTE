/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenements;
import com.mycompany.services.ServiceEvenements;
import java.util.ArrayList;

/**
 *
 * @author trabelssi
 */
public class DetailEvent extends Form{
    public static Evenements evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");
    static TextField tfIde = new TextField();

    static Object getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    Button btnAjouter;

    public DetailEvent(Form previous) {
        super("", new BoxLayout(BoxLayout.Y_AXIS));
        
        addGUIs();
        
       // getToolbar().hideToolbar();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            //perevious.showBack();
        });
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        
        this.refreshTheme();
    }

    private void addGUIs() {
   
        Evenements e = new Evenements();
        Evenements evenements =  ServiceEvenements.getInstance().detailEvenement(Integer.parseInt(tfIde.getText()),e);
        
            this.add(creerEvenement(evenements));
        
    }

    Component creerEvenement(Evenements event) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("evenementContainer");

        Label labelLibelle = new Label((String) event.getLibelleE());
        labelLibelle.setUIID("Libelle");
        
        SpanLabel spanLabelDescription = new SpanLabel(event.getDescriptionE());
        spanLabelDescription.setTextUIID("description");
        
        Label labelDateD = new Label((String) event.getDateDE());
        labelDateD.setUIID("Date debut");
        
        Label labelDateF = new Label((String) event.getDateFE());
        labelDateF.setUIID("Date fin");
        
        Label labelEspace = new Label(event.getEspaceE());
        labelEspace.setUIID("Espace");
        
       Label sp = new Label();
       sp.setText("Capacite : " + event.getCapaciteE());
       
       Label spetat = new Label();
       
       if(event.getEtatEv()==0)
       {  
       spetat.setText("Non valider");
       }
       else
       {
        spetat.setText("Valider");
       }
      evenementModel.addAll(labelLibelle, labelDateD,labelDateF, spanLabelDescription,labelEspace,sp,spetat);     
        return evenementModel;


    }
}
