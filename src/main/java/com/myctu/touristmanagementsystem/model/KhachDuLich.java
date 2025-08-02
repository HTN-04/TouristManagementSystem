/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

/**
 *
 * @author ASUS
 */
public class KhachDuLich implements NguoiDung {
    private String ma;

    private String hoVaTen;

    private String tenDangNhap;

    private String matKhau;

    private String email;

    private String soDienThoai;

    private String vaiTro;

    public KhachDuLich() {
    }

    public KhachDuLich(String hoVaTen, String email, String soDienThoai) {
        this.hoVaTen = hoVaTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public KhachDuLich(String hoVaTen, String tenDangNhap, String matKhau, String email, String soDienThoai) {
        this.hoVaTen = hoVaTen;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public KhachDuLich(String hoVaTen, String tenDangNhap, String matKhau, String email, String soDienThoai, String vaiTro) {
        this.hoVaTen = hoVaTen;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.vaiTro = vaiTro;
    }

    public KhachDuLich(String ma, String hoVaTen, String tenDangNhap, String matKhau, String email, String soDienThoai, String vaiTro) {
        this.ma = ma;
        this.hoVaTen = hoVaTen;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.vaiTro = vaiTro;
    }

    @Override
    public String getHoVaTen() {
        return hoVaTen;
    }

    @Override
    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    @Override
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    @Override
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    /**
     *
     * @return
     */
    @Override
    public String getMatKhau() {
        return matKhau;
    }

    @Override
    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getSoDienThoai() {
        return soDienThoai;
    }

    @Override
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @Override
    public String getVaiTro() {
        return vaiTro;
    }

    @Override
    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    @Override
    public String getMa() {
        return ma;
    }

    @Override
    public void setMa(String ma) {
        this.ma = ma;
    }
}
