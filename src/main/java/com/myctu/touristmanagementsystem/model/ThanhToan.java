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
public class ThanhToan {

    private int MaTT;
    private boolean TrangThaiThanhToan;
    private LocalDateTime ThoiGianThanhToan;

    // Constructor
    public ThanhToan() {
    }

    public ThanhToan(int MaTT, boolean TrangThaiThanhToan, LocalDateTime ThoiGianThanhToan) {
        this.MaTT = MaTT;
        this.TrangThaiThanhToan = TrangThaiThanhToan;
        this.ThoiGianThanhToan = ThoiGianThanhToan;
    }

    // Getter và Setter cho maTT
    public int getMaTT() {
        return MaTT;
    }

    public void setMaTT(int MaTT) {
        this.MaTT = MaTT;
    }

    public boolean isTrangThaiThanhToan() {
        return TrangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(boolean TrangThaiThanhToan) {
        this.TrangThaiThanhToan = TrangThaiThanhToan;
    }

    public LocalDateTime getThoiGianThanhToan() {
        return ThoiGianThanhToan;
    }

    public void setThoiGianThanhToan(LocalDateTime ThoiGianThanhToan) {
        this.ThoiGianThanhToan = ThoiGianThanhToan;
    }

    
    // Phương thức hiển thị thông tin
    @Override
    public String toString() {
        return "ThanhToan{" + "MaTT=" + MaTT + ", TrangThaiThanhToan=" + TrangThaiThanhToan + ", ThoiGianThanhToan=" + ThoiGianThanhToan + '}';
    }

}
