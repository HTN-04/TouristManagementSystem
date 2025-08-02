/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.myctu.touristmanagementsystem.view;

import com.myctu.touristmanagementsystem.model.DVDaDatModel;
import com.myctu.touristmanagementsystem.model.DichVuTableModelInUser;
import com.myctu.touristmanagementsystem.model.KhachDuLich;
import com.myctu.touristmanagementsystem.model.LocaleManager;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author GIGABYTE
 */
public class UserView extends javax.swing.JFrame {

    /**
     * Creates new form ProductViewTourist
     */
    public UserView() {
        try {
            initComponents();
            customizeView();
            pack();
            this.setLocationRelativeTo(null);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    /**
     *
     * @author RHNA
     */
    private void customizeView() {

        addIcon();

        setEditProfile(false);
        lbToolTip.setOpaque(true); // Đảm bảo JLabel có nền
        lbToolTip.setBackground(Color.WHITE); // Màu nền của tooltip
        lbToolTip.setVisible(false); // Ẩn tooltip khi khởi tạo

        locale();
    }
    private ResourceBundle rb;

    private void locale() {
//        Locale.setDefault(locale); // Đặt locale mặc định
        try {
            rb = ResourceBundle.getBundle("com.myctu.touristmanagementsystem.properties.MyResources", LocaleManager.getCurrentLocale());
            System.out.println("Resource bundle loaded successfully.");
            setText(); // Cập nhật giao diện với ngôn ngữ mới
        } catch (MissingResourceException e) {
            System.err.println("Error loading resource bundle: " + e.getMessage());
        }
//        JComboBox<String> languageComboBox = new JComboBox<>(new String[]{"Tiếng Việt", "English"});
//        this.btnChange = languageComboBox;
    }

    private void setText() {
        //Trang chủ
        jTabbedPane1.setTitleAt(0, rb.getString("home"));
        jTabbedPane1.setTitleAt(1, rb.getString("orders"));
        jTabbedPane1.setTitleAt(2, rb.getString("services"));
        jTabbedPane1.setTitleAt(3, rb.getString("account"));

        // Labels
        lbMoTa.setText(rb.getString("description"));
        lbTimKiem.setText(rb.getString("search"));
        SwingUtilities.invokeLater(() -> {
            tbDanhSachDichVu.getColumnModel().getColumn(0).setHeaderValue(rb.getString("place"));
            tbDanhSachDichVu.getColumnModel().getColumn(1).setHeaderValue(rb.getString("address"));
            tbDanhSachDichVu.getColumnModel().getColumn(2).setHeaderValue(rb.getString("description"));
            tbDanhSachDichVu.getColumnModel().getColumn(3).setHeaderValue(rb.getString("quantity"));
            tbDanhSachDichVu.getColumnModel().getColumn(4).setHeaderValue(rb.getString("price"));
            tbDanhSachDichVu.getTableHeader().repaint();
        });

        // Buttons
        btnDatVe.setText(rb.getString("book"));

        // Cập nhật lại bảng để hiển thị tiêu đề cột mới
        tbDanhSachDichVu.getTableHeader().repaint();
        tbDanhSachDichVu.revalidate();
        tbDanhSachDichVu.repaint();

        //Trang dịch vụ đã chọn
        lbThongTinDichVu.setText(rb.getString("your_services"));
        lbNhapNgay.setText(rb.getString("enter_booking_date"));
        lbNhapSoLuong.setText(rb.getString("enter_ticket_quantity"));

       
        SwingUtilities.invokeLater(() -> {
            tbThongTinDichVuDaChon.getColumnModel().getColumn(0).setHeaderValue(rb.getString("place"));
            tbThongTinDichVuDaChon.getColumnModel().getColumn(1).setHeaderValue(rb.getString("bookingDate"));
            tbThongTinDichVuDaChon.getColumnModel().getColumn(2).setHeaderValue(rb.getString("price"));
            tbThongTinDichVuDaChon.getColumnModel().getColumn(3).setHeaderValue(rb.getString("quantity"));
            tbThongTinDichVuDaChon.getColumnModel().getColumn(4).setHeaderValue(rb.getString("totalAmount"));
            tbThongTinDichVuDaChon.getTableHeader().repaint();
        });

        btn_DongYNgayDat.setText(rb.getString("confirm"));
        btnDongYSoLuong.setText(rb.getString("confirm"));
        btnCapNhatThanhToan.setText(rb.getString("update"));
        btnThanhToan.setText(rb.getString("pay"));
        btnXoaDichVu.setText(rb.getString("delete_service"));

        tbThongTinDichVuDaChon.getTableHeader().repaint();
        tbThongTinDichVuDaChon.revalidate();
        tbThongTinDichVuDaChon.repaint();
        
        //Thong tin da dat
        lbDichVuDaThanhToan.setText(rb.getString("paid_services"));
        
        //thong tin tai khoan
        lbTaiKhoan.setText(rb.getString("infoAccount"));
        lbHoVaTen.setText(rb.getString("full_name"));
        lbEmail.setText(rb.getString("email"));
        lbSoDienThoai.setText(rb.getString("phone"));
        btnCapNhatTaiKhoan.setText(rb.getString("updateAccount"));
        btnDangXuat.setText(rb.getString("logOut"));
    }

    private void addIcon() {

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/myctu/touristmanagementsystem/img/magnifying-glass.png"));
        // Thay đổi kích thước icon theo ý muốn, ví dụ 20x20 pixels
        Image resizedImage = originalIcon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        // Tạo một ImageIcon mới từ ảnh đã thay đổi kích thước
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        btnTimKiem.setBackground(Color.white);
        btnTimKiem.setIcon(resizedIcon);

        ImageIcon imgChinhSuaHoSo = new ImageIcon(getClass().getResource("/com/myctu/touristmanagementsystem/img/user-avatar.png"));
        Image resizedImageChinhSuaHoSo = imgChinhSuaHoSo.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon newIconChinhSuHoSo = new ImageIcon(resizedImageChinhSuaHoSo);
        btnChinhSuaHoSo.setBackground(Color.WHITE);
        btnChinhSuaHoSo.setIcon(newIconChinhSuHoSo);

    }

    public void setEditProfile(Boolean bool) {
        if (bool) {
            tfHoVaTen.setEditable(true);
            tfEmail.setEditable(true);
            tfSoDienThoai.setEditable(true);
            btnCapNhatTaiKhoan.setEnabled(true);
        } else {
            tfHoVaTen.setEditable(false);
            tfEmail.setEditable(false);
            tfSoDienThoai.setEditable(false);
            btnCapNhatTaiKhoan.setEnabled(false);
        }
    }

    public void setThongTinTaiKhoan(KhachDuLich khachDuLich) {
        tfHoVaTen.setText(khachDuLich.getHoVaTen());
        tfEmail.setText(khachDuLich.getEmail());
        tfSoDienThoai.setText(khachDuLich.getSoDienThoai());
    }

    public KhachDuLich getThongTinTaiKhoan() {
        KhachDuLich khachDuLich;
        String hoVaTen = tfHoVaTen.getText().trim();
        String email = tfEmail.getText().trim();
        String sdt = tfSoDienThoai.getText().trim();
        khachDuLich = new KhachDuLich(hoVaTen, email, sdt);
        return khachDuLich;
    }

    public void setToolTip(Boolean bol) {
        lbToolTip.setVisible(bol);
    }
//Function: search

    public String getKeyTimKiem() {
        String key;
        key = (tfTimKiem.getText());
        System.out.println(key);
        return key;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnTrangChu = new javax.swing.JPanel();
        btnDatVe = new javax.swing.JButton();
        tfTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        lbMoTa = new javax.swing.JLabel();
        scrMoTa = new javax.swing.JScrollPane();
        tfMoTa = new javax.swing.JTextArea();
        scrDichVu = new javax.swing.JScrollPane();
        tbDanhSachDichVu = new javax.swing.JTable();
        lbTimKiem = new javax.swing.JLabel();
        pnThongTinThanhToan = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbThongTinDichVuDaChon = new javax.swing.JTable();
        lbThongTinDichVu = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnCapNhatThanhToan = new javax.swing.JButton();
        lbNhapSoLuong = new javax.swing.JLabel();
        jTextField_nhapSoLuongVe = new javax.swing.JTextField();
        btnDongYSoLuong = new javax.swing.JButton();
        btnXoaDichVu = new javax.swing.JButton();
        btn_DongYNgayDat = new javax.swing.JButton();
        lbNhapNgay = new javax.swing.JLabel();
        jDay = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbShowDaDat = new javax.swing.JTable();
        lbDichVuDaThanhToan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnTaiKhoan = new javax.swing.JPanel();
        pnTKhoan = new javax.swing.JPanel();
        lbTaiKhoan = new javax.swing.JLabel();
        btnCapNhatTaiKhoan = new javax.swing.JButton();
        btnDangXuat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbHoVaTen = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        tfHoVaTen = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfSoDienThoai = new javax.swing.JTextField();
        lbSoDienThoai = new javax.swing.JLabel();
        btnChinhSuaHoSo = new javax.swing.JButton();
        lbToolTip = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(153, 153, 153));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1000, 606));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        pnTrangChu.setBackground(new java.awt.Color(242, 250, 250));
        pnTrangChu.setPreferredSize(new java.awt.Dimension(996, 600));

