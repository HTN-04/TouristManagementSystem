/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.test;

import com.myctu.touristmanagementsystem.controller.RegisterController;
import com.myctu.touristmanagementsystem.view.RegisterView;
import java.awt.EventQueue;

/**
 *
 * @author ASUS
 */
public class TestResgisterView {
     public static void main(String[] args) {
          EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                 
                RegisterView newView = new RegisterView();

                RegisterController control = new RegisterController(newView);

                control.showResgisterView();
            }
        });
    }
}
