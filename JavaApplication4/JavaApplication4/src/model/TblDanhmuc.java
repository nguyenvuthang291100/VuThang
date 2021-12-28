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
@Table(name = "tbl_danhmuc")
@NamedQueries({
    @NamedQuery(name = "TblDanhmuc.findAll", query = "SELECT t FROM TblDanhmuc t"),
    @NamedQuery(name = "TblDanhmuc.findByMaDM", query = "SELECT t FROM TblDanhmuc t WHERE t.maDM = :maDM"),
    @NamedQuery(name = "TblDanhmuc.findByTenDM", query = "SELECT t FROM TblDanhmuc t WHERE t.tenDM = :tenDM")})
public class TblDanhmuc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaDM")
    private Long maDM;
    @Basic(optional = false)
    @Column(name = "TenDM")
    private String tenDM;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maDM")
    private Collection<TblSanpham> tblSanphamCollection;

    public TblDanhmuc() {
    }

    public TblDanhmuc(Long maDM) {
        this.maDM = maDM;
    }

    public TblDanhmuc(Long maDM, String tenDM) {
        this.maDM = maDM;
        this.tenDM = tenDM;
    }

    public Long getMaDM() {
        return maDM;
    }

    public void setMaDM(Long maDM) {
        this.maDM = maDM;
    }

    public String getTenDM() {
        return tenDM;
    }

    public void setTenDM(String tenDM) {
        this.tenDM = tenDM;
    }

    public Collection<TblSanpham> getTblSanphamCollection() {
        return tblSanphamCollection;
    }

    public void setTblSanphamCollection(Collection<TblSanpham> tblSanphamCollection) {
        this.tblSanphamCollection = tblSanphamCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDM != null ? maDM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDanhmuc)) {
            return false;
        }
        TblDanhmuc other = (TblDanhmuc) object;
        if ((this.maDM == null && other.maDM != null) || (this.maDM != null && !this.maDM.equals(other.maDM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tenDM;
    }
    
}
