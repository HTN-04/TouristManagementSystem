/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

/**
 *
 * @author ASUS
 */
public class DichVu {
    private String maDV;
    
    private String maCDV;
    
    private String tenDV;
    
    private String diaChi;
    
    private String moTa;
    
    private String Gia;
    
    private String SoLuong;

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getMaCDV() {
        return maCDV;
    }

    public void setMaCDV(String maCDV) {
        this.maCDV = maCDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String Gia) {
        this.Gia = Gia;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public DichVu(String maDV, String maCDV, String tenDV, String diaChi, String moTa, String Gia, String SoLuong) {
        this.maDV = maDV;
        this.maCDV = maCDV;
        this.tenDV = tenDV;
        this.diaChi = diaChi;
        this.moTa = moTa;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
    }
    
    
}
