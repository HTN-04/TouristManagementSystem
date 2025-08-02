/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.controller;

import com.myctu.touristmanagementsystem.dao.DichVuDAO;
import com.myctu.touristmanagementsystem.dao.DonHangDAO;
import com.myctu.touristmanagementsystem.model.DVDaDatModel;
import com.myctu.touristmanagementsystem.model.DichVu;
import com.myctu.touristmanagementsystem.model.DichVuTableModelInUser;
import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;
import com.myctu.touristmanagementsystem.view.ThanhToanView;
import com.myctu.touristmanagementsystem.view.UserView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 * @author GIGABYTE
 */
public class DichVuController {

    private UserView userView;

    private ThanhToanView thanhToanView;

    private DichVuDAO dichVuDAO;
//fix
    private DichVuTableModelInUser tableModel;

    //update 1117
    private DVDaDatModel tableDVDaDatModel;

    public DichVuController(UserView view) {
        this.userView = view;
        this.thanhToanView = new ThanhToanView();

        dichVuDAO = new DichVuDAO();
//fix
        tableModel = new DichVuTableModelInUser();

        tableDVDaDatModel = new DVDaDatModel();

        //add sự kiện    
        userView.addTableSelecctionListener(new TableSelecctionListener());

        userView.addTimKiemListener(new TimKiemListener());
        userView.addDatVeListener(new btnDatVe());
        userView.addBtnDongYListener(new btnDongY());
        userView.addThanhToanListener(new btnThanhToan());
        userView.addCapNhatListener(new btnCapNhap());
        userView.addBtnDongYNgayDatListener(new btnDongYNgayDat());
        userView.addXoaListener(new btnXoa());

//        userView.addRefreshProductListener(new RefreshProductListener());
//
//        userView.addAddProductListener(new AddProductListener());
//
//        userView.addEditProductListener(new EditProductListener());
//
//        userView.addDeleteProductListener(new DeleteProductListener());
    }

    public void showDichVuView() {
        try {
            //Đọc dữ liệu từ csdl về để hiển thị lên view
            List<DichVu> dichVus = dichVuDAO.getListProducts();
//add fix            
            tableModel.setData(dichVus);

            userView.setTableModel(tableModel);

            userView.setLocationRelativeTo(null);
            userView.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(DichVuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showTimKiemDichVuView(String key) {
        //Đọc dữ liệu từ csdl về để hiển thị lên view
        List<DichVu> dichVus = dichVuDAO.timKiemDichVu(key);
        //add fix
        tableModel.setData(dichVus);
        userView.setTableModel(tableModel);
        userView.setLocationRelativeTo(null);
        userView.setVisible(true);
        if (dichVus == null || dichVus.isEmpty()) {
            JOptionPane.showMessageDialog(userView, "Không tìm tháy dịch vụ nào !");
        }
    }

    class btnXoa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userView.removeSelectedRow();
        }
    }

    class btnDongYNgayDat implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userView.transferDataToAnotherTableNgayDat();// Gọi phương thức chuyển dữ liệu
        }
    }

    class btnCapNhap implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userView.updateGiaTien();
        }
    }

    class btnThanhToan implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            ThanhToanController thanhToan = new ThanhToanController(thanhToanView);
//            thanhToan.showThanhToanView();

            userView.chuyenDuLieuSangThanhToanView();

        }
    }

    class btnDongY implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userView.updateBtnQuantity();
        }
    }

    class btnDatVe implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            try {
//                // Lấy mã khách du lịch từ người dùng hiện tại
////            int maKDL = NguoiDungHienTai.getMaK
//                int maKDL = NguoiDungHienTai.getInstance().getMaKDL(); // Giả sử có phương thức lấy MaKDL
//                System.out.println("MaKDL:    "+maKDL);
//                int maTT = 1; // Mã thanh toán mặc định
//                boolean trangThai = false; // Trạng thái mặc định là 0 (chưa thanh toán)
//
//                // Tạo đối tượng DonHang
//                DonHang donHang = new DonHang(maKDL, maTT, trangThai);
//
//                // Gọi phương thức chèn dữ liệu vào bảng DON_HANG
//                DonHangDAO donHangDAO = new DonHangDAO();
//                boolean isInserted = donHangDAO.insert(donHang);
//
////                // Kiểm tra kết quả và hiển thị thông báo
////                if (isInserted) {
////                    JOptionPane.showMessageDialog(userView, "Đặt vé thành công!");
////                } else {
////                    JOptionPane.showMessageDialog(userView, "Đặt vé thất bại, vui lòng thử lại.");
////                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(userView, "Lỗi khi đặt vé: " + ex.getMessage());
//            }
            userView.transferDataToAnotherTable();
        }
    }

    private class TableSelecctionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            userView.fillInputForm();
        }
    }

    private class TimKiemListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String key = userView.getKeyTimKiem();
            showTimKiemDichVuView(key);
        }

    }

    private DichVuDAO dao;

    public DichVuController() {
        this.dao = new DichVuDAO();
    }

    public void loadTableData(javax.swing.JTable table) {
        List<DichVu> list = dao.getAllDichVu();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        for (DichVu dv : list) {
            model.addRow(new Object[]{
                dv.getMaDV(),
                dv.getTenDV(),
                //.getSoLuong(),
                dv.getDiaChi(),
                dv.getGia(),
                dv.getMoTa()
            });
        }
    }

    public boolean addDichVu(DichVu dv) {
        return dao.addDichVu(dv);
    }

    public boolean updateDichVu(DichVu dv) {
        return dao.updateDichVu(dv);
    }

    public boolean deleteDichVu(String maDV) {
        return dao.deleteDichVu(maDV);
    }
//1117

    public void showDichVuDatView() {
        //Đọc dữ liệu từ csdl về để hiển thị lên view
        DonHangDAO donHangDAO = new DonHangDAO();

        List<Object[]> lists = donHangDAO.getTourDetailsByMaKDL(NguoiDungHienTai.getInstance().getMaKDL());
        //add fix
        tableDVDaDatModel.setData(lists);
        userView.setTableDVDaDatModel(tableDVDaDatModel);
    }
}
