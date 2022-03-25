/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.User;
import com.mycompany.gui.Abs.showAbsForm;
import com.mycompany.gui.User.showUserForm;

/**
 *
 * @author dell
 */
public class HomeUserForm extends Form{

    public HomeUserForm(User u, Form precedent) {
 
        setTitle("Bonjour :" + u.getPrenom()+" "+ u.getNom());
        setLayout(BoxLayout.y());
         Toolbar tb = this.getToolbar();
         tb.addCommandToOverflowMenu("Déconnecter", null,new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                boolean yes = Dialog.show("Alerte", "Voulez-vous vraiment se déconnecter ?", "oui", "non");
                if (yes) {
                    precedent.show();
                }
            }
        });
         

        Button btnShowUser = new Button("Show Users");
        btnShowUser.addActionListener(e -> new showUserForm(this).show());
        Button btnShowAbs = new Button("Show Abs");
        btnShowAbs.addActionListener(e -> new showAbsForm(this).show());
        addAll(btnShowUser,btnShowAbs);
        
    }

   

    

    
}
