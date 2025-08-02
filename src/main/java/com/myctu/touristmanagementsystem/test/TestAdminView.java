/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.test;

import com.myctu.touristmanagementsystem.controller.ChuDichVuController;
import com.myctu.touristmanagementsystem.view.AdminView;
import java.awt.EventQueue;

/**
 *
 * @author ASUS
 */
public class TestAdminView {
      public static void main(String[] args) {
          EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                 
                AdminView  newView = new AdminView();

                ChuDichVuController control = new ChuDichVuController(newView);
                control.showAdminView();
            }
        });
    }
}
