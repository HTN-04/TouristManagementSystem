/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

/**
 *
 * @author ASUS
 */
public class NguoiDungHienTai {

//    private String tenDangNhap;
//
//    private String vaitro;
//
//    private static NguoiDungHienTai nguoiDungHienTai;
//
//    private NguoiDungHienTai(String tenNguoiDung, String vaiTro) {
//        this.tenDangNhap = tenNguoiDung;
//        this.vaitro = vaiTro;
//    }
//
//    public static void setNguoiDungHienTai(String tenNguoiDung, String vaiTro) {
//        nguoiDungHienTai = new NguoiDungHienTai(tenNguoiDung, vaiTro);
//    }
//
//    public static NguoiDungHienTai getNguoiDung() {
//        return nguoiDungHienTai;
//    }
//
//    public String getTenDangNhap() {
//        return tenDangNhap;
//    }
//
//    public void setTenDangNhap(String tenDangNhap) {
//        this.tenDangNhap = tenDangNhap;
//    }
//
//    public String getVaitro() {
//        return vaitro;
//    }
//
//    public void setVaitro(String vaitro) {
//        this.vaitro = vaitro;
//    }
  
    private String tenDangNhap;
    private String vaitro;
    private int maKDL;

    // Singleton instance
    private static NguoiDungHienTai instance;

    // Constructor private để áp dụng Singleton Pattern
    private NguoiDungHienTai(String tenDangNhap, String vaitro) {
        this.tenDangNhap = tenDangNhap;
        this.vaitro = vaitro;
    }

    // Phương thức lấy instance (Singleton)
    public static NguoiDungHienTai getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance chưa được khởi tạo. Hãy gọi setNguoiDungHienTai() trước.");
        }
        return instance;
    }

 //    Phương thức khởi tạo instance
    public static void setNguoiDungHienTai(String tenDangNhap, String vaitro) {
        if (instance == null) {
            instance = new NguoiDungHienTai(tenDangNhap, vaitro);
        }
        
        int maKDL = NguoiDungHienTai.getInstance().getMaKDL();
        int maTT = 1; // Mã thanh toán mặc định
        int trangThai = 0; // Trạng thái mặc định

        System.out.println("MaKDL: " + maKDL);
        System.out.println("MaTT: " + maTT);
        System.out.println("TrangThai: " + trangThai);

    }
    
    
    
    // Phương thức khởi tạo instance
public static void setNguoiDungHienTai(String tenDangNhap, String vaitro, int maKDL) {
    if (instance == null) {
        instance = new NguoiDungHienTai(tenDangNhap, vaitro);
    }

    // Thiết lập maKDL sau khi instance được khởi tạo
    instance.setMaKDL(maKDL);

    // Kiểm tra giá trị maKDL sau khi thiết lập
    System.out.println("MaKDL: " + instance.getMaKDL());
}


    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getVaitro() {
        return vaitro;
    }

    public int getMaKDL() {
        return maKDL;
    }

    public void setMaKDL(int maKDL) {
        this.maKDL = maKDL;
    }

}
