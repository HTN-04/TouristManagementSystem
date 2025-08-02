/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.dao;

import com.myctu.touristmanagementsystem.database.DatabaseUtils;
import com.myctu.touristmanagementsystem.model.DichVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author GIGABYTE
 * @author ASUS
 */
public class DichVuDAO {

    public DichVuDAO() {

    }

    /**
     *
     * @return @throws java.sql.SQLException
     * @throws java.sql.SQLException
     */
//    private void chuanHoaDuLieu(String gia, String soLuong){
//        if(soLuong.equals("-1") ){
//            soLuong = "0";
//        }
//        if(gia.equals("0")){
//            gia = "Miễn phí";
//        }
//    }
    private String changeSoLuong(String soLuong) {
        if (soLuong.equals("-1")) {
            return "";
        }
        if(soLuong==null){
            return "";
        }
        return soLuong;
    }

    private String changeGia(String gia) {
        if (gia.equals("0")) {
            return "Miễn phí";
        }
        return gia;
    }

    public List<DichVu> getListProducts() throws SQLException {
        Connection con = DatabaseUtils.getConnection();

        List<DichVu> dichVus = new ArrayList<>();

        String sqlSelect = "select * from DICH_VU";

        try {
            Statement selectStatement;
            ResultSet result;
            selectStatement = con.createStatement();
            result = selectStatement.executeQuery(sqlSelect);

            while (result.next()) {
                String maDV = result.getString("MaDV");
                String maCDV = result.getString("MaCDV");
                String tenDV = result.getString("TenDV");
                String diaChi = result.getString("DiaChi");
                String moTa = result.getString("MoTa");
                String gia = result.getString("Gia");
                String soLuong = result.getString("soLuong");
                String newGia = changeGia(gia);
                String newSoluong = changeSoLuong(soLuong);
//                chuanHoaDuLieu(gia, soLuong);
                System.out.println(gia + " " + newSoluong);
                DichVu dichVu = new DichVu(maDV, maCDV, tenDV, diaChi, moTa, newGia, newSoluong);
                dichVus.add(dichVu);
            }
            selectStatement.close();
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(DichVuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database closed DAO!.");
            }
//            closeConnection(con);
        }
        return dichVus;
    }

