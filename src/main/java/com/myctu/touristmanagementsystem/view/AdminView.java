/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.myctu.touristmanagementsystem.view;

import com.myctu.touristmanagementsystem.controller.ThongKeController;
import com.myctu.touristmanagementsystem.model.LocaleManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class AdminView extends javax.swing.JFrame {

    /**
     * Creates new form AdminView
     */
    public AdminView() {
        initComponents();
        initPanel();
        customizeView();
        addIcon();
        locale();
        this.setLocationRelativeTo(null);
    }

    private ResourceBundle rb;

    private void locale() {
//        Locale.setDefault(); // Đặt locale mặc định
        try {
            rb = ResourceBundle.getBundle("com.myctu.touristmanagementsystem.properties.MyResources", LocaleManager.getCurrentLocale());
            System.out.println("Resource bundle loaded successfully.");
            setText();
        } catch (MissingResourceException e) {
            System.err.println("Error loading resource bundle: " + e.getMessage());
        }
//        JComboBox<String> languageComboBox = new JComboBox<>(new String[]{"Tiếng Việt", "English"});
//        this.btnChange = languageComboBox;
    }

    private void setText() {
        try {
            mnHeThong.setText(rb.getString("system"));
            mnHoSo.setText(rb.getString("account"));
  
            mnQuanLyTour.setText(rb.getString("manageTour"));
            mnDsDatTour.setText(rb.getString("bookingList"));
            mnBaoCaoThongKe.setText(rb.getString("statisticalReport"));
            
            mnEditAccount.setText(rb.getString("editAccount"));
            mnLogOut.setText(rb.getString("logOut"));

        } catch (MissingResourceException e) {
            System.err.println("Error loading resource bundle: " + e.getMessage());
        }
    }

    private void customizeView() {
        menuAdmin = new JMenuBar() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Vẽ nền gradient cho thanh menu
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();

                // Gradient từ màu xám nhạt đến trắng
                GradientPaint gp = new GradientPaint(0, 0, Color.LIGHT_GRAY, 0, height, Color.WHITE);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };

        mnDsDatTour.setFont(new Font("Arial", Font.PLAIN, 14));
        mnQuanLyTour.setFont(new Font("Arial", Font.PLAIN, 14));
        menuAdmin.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        menuAdmin.repaint();
    }

    private void addIcon() {
        // Tải ảnh gốc từ đường dẫn

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/myctu/touristmanagementsystem/img/home.png"));
        ImageIcon iconHoSo = new ImageIcon(getClass().getResource("/com/myctu/touristmanagementsystem/img/user-profile.png"));
        // Thay đổi kích thước icon theo ý muốn, ví dụ 20x20 pixels
        Image resizedImage = originalIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image resizedImageHoSo = iconHoSo.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // Tạo một ImageIcon mới từ ảnh đã thay đổi kích thước
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        ImageIcon resizedIconHoSo = new ImageIcon(resizedImageHoSo);
        // Gán icon đã thay đổi kích thước cho mnHeThong
        mnHeThong.setIcon(resizedIcon);
        mnHoSo.setIcon(resizedIconHoSo);

    }

    private void initPanel() {
        pnMainAdmin.setPreferredSize(new java.awt.Dimension(1200, 700));

        pnMainAdmin.add(quanLyTourView, "QuanLyTour");
        pnMainAdmin.add(danhSachDatTourView, "DanhSachDatTour");
        pnMainAdmin.add(thongKeBaoCaoView, "ThongKeBaoCao");
        pnMainAdmin.add(chinhSuaHoSoAdminView, "ChinhSua");

        mnLogOut.setActionCommand("DangXuat");
        mnEditAccount.setActionCommand("ChinhSuaTK");
        mnQuanLyTour.setActionCommand("QuanLyTour");
        mnDsDatTour.setActionCommand("DsDatTour");
        mnBaoCaoThongKe.setActionCommand("BaoCaoThongKe");

        pnMainAdmin.revalidate();
        pnMainAdmin.repaint();
        pack();
    }

    public String getSelectedMenuItemName(JMenuItem menuItem) {
        return menuItem.getActionCommand();
    }

    public JPanel getPnMainAdmin() {
        return pnMainAdmin;
    }

    public ChinhSuaHoSoAdminVew getChinhSuaHoSoAdminView() {
        return chinhSuaHoSoAdminView;
    }

    public DanhSachDatTour getDanhSachDatTourView() {
        return danhSachDatTourView;
    }

    public QuanLyTour getQuanLyTourView() {
        return quanLyTourView;
    }

    public ThongKeBaoCao getThongKeBaoCaoView() {
        return thongKeBaoCaoView;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMainAdmin = new javax.swing.JPanel();
        menuAdmin = new javax.swing.JMenuBar();
        mnHeThong = new javax.swing.JMenu();
        mnQuanLyTour = new javax.swing.JMenuItem();
        mnDsDatTour = new javax.swing.JMenuItem();
        mnBaoCaoThongKe = new javax.swing.JMenuItem();
        mnHoSo = new javax.swing.JMenu();
        mnEditAccount = new javax.swing.JMenuItem();
        mnLogOut = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setSize(new java.awt.Dimension(0, 0));

        pnMainAdmin.setPreferredSize(new java.awt.Dimension(1200, 650));
        pnMainAdmin.setLayout(new java.awt.CardLayout());
        getContentPane().add(pnMainAdmin, java.awt.BorderLayout.CENTER);

        menuAdmin.setMinimumSize(new java.awt.Dimension(116, 25));
        menuAdmin.setPreferredSize(new java.awt.Dimension(116, 35));

        mnHeThong.setText("Hệ Thống");

        mnQuanLyTour.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mnQuanLyTour.setText("Quản lý tour");
        mnQuanLyTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnQuanLyTourActionPerformed(evt);
            }
        });
        mnHeThong.add(mnQuanLyTour);

        mnDsDatTour.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mnDsDatTour.setText("Danh sách đặt tour");
        mnDsDatTour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDsDatTourActionPerformed(evt);
            }
        });
        mnHeThong.add(mnDsDatTour);

        mnBaoCaoThongKe.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mnBaoCaoThongKe.setText("Báo cáo thống kê");
        mnBaoCaoThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBaoCaoThongKeActionPerformed(evt);
            }
        });
        mnHeThong.add(mnBaoCaoThongKe);

        menuAdmin.add(mnHeThong);

        mnHoSo.setText("Hồ sơ");

        mnEditAccount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mnEditAccount.setText("Chỉnh sửa hồ sơ");
        mnEditAccount.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                mnEditAccountAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        mnEditAccount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mnEditAccountStateChanged(evt);
            }
        });
        mnEditAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEditAccountActionPerformed(evt);
            }
        });
        mnHoSo.add(mnEditAccount);

        mnLogOut.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        mnLogOut.setText("Đăng xuất");
        mnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnLogOutActionPerformed(evt);
            }
        });
        mnHoSo.add(mnLogOut);

        menuAdmin.add(mnHoSo);

        setJMenuBar(menuAdmin);

        setBounds(0, 0, 812, 506);
    }// </editor-fold>//GEN-END:initComponents

    private void mnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnLogOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnLogOutActionPerformed

    private void mnEditAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEditAccountActionPerformed
