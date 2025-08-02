/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

/**
 *
 * @author ASUS
 */
public interface NguoiDung {

    String getMa();

    String getHoVaTen();

    String getTenDangNhap();

    String getMatKhau();

    String getEmail();

    String getSoDienThoai();

    String getVaiTro();

    void setHoVaTen(String hoVaTen);

    void setTenDangNhap(String tenDangNhap);

    void setMatKhau(String matKhau);

    void setEmail(String email);

    void setSoDienThoai(String sdt);

    void setVaiTro(String vaiTro);
    
    void setMa(String ma);

}
