/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.dao;

import com.myctu.touristmanagementsystem.database.DatabaseUtils;
import com.myctu.touristmanagementsystem.model.DonHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GIGABYTE
 */
public class DonHangDAO implements DAOInterface<DonHang> {

    public static DonHangDAO getInstance() {
        return new DonHangDAO();
    }

    @Override
    public boolean insert(DonHang t) {
        try {
            java.sql.Connection con = DatabaseUtils.getConnection();

            // Câu lệnh SQL chèn mà không có cột MaDH
            String sql = "INSERT INTO DON_HANG (MaKDL, MaTT, TrangThaiDonHang) VALUES (?, ?, ?)";

            java.sql.PreparedStatement pst = con.prepareStatement(sql);

            // Thiết lập giá trị cho câu lệnh INSERT
            pst.setInt(1, t.getMaKDL()); // MaKDL
            pst.setInt(2, t.getMaTT()); // MaTT
            pst.setBoolean(3, t.isTrangThaiDonHang()); // TrangThaiDonHang

            System.out.println("Bạn đã thực thi: " + sql);
            int rowsInserted = pst.executeUpdate();

            DatabaseUtils.closeConnection(con);

            // Kiểm tra kết quả chèn
            return rowsInserted > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(DonHang t) {
        return false;
    }

    @Override
    public boolean delete(DonHang t) {
        try {
            java.sql.Connection con = DatabaseUtils.getConnection();

            String sql = "DELETE from DON_HANG "//kiểu dữ liệu text thì mới dùng dấu nháy
                    + " WHERE MaKDL = ?";

            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaKDL());

            System.out.println("ban da thuc thi: " + sql);
            pst.executeUpdate();
            DatabaseUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<DonHang> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DonHang selectByTenDangNhap(DonHang t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Object[]> getChiTietDonHang() {
        System.out.println("getChiTietDonHang");
        List<Object[]> lists = new ArrayList<>();
        String sql = "SELECT \n"
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
                + "    DICH_VU ON CHI_TIET_DON_HANG.MaDV = DICH_VU.MaDV;";

        Connection conn = DatabaseUtils.getConnection();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);

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
    //------------------------------------------------------------------------------code mới

    // Phương thức tạo đơn hàng mới
    public boolean createDonHang(int maTT, int maKDL) {
        String sql = "INSERT INTO DON_HANG (MaTT, MaKDL, TrangThaiDonHang) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, maTT);
            stmt.setInt(2, maKDL);
            stmt.setBoolean(3, true); // Đã thanh toán

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int createDonHang1(int maTT, int maKDL) {
        String sql = "INSERT INTO DON_HANG (MaTT, MaKDL, TrangThaiDonHang) VALUES (?, ?, ?)";
        int maDH = -1;

        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, maTT);
            stmt.setInt(2, maKDL);
            stmt.setBoolean(3, true); // Đã thanh toán

            int rowsInserted = stmt.executeUpdate();

            // Lấy MaDH tự động tạo
            if (rowsInserted > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    maDH = rs.getInt(1);
                    System.out.println("Đơn hàng được tạo thành công với MaDH: " + maDH);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maDH;
    }

    //don hang da dat
    public List<Object[]> getTourDetailsByMaKDL(int maKDL) {
        List<Object[]> lists = new ArrayList<>();
        String sql = "SELECT \n"
                + "    DV.TenDV AS DiaDiem, \n"
                + "    CONVERT(DATE, CTDH.ThoiGianDat) AS NgayDat, \n"
                + "    DV.Gia AS Gia, \n"
                + "    CTDH.SoLuongDat AS SoLuong, \n"
                + "    CTDH.TongSoTien AS TongSoTien \n"
                + "FROM \n"
                + "    KHACH_DU_LICH KDL \n"
                + "JOIN \n"
                + "    DON_HANG DH ON KDL.MaKDL = DH.MaKDL \n"
                + "JOIN \n"
                + "    CHI_TIET_DON_HANG CTDH ON DH.MaDH = CTDH.MaDH \n"
                + "JOIN \n"
                + "    DICH_VU DV ON CTDH.MaDV = DV.MaDV \n"
                + "WHERE \n"
                + "    KDL.MaKDL = ?";

        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            // Gán giá trị cho tham số MaKDL trong câu truy vấn
            stm.setInt(1, maKDL);

            try (ResultSet rs = stm.executeQuery()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày
                while (rs.next()) {
                    // Chuyển đổi Timestamp sang String
                    String ngayDat = dateFormat.format(rs.getTimestamp("NgayDat"));

                    // Thêm dữ liệu vào danh sách
                    lists.add(new Object[]{
                        rs.getString("DiaDiem"), // Địa điểm
                        ngayDat, // Ngày đặt (định dạng dd/MM/yyyy)
                        rs.getDouble("Gia"), // Giá vé
                        rs.getInt("SoLuong"), // Số lượng
                        rs.getDouble("TongSoTien") // Tổng số tiền
                    });
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, "Lỗi khi lấy thông tin tour theo MaKDL", ex);
        }
        return lists;
    }

}
