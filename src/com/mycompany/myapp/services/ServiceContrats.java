/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import static com.codename1.contacts.ContactsManager.refresh;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Contrats;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

import com.sun.mail.smtp.SMTPTransport;

import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author asus
 */
public class ServiceContrats {
    public ArrayList<Contrats> evenements;
    public static ServiceContrats instance=null;
    public boolean resultOK=true;
    private ConnectionRequest req;

    public ServiceContrats() {
         req = new ConnectionRequest();
    }

    public static ServiceContrats getInstance() {
        if (instance == null) {
            instance = new ServiceContrats();
        }
        return instance;
    }
    
    
     public boolean AddEvenements(Contrats t){
       
  System.out.println(t);
        System.out.println("********");
        
       String url = Statics.BASE_URL + "/ajoutC?titre="+t.getTitre()+"&contenu=" + t.getContenu()+"&dated=" + t.getDated()
               +"&datef=" + t.getDatef() + "&prix=" + t.getPrix()+ "&agence=" + t.getAgence();
       req.setUrl(url);
       req.addArgument("Libelle", t.getTitre());
       req.addArgument("description", t.getContenu());
       req.addArgument("datedebut", t.getDated());
       req.addArgument("datefin", t.getDatef());
       req.addArgument("prix", t.getPrix()+"");
       req.addArgument("agence", t.getAgence());
       
        
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
      public ArrayList<Contrats> parseEvenements(String jsonText){
           try {
            evenements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> evenementsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             List<Map<String,Object>> list = (List<Map<String,Object>>)evenementsListJson.get("root");
            for(Map<String,Object> obj : list){
                Contrats t = new Contrats();
                   float id= Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                float prix= Float.parseFloat(obj.get("prix").toString());
                t.setPrix((int)prix);
                   t.setTitre(obj.get("titre").toString());
                t.setDated(obj.get("dated").toString());
                t.setDatef(obj.get("datef").toString());
                t.setContenu(obj.get("contenu").toString());
                         
                evenements.add(t);
            }  
        } catch (IOException ex) {
            
        }
        return evenements;
    }
      public ArrayList<Contrats> getAllEvenementssP(){
String url = Statics.BASE_URL +"/disp";
    req.setUrl(url);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenements = parseEvenements(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenements;
    }
    public boolean deleteEvent(int id)
    {
        String url = Statics.BASE_URL +"/deletecc?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            
            @Override
            public void actionPerformed(NetworkEvent evt)
            {
                req.removeResponseCodeListener(this);
            }    
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public boolean validercontrat (int id) throws IOException
   {
       
        String url = Statics.BASE_URL +"/validercc?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            
            @Override
            public void actionPerformed(NetworkEvent evt)
            {
                req.removeResponseCodeListener(this);
            }    
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       sendMail();
        return resultOK;
   }
     /* public void sendMail() throws IOException 
     {
     
                

                Email from = new Email("myhotel70@gmail.com");
                String subject = "Compte Banni";
                Email to = new Email("myhotel70@gmail.com");
                Content content = new Content("text/plain", "l'etat du contrat est modifie"
                        + "Veuillez verifier dans la base de donnée." + "Cordialement,");
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request request = new Request();
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
                refresh();
     
     
     }*/
   public void sendMail() {
          String email;
        email = "myhotel.contact@gmail.com";
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
            Session session = Session.getInstance(props,null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar
            
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("Reintialisation mot de passe <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("Application nom  : Confirmation du ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
           
           String txt = "l'etat de votre contrat a changé";
           
           
           msg.setText(txt);
           
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"myhotel.contact@gmail.com","myhotel@pidev");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());
          
        }catch(Exception e ) {
            e.printStackTrace();
        }
    } 
}
