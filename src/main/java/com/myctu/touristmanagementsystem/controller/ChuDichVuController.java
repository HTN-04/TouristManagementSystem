/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.controller;

import com.myctu.touristmanagementsystem.dao.ChuDichVuDAO;
import com.myctu.touristmanagementsystem.dao.DichVuDAO;
import com.myctu.touristmanagementsystem.dao.DonHangDAO;
import com.myctu.touristmanagementsystem.model.ChuDichVu;
import com.myctu.touristmanagementsystem.model.DanhSachDatTourTableModelInAd;
import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;
import com.myctu.touristmanagementsystem.view.AdminView;
import com.myctu.touristmanagementsystem.view.LoginView;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ChuDichVuController {

    private AdminView adminView;

    private boolean isChinhSuaHoSo = false;

    private boolean hasUnsavedChanges = false; //chua co thay đổi tab

    private String lastSelectedIndex = ""; //luu index cua tab duoc chon
//fix
    private DanhSachDatTourTableModelInAd tableDSDatTourModel;

    public ChuDichVuController(AdminView adminView) {
        this.adminView = adminView;
        //cai dat cho trang thong tin tai khoan
        this.adminView.getChinhSuaHoSoAdminView().addButtonChinhSuaListener(new ButtonChinhSuaHoSoListener(), new ButtonChinhSuaHoSoListener());
        this.adminView.getChinhSuaHoSoAdminView().addButtonCapNhatTaiKhoanListener(new ButtonCapNhatTaiKhoanListener());
        this.adminView.addMenuItemDangXuatListener(new MenuDangXuatListener());
        this.adminView.addMenuEditAccountStateChanged(new StateChangedListener());
        this.adminView.getDanhSachDatTourView().addTimKiemListener(new TimKiemListener());

        //CODE DS DAT TOUR
        tableDSDatTourModel = new DanhSachDatTourTableModelInAd();

        //add sự kiện    
//        adminView.getDanhSachDatTourView().addTableSelecctionListener(new DichVuController.TableSelecctionListener());
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    public void showAdminView() {
        adminView.setVisible(true);
        adminView.addMenuItemDangXuatListener(new MenuDangXuatListener());
//        adminView.addMenuChinhSuaHoSoListener(new MenuChinhSuaHoSoListener());
//        adminView.addMenuQuanLyTourListener(new MenuQuanLyTourListener());
//        adminView.addMenuDSDatTourListener(new MenuDSDatTourListener());
//        adminView.addMenuThongKeListener(new MenuThongKeListener());
        StateChangedListener listener = new StateChangedListener();
        adminView.addMenuItemDangXuatListener(listener);
        adminView.addMenuChinhSuaHoSoListener(listener);
        adminView.addMenuQuanLyTourListener(listener);
        adminView.addMenuDSDatTourListener(listener);
        adminView.addMenuThongKeListener(listener);

    }

    private void showPanelMain(String panelName) {
        CardLayout cardLayout = (CardLayout) adminView.getPnMainAdmin().getLayout();
        cardLayout.show(adminView.getPnMainAdmin(), panelName);
        System.out.println("Đang hiển thị panel: " + panelName);
        adminView.getPnMainAdmin().revalidate();
        adminView.getPnMainAdmin().repaint();
    }

    private void showThongTinTaiKhoan() {
        NguoiDungHienTai nguoiDungHienTai = NguoiDungHienTai.getInstance();
        if (nguoiDungHienTai != null) {
            if (nguoiDungHienTai.getVaitro().equals("chudichvu")) {
                ChuDichVuDAO cdvdao = new ChuDichVuDAO();
                ChuDichVu cdv = cdvdao.getThongTinTaiKhoan(nguoiDungHienTai.getTenDangNhap());
                adminView.getChinhSuaHoSoAdminView().setThongTinTaiKhoan(cdv);
            }
        }
    }

    public void disposeAdminView() {
        adminView.dispose();
    }

    private void chuyenDoiSuaDoiChinhSuaHoSo() {
        isChinhSuaHoSo = !isChinhSuaHoSo;
        adminView.getChinhSuaHoSoAdminView().setEditProfile(isChinhSuaHoSo);
    }

    private boolean flag = true;

    private class StateChangedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Change");
            boolean stayOnPanel = false; // Biến flag để kiểm soát việc ở lại panel hiện tại
            JMenuItem selectedItem = (JMenuItem) e.getSource();

            // Lấy index của JMenuItem được chọn (nếu bạn cần lưu index)
            String selectedIndex = adminView.getSelectedMenuItemName(selectedItem);
            System.out.println("Selected menu item index: " + selectedIndex);
            if (lastSelectedIndex.equals("ChinhSuaTK")) {
                System.out.println("lastSelectedIndex: ChinhSuaTK");
                ChuDichVuDAO chuDichVuDAO = new ChuDichVuDAO();
                ChuDichVu khachDuLichPre = chuDichVuDAO.getThongTinTaiKhoan(NguoiDungHienTai.getInstance().getTenDangNhap());
                ChuDichVu khachDuLichCurrrent = adminView.getChinhSuaHoSoAdminView().getThongTinTaiKhoan();

//                if (hasUnsavedChanges && flag) {
//                    System.out.println("hasUnsavedChangesTab: true");
//                    flag = false;
//                    return;
//                }
                if (khachDuLichPre != null) {
                    if (!khachDuLichCurrrent.getHoVaTen().equals(khachDuLichPre.getHoVaTen())
                            || !khachDuLichCurrrent.getEmail().equals(khachDuLichPre.getEmail())
                            || !khachDuLichCurrrent.getSoDienThoai().equals(khachDuLichPre.getSoDienThoai())) {
                        System.out.println(khachDuLichPre.getHoVaTen());
                        System.out.println(khachDuLichCurrrent.getHoVaTen());
                        int confirm = JOptionPane.showConfirmDialog(adminView,
                                "Bạn có thay đổi chưa lưu. Bạn có muốn chuyển tab?",
                                "Xác nhận",
                                JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.NO_OPTION) {
                            System.out.println("No");
                            hasUnsavedChanges = true;//co thay doi xay ra
                            flag = true;
                            stayOnPanel = true; // Đặt flag để ở lại panel hiện tại
                            System.out.println("bỏ qua");
                            return;
                        } else {
                            flag = true;
                            stayOnPanel = false;
                            switch (selectedIndex) {
//                            case "DangXuat": // Trang chủ
//                                showHomePage();
//                                break;
                                case "QuanLyTour": // Quản lý tài khoản
                                    showPanelMain("QuanLyTour");
                                    break;
                                case "DsDatTour": // Dịch vụ
                                    showPanelMain("DanhSachDatTour");
                                    break;
                                case "BaoCaoThongKe":
                                    showPanelMain("ThongKeBaoCao");
                                    break;
                                // Thêm các case khác tương ứng với các JMenuItem khác
                                default:
                                    System.out.println("Menu item không xác định");
                                    break;
                            }
                        }
                    } else { //Neu khong co thay doi nhung dang o che do chinh sua
                        System.out.println("khong co thay doi nhung dang o che do chinh sua");
                        chuyenDoiSuaDoiChinhSuaHoSo();
                        showThongTinTaiKhoan();
                        hasUnsavedChanges = false;
                    }
                }
            } else if (isChinhSuaHoSo == true) { //Nếu ở trạng thái chỉnh sửa
                //cap nhat lai thong tin
                chuyenDoiSuaDoiChinhSuaHoSo();
                showThongTinTaiKhoan();
                System.out.println("chuyenDoiSuaDoiChinhSuaHoSo();");
            }
            swichtPanel(selectedIndex);
            lastSelectedIndex = selectedIndex;
            System.out.println("last" + lastSelectedIndex);
            System.out.println("bỏ qua 2");
            System.out.println("flag " + flag);
            System.out.println("has unsave " + hasUnsavedChanges);
        }
    }

    private void swichtPanel(String selectedIndex) {
        switch (selectedIndex) {
            case "QuanLyTour":
                showPanelMain("QuanLyTour");
                break;
            case "DsDatTour":
                showDsDatTour();
                showPanelMain("DanhSachDatTour");
                break;
            case "BaoCaoThongKe":
                showPanelMain("ThongKeBaoCao");
                break;
            case "ChinhSuaTK":
                showThongTinTaiKhoan();
                showPanelMain("ChinhSua");
                break;
            default:
                System.out.println("Menu item không xác định");
                break;
        }
    }

    private class ButtonChinhSuaHoSoListener implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hasUnsavedChanges = false;
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
            adminView.getChinhSuaHoSoAdminView().setToolTip(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            adminView.getChinhSuaHoSoAdminView().setToolTip(false);
        }

    }

    private class ButtonCapNhatTaiKhoanListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ChuDichVu chuDichVu = new ChuDichVu();
            chuDichVu = adminView.getChinhSuaHoSoAdminView().getThongTinTaiKhoan();
            chuDichVu.setTenDangNhap(NguoiDungHienTai.getInstance().getTenDangNhap());
            System.out.println(chuDichVu.getTenDangNhap() + " " + chuDichVu.getSoDienThoai());
            ChuDichVuDAO chuDichVuDAO = new ChuDichVuDAO();
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn cập nhật thông tin này không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                chuDichVuDAO.update(chuDichVu);
                showThongTinTaiKhoan();
                JOptionPane.showMessageDialog(adminView, "Cập nhật thông tin tài khoản thành công!");
                if (isChinhSuaHoSo) {
                    chuyenDoiSuaDoiChinhSuaHoSo();
                }
                hasUnsavedChanges = false;
            }
        }
    }

    private class MenuDangXuatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginView loginView = new LoginView();
            DangNhapController loginController = new DangNhapController(loginView);
            loginController.showLoginView();
            disposeAdminView();
        }
    }

//    private class MenuChinhSuaHoSoListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            showThongTinTaiKhoan();
//            showPanelMain("ChinhSua");
//        }
//    }
//
//    private class MenuQuanLyTourListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            showPanelMain("QuanLyTour");
//        }
//    }
//
//    private class MenuDSDatTourListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            showPanelMain("DanhSachDatTour");
//        }
//    }
//
//    private class MenuThongKeListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            showPanelMain("ThongKeBaoCao");
//        }
//    }
    //CODE DS DAT TOUR
//     private class TableSelecctionListener implements ListSelectionListener {
//
//        @Override
//        public void valueChanged(ListSelectionEvent e) {
//            adminView.fillInputForm();
//        }
//    }
    public void showDsDatTour() {
        //Đọc dữ liệu từ csdl về để hiển thị lên view
        DonHangDAO donHangDAO = new DonHangDAO();
        List<Object[]> lists = donHangDAO.getChiTietDonHang();
        //add fix
        tableDSDatTourModel.setData(lists);
        adminView.getDanhSachDatTourView().setTableModel(tableDSDatTourModel);
    }

    public void showTimKiemDichVuView(String key) {
        //Đọc dữ liệu từ csdl về để hiển thị lên view
//        List<DichVu> dichVus = dichVuDAO.timKiemDichVu(key);
        DichVuDAO dvDao = new DichVuDAO();
        List<Object[]> lists = dvDao.timKiemDichVuDaDatbyCDV(key);
        //add fix
        tableDSDatTourModel.setData(lists);
        adminView.getDanhSachDatTourView().setTableModel(tableDSDatTourModel);
        if (lists == null || lists.isEmpty()) {
            JOptionPane.showMessageDialog(adminView, "Không tìm tháy dịch vụ nào !");
        }
    }

    private class TimKiemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String key = adminView.getDanhSachDatTourView().getKeyTimKiem();
            showTimKiemDichVuView(key);
        }

    }
}
