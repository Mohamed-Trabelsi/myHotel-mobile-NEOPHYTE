/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
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
import com.mycompany.myapp.services.ServiceSponsors;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;

/**
 *
 * @author trabelssi
 */
public class AjoutSponsor extends Form{
     public AjoutSponsor(Form previous) {
        setTitle("Ajouter un Sponsor");
        setLayout(BoxLayout.y());
            
TextField tfNom = new TextField("","Nom");
TextField tfAdresse = new TextField("","Adresse");
TextField tfTel = new TextField("","Tel");
Button btnAdd = new Button("Add");
btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfAdresse.getText().length()==0)||(tfTel.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    Sponsors s = new Sponsors(Integer.parseInt(tfTel.getText()),tfAdresse.getText(),tfNom.getText());

                        if( ServiceSponsors.getInstance().AddSponsor(s))
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
/*ToastBar.getInstance().setPosition(BOTTOM);
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setShowProgressIndicator(true);
                //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                status.setMessage("Sponsor ajouté avec succès");
                status.setExpires(10000);
                status.show();

                refreshTheme();*/
addAll(tfNom,tfAdresse,tfTel,btnAdd);
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});                 
    }
}
