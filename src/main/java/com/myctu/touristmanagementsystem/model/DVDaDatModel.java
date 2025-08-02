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
public class DVDaDatModel extends AbstractTableModel {

    private String[] columnNames = {"Địa điểm", "Ngày Đặt", "Giá vé", "Số lượng", "Tổng số tiền"};
    private List<Object[]> lists;
    private ResourceBundle rb; 

    public DVDaDatModel() {
        lists = new ArrayList<>();
        locale();
    }

    private void locale() {
        try {
            rb = ResourceBundle.getBundle("com.myctu.touristmanagementsystem.properties.MyResources", LocaleManager.getCurrentLocale());
            columnNames[0] = rb.getString("place");
            columnNames[1] = rb.getString("bookingDate");
            columnNames[2] = rb.getString("price");
            columnNames[3] = rb.getString("quantity");
            columnNames[4] = rb.getString("totalAmount");
            fireTableStructureChanged();  //cap nhat lai bang
        } catch (MissingResourceException e) {
            System.err.println("Error loading resource bundle: " + e.getMessage());
        }
    }

    public void setData(List<Object[]> lists) {
        this.lists = lists;
        System.out.println("Data set: " + lists.size() + " dich vu");
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
        return data[columnIndex];
    }
}