//        // TODO add your handling code here:
//        InfoAdminView adminIF = new InfoAdminView();
//        adminIF.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_mnEditAccountActionPerformed

    private void mnEditAccountAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_mnEditAccountAncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_mnEditAccountAncestorMoved

    private void mnDsDatTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDsDatTourActionPerformed
//
//        quanLyTourView.setVisible(false);
//        danhSachDatTourView.setVisible(true);

    }//GEN-LAST:event_mnDsDatTourActionPerformed

    private void mnQuanLyTourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnQuanLyTourActionPerformed
//        danhSachDatTourView.setVisible(false);
//        quanLyTourView.setVisible(true);
    }//GEN-LAST:event_mnQuanLyTourActionPerformed

    private void mnBaoCaoThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBaoCaoThongKeActionPerformed
//        // TODO add your handling code here:
//        danhSachDatTourView.setVisible(false);
//        quanLyTourView.setVisible(false);
//        thongKeBaoCaoView.setVisible(true);
    }//GEN-LAST:event_mnBaoCaoThongKeActionPerformed

    private void mnEditAccountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mnEditAccountStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_mnEditAccountStateChanged

    public void addMenuItemDangXuatListener(ActionListener listener) {
        mnLogOut.addActionListener(listener);
    }

    public void addMenuChinhSuaHoSoListener(ActionListener listener) {
        mnEditAccount.addActionListener(listener);
    }

    public void addMenuQuanLyTourListener(ActionListener listener) {
        mnQuanLyTour.addActionListener(listener);
    }

    public void addMenuDSDatTourListener(ActionListener listener) {
        mnDsDatTour.addActionListener(listener);
    }

    public void addMenuThongKeListener(ActionListener listener) {
        mnBaoCaoThongKe.addActionListener(listener);
    }

    public void addMenuEditAccountStateChanged(ActionListener listener) {
        mnHoSo.addActionListener(listener);
    }

    private DanhSachDatTour danhSachDatTourView = new DanhSachDatTour();

    private QuanLyTour quanLyTourView = new QuanLyTour();

    private ThongKeBaoCao thongKeBaoCaoView = new ThongKeBaoCao();

    private ChinhSuaHoSoAdminVew chinhSuaHoSoAdminView = new ChinhSuaHoSoAdminVew();

    //fix update ThongKeView
    AdminView adminView = this;
    ThongKeController thongKeController = new ThongKeController(thongKeBaoCaoView, adminView);


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar menuAdmin;
    private javax.swing.JMenuItem mnBaoCaoThongKe;
    private javax.swing.JMenuItem mnDsDatTour;
    private javax.swing.JMenuItem mnEditAccount;
    private javax.swing.JMenu mnHeThong;
    private javax.swing.JMenu mnHoSo;
    private javax.swing.JMenuItem mnLogOut;
    private javax.swing.JMenuItem mnQuanLyTour;
    private javax.swing.JPanel pnMainAdmin;
    // End of variables declaration//GEN-END:variables
}
