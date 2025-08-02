/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.dao;

import com.myctu.touristmanagementsystem.database.DatabaseUtils;
import com.myctu.touristmanagementsystem.model.ThongKe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GIGABYTE
 */
public class ThongKeDAO implements DAOInterface<ThongKe> {

    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    @Override
    public boolean insert(ThongKe t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(ThongKe t) {
        try {
            java.sql.Connection con = DatabaseUtils.getConnection();
            // Tạo câu lệnh SQL với các giá trị cần cập nhật
            String sql = "UPDATE CHI_TIET_DON_HANG SET MaDV = ?, MaDH = ?, SoLuongDat = ?, ThoiGianDat = ?, TongSoTien = ? WHERE MaDV = ? AND MaDH = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            // Đặt các tham số cho câu lệnh SQL theo thứ tự
            pst.setInt(1, t.getMaDV());
            pst.setInt(2, t.getMaDH());
            pst.setInt(3, t.getSoLuongDat());
            pst.setTimestamp(4, java.sql.Timestamp.valueOf(t.getThoiGianDat()));
            pst.setDouble(5, t.getTongSoTien());

            // Điều kiện WHERE cũng theo thứ tự MaDV và MaDH
            pst.setInt(6, t.getMaDV());
            pst.setInt(7, t.getMaDH());
            // Thực thi câu lệnh SQL
            int result = pst.executeUpdate();
            // In thông báo số dòng đã cập nhật
            System.out.println("Số dòng cập nhật: " + result);
            DatabaseUtils.closeConnection(con);
            return true;

        } catch (Exception ex) {
            Logger.getLogger(DonHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean delete(ThongKe t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ThongKe> selectAll() {
        List<ThongKe> list = new ArrayList<>();
        String sql = "SELECT MaDV, MaDH, SoLuongDat, ThoiGianDat, TongSoTien FROM [DBTOUR].[dbo].[CHI_TIET_DON_HANG]";

        try {
            java.sql.Connection con = DatabaseUtils.getConnection();
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaDV(rs.getInt("MaDV"));
                thongKe.setMaDH(rs.getInt("MaDH"));
                thongKe.setSoLuongDat(rs.getInt("SoLuongDat"));
                thongKe.setThoiGianDat(rs.getTimestamp("ThoiGianDat").toLocalDateTime());
                thongKe.setTongSoTien(rs.getDouble("TongSoTien"));

                list.add(thongKe);

            }
            DatabaseUtils.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ThongKe selectByTenDangNhap(ThongKe t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
//thu nghiem

    public Map<String, Integer> getTouristStatisticsByMonth(String maCDV) {
        Map<String, Integer> statistics = new LinkedHashMap<>();
        String query = """
            SELECT FORMAT(CTDH.ThoiGianDat, 'yyyy-MM') AS Month, SUM(CTDH.SoLuongDat) AS Total
            FROM CHI_TIET_DON_HANG CTDH
            JOIN DICH_VU DV ON CTDH.MaDV = DV.MaDV
            WHERE DV.MaCDV = ?
            GROUP BY FORMAT(CTDH.ThoiGianDat, 'yyyy-MM')
            ORDER BY Month
        """;
        Connection conn = DatabaseUtils.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maCDV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                statistics.put(rs.getString("Month"), rs.getInt("Total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistics;
    }

    public Map<String, Double> getRevenueStatisticsByMonth(String maCDV) {
        Map<String, Double> statistics = new LinkedHashMap<>();
        String query = """
            SELECT FORMAT(CTDH.ThoiGianDat, 'yyyy-MM') AS Month, SUM(CTDH.TongSoTien) AS TotalRevenue
            FROM CHI_TIET_DON_HANG CTDH
            JOIN DICH_VU DV ON CTDH.MaDV = DV.MaDV
            WHERE DV.MaCDV = ?
            GROUP BY FORMAT(CTDH.ThoiGianDat, 'yyyy-MM')
            ORDER BY Month
        """;

        Connection conn = DatabaseUtils.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maCDV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                statistics.put(rs.getString("Month"), rs.getDouble("TotalRevenue"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statistics;
    }
}
