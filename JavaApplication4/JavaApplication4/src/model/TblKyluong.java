/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Thang
 */
@Entity
@Table(name = "tbl_kyluong")
@NamedQueries({
    @NamedQuery(name = "TblKyluong.findAll", query = "SELECT t FROM TblKyluong t"),
    @NamedQuery(name = "TblKyluong.findByMaKL", query = "SELECT t FROM TblKyluong t WHERE t.maKL = :maKL"),
    @NamedQuery(name = "TblKyluong.findByTenKL", query = "SELECT t FROM TblKyluong t WHERE t.tenKL = :tenKL"),
    @NamedQuery(name = "TblKyluong.findByLuong", query = "SELECT t FROM TblKyluong t WHERE t.luong = :luong")})
public class TblKyluong implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaKL")
    private Long maKL;
    @Basic(optional = false)
    @Column(name = "TenKL")
    private String tenKL;
    @Basic(optional = false)
    @Column(name = "Luong")
    private float luong;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maKL")
    private Collection<TblTinhluong> tblTinhluongCollection;

    public TblKyluong() {
    }

    public TblKyluong(Long maKL) {
        this.maKL = maKL;
    }

    public TblKyluong(Long maKL, String tenKL, float luong) {
        this.maKL = maKL;
        this.tenKL = tenKL;
        this.luong = luong;
    }

    public Long getMaKL() {
        return maKL;
    }

    public void setMaKL(Long maKL) {
        this.maKL = maKL;
    }

    public String getTenKL() {
        return tenKL;
    }

    public void setTenKL(String tenKL) {
        this.tenKL = tenKL;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public Collection<TblTinhluong> getTblTinhluongCollection() {
        return tblTinhluongCollection;
    }

    public void setTblTinhluongCollection(Collection<TblTinhluong> tblTinhluongCollection) {
        this.tblTinhluongCollection = tblTinhluongCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maKL != null ? maKL.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblKyluong)) {
            return false;
        }
        TblKyluong other = (TblKyluong) object;
        if ((this.maKL == null && other.maKL != null) || (this.maKL != null && !this.maKL.equals(other.maKL))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tenKL;
    }
    
}
