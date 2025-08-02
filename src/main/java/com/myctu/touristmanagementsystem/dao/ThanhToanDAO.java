/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.dao;

import com.myctu.touristmanagementsystem.database.DatabaseUtils;
import com.myctu.touristmanagementsystem.model.ThanhToan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GIGABYTE
 */
public class ThanhToanDAO implements DAOInterface<ThanhToan> {

    @Override
    public boolean insert(ThanhToan t) {
                String sql = "INSERT INTO THANH_TOAN (TrangThaiThanhToan, ThoiGianThanhToan) VALUES (?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setBoolean(1, t.isTrangThaiThanhToan());
            stmt.setTimestamp(2, Timestamp.valueOf(t.getThoiGianThanhToan()));

            int rowsInserted = stmt.executeUpdate();

            // Lấy MaTT tự động tăng
            if (rowsInserted > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    t.setMaTT(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(ThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(ThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ThanhToan> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ThanhToan selectByTenDangNhap(ThanhToan t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
        public int themThanhToan() throws SQLException {
        String query = "INSERT INTO THANH_TOAN (TrangThaiThanhToan, ThoiGianThanhToan) VALUES (0, GETDATE())";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Lấy mã thanh toán vừa tạo
                }
            }
        }
        throw new SQLException("Không thể tạo mới thanh toán.");
    }
    
    
    
    //------------------------------------------------------------------------------code mới
        
        
        
        
        
        
        
        
        
    // Phương thức tạo giao dịch thanh toán và trả về mã MaTT vừa tạo
    public int createThanhToan() {
        int maTT = -1;
        String sql = "INSERT INTO THANH_TOAN (TrangThaiThanhToan, ThoiGianThanhToan) VALUES (?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setBoolean(1, true); // TrangThaiThanhToan = 1 (Đã thanh toán)
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

            stmt.executeUpdate();

            // Lấy mã MaTT vừa tạo
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                maTT = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maTT;
    }
    
    
    
    
    
    
    
    
    
    

}
