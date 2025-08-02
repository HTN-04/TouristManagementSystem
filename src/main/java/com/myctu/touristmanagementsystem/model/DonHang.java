/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myctu.touristmanagementsystem.model;

/**
 *
 * @author GIGABYTE
 */
//public class DonHang {
//    private  int MaDH;
//    private int MaKDL;
//    private int MaTT;
//    private boolean TrangThaiDonHang;    
//
//    public DonHang( int MaKDL, int MaTT, boolean TrangThaiDonHang) {
//        this.MaKDL = MaKDL;
//        this.MaTT = MaTT;
//        this.TrangThaiDonHang = TrangThaiDonHang;
//    }
//
//    public DonHang() {
//    }
//
//    public DonHang(int MaKDL) {
//        this.MaKDL = MaKDL;
//    }
//
//
//    public void setMaDH(int MaDH) {
//        this.MaDH = MaDH;
//    }
//
//    public void setMaKDL(int MaKDL) {
//        this.MaKDL = MaKDL;
//    }
//
//    public void setMaTT(int MaTT) {
//        this.MaTT = MaTT;
//    }
//
//    public int getMaDH() {
//        return MaDH;
//    }
//
//    public int getMaKDL() {
//        return MaKDL;
//    }
//
//    public int getMaTT() {
//        return MaTT;
//    }
//
//    public boolean isTrangThaiDonHang() {
//        return TrangThaiDonHang;
//    }
//
//    public void setTrangThaiDonHang(boolean TrangThaiDonHang) {
//        this.TrangThaiDonHang = TrangThaiDonHang;
//    }
//
//    @Override
//    public String toString() {
//        return "DonHang{" + "TrangThaiDonHang=" + TrangThaiDonHang + '}';
//    }
//
//    
//}
//---------------------------------------------------------code mới
public class DonHang {
    private int maDH;
    private int maTT;
    private int maKDL;
    private boolean trangThaiDonHang;

    // Constructor
    public DonHang() {
    }

    public DonHang(int maTT, int maKDL, boolean trangThaiDonHang) {
        this.maTT = maTT;
        this.maKDL = maKDL;
        this.trangThaiDonHang = trangThaiDonHang;
    }

    // Getter và Setter
    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getMaKDL() {
        return maKDL;
    }

    public void setMaKDL(int maKDL) {
        this.maKDL = maKDL;
    }

    public boolean isTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(boolean trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }
}