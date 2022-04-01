package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.User;
import com.mycompany.gui.HomeUserForm;
import com.mycompany.services.ServiceUser;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename
 * One</a> for the purpose of building native mobile applications using Java.
 */
public class MyApplication {

    private Form hi;
    private Form current;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if (err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });
    }

    public void start() {
        hi = new Form("S'authentifier", BoxLayout.y());

        Button btConnecter = new Button("Connecter");
        Button btTest = new Button("Test");
        TextField tfEmail = new TextField(null, "E-mail d'utilisateur");
        TextField tfPassword = new TextField(null, "mot de passe", 0, TextField.PASSWORD);
        ImageViewer i = new ImageViewer(theme.getImage("iconH.png"));

        hi.add(tfEmail);
        hi.add(tfPassword);
        hi.add(btConnecter);
        hi.add(btTest);
        hi.add(i);
        hi.show();

        btConnecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                User u = new ServiceUser().login(tfEmail.getText(), tfPassword.getText());
                
                if (u == null ) {
                    Dialog.show("Alerte", "username et/ou mot de passe incorrect ! ", "ok", null);
                } else if (u != null && u.getEtat() == 1 ) {
                    u.setPassword(tfPassword.getText());
                    tfEmail.setText("");
                    tfPassword.setText("");
                    HomeUserForm Hu = new HomeUserForm(u, hi);

                    Hu.show();
                } else {
                   Dialog.show("Désolé", "Vous ne pouvez pas accéder à notre application en tant que " + u.getNom() + " ! ", "ok", null);
                }
            }

        });
        btTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                User u = new User();
                HomeUserForm Hu = new HomeUserForm(u, hi);

                Hu.show();
            }
        });

    }

    public void stop() {
        current = getCurrentForm();
        if (current instanceof Dialog) {
            ((Dialog) current).dispose();
            current = getCurrentForm();
        }
    }

    public void destroy() {
    }

}
