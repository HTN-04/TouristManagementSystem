/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.dao;

import com.myctu.touristmanagementsystem.database.DatabaseUtils;
import com.myctu.touristmanagementsystem.model.ChiTietDonHangModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GIGABYTE
 */
public class ChiTietDonHangDAO {
    
    public boolean addChiTietDonHang(ChiTietDonHangModel chiTietDonHang) {
        String query = "INSERT INTO CHI_TIET_DON_HANG (MaDH, MaDV, ThoiGianDat, SoLuongDat, TongSoTien) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, chiTietDonHang.getMaDH());
            stmt.setInt(2, chiTietDonHang.getMaDV());
            stmt.setObject(3, chiTietDonHang.getThoiGianDat());
            stmt.setInt(4, chiTietDonHang.getSoLuongDat());
            stmt.setDouble(5, chiTietDonHang.getTongSoTien());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