        btnDatVe.setBackground(new java.awt.Color(51, 153, 255));
        btnDatVe.setForeground(new java.awt.Color(255, 255, 255));
        btnDatVe.setText("đặt vé");
        btnDatVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatVeActionPerformed(evt);
            }
        });

        tfTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setForeground(new java.awt.Color(255, 255, 255));
        btnTimKiem.setOpaque(true);
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        lbMoTa.setText("mô tả");

        tfMoTa.setEditable(false);
        tfMoTa.setColumns(20);
        tfMoTa.setLineWrap(true);
        tfMoTa.setRows(5);
        tfMoTa.setWrapStyleWord(true);
        scrMoTa.setViewportView(tfMoTa);

        tbDanhSachDichVu.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 255, 255), null));
        tbDanhSachDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Địa Điểm", "Địa Chỉ", "Giá", "Số lượng", "Mô Tả"
            }
        ));
        tbDanhSachDichVu.setShowGrid(false);
        scrDichVu.setViewportView(tbDanhSachDichVu);

        lbTimKiem.setText("Tìm kiếm:");

        javax.swing.GroupLayout pnTrangChuLayout = new javax.swing.GroupLayout(pnTrangChu);
        pnTrangChu.setLayout(pnTrangChuLayout);
        pnTrangChuLayout.setHorizontalGroup(
            pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTrangChuLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnTrangChuLayout.createSequentialGroup()
                        .addComponent(lbMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(scrMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnDatVe))
                    .addGroup(pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnTrangChuLayout.createSequentialGroup()
                            .addGap(225, 225, 225)
                            .addComponent(lbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 65, Short.MAX_VALUE))
        );
        pnTrangChuLayout.setVerticalGroup(
            pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTrangChuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTimKiem)))
                .addGap(18, 18, 18)
                .addComponent(scrDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(pnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDatVe)
                    .addComponent(scrMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnTrangChuLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lbMoTa)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Trang chủ", pnTrangChu);

        pnThongTinThanhToan.setBackground(new java.awt.Color(242, 250, 250));
        pnThongTinThanhToan.setPreferredSize(new java.awt.Dimension(1000, 600));

        tbThongTinDichVuDaChon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tbThongTinDichVuDaChon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Địa điểm", "Ngày đặt", "Giá vé", "Số lượng", "Thành tiền"
            }
        ));
        jScrollPane4.setViewportView(tbThongTinDichVuDaChon);

        lbThongTinDichVu.setText("thông tin vé đã đặt");

        btnThanhToan.setBackground(new java.awt.Color(214, 241, 255));
        btnThanhToan.setText("thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnCapNhatThanhToan.setBackground(new java.awt.Color(214, 241, 255));
        btnCapNhatThanhToan.setText("cập nhập");
        btnCapNhatThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatThanhToanActionPerformed(evt);
            }
        });

        lbNhapSoLuong.setText("vui lòng nhập số lượng vé");

        jTextField_nhapSoLuongVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nhapSoLuongVeActionPerformed(evt);
            }
        });

        btnDongYSoLuong.setBackground(new java.awt.Color(214, 241, 255));
        btnDongYSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnDongYSoLuong.setForeground(new java.awt.Color(51, 51, 51));
        btnDongYSoLuong.setText("đồng ý");
        btnDongYSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongYSoLuongActionPerformed(evt);
            }
        });

        btnXoaDichVu.setBackground(new java.awt.Color(214, 241, 255));
        btnXoaDichVu.setText("xóa dịch vụ");
        btnXoaDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDichVuActionPerformed(evt);
            }
        });

        btn_DongYNgayDat.setBackground(new java.awt.Color(214, 241, 255));
        btn_DongYNgayDat.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btn_DongYNgayDat.setForeground(new java.awt.Color(51, 51, 51));
        btn_DongYNgayDat.setText("đồng ý");

        lbNhapNgay.setText("vui lòng nhập ngày đặt");

        jDay.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout pnThongTinThanhToanLayout = new javax.swing.GroupLayout(pnThongTinThanhToan);
        pnThongTinThanhToan.setLayout(pnThongTinThanhToanLayout);
        pnThongTinThanhToanLayout.setHorizontalGroup(
            pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinThanhToanLayout.createSequentialGroup()
                .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinThanhToanLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbThongTinDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnThongTinThanhToanLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoaDichVu)
                                    .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnCapNhatThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnThanhToan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(pnThongTinThanhToanLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNhapNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNhapSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDay, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_nhapSoLuongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_DongYNgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDongYSoLuong))))
                .addGap(57, 57, 57))
        );
        pnThongTinThanhToanLayout.setVerticalGroup(
            pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongTinThanhToanLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinThanhToanLayout.createSequentialGroup()
                        .addComponent(btn_DongYNgayDat)
                        .addGap(1, 1, 1)
                        .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_nhapSoLuongVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDongYSoLuong)
                            .addComponent(lbNhapSoLuong)))
                    .addComponent(jDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNhapNgay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(lbThongTinDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnThongTinThanhToanLayout.createSequentialGroup()
                        .addComponent(btnCapNhatThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThanhToan)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaDichVu))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jTabbedPane1.addTab("Đơn Hàng", pnThongTinThanhToan);

        jPanel2.setBackground(new java.awt.Color(242, 250, 250));
        jPanel2.setLayout(null);

        tbShowDaDat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbShowDaDat);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(140, 140, 770, 350);

        lbDichVuDaThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbDichVuDaThanhToan.setForeground(new java.awt.Color(102, 102, 102));
        lbDichVuDaThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDichVuDaThanhToan.setText("Dịch vụ đã thanh toán");
        jPanel2.add(lbDichVuDaThanhToan);
        lbDichVuDaThanhToan.setBounds(320, 20, 428, 46);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myctu/touristmanagementsystem/img/Background-trang-xanh.jpg"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(-1, 0, 1000, 100);

        jTabbedPane1.addTab("Dịch vụ đã đặt", jPanel2);

        pnTKhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnTKhoan.setForeground(new java.awt.Color(102, 102, 102));
        pnTKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnTKhoan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pnTKhoan.setLayout(null);

        lbTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTaiKhoan.setForeground(new java.awt.Color(102, 102, 102));
        lbTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTaiKhoan.setText("Thông tin tài khoản");
        pnTKhoan.add(lbTaiKhoan);
        lbTaiKhoan.setBounds(160, 20, 750, 50);

        btnCapNhatTaiKhoan.setBackground(new java.awt.Color(204, 204, 204));
        btnCapNhatTaiKhoan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCapNhatTaiKhoan.setText("Cập nhập tài khoản");
        btnCapNhatTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatTaiKhoanActionPerformed(evt);
            }
        });
        pnTKhoan.add(btnCapNhatTaiKhoan);
        btnCapNhatTaiKhoan.setBounds(370, 380, 350, 32);

        btnDangXuat.setBackground(new java.awt.Color(204, 204, 204));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        pnTKhoan.add(btnDangXuat);
        btnDangXuat.setBounds(840, 490, 120, 32);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/myctu/touristmanagementsystem/img/profile-background-2ozk94drrgrfpa5u.jpg"))); // NOI18N
        pnTKhoan.add(jLabel2);
        jLabel2.setBounds(-10, 0, 1010, 100);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbHoVaTen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbHoVaTen.setText("Họ và tên");

        lbEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbEmail.setText("Email");

        tfHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tfHoVaTen.setMargin(new java.awt.Insets(2, 10, 2, 6));
        tfHoVaTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHoVaTenActionPerformed(evt);
            }
        });

        tfEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tfEmail.setMargin(new java.awt.Insets(2, 10, 2, 6));

        tfSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tfSoDienThoai.setMargin(new java.awt.Insets(2, 10, 2, 6));

        lbSoDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSoDienThoai.setText("Số điện thoại");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfEmail))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfSoDienThoai)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbHoVaTen)
                        .addComponent(tfHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbEmail)
                        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(9, 9, 9)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbSoDienThoai)
                        .addComponent(tfSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnTKhoan.add(jPanel1);
        jPanel1.setBounds(250, 170, 500, 170);

        btnChinhSuaHoSo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChinhSuaHoSoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChinhSuaHoSoMouseExited(evt);
            }
        });
        btnChinhSuaHoSo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaHoSoActionPerformed(evt);
            }
        });
        pnTKhoan.add(btnChinhSuaHoSo);
        btnChinhSuaHoSo.setBounds(770, 170, 30, 30);

        lbToolTip.setBackground(new java.awt.Color(255, 255, 255));
        lbToolTip.setFont(new java.awt.Font("Segoe UI", 2, 8)); // NOI18N
        lbToolTip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbToolTip.setText("Click here to edit");
        pnTKhoan.add(lbToolTip);
        lbToolTip.setBounds(790, 200, 70, 11);

        javax.swing.GroupLayout pnTaiKhoanLayout = new javax.swing.GroupLayout(pnTaiKhoan);
        pnTaiKhoan.setLayout(pnTaiKhoanLayout);
        pnTaiKhoanLayout.setHorizontalGroup(
            pnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnTaiKhoanLayout.setVerticalGroup(
            pnTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTaiKhoanLayout.createSequentialGroup()
                .addComponent(pnTKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tài khoản", pnTaiKhoan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tfTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimKiemActionPerformed

    private void btnDatVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatVeActionPerformed

    }//GEN-LAST:event_btnDatVeActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed

    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnCapNhatTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatTaiKhoanActionPerformed

    }//GEN-LAST:event_btnCapNhatTaiKhoanActionPerformed

    private void tfHoVaTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHoVaTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfHoVaTenActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnCapNhatThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatThanhToanActionPerformed

    private void btnDongYSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongYSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDongYSoLuongActionPerformed

    private void jTextField_nhapSoLuongVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nhapSoLuongVeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nhapSoLuongVeActionPerformed

    private void btnChinhSuaHoSoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaHoSoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChinhSuaHoSoActionPerformed

    private void btnChinhSuaHoSoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChinhSuaHoSoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChinhSuaHoSoMouseEntered

    private void btnChinhSuaHoSoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChinhSuaHoSoMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_btnChinhSuaHoSoMouseExited

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void btnXoaDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDichVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaDichVuActionPerformed

    public void addCapNhatTaiKhoanListener(ActionListener listener) {
        btnCapNhatTaiKhoan.addActionListener(listener);
    }

    public void addButtonChinhSuaListener(ActionListener listener, MouseListener mouseListener) {
        btnChinhSuaHoSo.addActionListener(listener);
        btnChinhSuaHoSo.addMouseListener(mouseListener);
    }

    public void addDangXuatListener(ActionListener listener) {
        btnDangXuat.addActionListener(listener);
    }

    public void addDatVeListener(ActionListener listener) {
        btnDatVe.addActionListener(listener);
    }

    public void addCapNhatListener(ActionListener listener) {
        btnCapNhatThanhToan.addActionListener(listener);
    }

    public void addXoaListener(ActionListener listener) {
        btnXoaDichVu.addActionListener(listener);
    }

    public void addThanhToanListener(ActionListener listener) {
        btnThanhToan.addActionListener(listener);
    }

    public void addTimKiemListener(ActionListener listener) {
        btnTimKiem.addActionListener(listener);
    }

    public void addStateChangedListener(ChangeListener listener) {
        jTabbedPane1.addChangeListener(listener);
    }
    //-------------------------Bỏ-----------------------
//     public void addFieldChangeListener(KeyListener listener) {
//        tfHoVaTen.addKeyListener(listener);
//        tfEmail.addKeyListener(listener);
//        tfSoDienThoai.addKeyListener(listener);
//    }

    //CODE MODEL TABLE  
    public void setTableModel(DichVuTableModelInUser tableModel) {
        tbDanhSachDichVu.setModel(tableModel);
    }

    public void fillInputForm() {
        int row = tbDanhSachDichVu.getSelectedRow();

        if (row >= 0) {
//            tfId.setText(tbDanhSachDichVu.getValueAt(row, 0).toString());
//            tfProductName.setText(tbDanhSachDichVu.getValueAt(row, 1).toString());
            tfMoTa.setText(tbDanhSachDichVu.getValueAt(row, 2).toString());
        }
    }

    public void addTableSelecctionListener(ListSelectionListener listener) {
        tbDanhSachDichVu.getSelectionModel().addListSelectionListener(listener);
    }
    //LH

    public void addBtnDongYListener(ActionListener listener) {
        btnDongYSoLuong.addActionListener(listener);
    }

    public void addBtnDongYNgayDatListener(ActionListener listener) {
        btn_DongYNgayDat.addActionListener(listener);
    }

//câp nhập số lượng vé
    public void updateBtnQuantity() {
        int row = tbThongTinDichVuDaChon.getSelectedRow();

        if (row >= 0) {
            // Lấy dữ liệu từ các cột của hàng được chọn
            String diaDiem = tbThongTinDichVuDaChon.getValueAt(row, 0).toString();
            String dateNow = tbThongTinDichVuDaChon.getValueAt(row, 1).toString();
            String quantity = jTextField_nhapSoLuongVe.getText();

            // Cập nhật dữ liệu của hàng đã chọn
            DefaultTableModel model = (DefaultTableModel) tbThongTinDichVuDaChon.getModel();
            model.setValueAt(diaDiem, row, 0);
            model.setValueAt(dateNow, row, 1);
            model.setValueAt(quantity, row, 3);

            // Yêu cầu làm mới bảng để hiển thị thay đổi
            model.fireTableDataChanged();
            JOptionPane.showMessageDialog(this, "nhập số lượng vé thành công");
            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Lỗi: Vui lòng chọn hàng để cập nhật");
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng. ");
        }
    }

//    cập nhập giá tiền
    public void updateGiaTien() {
        int row = tbThongTinDichVuDaChon.getSelectedRow();
        if (row >= 0) {
            // Lấy dữ liệu từ các cột của hàng được chọn
            String diaDiem = tbThongTinDichVuDaChon.getValueAt(row, 0).toString();
            String dateNow = tbThongTinDichVuDaChon.getValueAt(row, 1).toString();
            String quantity = tbThongTinDichVuDaChon.getValueAt(row, 3).toString();
            String giaTien = tbThongTinDichVuDaChon.getValueAt(row, 2).toString();
            System.out.println(giaTien);
            String thanhTien;

            if ("Miễn phí".equals(giaTien)) {
                thanhTien = "Miễn phí";
                System.out.println(thanhTien);
                System.out.println("free");
            } else {
                // Chuyen gia tien tu chuoi sang so nguyen
                int giaTienInt = Integer.parseInt(giaTien);
                int quantityInt = Integer.parseInt(quantity);
                thanhTien = String.valueOf(giaTienInt * quantityInt);
                System.out.println("tinh phi");
            }

            // Cập nhật dữ liệu của hàng đã chọn
            DefaultTableModel model = (DefaultTableModel) tbThongTinDichVuDaChon.getModel();
            model.setValueAt(diaDiem, row, 0);
            model.setValueAt(dateNow, row, 1);
            model.setValueAt(quantity, row, 2);
            model.setValueAt(thanhTien, row, 4);

            // Yêu cầu làm mới bảng để hiển thị thay đổi
            model.fireTableDataChanged();
            JOptionPane.showMessageDialog(this, "nhập số lượng vé thành công");
            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Lỗi: Vui lòng chọn hàng để cập nhật");
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng. ");
        }
    }

    public void transferDataToAnotherTable() {
        // Lấy hàng được chọn trong tbDanhSachDichVu
        int row = tbDanhSachDichVu.getSelectedRow();

        if (row >= 0) {

            // Lấy dữ liệu từ các cột của tbDanhSachDichVu
            String diaDiem = tbDanhSachDichVu.getValueAt(row, 0).toString(); // Địa điểm
            String giaVe = tbDanhSachDichVu.getValueAt(row, 3).toString();   // Giá vé

            // Chuẩn bị dữ liệu hàng mới cho bảng đích
            Object[] rowData = {diaDiem, null, giaVe}; // Địa điểm ở cột 1, Giá vé ở cột 3

            // Thêm dữ liệu vào tbThongTinDichVuDaDat
            DefaultTableModel model = (DefaultTableModel) tbThongTinDichVuDaChon.getModel();
            model.addRow(rowData);

            JOptionPane.showMessageDialog(this, "Bạn đã đặt vé thành công\nBấm vào đơn hàng để xem chi tiết");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ");
        }
    }

    public void transferDataToAnotherTableNgayDat() {
        // Lấy hàng được chọn trong tbThongTinDichVuDaDat
        int row = tbThongTinDichVuDaChon.getSelectedRow();
        String dateNow = "";
        if (row >= 0) {
//fix  
            Date selectedDate = (Date) jDay.getDate();
            System.out.println(selectedDate);
            if (selectedDate != null) {
                System.out.println("selectedDate != null");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(selectedDate);
                dateNow = formattedDate;
//                JOptionPane.showMessageDialog(null, "Ngày đã chọn: " + formattedDate);
            }
//            else {
//                JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày!");
//            }
            // Lấy dữ liệu ngày đặt từ txtNgayDat
//            String dateNow = txtNgayDat.getText().trim();

            // Kiểm tra xem người dùng đã nhập ngày đặt chưa
            if (dateNow.isEmpty()) {
                showMessage("Vui lòng nhập ngày đặt vào ô ngày đặt!");
                return;
            }

            // Cập nhật cột ngày đặt cho hàng đã chọn
            DefaultTableModel model = (DefaultTableModel) tbThongTinDichVuDaChon.getModel();
            model.setValueAt(dateNow, row, 1); // Cột 1 là cột Ngày đặt (giả sử)

            JOptionPane.showMessageDialog(this, "Ngày đặt đã được cập nhật thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ để cập nhật ngày đặt");
        }
    }

    public void removeSelectedRow() {
        // Lấy hàng được chọn trong tbThongTinDichVuDaDat
        int selectedRow = tbThongTinDichVuDaChon.getSelectedRow();

        if (selectedRow >= 0) {
            // Xóa hàng đã chọn
            DefaultTableModel model = (DefaultTableModel) tbThongTinDichVuDaChon.getModel();
            model.removeRow(selectedRow);

            JOptionPane.showMessageDialog(this, "Dịch vụ đã được xóa khỏi bảng!");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dịch vụ để xóa.");
        }
    }

    // truyền dữ liệu từ table thông tin dv đã đặt sang table thanh toán
    public void chuyenDuLieuSangThanhToanView() {
        DefaultTableModel modelDaDat = (DefaultTableModel) tbThongTinDichVuDaChon.getModel();

        // Khởi tạo ThanhToanView
        ThanhToanView thanhToanView = new ThanhToanView();

        // Truyền dữ liệu sang ThanhToanView
        thanhToanView.copyDataToThanhToanTable(modelDaDat);

        // Hiển thị ThanhToanView
        thanhToanView.setVisible(true);
        thanhToanView.setLocationRelativeTo(null);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);// method hiện thi thông báo
    }

    //CODE MODEL TABLE  
    public void setTableDVDaDatModel(DVDaDatModel tableModel) {
        tbShowDaDat.setModel(tableModel);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatTaiKhoan;
    private javax.swing.JButton btnCapNhatThanhToan;
    private javax.swing.JButton btnChinhSuaHoSo;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDatVe;
    private javax.swing.JButton btnDongYSoLuong;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoaDichVu;
    private javax.swing.JButton btn_DongYNgayDat;
    private com.toedter.calendar.JDateChooser jDay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField_nhapSoLuongVe;
    private javax.swing.JLabel lbDichVuDaThanhToan;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbHoVaTen;
    private javax.swing.JLabel lbMoTa;
    private javax.swing.JLabel lbNhapNgay;
    private javax.swing.JLabel lbNhapSoLuong;
    private javax.swing.JLabel lbSoDienThoai;
    private javax.swing.JLabel lbTaiKhoan;
    private javax.swing.JLabel lbThongTinDichVu;
    private javax.swing.JLabel lbTimKiem;
    private javax.swing.JLabel lbToolTip;
    private javax.swing.JPanel pnTKhoan;
    private javax.swing.JPanel pnTaiKhoan;
    private javax.swing.JPanel pnThongTinThanhToan;
    private javax.swing.JPanel pnTrangChu;
    private javax.swing.JScrollPane scrDichVu;
    private javax.swing.JScrollPane scrMoTa;
    private javax.swing.JTable tbDanhSachDichVu;
    private javax.swing.JTable tbShowDaDat;
    private javax.swing.JTable tbThongTinDichVuDaChon;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfHoVaTen;
    private javax.swing.JTextArea tfMoTa;
    private javax.swing.JTextField tfSoDienThoai;
    private javax.swing.JTextField tfTimKiem;
    // End of variables declaration//GEN-END:variables
}
