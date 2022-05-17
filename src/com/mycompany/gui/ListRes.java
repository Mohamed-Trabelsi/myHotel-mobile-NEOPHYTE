/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.gui.Abs.showAbsForm;
import static com.mycompany.gui.HomeEvenement.Menu;
import com.mycompany.gui.User.showUserForm;
import com.mycompany.services.ReservationChambre;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author WiiWii
 */
public class ListRes extends Form{
        Form current;
           ImageViewer imgv;

        public ListRes( Form previous ){
        setTitle("Liste des Reservations");
        
          Container co=new Container(BoxLayout.xCenter());;
                    ArrayList <Reservation> ress = new ArrayList();
                        ReservationChambre sa =new ReservationChambre();
                    ress=sa.getAllReservations();
                                            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new 
                        SimpleDateFormat(DateFormatPatterns.ISO8601);

                             for (Reservation fi : ress) {
                            Container ct = new Container(BoxLayout.y());

                            Label l = new Label("ID : "+fi.getId());
                            Label l2 = new Label("Date Debut : "+fi.getDateD().toString(),"SmallLabel");
                            Label l3 = new Label("Date Fin : "+fi.getDateF().toString(),"SmallLabel");
                            Label l4 = new Label("Client : "+fi.getClient(),"RedLabel");
                            l2.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(l);
                            ct.add(l2);
                            ct.add(l3);
                            ct.add(l4);

                            Button Modifier = new Button("Modifier");
                            Button Supprimer = new Button("Supprimer");
                            Modifier.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               

                                new ModifReservationForm(current,fi).show();
                   
                }   
        });
                            Supprimer.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               

                if (Dialog.show("Confirmation", "Voulez vous supprimer cette reservation ?", "Oui", "Annuler")) {

                  if( ReservationChambre.getInstance().deleteRes(fi.getId()))
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
                                new ListRes(previous).show();
                            }

                            }
                   
                   
                }   
        });
                       ct.add(Modifier);
                       ct.add(Supprimer);
                       Label separator = new Label("","Separator");
                       ct.add(separator);
                       add(ct);
                             
                                 }
                                  

        //Tool Bar
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});
    }


}
