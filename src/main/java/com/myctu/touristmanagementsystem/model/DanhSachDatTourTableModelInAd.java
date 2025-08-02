/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class DanhSachDatTourTableModelInAd extends AbstractTableModel {

    private String[] columnNames = {"Mã đơn hàng", "Tên người đặt", "Tên tour", "Số lượng đặt", "Thời gian đặt", "Tổng số tiền"};

    private List<Object[]> lists;

    private ResourceBundle rb;

    public DanhSachDatTourTableModelInAd() {
        lists = new ArrayList<>();
        locale();  
    }

    private void locale() {
        try {
            rb = ResourceBundle.getBundle("com.myctu.touristmanagementsystem.properties.MyResources", LocaleManager.getCurrentLocale());
           
            columnNames[0] = rb.getString("bookedServiceId");  // Mã đơn hàng
            columnNames[1] = rb.getString("customerName");  // Tên người đặt
            columnNames[2] = rb.getString("tourName");  // Tên tour
            columnNames[3] = rb.getString("quantityOrdered");  // Số lượng đặt
            columnNames[4] = rb.getString("bookingDate");  // Thời gian đặt
            columnNames[5] = rb.getString("totalAmount");  // Tổng số tiền
            fireTableStructureChanged();
                    
        } catch (MissingResourceException e) {
            System.err.println("Error loading resource bundle: " + e.getMessage());
        }
    }

    public void setData(List<Object[]> lists) {
        this.lists = lists;
        System.out.println("Data set: " + lists.size() + " orders");
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return lists.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] data = lists.get(rowIndex);
        return data[columnIndex];  // Trả về giá trị tại dòng rowIndex và cột columnIndex
    }
}
