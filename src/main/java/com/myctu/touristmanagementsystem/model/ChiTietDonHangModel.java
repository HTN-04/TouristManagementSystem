/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

import java.time.LocalDate;

public class ChiTietDonHangModel {

    private int maDH; // Mã đơn hàng
    private int maDV; // Mã dịch vụ
    private LocalDate thoiGianDat; // Thời gian đặt (chỉ ngày tháng)
    private int soLuongDat; // Số lượng dịch vụ đặt
    private double tongSoTien; // Tổng số tiền

    // Constructor mặc định
    public ChiTietDonHangModel() {
    }

    // Constructor đầy đủ
    public ChiTietDonHangModel(int maDH, int maDV, LocalDate thoiGianDat, int soLuongDat, double tongSoTien) {
        this.maDH = maDH;
        this.maDV = maDV;
        this.thoiGianDat = thoiGianDat;
        this.soLuongDat = soLuongDat;
        this.tongSoTien = tongSoTien;
    }

    // Getter và Setter
    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaDV() {
        return maDV;
    }

    public void setMaDV(int maDV) {
        this.maDV = maDV;
    }

    public LocalDate getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(LocalDate thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
    }

    public int getSoLuongDat() {
        return soLuongDat;
    }

    public void setSoLuongDat(int soLuongDat) {
        this.soLuongDat = soLuongDat;
    }

    public double getTongSoTien() {
        return tongSoTien;
    }

    public void setTongSoTien(double tongSoTien) {
        this.tongSoTien = tongSoTien;
    }

    @Override
    public String toString() {
        return "ChiTietDonHangModel{"
                + "maDH=" + maDH
                + ", maDV=" + maDV
                + ", thoiGianDat=" + thoiGianDat
                + ", soLuongDat=" + soLuongDat
                + ", tongSoTien=" + tongSoTien
                + '}';
    }
}
