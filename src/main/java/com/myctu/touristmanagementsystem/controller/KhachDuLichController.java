/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.controller;

import com.myctu.touristmanagementsystem.dao.KhachDuLichDAO;
import com.myctu.touristmanagementsystem.model.KhachDuLich;
import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;
import com.myctu.touristmanagementsystem.view.LoginView;
import com.myctu.touristmanagementsystem.view.ThanhToanView;
import com.myctu.touristmanagementsystem.view.UserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author GIGABYTE
 */
public class KhachDuLichController {

    private UserView userView;
    private LoginView loginView;
    private ThanhToanView thanhToanView;
    private boolean isChinhSuaHoSo = false;
    private boolean hasUnsavedChangesTab = false; //chua co thay đổi tab
    private int lastSelectedIndex = 0; //luu index cua tab duoc chon
    private NguoiDungHienTai nguoiDungHienTai; //lấy ten đăng nhập và vai trò để query
    private DichVuController control;

    public KhachDuLichController(UserView userView) {
        this.userView = userView;
        control = new DichVuController(userView);
        control.showDichVuView();
        control.showDichVuDatView();
        userView.addDangXuatListener(new DangXuatListener());
        userView.addButtonChinhSuaListener(new ButtonChinhSuaHoSoListener(), new ButtonChinhSuaHoSoListener());
        userView.addCapNhatTaiKhoanListener(new CapNhatTaiKhoanListener());
        userView.addStateChangedListener(new StateChangedListener());
//        userView.addFieldChangeListener(new FieldChangeHandler()); bỏ
    }

    private class DangXuatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginView = new LoginView();
            DangNhapController loginController = new DangNhapController(loginView);

            loginController.showLoginView();
            userView.dispose();
        }

    }

    private class ButtonChinhSuaHoSoListener implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            chuyenDoiSuaDoiChinhSuaHoSo();
            showThongTinTaiKhoan();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            userView.setToolTip(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            userView.setToolTip(false);
        }

    }

    private class CapNhatTaiKhoanListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            KhachDuLich khachDuLich = new KhachDuLich();
            khachDuLich = userView.getThongTinTaiKhoan();
            khachDuLich.setTenDangNhap(NguoiDungHienTai.getInstance().getTenDangNhap());
            System.out.println(khachDuLich.getTenDangNhap() + " " + khachDuLich.getSoDienThoai());
            KhachDuLichDAO khachDuLichDAO = new KhachDuLichDAO();
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn cập nhật thông tin này không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                khachDuLichDAO.update(khachDuLich);
                showThongTinTaiKhoan();
                JOptionPane.showMessageDialog(userView, "Cập nhật thông tin tài khoản thành công!");
                if (isChinhSuaHoSo) {
                    chuyenDoiSuaDoiChinhSuaHoSo();
                }
                hasUnsavedChangesTab = false;
            }
        }

    }
    private boolean flag = true;

    private class StateChangedListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {

            control.showDichVuDatView();
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = tabbedPane.getSelectedIndex();

//            if(isChinhSuaHoSo){
//                flag = false;
//            }
//----------------------Bỏ----------------------
//            if (hasUnsavedChangesTab) {
//                int confirm = JOptionPane.showConfirmDialog(userView,
//                        "Bạn có thay đổi chưa lưu. Bạn có muốn chuyển tab?",
//                        "Xác nhận",
//                        JOptionPane.YES_NO_OPTION);
//
//                if (confirm == JOptionPane.NO_OPTION) {
//                    hasUnsavedChangesTab = false;
//                    isChinhSuaHoSo = false;
//                    tabbedPane.setSelectedIndex(lastSelectedIndex);
//                    return;
//                } else {
//                    chuyenDoiSuaDoiChinhSuaHoSo();
//                    showThongTinTaiKhoan();
//                    hasUnsavedChangesTab = false;
//                }
////            }
            if (lastSelectedIndex == 3) {

                System.out.println("aa");
                KhachDuLichDAO khachDuLichDAO = new KhachDuLichDAO();
                KhachDuLich khachDuLichPre = khachDuLichDAO.getThongTinTaiKhoan(NguoiDungHienTai.getInstance().getTenDangNhap());
                KhachDuLich khachDuLichCurrrent = userView.getThongTinTaiKhoan();
                if (hasUnsavedChangesTab && flag) {
                    System.out.println("true");
                    flag = false;
                    return;
                }
                if (khachDuLichPre != null) {
                    if (!khachDuLichCurrrent.getHoVaTen().equals(khachDuLichPre.getHoVaTen())
                            || !khachDuLichCurrrent.getEmail().equals(khachDuLichPre.getEmail())
                            || !khachDuLichCurrrent.getSoDienThoai().equals(khachDuLichPre.getSoDienThoai())) {
                        System.out.println(khachDuLichPre.getHoVaTen());
                        System.out.println(khachDuLichCurrrent.getHoVaTen());
                        int confirm = JOptionPane.showConfirmDialog(userView,
                                "Bạn có thay đổi chưa lưu. Bạn có muốn chuyển tab?",
                                "Xác nhận",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.NO_OPTION) {
                            System.out.println("No");
                            hasUnsavedChangesTab = true;//co thay doi xay ra
                            flag = true;
//                        isChinhSuaHoSo = false;
                            tabbedPane.setSelectedIndex(lastSelectedIndex);
                            return;
                        }
//                    else{
//                        flag = true; 
//                    }
                    } else { //Neu khong co thay doi nhung dang o che do chinh sua
                        System.out.println("aaaa");
                        chuyenDoiSuaDoiChinhSuaHoSo();
                        showThongTinTaiKhoan();
                        hasUnsavedChangesTab = false;
                    }
                }
            } else if (isChinhSuaHoSo == true) {
                //cap nhat lai thong tin
                chuyenDoiSuaDoiChinhSuaHoSo();
                showThongTinTaiKhoan();
                System.out.println("chuyenDoiSuaDoiChinhSuaHoSo();");
            }
            lastSelectedIndex = selectedIndex;
            System.out.println(lastSelectedIndex);
        }
    }

    private void chuyenDoiSuaDoiChinhSuaHoSo() {
        isChinhSuaHoSo = !isChinhSuaHoSo;
        userView.setEditProfile(isChinhSuaHoSo);
    }

    public void showUserView() {
        userView.setVisible(true);
        showThongTinTaiKhoan();
    }

    private void showThongTinTaiKhoan() {
        nguoiDungHienTai = NguoiDungHienTai.getInstance();
        if (nguoiDungHienTai != null) {
//            System.out.println(nguoiDungHienTai.getTenDangNhap() + " " + nguoiDungHienTai.getVaitro());
            if ("khachdulich".equals(nguoiDungHienTai.getVaitro())) {
                KhachDuLichDAO khachDuLichDAO = new KhachDuLichDAO();
                KhachDuLich khachDuLich = khachDuLichDAO.getThongTinTaiKhoan(nguoiDungHienTai.getTenDangNhap());
                userView.setThongTinTaiKhoan(khachDuLich);
            }
        } else {
            System.out.println("huhu show thong tin tai khoan fail");
        }
    }

    //Not use
    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

}
