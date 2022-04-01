/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.User;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class showUserForm extends Form {

    public static User evenementActuelle = null;
    public static Resources theme = UIManager.initFirstTheme("/theme");

    Button btnAjouter;

    public showUserForm(Form perevious) {
        super("Liste Des Utilisateurs", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
        addActions();
        // getToolbar().hideToolbar();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            perevious.showBack();
        });

    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        btnAjouter = new Button("Ajouter");
        btnAjouter.setUIID("newButton");
        getToolbar().addMaterialCommandToRightBar(" ", FontImage.MATERIAL_ADD_CIRCLE, (evt1) -> {
            new addUserForm(this).show();
        });
        getToolbar().addMaterialCommandToRightBar(" ", FontImage.MATERIAL_ACCOUNT_BOX, (evt1) -> {
            new showNUser(this).show();
        });

        // this.add(btnAjouter);
        ArrayList<User> users = ServiceUser.getInstance().getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            this.add(creerUser(users.get(i)));
        }
    }

    private void addActions() {
        btnAjouter.addActionListener(action -> {
            new addUserForm(this).show();
        });
    }

    private Component creerUser(User user) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("evenementContainer");

        Label labelNom = new Label("Nom : " + user.getNom());

        Label labelPrenom = new Label("Prenom : " + user.getPrenom());

        Label labelAge = new Label("Age : " + user.getAge());
        //labelAge.setUIID("Age");

        Label labelCin = new Label("CIN : " + user.getCin());

        Label labelTelUser = new Label("N°tel : " + user.getTel_user());

        Label labelEmailUser = new Label("Email : " + user.getEmail_user());

        Label labelRole = new Label("Role : " + user.getRole());

        Label labelGenre = new Label("Genre : " + user.getGenre());

        Container btnsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnsContainer.setUIID("buttonsContainer");
        btnsContainer.setPreferredH(200);

        Button btnModifier = new Button();
        btnModifier.setUIID("actionButton");
        Button btnSupprimer = new Button();
        btnSupprimer.setUIID("actionButton");
        FontImage.setMaterialIcon(btnSupprimer, FontImage.MATERIAL_DELETE);
        FontImage.setMaterialIcon(btnModifier, FontImage.MATERIAL_UPDATE);

        Button btnBan = new Button();
        btnBan.setUIID("actionButton");
        FontImage.setMaterialIcon(btnBan, FontImage.MATERIAL_CANCEL);
        // refresh();

        btnBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setMessage("User banni");
                status.show();

                ServiceUser su = new ServiceUser();
                su.getInstance().Ban(user.getId_user());

                Email from = new Email("myhotel70@gmail.com");
                String subject = "Compte Banni";
                Email to = new Email(user.getEmail_user() + "@gmail.com");
                Content content = new Content("text/plain", "Bonjour,cher employeé on vous annonce que Votre compte a été banni"
                        + "Veuillez verifier avec l'administration." + "Cordialement,");
                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid("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");
                Request request = new Request();
                try {
                    request.setMethod(Method.POST);
                    request.setEndpoint("mail/send");
                    try {
                        request.setBody(mail.build());
                    } catch (IOException ex) {
                        System.out.println("errrrrreur");
                    }
                    Response response = sg.api(request);
                    System.out.println(response.getStatusCode());
                    System.out.println(response.getBody());
                    System.out.println(response.getHeaders());
                } catch (IOException ex) {
                    System.out.println("message non envoyé");
                }
                refresh();

            }
        });

        btnModifier.addActionListener(action -> {

            /* evenementActuelle = user;
            new updateUser(this).show();
            ServiceUser.getInstance().updateUser(user);

            addGUIs();*/
            updateUser.tfId.setText(String.valueOf(user.getId_user()));
            updateUser.tfnomU.setText(String.valueOf(user.getNom()));
            updateUser.tfPrenomU.setText(String.valueOf(user.getPrenom()));
            updateUser.tfAgeU.setText(String.valueOf(user.getAge()));
            updateUser.tfCinU.setText(String.valueOf(user.getCin()));
            updateUser.tfTelUserU.setText(String.valueOf(user.getTel_user()));
            updateUser.tfEmailUserU.setText(String.valueOf(user.getEmail_user()));

            refresh();

            new updateUser(this).show();
        });

        btnSupprimer.addActionListener(action -> {

            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                ServiceUser.getInstance().deleteUser(user.getId_user());
                evenementActuelle = null;
                dlg.dispose();
                this.removeAll();
                addGUIs();
                this.refreshTheme();
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            //Dimension pre = dlg.getContentPane().getPreferredSize();
            //dlg.show(0, 0, Display.getInstance().getDisplayWidth() - (pre.getWidth() + pre.getWidth() / 6), 0);
            dlg.show(1000, 1000, 10, 10);

        });

        btnsContainer.addAll(btnSupprimer, btnModifier, btnBan);
        evenementModel.addAll(labelNom, labelPrenom, labelAge, labelCin, labelTelUser,
                labelEmailUser, labelRole, labelGenre, btnsContainer);
        return evenementModel;

    }

}
