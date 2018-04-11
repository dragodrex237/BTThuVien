/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thuvien;

/**
 *
 * @author ASUS
 */
public class HoiVien {
    String MaHV;
    String TenHV;

    public HoiVien(String MaHV, String TenHV) {
        this.MaHV = MaHV;
        this.TenHV = TenHV;
    }

    public String getMaHV() {
        return MaHV;
    }

    public void setMaHV(String MaHV) {
        this.MaHV = MaHV;
    }

    public String getTenHV() {
        return TenHV;
    }

    public void setTenHV(String TenHV) {
        this.TenHV = TenHV;
    }
    
}
