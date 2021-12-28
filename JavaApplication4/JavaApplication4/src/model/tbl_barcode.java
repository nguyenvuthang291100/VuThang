/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.InputStream;

/**
 *
 * @author Thang
 */
public class tbl_barcode {
    private float giaSP;
    private InputStream image;

    public tbl_barcode(float giaSP, InputStream image) {
        this.giaSP = giaSP;
        this.image = image;
    }

    public tbl_barcode() {
    }

    public float getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(float giaSP) {
        this.giaSP = giaSP;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
    
}
