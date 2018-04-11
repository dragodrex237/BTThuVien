/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class MTSach {
    int ma_muon;
    String ma_hoivien;
    String ma_sach;
    Date ngay_muon;
    Date ngay_tra_dk;
    Date ngay_tra_tt;
    String tinh_trang;
    int so_ngay_tre;
    String tien_phat;

    public MTSach(int ma_muon, String ma_hoivien, String ma_sach, Date ngay_muon, Date ngay_tra_dk, Date ngay_tra_tt, String tinh_trang, int so_ngay_tre, String tien_phat) {
        this.ma_muon = ma_muon;
        this.ma_hoivien = ma_hoivien;
        this.ma_sach = ma_sach;
        this.ngay_muon = ngay_muon;
        this.ngay_tra_dk = ngay_tra_dk;
        this.ngay_tra_tt = ngay_tra_tt;
        this.tinh_trang = tinh_trang;
        this.so_ngay_tre = so_ngay_tre;
        this.tien_phat = tien_phat;
    }
    
    public int getSo_ngay_tre() {
        return so_ngay_tre;
    }

    public void setSo_ngay_tre(int so_ngay_tre) {
        this.so_ngay_tre = so_ngay_tre;
    }

    public String getTien_phat() {
        return tien_phat;
    }

    public void setTien_phat(String tien_phat) {
        this.tien_phat = tien_phat;
    }

    public int getMa_muon() {
        return ma_muon;
    }

    public void setMa_muon(int ma_muon) {
        this.ma_muon = ma_muon;
    }

    public String getMa_hoivien() {
        return ma_hoivien;
    }

    public void setMa_hoivien(String ma_hoivien) {
        this.ma_hoivien = ma_hoivien;
    }

    public String getMa_sach() {
        return ma_sach;
    }

    public void setMa_sach(String ma_sach) {
        this.ma_sach = ma_sach;
    }

    public Date getNgay_muon() {
        return ngay_muon;
    }

    public void setNgay_muon(Date ngay_muon) {
        this.ngay_muon = ngay_muon;
    }

    public Date getNgay_tra_dk() {
        return ngay_tra_dk;
    }

    public void setNgay_tra_dk(Date ngay_tra_dk) {
        this.ngay_tra_dk = ngay_tra_dk;
    }

    public Date getNgay_tra_tt() {
        return ngay_tra_tt;
    }

    public void setNgay_tra_tt(Date ngay_tra_tt) {
        this.ngay_tra_tt = ngay_tra_tt;
    }

    public String getTinh_trang() {
        return tinh_trang;
    }

    public void setTinh_trang(String tinh_trang) {
        this.tinh_trang = tinh_trang;
    }
    
}
