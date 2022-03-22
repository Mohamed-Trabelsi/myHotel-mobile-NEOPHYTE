/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author dell
 */
public class HomeForm extends Form{

    public HomeForm() {
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        
        Button btnShow = new Button("Show Users");
        btnShow.addActionListener(e -> new showUserForm(this).show());
        addAll(btnShow);
        
    }
    
}
