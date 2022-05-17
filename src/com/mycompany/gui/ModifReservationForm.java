/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
 import com.codename1.ui.Component;
 import com.codename1.ui.Container;
 import com.codename1.ui.Toolbar;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Reservation;
import com.mycompany.services.ReservationChambre;
import java.util.Date;

/**
 *
 * @author WiiWii
 */

    public class ModifReservationForm extends Form{



    Reservation current;

    public ModifReservationForm(Form previous,Reservation fi) {

        setTitle("Modifier Reservation");
        setLayout(BoxLayout.y());
          TextField tfClient = new TextField("Client");

        tfClient.setText(fi.getClient());
        Button btnValider = new Button("Modifier ");
       Picker DateDPicker = new Picker();
        DateDPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);   
        DateDPicker.setDate(fi.getDateD());

Picker DateFPicker = new Picker();

DateFPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
       DateFPicker.setDate(fi.getDateD());
         addAll( tfClient,DateDPicker,DateFPicker, btnValider);

        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfClient.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                                                                        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);


                        fi.setClient(tfClient.getText());
                        fi.setDateD(DateDPicker.getDate());
                        fi.setDateF(DateFPicker.getDate());
                        if (ReservationChambre.getInstance().modifReservation(fi )) 
                        {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            //new ListRes().show();
                        } 
                        else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente

    }
    
}


   

