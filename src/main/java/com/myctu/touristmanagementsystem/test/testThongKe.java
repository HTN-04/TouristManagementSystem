/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.test;

import com.myctu.touristmanagementsystem.dao.ThongKeDAO;
import com.myctu.touristmanagementsystem.model.ThongKe;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GIGABYTE
 */
public class testThongKe {

    public static void main(String[] args) {
        List<ThongKe> list = ThongKeDAO.getInstance().selectAll();
        for(ThongKe thongKe : list){
            
            System.out.println(thongKe.toString());
        }
    }
}
