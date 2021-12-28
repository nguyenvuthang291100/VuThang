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
public class ChitiethdbhPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MaHDBH")
    private long maHDBH;
    @Basic(optional = false)
    @Column(name = "MaSP")
    private long maSP;

    public ChitiethdbhPK() {
    }

    public ChitiethdbhPK(long maHDBH, long maSP) {
        this.maHDBH = maHDBH;
        this.maSP = maSP;
    }

    public long getMaHDBH() {
        return maHDBH;
    }

    public void setMaHDBH(long maHDBH) {
        this.maHDBH = maHDBH;
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
        hash += (int) maHDBH;
        hash += (int) maSP;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChitiethdbhPK)) {
            return false;
        }
        ChitiethdbhPK other = (ChitiethdbhPK) object;
        if (this.maHDBH != other.maHDBH) {
            return false;
        }
        if (this.maSP != other.maSP) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ChitiethdbhPK[ maHDBH=" + maHDBH + ", maSP=" + maSP + " ]";
    }
    
}
