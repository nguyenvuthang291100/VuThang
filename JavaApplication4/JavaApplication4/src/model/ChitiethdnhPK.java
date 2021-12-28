/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Thang
 */
@Embeddable
public class ChitiethdnhPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MaHDNH")
    private long maHDNH;
    @Basic(optional = false)
    @Column(name = "MaSP")
    private long maSP;

    public ChitiethdnhPK() {
    }

    public ChitiethdnhPK(long maHDNH, long maSP) {
        this.maHDNH = maHDNH;
        this.maSP = maSP;
    }

    public long getMaHDNH() {
        return maHDNH;
    }

    public void setMaHDNH(long maHDNH) {
        this.maHDNH = maHDNH;
    }

    public long getMaSP() {
        return maSP;
    }

    public void setMaSP(long maSP) {
        this.maSP = maSP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) maHDNH;
        hash += (int) maSP;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChitiethdnhPK)) {
            return false;
        }
        ChitiethdnhPK other = (ChitiethdnhPK) object;
        if (this.maHDNH != other.maHDNH) {
            return false;
        }
        if (this.maSP != other.maSP) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ChitiethdnhPK[ maHDNH=" + maHDNH + ", maSP=" + maSP + " ]";
    }
    
}
