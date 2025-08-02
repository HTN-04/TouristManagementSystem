//package com.myctu.touristmanagementsystem.controller;
//
/////*
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//// */
////package com.myctu.touristmanagementsystem.controller;
////
////import com.myctu.touristmanagementsystem.dao.ChiTietDonHangDAO;
////import com.myctu.touristmanagementsystem.dao.DichVuDAO;
////import com.myctu.touristmanagementsystem.dao.DonHangDAO;
////import com.myctu.touristmanagementsystem.dao.ThanhToanDAO;
////import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;
////import com.myctu.touristmanagementsystem.view.ThanhToanView;
////import java.time.LocalDateTime;
////import javax.swing.JOptionPane;
////import javax.swing.table.DefaultTableModel;
////
/////**
//// *
//// * @author GIGABYTE
//// */
////public class ThanhToanController {
////    private ThanhToanView thanhToanView;
////    private ThanhToanDAO thanhToanDAO;
////    //code mới
////    private DonHangDAO donHangDAO;
////    private ChiTietDonHangDAO chiTietDonHangDAO;
////    private DichVuDAO dichVuDAO;
////
////    public ThanhToanController(ThanhToanView thanhToanView) {
////        this.thanhToanView = thanhToanView;
////        //------code mới
////                this.thanhToanDAO = new ThanhToanDAO();
////        this.donHangDAO = new DonHangDAO();
////        this.chiTietDonHangDAO = new ChiTietDonHangDAO();
////        this.dichVuDAO = new DichVuDAO();
////        
////    }
////
////    public ThanhToanView getThanhToanView() {
////        return thanhToanView;
////    }
////
////    public void setThanhToanView(ThanhToanView thanhToanView) {
////        this.thanhToanView = thanhToanView;
////    }
////    
////    
////    public void showThanhToanView(){
////        thanhToanView.setVisible(true);
////        thanhToanView.setLocationRelativeTo(null);
////    }
////    
////    
////    
////    //-----------------------------------------------------------code mới
//////    public void xuLyXacNhanDatVe() {
//////        try {
//////            // Bước 1: Tạo giao dịch thanh toán
//////            int maTT = thanhToanDAO.createThanhToan();
//////            if (maTT == -1) {
//////                JOptionPane.showMessageDialog(thanhToanView, "Lỗi tạo thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//////                return;
//////            }
//////
//////            // Bước 2: Tạo đơn hàng
//////            int maKDL = NguoiDungHienTai.getInstance().getMaKDL();
//////            boolean donHangCreated = donHangDAO.insertDonHang(maTT, maKDL);
//////            if (!donHangCreated) {
//////                JOptionPane.showMessageDialog(thanhToanView, "Lỗi tạo đơn hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//////                return;
//////            }
//////
//////            // Lấy MaDH vừa tạo
//////            int maDH = donHangDAO.getLastInsertedMaDH();
//////
//////            // Bước 3: Duyệt qua các dịch vụ trong bảng thanh toán
//////            DefaultTableModel model = (DefaultTableModel) thanhToanView.getTbThongTinDichVuThanhToan().getModel();
//////            for (int i = 0; i < model.getRowCount(); i++) {
//////                String diaDiem = model.getValueAt(i, 0).toString();
//////                LocalDateTime thoiGianDat = LocalDateTime.parse(model.getValueAt(i, 1).toString());
//////                int soLuong = Integer.parseInt(model.getValueAt(i, 3).toString());
//////                double giaTien = Double.parseDouble(model.getValueAt(i, 2).toString());
//////                double thanhTien = Double.parseDouble(model.getValueAt(i, 4).toString());
//////
//////                // Lấy MaDV từ bảng DICH_VU
//////                int maDV = dichVuDAO.getMaDVByDiaDiem(diaDiem);
//////                if (maDV == -1) {
//////                    JOptionPane.showMessageDialog(thanhToanView, "Lỗi tìm mã dịch vụ cho: " + diaDiem, "Lỗi", JOptionPane.ERROR_MESSAGE);
//////                    continue;
//////                }
//////
//////                // Tạo ChiTietDonHangModel
//////                ChiTietDonHangModel chiTiet = new ChiTietDonHangModel(maDH, maDV, thoiGianDat, soLuong, giaTien, thanhTien);
//////
//////                // Thêm chi tiết đơn hàng
//////                boolean chiTietInserted = chiTietDonHangDAO.insertChiTietDonHang(chiTiet);
//////                if (!chiTietInserted) {
//////                    JOptionPane.showMessageDialog(thanhToanView, "Lỗi thêm chi tiết đơn hàng cho: " + diaDiem, "Lỗi", JOptionPane.ERROR_MESSAGE);
//////                }
//////            }
//////
//////            // Hiển thị thông báo thành công
//////            JOptionPane.showMessageDialog(thanhToanView, "Đặt vé thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
//////
//////        } catch (Exception e) {
//////            e.printStackTrace();
//////            JOptionPane.showMessageDialog(thanhToanView, "Lỗi xử lý đặt vé!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//////        }
//////    }
////    
////}
//import com.myctu.touristmanagementsystem.dao.ChiTietDonHangDAO;
//import com.myctu.touristmanagementsystem.dao.DichVuDAO;
//import com.myctu.touristmanagementsystem.dao.DonHangDAO;
//import com.myctu.touristmanagementsystem.dao.ThanhToanDAO;
//import com.myctu.touristmanagementsystem.model.ChiTietDonHangModel;
//import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;
//import com.myctu.touristmanagementsystem.view.ThanhToanView;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import javax.swing.JOptionPane;
//
//public class ThanhToanController {
//
//    private ThanhToanDAO thanhToanDAO;
//    private DonHangDAO donHangDAO;
//    private ChiTietDonHangDAO chiTietDonHangDAO;
//    private DichVuDAO dichVuDAO;
//
//    public ThanhToanController() {
//        this.thanhToanDAO = new ThanhToanDAO();
//        this.donHangDAO = new DonHangDAO();
//        this.chiTietDonHangDAO = new ChiTietDonHangDAO();
//        this.dichVuDAO = new DichVuDAO();
//    }
//
//    public void btnXacNhanDatVeActionPerformed(ThanhToanView view) {
//        try {
//            // Bước 1: Tạo giao dịch thanh toán
//            int maTT = thanhToanDAO.createThanhToan();
//            if (maTT == -1) {
//                JOptionPane.showMessageDialog(view, "Không thể tạo giao dịch thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Bước 2: Lấy mã khách du lịch
//            int maKDL = NguoiDungHienTai.getInstance().getMaKDL();
//
//            // Bước 3: Tạo đơn hàng mới và lấy MaDH
//            int maDH = donHangDAO.createDonHang1(maTT, maKDL);
//            if (maDH == -1) {
//                JOptionPane.showMessageDialog(view, "Không thể tạo đơn hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Bước 4: Lấy dữ liệu từ bảng tbThongTinDichVuThanhToan và thêm vào CHI_TIET_DON_HANG
//            for (int i = 0; i < view.getTbThongTinDichVuThanhToan().getRowCount(); i++) {
//                // Lấy thông tin từ table
//                String diaDiem = view.getTbThongTinDichVuThanhToan().getValueAt(i, 0).toString();
////                LocalDateTime thoiGianDat = LocalDateTime.parse(view.getTbThongTinDichVuThanhToan().getValueAt(i, 1).toString());
//                // Định dạng ngày giờ chính xác
//
//// Định dạng chỉ lấy ngày, tháng, năm
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//try {
//    // Lấy chuỗi ngày từ bảng
//    String ngayDat = view.getTbThongTinDichVuThanhToan().getValueAt(i, 1).toString();
//
//// Chuyển đổi chuỗi thành LocalDate (chỉ lấy ngày, tháng, năm)
//LocalDate thoiGianDatLocal = LocalDate.parse(ngayDat, formatter);
//
//// Chuyển đổi LocalDate thành java.sql.Date để lưu vào cơ sở dữ liệu
//java.sql.Date thoiGianDat = java.sql.Date.valueOf(thoiGianDatLocal);
//
//// In ra giá trị để kiểm tra
//System.out.println("Ngày đặt: " + thoiGianDat);
//
//
//    // In ra giá trị để kiểm tra
//    System.out.println("Ngày đặt: " + thoiGianDat);
//
//} catch (java.time.format.DateTimeParseException e) {
//    System.out.println("Định dạng ngày không hợp lệ: " + e.getMessage());
//    JOptionPane.showMessageDialog(view, "Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng 'yyyy-MM-dd'.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//    return;
//}
//
//
//                int soLuongDat = Integer.parseInt(view.getTbThongTinDichVuThanhToan().getValueAt(i, 2).toString());
//                String tongSoTienStr = view.getTbThongTinDichVuThanhToan().getValueAt(i, 4).toString().trim();
//
//// Kiểm tra nếu giá trị là "Miễn phí"
//                double tongSoTien;
//                if (tongSoTienStr.equalsIgnoreCase("Miễn phí")) {
//                    tongSoTien = 0.0;
//                } else {
//                    try {
//                        tongSoTien = Double.parseDouble(tongSoTienStr);
//                    } catch (NumberFormatException e) {
//                        System.out.println("Lỗi định dạng số: " + tongSoTienStr);
//                        JOptionPane.showMessageDialog(view, "Định dạng số không hợp lệ: " + tongSoTienStr, "Lỗi", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//                }
//
//// Lấy MaDV từ bảng DICH_VU thông qua địa điểm
//                int maDV = dichVuDAO.getMaDVByDiaDiem(diaDiem);
//
//                if (maDV == -1) {
//                    JOptionPane.showMessageDialog(view, "Không tìm thấy mã dịch vụ cho địa điểm: " + diaDiem, "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    continue;
//                }
//
//                // Thêm chi tiết đơn hàng vào bảng CHI_TIET_DON_HANG
//                ChiTietDonHangModel chiTiet = new ChiTietDonHangModel(maDH, maDV, thoiGianDat.toLocalDate(), soLuongDat, tongSoTien);
//
//                boolean successChiTiet = chiTietDonHangDAO.addChiTietDonHang(chiTiet);
//
//                if (!successChiTiet) {
//                    JOptionPane.showMessageDialog(view, "Thêm chi tiết đơn hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//
//            // Hiển thị thông báo thành công
//            JOptionPane.showMessageDialog(view, "Đặt vé thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi đặt vé!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}
//package com.myctu.touristmanagementsystem.controller;
//
//import com.myctu.touristmanagementsystem.dao.ChiTietDonHangDAO;
//import com.myctu.touristmanagementsystem.dao.DichVuDAO;
//import com.myctu.touristmanagementsystem.dao.DonHangDAO;
//import com.myctu.touristmanagementsystem.dao.ThanhToanDAO;
//import com.myctu.touristmanagementsystem.model.ChiTietDonHangModel;
//import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;
//import com.myctu.touristmanagementsystem.view.ThanhToanView;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import javax.swing.JOptionPane;
//
//public class ThanhToanController {
//
//    private ThanhToanDAO thanhToanDAO;
//    private DonHangDAO donHangDAO;
//    private ChiTietDonHangDAO chiTietDonHangDAO;
//    private DichVuDAO dichVuDAO;
//
//    public ThanhToanController() {
//        this.thanhToanDAO = new ThanhToanDAO();
//        this.donHangDAO = new DonHangDAO();
//        this.chiTietDonHangDAO = new ChiTietDonHangDAO();
//        this.dichVuDAO = new DichVuDAO();
//    }
//
//    public void btnXacNhanDatVeActionPerformed(ThanhToanView view) {
//        try {
//            // Bước 1: Tạo giao dịch thanh toán
//            int maTT = thanhToanDAO.createThanhToan();
//            if (maTT == -1) {
//                JOptionPane.showMessageDialog(view, "Không thể tạo giao dịch thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Bước 2: Lấy mã khách du lịch
//            int maKDL = NguoiDungHienTai.getInstance().getMaKDL();
//
//            // Bước 3: Tạo đơn hàng mới và lấy MaDH
//            int maDH = donHangDAO.createDonHang1(maTT, maKDL);
//            if (maDH == -1) {
//                JOptionPane.showMessageDialog(view, "Không thể tạo đơn hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Bước 4: Lấy dữ liệu từ bảng tbThongTinDichVuThanhToan và thêm vào CHI_TIET_DON_HANG
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            for (int i = 0; i < view.getTbThongTinDichVuThanhToan().getRowCount(); i++) {
//                // Lấy thông tin từ bảng
//                String diaDiem = view.getTbThongTinDichVuThanhToan().getValueAt(i, 0).toString();
//                String ngayDatStr = view.getTbThongTinDichVuThanhToan().getValueAt(i, 1).toString();
//
//                // Chuyển đổi chuỗi thành LocalDate (chỉ lấy ngày, tháng, năm)
//                LocalDate thoiGianDat;
//                try {
//                    thoiGianDat = LocalDate.parse(ngayDatStr, formatter);
//                    System.out.println("Ngày đặt: " + thoiGianDat);
//                } catch (java.time.format.DateTimeParseException e) {
//                    System.out.println("Định dạng ngày không hợp lệ: " + e.getMessage());
//                    JOptionPane.showMessageDialog(view, "Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng 'yyyy-MM-dd'.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//
//                int soLuongDat = Integer.parseInt(view.getTbThongTinDichVuThanhToan().getValueAt(i, 2).toString());
//                String tongSoTienStr = view.getTbThongTinDichVuThanhToan().getValueAt(i, 4).toString().trim();
//
//                // Kiểm tra nếu giá trị là "Miễn phí"
//                double tongSoTien;
//                if (tongSoTienStr.equalsIgnoreCase("Miễn phí")) {
//                    tongSoTien = 0.0;
//                } else {
//                    try {
//                        tongSoTien = Double.parseDouble(tongSoTienStr);
//                    } catch (NumberFormatException e) {
//                        System.out.println("Lỗi định dạng số: " + tongSoTienStr);
//                        JOptionPane.showMessageDialog(view, "Định dạng số không hợp lệ: " + tongSoTienStr, "Lỗi", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//                }
//
//                // Lấy MaDV từ bảng DICH_VU thông qua địa điểm
//                int maDV = dichVuDAO.getMaDVByDiaDiem(diaDiem);
//                if (maDV == -1) {
//                    JOptionPane.showMessageDialog(view, "Không tìm thấy mã dịch vụ cho địa điểm: " + diaDiem, "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    continue;
//                }
//
//                // Tạo đối tượng ChiTietDonHangModel và thêm vào cơ sở dữ liệu
//                ChiTietDonHangModel chiTiet = new ChiTietDonHangModel(maDH, maDV, thoiGianDat, soLuongDat, tongSoTien);
//                boolean successChiTiet = chiTietDonHangDAO.addChiTietDonHang(chiTiet);
//
//                if (!successChiTiet) {
//                    JOptionPane.showMessageDialog(view, "Thêm chi tiết đơn hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//            }
//
//            // Hiển thị thông báo thành công
//            JOptionPane.showMessageDialog(view, "Đặt vé thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi đặt vé!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}
package com.myctu.touristmanagementsystem.controller;

import com.myctu.touristmanagementsystem.dao.ChiTietDonHangDAO;
import com.myctu.touristmanagementsystem.dao.DichVuDAO;
import com.myctu.touristmanagementsystem.dao.DonHangDAO;
import com.myctu.touristmanagementsystem.dao.ThanhToanDAO;
import com.myctu.touristmanagementsystem.model.ChiTietDonHangModel;
import com.myctu.touristmanagementsystem.model.NguoiDungHienTai;
import com.myctu.touristmanagementsystem.view.ThanhToanView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class ThanhToanController {

    private ThanhToanDAO thanhToanDAO;
    private DonHangDAO donHangDAO;
    private ChiTietDonHangDAO chiTietDonHangDAO;
    private DichVuDAO dichVuDAO;

    public ThanhToanController() {
        this.thanhToanDAO = new ThanhToanDAO();
        this.donHangDAO = new DonHangDAO();
        this.chiTietDonHangDAO = new ChiTietDonHangDAO();
        this.dichVuDAO = new DichVuDAO();
    }

    public void btnXacNhanDatVeActionPerformed(ThanhToanView view) {
        try {
            // Bước 1: Tạo giao dịch thanh toán
            int maTT = thanhToanDAO.createThanhToan();
            if (maTT == -1) {
                JOptionPane.showMessageDialog(view, "Không thể tạo giao dịch thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Bước 2: Lấy mã khách du lịch
            int maKDL = NguoiDungHienTai.getInstance().getMaKDL();

            // Bước 3: Tạo đơn hàng mới và lấy MaDH
            int maDH = donHangDAO.createDonHang1(maTT, maKDL);
            if (maDH == -1) {
                JOptionPane.showMessageDialog(view, "Không thể tạo đơn hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Bước 4: Lấy dữ liệu từ bảng tbThongTinDichVuThanhToan và thêm vào CHI_TIET_DON_HANG
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (int i = 0; i < view.getTbThongTinDichVuThanhToan().getRowCount(); i++) {
                String diaDiem = view.getTbThongTinDichVuThanhToan().getValueAt(i, 0).toString();
                String ngayDatStr = view.getTbThongTinDichVuThanhToan().getValueAt(i, 1).toString();

                LocalDate thoiGianDat;
                try {
                    thoiGianDat = LocalDate.parse(ngayDatStr, formatter);
                    System.out.println("Ngày đặt: " + thoiGianDat);
                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println("Định dạng ngày không hợp lệ: " + e.getMessage());
                    JOptionPane.showMessageDialog(view, "Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng 'yyyy-MM-dd'.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int soLuongDat = Integer.parseInt(view.getTbThongTinDichVuThanhToan().getValueAt(i, 2).toString());
                String tongSoTienStr = view.getTbThongTinDichVuThanhToan().getValueAt(i, 4).toString().trim();

                double tongSoTien;
                if (tongSoTienStr.equalsIgnoreCase("Miễn phí")) {
                    tongSoTien = 0.0;
                } else {
                    try {
                        tongSoTien = Double.parseDouble(tongSoTienStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi định dạng số: " + tongSoTienStr);
                        JOptionPane.showMessageDialog(view, "Định dạng số không hợp lệ: " + tongSoTienStr, "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                int maDV = dichVuDAO.getMaDVByDiaDiem(diaDiem);
                if (maDV == -1) {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy mã dịch vụ cho địa điểm: " + diaDiem, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                ChiTietDonHangModel chiTiet = new ChiTietDonHangModel(maDH, maDV, thoiGianDat, soLuongDat, tongSoTien);
                boolean successChiTiet = chiTietDonHangDAO.addChiTietDonHang(chiTiet);

                if (!successChiTiet) {
                    JOptionPane.showMessageDialog(view, "Thêm chi tiết đơn hàng thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            JOptionPane.showMessageDialog(view, "Đặt vé thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi khi đặt vé!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
