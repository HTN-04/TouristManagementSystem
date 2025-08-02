/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

import java.time.LocalDateTime;

/**
 *
 * @author GIGABYTE
 */
public class ThongKe {
    private int maDV;            // Mã dịch vụ
    private int maDH;            // Mã đơn hàng
    private int soLuongDat;      // Số lượng đặt
    private LocalDateTime thoiGianDat; // Thời gian đặt
    private double tongSoTien;   // Tổng số tiền
    private double tongSoTienSanPham;
    private int tongSoLuongDat;
    
    // Constructor

    public ThongKe(int maDV, int maDH, int soLuongDat, LocalDateTime thoiGianDat, double tongSoTien) {
        this.maDV = maDV;
        this.maDH = maDH;
        this.soLuongDat = soLuongDat;
        this.thoiGianDat = thoiGianDat;
        this.tongSoTien = tongSoTien;
    }

    public ThongKe() {
    }
    
    
    
    // Getters and Setters

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getSoLuongDat() {
        return soLuongDat;
    }

    public void setSoLuongDat(int soLuongDat) {
        this.soLuongDat = soLuongDat;
    }

    public LocalDateTime getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(LocalDateTime thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public double getTongSoTien() {
        return tongSoTien;
    }

    public void setTongSoTien(double tongSoTien) {
        this.tongSoTien = tongSoTien;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ThongKe{");
        sb.append("maDV=").append(maDV);
        sb.append(", maDH=").append(maDH);
        sb.append(", soLuongDat=").append(soLuongDat);
        sb.append(", thoiGianDat=").append(thoiGianDat);
        sb.append(", tongSoTien=").append(tongSoTien);
        sb.append(", tongSoTienSanPham=").append(tongSoTienSanPham);
        sb.append(", tongSoLuongDat=").append(tongSoLuongDat);
        sb.append('}');
        return sb.toString();
    }   
}