    public List<DichVu> timKiemDichVu(String tenDV) {
        Connection conn = DatabaseUtils.getConnection();
        String khongDau = DiacriticRemover.removeDiacritics(tenDV);
        String sqlTimKiem = "select * from DICH_VU where dbo.RemoveDiacritics(TenDV) COLLATE Vietnamese_CI_AS like ? "
                + "or TenDV COLLATE Vietnamese_CI_AS like ?;";
        List<DichVu> dichVus = new ArrayList<>();
        try {
            PreparedStatement smt = conn.prepareStatement(sqlTimKiem);
            smt.setString(1, "%" + khongDau + "%");
            smt.setString(2, "%" + tenDV + "%");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                String maDv = rs.getString("MaDV");
                String maCDV = rs.getString("MaCDV");
                String tenDv = rs.getString("TenDV");
                String diaChi = rs.getString("DiaChi");
                String moTa = rs.getString("MoTa");
                String gia = rs.getString("Gia");
                String soLuong = rs.getString("soLuong");
                String newGia = changeGia(gia);
                String newSoluong = changeSoLuong(soLuong);
//                chuanHoaDuLieu(gia, soLuong);
                System.out.println(gia + " " + newSoluong);
                DichVu dichVu = new DichVu(maDv, maCDV, tenDv, diaChi, moTa, newGia, newSoluong);
                dichVus.add(dichVu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DichVuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DichVuDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dichVus;
    }

    private class DiacriticRemover {

        private static String removeDiacritics(String input) {
            String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
            return Pattern.compile("\\p{InCombiningDiacriticalMarks}+").matcher(normalized).replaceAll("");
        }
    }

    //------------------------------------------------------------code mới
    public int getMaDVByDiaDiem(String diaDiem) {
        String sql = "SELECT MaDV FROM DICH_VU WHERE TenDV = ?";
        int maDV = -1;

        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, diaDiem);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maDV = rs.getInt("MaDV");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maDV;
    }

    public List<DichVu> getAllDichVu() {
        List<DichVu> list = new ArrayList<>();
        String query = "SELECT * FROM DICH_VU";
        try (Connection conn = DatabaseUtils.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                DichVu dv = new DichVu(
                        rs.getString("maDV"),
                        rs.getString("maCDV"),
                        rs.getString("tenDV"),
                        rs.getString("diaChi"),
                        rs.getString("moTa"),
                        rs.getString("Gia"),
                        rs.getString("SoLuong")
                );
                list.add(dv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addDichVu(DichVu dv) {
        String query = "INSERT INTO DichVu (maDV, maCDV, tenDV, diaChi, moTa, Gia, SoLuong) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, dv.getMaDV());
            ps.setString(2, "4");
            ps.setString(3, dv.getTenDV());
            ps.setString(4, dv.getDiaChi());
            ps.setString(5, dv.getMoTa());
            ps.setString(6, dv.getGia());
            ps.setString(7, dv.getSoLuong());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDichVu(DichVu dv) {
        String query = "UPDATE DichVu SET maCDV = ?, tenDV = ?, diaChi = ?, moTa = ?, Gia = ?, SoLuong = ? WHERE maDV = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, dv.getMaCDV());
            ps.setString(2, dv.getTenDV());
            ps.setString(3, dv.getDiaChi());
            ps.setString(4, dv.getMoTa());
            ps.setString(5, dv.getGia());
            ps.setString(6, dv.getSoLuong());
            ps.setString(7, dv.getMaDV());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDichVu(String maDV) {
        String query = "DELETE FROM DichVu WHERE maDV = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, maDV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Object[]> timKiemDichVuDaDatbyCDV(String tenDV) {
        Connection conn = DatabaseUtils.getConnection();
        String khongDau = DiacriticRemover.removeDiacritics(tenDV);

        String sqlTimKiem = "SELECT \n"
                + "    DON_HANG.MaDH AS MaDonHang,\n"
                + "    KHACH_DU_LICH.TenKDL AS TenNguoiDat,\n"
                + "    DICH_VU.TenDV AS TenTour,\n"
                + "    CHI_TIET_DON_HANG.SoLuongDat AS SoLuongDat,\n"
                + "    CHI_TIET_DON_HANG.ThoiGianDat AS ThoiGianDat,\n"
                + "    CHI_TIET_DON_HANG.TongSoTien AS TongSoTien\n"
                + "FROM \n"
                + "    DON_HANG\n"
                + "INNER JOIN \n"
                + "    KHACH_DU_LICH ON DON_HANG.MaKDL = KHACH_DU_LICH.MaKDL\n"
                + "INNER JOIN \n"
                + "    CHI_TIET_DON_HANG ON DON_HANG.MaDH = CHI_TIET_DON_HANG.MaDH\n"
                + "INNER JOIN \n"
                + "    DICH_VU ON CHI_TIET_DON_HANG.MaDV = DICH_VU.MaDV\n"
                + "WHERE dbo.RemoveDiacritics(DICH_VU.TenDV) COLLATE Vietnamese_CI_AS LIKE ? "
                + "OR DICH_VU.TenDV COLLATE Vietnamese_CI_AS LIKE ?;";

        List<Object[]> lists = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(sqlTimKiem);
            stm.setString(1, "%" + khongDau + "%");
            stm.setString(2, "%" + tenDV + "%");
            ResultSet rs = stm.executeQuery();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày
            while (rs.next()) {
                String ngayDat = dateFormat.format(rs.getTimestamp("ThoiGianDat"));
                lists.add(new Object[]{
                    rs.getInt("MaDonHang"),
                    rs.getString("TenNguoiDat"),
                    rs.getString("TenTour"),
                    rs.getInt("SoLuongDat"),
                    ngayDat,
                    rs.getDouble("TongSoTien")
                });
                System.out.println(rs.getString("TenTour"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lists;
    }
}
