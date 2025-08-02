/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.controller;

import com.myctu.touristmanagementsystem.dao.DonHangDAO;
import com.myctu.touristmanagementsystem.dao.ThanhToanDAO;
import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;

/**
 *
 * @author GIGABYTE
 */
public class DonHangController {
    
     private ThanhToanDAO thanhToanDAO;
    private DonHangDAO donHangDAO;

    public DonHangController() {
        this.thanhToanDAO = new ThanhToanDAO();
        this.donHangDAO = new DonHangDAO();
    }

    public void xuLyXacNhanDatVe() {
        // Bước 1: Tạo giao dịch thanh toán và nhận mã MaTT
        int maTT = thanhToanDAO.createThanhToan();

        if (maTT != -1) {
            // Bước 2: Lấy mã khách du lịch từ người dùng hiện tại
            int maKDL = NguoiDungHienTai.getInstance().getMaKDL();

            // Bước 3: Tạo đơn hàng mới
            boolean success = donHangDAO.createDonHang(maTT, maKDL);

            if (success) {
                System.out.println("Tạo đơn hàng thành công với MaTT: " + maTT);
            } else {
                System.out.println("Không thể tạo đơn hàng.");
            }
        } else {
            System.out.println("Không thể tạo giao dịch thanh toán.");
        }
    }
}
