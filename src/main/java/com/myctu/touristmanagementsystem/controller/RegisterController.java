/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.controller;

import com.myctu.touristmanagementsystem.dao.ChuDichVuDAO;
import com.myctu.touristmanagementsystem.dao.DangNhapDAO;
import com.myctu.touristmanagementsystem.dao.KhachDuLichDAO;
import com.myctu.touristmanagementsystem.model.ChuDichVu;
import com.myctu.touristmanagementsystem.model.KhachDuLich;
import com.myctu.touristmanagementsystem.model.NguoiDung;
import com.myctu.touristmanagementsystem.view.LoginView;
import com.myctu.touristmanagementsystem.view.RegisterView;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author GIGABYTE
 */
public class RegisterController {

    private RegisterView registerView;

    private LoginView loginView;

    private ChuDichVuDAO chuDichVuDAO;

    private KhachDuLichDAO khachDuLichDAO;

    public RegisterController(RegisterView resgisterView) {
        this.registerView = resgisterView;
        chuDichVuDAO = new ChuDichVuDAO();
        khachDuLichDAO = new KhachDuLichDAO();
        resgisterView.addDangKyListener(new DangKiListener());
        resgisterView.addHuyListener(new HuyListener());
    }

    public void showResgisterView() {
        this.registerView.setVisible(true);
    }

    public void disposeRegisterView() {
        registerView.dispose();
    }

    public boolean isExistedUser(String tenDangNhap) {
        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        return dangNhapDAO.isExistChuDichVu(tenDangNhap) || dangNhapDAO.isExistKhachDuLich(tenDangNhap);
    }

    private class HuyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            disposeRegisterView();
            DangNhapController con = new DangNhapController(new LoginView());
            con.showLoginView();
        }
    }

    private class DangKiListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NguoiDung newUser = registerView.getDangKy();

            if (newUser == null) {
                JOptionPane.showMessageDialog(registerView, "Đăng Ký Thất Bại.");
            } else if (isExistedUser(newUser.getTenDangNhap())) {
                JOptionPane.showMessageDialog(registerView, "Người dùng đã tồn tại! Vui lòng kiểm tra lại.");
            } else {
                try {
                    boolean success;
                    if (newUser.getVaiTro().equals("chudichvu")) {
                        success = chuDichVuDAO.insert((ChuDichVu) newUser);
                        if (success) {
                            JOptionPane.showMessageDialog(registerView, "Đăng ký thành công!");
//                            AdminView adminView = new AdminView();
//                            AdminController adminController = new AdminController(adminView);
//                            adminController.showAdminView();
                            disposeRegisterView();
                            loginView = new LoginView();
                            DangNhapController loginController = new DangNhapController(loginView);
                            loginController.showLoginView();
                        } else {
                            JOptionPane.showMessageDialog(registerView, "Đăng Ký Thất Bại");
                        }
                    } else {
                        success = khachDuLichDAO.insert((KhachDuLich) newUser);
                        if (success) {
                            JOptionPane.showMessageDialog(registerView, "Đăng ký thành công!");
//                            UserView userView = new UserView();
//                            UserController userController = new UserController(userView);
//                            userController.showUserView();
                            disposeRegisterView();
                            loginView = new LoginView();
                            DangNhapController loginController = new DangNhapController(loginView);
                            loginController.showLoginView();
                        } else {
                            JOptionPane.showMessageDialog(registerView, "Đăng Ký Thất Bại");
                        }
                    }
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(registerView, "Lỗi trong quá trình đăng ký: " + ex.getMessage());
                    ex.printStackTrace();  // In ngoại lệ ra console để dễ kiểm tra lỗi
                }
            }
        }
    }
}
