/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.categ;
import com.mycompany.services.ServiceCateg;

import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;



/**
 *
 * @author wiem
 */
public class addCategForm extends Form {
       public addCategForm(Form previous,Resources res) {
        setTitle("Ajouter une nouvelle catégorie");
        setLayout(BoxLayout.y());
       
        TextField tfName = new TextField("","Name");
       
        
     ;
        Button btnValider = new Button("Ajouter catégorie");
  btnValider.setUIID("SkipButton");
 


        btnValider.addActionListener(new ActionListener() {
            {
              
            }
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                    
                       categ t = new categ(tfName.getText());
                        if( ServiceCateg.getInstance().addCateg(t))
                        { 
                           
                           Dialog.show("Success","ajout effectué",new Command("OK"));
                            ToastBar.showMessage("catégorie ajoutée avec succés", FontImage.MATERIAL_INFO);
                           LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme");
                        ln.setAlertSound("/notification_sound_notif.mp3");
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                          
                            
                          try {
                            Properties props = new Properties();
                            props.put("mail.transport.protocol", "smtp");
                            props.put("mail.smtps.host", "smtp.gmail.com");
                            props.put("mail.smtps.auth", "true");
                            props.put("mail.smtp.starttls.enable", "true");
                            
                      
                            
                            
                            Session session = Session.getInstance(props, null);
                            MimeMessage msg = new MimeMessage(session);
                            msg.setFrom(new InternetAddress("MyHotel <myHotel@domain.com>"));
                        
                           msg.setRecipients(Message.RecipientType.TO, "nina180girl@gmail.com");
                            msg.setSubject("Catégorie ajoutée ");
                            msg.setSentDate(new Date(System.currentTimeMillis()));
                           
                              // Send the actual HTML message, as big as you like
	 
                            
                           String txt = "une nouvelle catégorie est ajoutée  ";
                            //msg.setText(txt);
                              buildMessage("", msg);
                            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
                            st.connect("smtp.gmail.com",465, "myhotel48@gmail.com","123456789myHotel");
                            st.sendMessage(msg, msg.getAllRecipients());
                            System.out.println("server response : " + st.getLastServerResponse());

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                            
                        refreshTheme();
                           new listCategForm(previous,res);
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        
  

//

        });
          
     Form f = new Form("", BoxLayout.yBottom());      
Button showNotification = new Button("Besoin d'aide ?");

         /*  String[] recipients = new String[] {"nina180girl@gmail.com"};
    Message m= new Message("catégorie ajouté");
           String subject = "catégorie";
 Display.getInstance().sendMessage(recipients, subject, m);*/
showNotification.addActionListener(e -> ToastBar.showMessage("le nom est celui du catégorie des servvices que vous voulez l'ajouter , par exemple restaurent,piscine..", FontImage.MATERIAL_INFO));



        add(showNotification);
  

f.show();
        
        addAll(tfName,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
                 /* public static void sendMessage(String [] recipients, String subject, com.codename1.messaging.Message msg){
        recipients=new String[] {"nina180girl@gmail.com"};
    Message m= new Message("Body of message");
      //  Display.getInstance().sendMessage(recipients, subject, m);
    }*/
  public static void buildMessage(String contenu, Message msg) throws MessagingException, IOException {
 
    String sujet = msg.getSubject();
    StringBuilder sb = new StringBuilder();
    sb.append("<HTML>");
    sb.append("<HEAD>");
    sb.append("<TITLE>");
    sb.append(sujet + "");
    sb.append("</TITLE>");
    sb.append("</HEAD>");
 
    sb.append("<BODY>");
    sb.append("<center>"+"<H1>" + sujet + "</H1>"+"</center>" + "<br>");
 
      sb.append(contenu);
        sb.append("<H2 " + "Bonjour mr," + "</H2>" + "");
      sb.append("<H2>" +"Une nouvelle catégorie a été ajouté maintenant , veuillez verifier la nouvelle liste des catégories "+ "</H2>" + "");
  sb.append("<H2 >" + "cordialement" + "</H2>" + "");

    sb.append("</BODY>");
    sb.append("</HTML>");
 
    msg.setDataHandler(new DataHandler(new ByteArrayDataSource(sb.toString(), "text/html")));
  }
}   

