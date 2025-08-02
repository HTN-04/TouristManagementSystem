/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.myctu.touristmanagementsystem.view;

import com.myctu.touristmanagementsystem.controller.DichVuController;
import com.myctu.touristmanagementsystem.database.DatabaseUtils;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class QuanLyTour extends javax.swing.JPanel {

    private DichVuController controller = new DichVuController();

    private DefaultTableModel tableModel; // Quản lý dữ liệu của JTable

    public QuanLyTour() {
        initComponents();
        initTableModel();
        addEventHandlers();
        addTableSelectionListener();
        loadTableData();
        controller.loadTableData(tbDanhSachTour);
    }

    // Tạo model cho bảng
    private void initTableModel() {
        tableModel = new DefaultTableModel(
                new Object[]{"Mã số", "Tên tour", "Địa điểm", "Giá tiền", "Mô tả", "Số lượng"}, 0);
        tbDanhSachTour.setModel(tableModel);
    }

    // Gắn sự kiện cho các nút
    private void addEventHandlers() {
        // Thêm dữ liệu
        btnThem.addActionListener(evt -> {
            // Hiển thị hộp thoại nhập thông tin
            String tenDV = JOptionPane.showInputDialog(this, "Nhập tên dịch vụ:");
            String diaChi = JOptionPane.showInputDialog(this, "Nhập địa chỉ:");
            String gia = JOptionPane.showInputDialog(this, "Nhập giá:");
            String moTa = JOptionPane.showInputDialog(this, "Nhập mô tả:");
            String soLuong = JOptionPane.showInputDialog(this, "Nhập số lượng:");

            if (tenDV != null && diaChi != null && gia != null && moTa != null && soLuong != null) {
                try (Connection conn = DatabaseUtils.getConnection()) {
                    String sql = "INSERT INTO DICH_VU (MaCDV,tenDV, diaChi, Gia, moTa, soLuong) VALUES (4,?, ?, ?, ?, ?)";
                    var pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                    pstmt.setString(1, tenDV);
                    pstmt.setString(2, diaChi);
                    pstmt.setInt(3, Integer.parseInt(gia));
                    pstmt.setString(4, moTa);
                    pstmt.setString(5, soLuong);

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {
                        // Lấy giá trị `maDV` vừa được sinh ra
                        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                int maDV = generatedKeys.getInt(1); // Lấy giá trị `maDV`

                                // Cập nhật `JTable`
                                //tableModel.addRow(new Object[]{maDV, tenDV, diaChi, gia, moTa});
                                loadTableData();
                                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể thêm dịch vụ!");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            }
        });

        // Sửa dữ liệu
        btnSua.addActionListener(evt -> {
            int selectedRow = tbDanhSachTour.getSelectedRow();
            if (selectedRow != -1) {
                String maDV = (String) tableModel.getValueAt(selectedRow, 0);
                //String maCDV = JOptionPane.showInputDialog(this, "Sửa mã chủ dịch vụ:", tableModel.getValueAt(selectedRow, 1));
                String tenDV = JOptionPane.showInputDialog(this, "Sửa tên dịch vụ:", tableModel.getValueAt(selectedRow, 1));
                String diaChi = JOptionPane.showInputDialog(this, "Sửa địa chỉ:", tableModel.getValueAt(selectedRow, 2));
                String gia = JOptionPane.showInputDialog(this, "Sửa giá:", tableModel.getValueAt(selectedRow, 3));
                String moTa = JOptionPane.showInputDialog(this, "Sửa mô tả:", tableModel.getValueAt(selectedRow, 4));
                String soLuong = JOptionPane.showInputDialog(this, "Sửa số lượng:", tableModel.getValueAt(selectedRow, 5));

                try (Connection conn = DatabaseUtils.getConnection()) {
                    String sql = "UPDATE DICH_VU SET tenDV = ?, diaChi = ?, Gia = ?, moTa = ?, soLuong = ? WHERE maDV = ?";
                    var pstmt = conn.prepareStatement(sql);
                    //pstmt.setString(1, maCDV);
                    pstmt.setString(1, tenDV);
                    pstmt.setString(2, diaChi);
                    pstmt.setInt(3, Integer.parseInt(gia));
                    pstmt.setString(4, moTa);
                    pstmt.setString(5, soLuong);
                    pstmt.setInt(6, Integer.parseInt(maDV));

                    pstmt.executeUpdate(); // Thực thi lệnh SQL

                    // Cập nhật lại trong JTable
                    //tableModel.setValueAt(maCDV, selectedRow, 1);
                    tableModel.setValueAt(tenDV, selectedRow, 1);
                    tableModel.setValueAt(diaChi, selectedRow, 2);
                    tableModel.setValueAt(gia, selectedRow, 3);
                    tableModel.setValueAt(moTa, selectedRow, 4);
                    tableModel.setValueAt(soLuong, selectedRow, 5);

                    JOptionPane.showMessageDialog(this, "Sửa thành công!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa!");
            }
        });

        // Xóa dữ liệu
        btnXoa.addActionListener(evt -> {
            int selectedRow = tbDanhSachTour.getSelectedRow();
            if (selectedRow != -1) {
                String maDV = (String) tableModel.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dòng này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try (Connection conn = DatabaseUtils.getConnection()) {
                        String sql = "DELETE FROM DICH_VU WHERE maDV = ?";
                        var pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1, Integer.parseInt(maDV));

                        pstmt.executeUpdate(); // Thực thi lệnh SQL

                        tableModel.removeRow(selectedRow); // Xóa dòng trong JTable
                        JOptionPane.showMessageDialog(this, "Xóa thành công!");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa!");
            }
        });
    }

    private void addTableSelectionListener() {
        tbDanhSachTour.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbDanhSachTour.getSelectedRow();
                if (selectedRow != -1) {
                    String moTa = (String) tableModel.getValueAt(selectedRow, 4);
                    jTextArea1.setText(moTa); // Hiển thị mô tả lên JTextArea
                }
            }
        });
    }

    private void loadTableData() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "SELECT * FROM DICH_VU";
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Xóa tất cả dữ liệu trong JTable trước khi tải lại
            tableModel.setRowCount(0);

            // Duyệt qua ResultSet và thêm từng dòng vào JTable
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("maDV"),
                    rs.getString("tenDV"),
                    rs.getString("diaChi"),
                    rs.getInt("Gia"),
                    rs.getString("moTa"),
                    rs.getString("soLuong")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhSachTour = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lbMoTa = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(204, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setPreferredSize(new java.awt.Dimension(1200, 650));
        setLayout(null);

        tbDanhSachTour.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbDanhSachTour.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã số", "Tên tour", "Thời gian", "Địa điểm", "Giá tiền", "Mô tả", "Số lượng"
            }
        ));
        jScrollPane1.setViewportView(tbDanhSachTour);

        add(jScrollPane1);
        jScrollPane1.setBounds(180, 30, 800, 430);

        btnThem.setText("Thêm");
        add(btnThem);
        btnThem.setBounds(850, 480, 90, 23);

        btnSua.setText("Sửa");
        add(btnSua);
        btnSua.setBounds(970, 480, 80, 23);

        btnXoa.setText("Xoá");
        add(btnXoa);
        btnXoa.setBounds(850, 520, 80, 23);

        lbMoTa.setText("Mô tả: ");
        add(lbMoTa);
        lbMoTa.setBounds(180, 500, 60, 16);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea1);

        add(jScrollPane2);
        jScrollPane2.setBounds(250, 480, 570, 120);
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbMoTa;
    private javax.swing.JTable tbDanhSachTour;
    // End of variables declaration                   
}
