/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem;

import com.myctu.touristmanagementsystem.controller.DangNhapController;
import com.myctu.touristmanagementsystem.view.LoginView;

/**
 *
 * @author ASUS
 */
public class TouristManagementSystem {

    public static void main(String[] args) {
        Thread runAdminApp = new Thread(new runViewThread());
        Thread runUserApp = new Thread(new runViewThread());
        runAdminApp.setName("Admin");
        runUserApp.setName("User");
        runAdminApp.start();
//        runUserApp.start();
//        System.out.println(runUserApp.getName());
        System.out.println(runAdminApp.getName());
    }
}

class runViewThread implements Runnable {

    @Override
    public void run() {
        LoginView newView = new LoginView();
        DangNhapController control = new DangNhapController(newView);
        control.showLoginView();
    }
}
