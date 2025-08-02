/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class DichVuTableModelInUser extends AbstractTableModel {

    private ResourceBundle rb;
    
    private String[] columnNames = {"Địa điểm", "Địa chỉ", "Mô Tả", "Giá", "Số lượng"};
    
    private List<DichVu> dichVus;

    public DichVuTableModelInUser() {
        dichVus = new ArrayList<>();
        local(); 
    }

    private void local() {
        try {
            // Lấy Locale từ LocaleManager và tải ResourceBundle tương ứng
            rb = ResourceBundle.getBundle("com.myctu.touristmanagementsystem.properties.MyResources", LocaleManager.getCurrentLocale());
            System.out.println("Resource bundle loaded successfully.");
        } catch (Exception e) {
            System.err.println("Error loading resource bundle: " + e.getMessage());
        }
    }

    public void setData(List<DichVu> products) {
        this.dichVus = products;
        fireTableDataChanged(); // Cập nhật lại bảng
    }

    @Override
    public String getColumnName(int column) {
        // Lấy tên cột từ ResourceBundle
        switch (column) {
            case 0:
                return rb.getString("place");
            case 1:
                return rb.getString("address"); 
            case 2:
                return rb.getString("description"); 
            case 3:
                return rb.getString("price"); 
            case 4:
                return rb.getString("quantity");
            default:
                return "N/A";
        }
    }

    @Override
    public int getRowCount() {
        return dichVus.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; // Tổng số cột
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DichVu dichVu = dichVus.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dichVu.getTenDV();
            case 1:
                return dichVu.getDiaChi();
            case 2:
                return dichVu.getMoTa();
            case 3:
                return dichVu.getGia();
            case 4:
                return dichVu.getSoLuong();
            default:
                return null;
        }
    }

    // Gọi phương thức này khi muốn thay đổi ngôn ngữ
    public void updateLanguage() {
        local(); // Cập nhật lại ResourceBundle
        fireTableStructureChanged(); // Cập nhật cấu trúc bảng sau khi thay đổi ngôn ngữ
    }
}
