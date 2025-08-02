/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.test;

import com.myctu.touristmanagementsystem.controller.KhachDuLichController;
import com.myctu.touristmanagementsystem.view.UserView;
import java.awt.EventQueue;

/**
 *
 * @author GIGABYTE
 */
public class TestUserView {
    public static void main(String[] args) {
          EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                 
                UserView  newView = new UserView();

                KhachDuLichController control = new KhachDuLichController(newView);
                control.showUserView();
                System.out.println("Cổ");
            }
        });
    }
}
