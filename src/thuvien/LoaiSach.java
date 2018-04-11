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
public class LoaiSach {
    String maLS;
    String tenLS;

    public LoaiSach(String maLS, String tenLS) {
        this.maLS = maLS;
        this.tenLS = tenLS;
    }

    public String getMaLS() {
        return maLS;
    }

    public void setMaLS(String maLS) {
        this.maLS = maLS;
    }

    public String getTenLS() {
        return tenLS;
    }

    public void setTenLS(String tenLS) {
        this.tenLS = tenLS;
    }
    
}
