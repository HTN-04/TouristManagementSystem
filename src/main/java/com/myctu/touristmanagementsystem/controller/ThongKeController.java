/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.controller;

import com.myctu.touristmanagementsystem.dao.ThongKeDAO;
import com.myctu.touristmanagementsystem.model.ThongKe;
import com.myctu.touristmanagementsystem.view.AdminView;
import com.myctu.touristmanagementsystem.view.ThongKeBaoCao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author GIGABYTE
 */
public class ThongKeController {

    private ThongKeBaoCao thongKeBaoCao;
    private ThongKeDAO thongKeDAO;
    private AdminView adminView;

    public ThongKeController(ThongKeBaoCao thongKeBaoCaoView, AdminView adminView) {
        System.out.println("Đã khởi tạo ThongKeController");
        this.thongKeBaoCao = thongKeBaoCaoView;
        this.adminView = adminView;
        thongKeDAO = new ThongKeDAO();

        // Gắn sự kiện cho nút Cập Nhật
        this.thongKeBaoCao.addCapNhapListener(new CapNhapThongKe());
    }
    class CapNhapThongKe implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Đã bấm nút Cập Nhật!");

            // Lấy dữ liệu thống kê từ ThongKeDAO
            List<ThongKe> list = thongKeDAO.selectAll();

            // Tính tổng số lượng và tổng số tiền
            int tongSoLuongDat = 0;
            double tongSoTien = 0;
            for (ThongKe thongKe : list) {
                tongSoLuongDat += thongKe.getSoLuongDat();
                tongSoTien += thongKe.getTongSoTien();
            }

            // Gọi phương thức hiển thị dữ liệu lên view
            thongKeBaoCao.displayThongKeData(tongSoLuongDat, tongSoTien);
        }
    }
}
