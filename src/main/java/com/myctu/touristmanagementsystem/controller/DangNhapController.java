/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.controller;

import com.myctu.touristmanagementsystem.dao.DangNhapDAO;
import com.myctu.touristmanagementsystem.model.DangNhap;
import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;
import com.myctu.touristmanagementsystem.view.AdminView;
import com.myctu.touristmanagementsystem.view.LoginView;
import com.myctu.touristmanagementsystem.view.RegisterView;
import com.myctu.touristmanagementsystem.view.UserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class DangNhapController {

    private LoginView loginView;

    private RegisterView resgisterView;

    private AdminView adminView;

    private UserView userView;

    private NguoiDungHienTai nguoiDung;

    public DangNhapController(LoginView loginView) {
        this.loginView = loginView;
        loginView.addDangNhapListener(new DangNhapListener());
        loginView.addDangKyListener(new DangKyListener());
        loginView.addEnterActionListener(new EnterActionListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    public void disposeLoginView() {
        loginView.dispose();
    }

    public String checkLogin(String tenDangNhap, String MatKhau) { // trả về role
        String role = null; // null : no account
        DangNhapDAO dnDao = new DangNhapDAO();
        if (dnDao.isExistChuDichVu(tenDangNhap)) {
            if (dnDao.validateChuDichVuLogin(tenDangNhap, MatKhau)) {
//                System.out.println("check login cdv");
                return "chudichvu";
            } else {
                return "err";
            }
        }
        if (dnDao.isExistKhachDuLich(tenDangNhap)) {
            if (dnDao.validateKhachDuLichLogin(tenDangNhap, MatKhau)) {
//                System.out.println("check login kdl");
                return "khachdulich";
            } else {
                System.out.println(" err check login kdl");
                return "err";
            }
        }
        return role;
    }

//    public void login(){
//        DangNhap dt = loginView.getDangNhap(); // trả về tên và mật khẩu
//            if (dt != null) {
//                //kiểm tra role để hiện thị lên view
//                String ketQua = checkLogin(dt.getTenDangNhap(), dt.getMatKhau());
//                switch (ketQua) {
//                    case "chudichvu" -> {
//
////                        nguoiDung.setTenDangNhap(dt.getTenDangNhap());
////                        nguoiDung.setVaitro("chudichvu");
//                        nguoiDung.setNguoiDungHienTai(dt.getTenDangNhap(), "chudichvu");
////                        dt.setNguoiDungHienTai((NguoiDung) dt);
//                        adminView = new AdminView();
//                        ChuDichVuController adminController = new ChuDichVuController(adminView);
//                        adminController.showAdminView();
//                        disposeLoginView();
//                    }
//                    case "khachdulich" -> {
////                        nguoiDung.setTenDangNhap(dt.getTenDangNhap());
////                        nguoiDung.setVaitro("chudichvu");
////                        new NguoiDungHienTai(nguoiDung);
////                        dt.setNguoiDungHienTai((NguoiDung) dt);
//                        nguoiDung.setNguoiDungHienTai(dt.getTenDangNhap(), "khachdulich");
//                        userView = new UserView();
//                        KhachDuLichController userController = new KhachDuLichController(userView);
//                        userController.showUserView();
//                        disposeLoginView();
//                    }
//                    case "err" ->
//                        JOptionPane.showMessageDialog(loginView, "Mật Khẩu không đúng! Vui lòng nhập lại.");
//                    case null ->
//                        JOptionPane.showMessageDialog(loginView, "Tài khoản không tồn tại!");
//                    default -> // Xử lý trường hợp bất ngờ
//                        JOptionPane.showMessageDialog(loginView, "Đã xảy ra lỗi, vui lòng thử lại!");
//                }
//            }
//    }
    public void login() {
        DangNhap dt = loginView.getDangNhap(); // Lấy thông tin đăng nhập

        if (dt != null) {
            // Kiểm tra role để xác định quyền
            String ketQua = checkLogin(dt.getTenDangNhap(), dt.getMatKhau());
            DangNhapDAO dangNhapDAO = new DangNhapDAO();

            // Lấy `maKDL` từ cơ sở dữ liệu dựa trên tên đăng nhập
            int maKDL = dangNhapDAO.getMaKDL(dt.getTenDangNhap());

            switch (ketQua) {
                case "chudichvu" -> {
                    // Khởi tạo người dùng hiện tại với vai trò "chudichvu"
                    NguoiDungHienTai.setNguoiDungHienTai(dt.getTenDangNhap(), "chudichvu", maKDL);

                    // Lấy instance và in thông tin để kiểm tra
                    NguoiDungHienTai nguoiDung = NguoiDungHienTai.getInstance();
                    System.out.println("TenDangNhap: " + nguoiDung.getTenDangNhap());
                    System.out.println("MaKDL: " + nguoiDung.getMaKDL());

                    // Mở AdminView
                    adminView = new AdminView();
                    ChuDichVuController adminController = new ChuDichVuController(adminView);
                    adminController.showAdminView();
                    disposeLoginView();
                }
                case "khachdulich" -> {
                    // Khởi tạo người dùng hiện tại với vai trò "khachdulich"
                    NguoiDungHienTai.setNguoiDungHienTai(dt.getTenDangNhap(), "khachdulich", maKDL);

                    // Lấy instance và in thông tin để kiểm tra
                    NguoiDungHienTai nguoiDung = NguoiDungHienTai.getInstance();
                    System.out.println("TenDangNhap: " + nguoiDung.getTenDangNhap());
                    System.out.println("MaKDL: " + nguoiDung.getMaKDL());

                    // Mở UserView
                    userView = new UserView();
                    KhachDuLichController userController = new KhachDuLichController(userView);
                    userController.showUserView();
                    disposeLoginView();
                }
                case "err" -> {
                    JOptionPane.showMessageDialog(loginView, "Mật Khẩu không đúng! Vui lòng nhập lại.");
                }
                case null -> {
                    JOptionPane.showMessageDialog(loginView, "Tài khoản không tồn tại!");
                }
                default -> {
                    JOptionPane.showMessageDialog(loginView, "Đã xảy ra lỗi, vui lòng thử lại!");
                }
            }
        }
    }

    private class DangNhapListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            login();
        }
    }

    private class DangKyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resgisterView = new RegisterView();
            RegisterController resgisterController = new RegisterController(resgisterView);
            resgisterController.showResgisterView();
            disposeLoginView();
        }

    }

    private class EnterActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            login();
        }
    }
}
