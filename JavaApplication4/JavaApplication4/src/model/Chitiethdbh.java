/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Thang
 */
@Entity
@Table(name = "chitiethdbh")
@NamedQueries({
    @NamedQuery(name = "Chitiethdbh.findAll", query = "SELECT c FROM Chitiethdbh c")
    ,
    @NamedQuery(name = "Chitiethdbh.findByMaHDBH", query = "SELECT c FROM Chitiethdbh c WHERE c.chitiethdbhPK.maHDBH = :maHDBH")
    ,
    @NamedQuery(name = "Chitiethdbh.findByMaSP", query = "SELECT c FROM Chitiethdbh c WHERE c.chitiethdbhPK.maSP = :maSP")
    ,
    @NamedQuery(name = "Chitiethdbh.findBySoLuong", query = "SELECT c FROM Chitiethdbh c WHERE c.soLuong = :soLuong")
    ,
    @NamedQuery(name = "Chitiethdbh.findByTongTien", query = "SELECT c FROM Chitiethdbh c WHERE c.tongTien = :tongTien")})
public class Chitiethdbh implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChitiethdbhPK chitiethdbhPK;
    @Basic(optional = false)
    @Column(name = "SoLuong")
    private int soLuong;
    @Basic(optional = false)
    @Column(name = "TongTien")
    private float tongTien;
    @JoinColumn(name = "MaHDBH", referencedColumnName = "MaHDBH", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Long MaHDBH;
    @JoinColumn(name = "MaSP", referencedColumnName = "MaSP", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Long MaSP;
    private String TenSP;

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public Chitiethdbh() {
    }

    public Chitiethdbh(ChitiethdbhPK chitiethdbhPK) {
        this.chitiethdbhPK = chitiethdbhPK;
    }

    public Chitiethdbh(ChitiethdbhPK chitiethdbhPK, int soLuong, float tongTien) {
        this.chitiethdbhPK = chitiethdbhPK;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public Chitiethdbh(long maHDBH, long maSP) {
        this.chitiethdbhPK = new ChitiethdbhPK(maHDBH, maSP);
    }

    public ChitiethdbhPK getChitiethdbhPK() {
        return chitiethdbhPK;
    }

    public void setChitiethdbhPK(ChitiethdbhPK chitiethdbhPK) {
        this.chitiethdbhPK = chitiethdbhPK;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public long getMaHDBH() {
        return MaHDBH;
    }

    public void setMaHDBH(long MaHDBH) {
        this.MaHDBH = MaHDBH;
    }

    public long getMaSP() {
        return MaSP;
    }

    public void setMaSP(long MaSP) {
        this.MaSP = MaSP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chitiethdbhPK != null ? chitiethdbhPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chitiethdbh)) {
            return false;
        }
        Chitiethdbh other = (Chitiethdbh) object;
        if ((this.chitiethdbhPK == null && other.chitiethdbhPK != null) || (this.chitiethdbhPK != null && !this.chitiethdbhPK.equals(other.chitiethdbhPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Chitiethdbh[ chitiethdbhPK=" + chitiethdbhPK + " ]";
    }

}
