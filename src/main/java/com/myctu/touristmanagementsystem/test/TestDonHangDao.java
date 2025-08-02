/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.test;

import com.myctu.touristmanagementsystem.dao.DonHangDAO;
import com.myctu.touristmanagementsystem.model.DonHang;

/**
 *
 * @author GIGABYTE
 */
public class TestDonHangDao {
    public static void main(String[] args) {
 while(true){
 DonHang DH = new DonHang();
 DonHangDAO.getInstance().insert(DH);
 }       
        
//        DonHang dh1 = new DonHang(3);
//        DonHangDAO.getInstance().delete(dh1);
    }
    
}
