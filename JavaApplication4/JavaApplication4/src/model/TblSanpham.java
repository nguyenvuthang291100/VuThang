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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Thang
 */
@Entity
@Table(name = "tbl_sanpham")
@NamedQueries({
    @NamedQuery(name = "TblSanpham.findAll", query = "SELECT t FROM TblSanpham t"),
    @NamedQuery(name = "TblSanpham.findByMaSP", query = "SELECT t FROM TblSanpham t WHERE t.maSP = :maSP"),
    @NamedQuery(name = "TblSanpham.findByTenSP", query = "SELECT t FROM TblSanpham t WHERE t.tenSP = :tenSP"),
    @NamedQuery(name = "TblSanpham.findBySize", query = "SELECT t FROM TblSanpham t WHERE t.size = :size"),
    @NamedQuery(name = "TblSanpham.findByMau", query = "SELECT t FROM TblSanpham t WHERE t.mau = :mau"),
    @NamedQuery(name = "TblSanpham.findByGia", query = "SELECT t FROM TblSanpham t WHERE t.gia = :gia"),
    @NamedQuery(name = "TblSanpham.findBySLTon", query = "SELECT t FROM TblSanpham t WHERE t.sLTon = :sLTon")})
public class TblSanpham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaSP")
    private Long maSP;
    @Basic(optional = false)
    @Column(name = "TenSP")
    private String tenSP;
    @Basic(optional = false)
    @Column(name = "Size")
    private String size;
    @Basic(optional = false)
    @Column(name = "Mau")
    private String mau;
    @Basic(optional = false)
    @Column(name = "Gia")
    private float gia;
    @Basic(optional = false)
    @Lob
    @Column(name = "HinhAnh")
    private String hinhAnh;
    @Basic(optional = false)
    @Column(name = "SLTon")
    private int sLTon;
    @JoinColumn(name = "MaDM", referencedColumnName = "MaDM")
    @ManyToOne(optional = false)
    private Long maDM;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblSanpham")
    private Collection<Chitiethdnh> chitiethdnhCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblSanpham")
    private Collection<Chitiethdbh> chitiethdbhCollection;
private String TenDM;

    public int getsLTon() {
        return sLTon;
    }

    public void setsLTon(int sLTon) {
        this.sLTon = sLTon;
    }

    public String getTenDM() {
        return TenDM;
    }

    public void setTenDM(String TenDM) {
        this.TenDM = TenDM;
    }

    public TblSanpham() {
    }

    public TblSanpham(Long maSP) {
        this.maSP = maSP;
    }
  

    public TblSanpham(Long maSP, String tenSP, String size, String mau, float gia, String hinhAnh, int sLTon) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.size = size;
        this.mau = mau;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.sLTon = sLTon;
    }

    public Long getMaSP() {
        return maSP;
    }

    public void setMaSP(Long maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSLTon() {
        return sLTon;
    }

    public void setSLTon(int sLTon) {
        this.sLTon = sLTon;
    }

    public long getMaDM() {
        return maDM;
    }

    public void setMaDM(long maDM) {
        this.maDM = maDM;
    }

    public Collection<Chitiethdnh> getChitiethdnhCollection() {
        return chitiethdnhCollection;
    }

    public void setChitiethdnhCollection(Collection<Chitiethdnh> chitiethdnhCollection) {
        this.chitiethdnhCollection = chitiethdnhCollection;
    }

    public Collection<Chitiethdbh> getChitiethdbhCollection() {
        return chitiethdbhCollection;
    }

    public void setChitiethdbhCollection(Collection<Chitiethdbh> chitiethdbhCollection) {
        this.chitiethdbhCollection = chitiethdbhCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maSP != null ? maSP.hashCode() : 0);
        return hash;
    }
     @Override
    public String toString() {
        return tenSP;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblSanpham)) {
            return false;
        }
        TblSanpham other = (TblSanpham) object;
        if ((this.maSP == null && other.maSP != null) || (this.maSP != null && !this.maSP.equals(other.maSP))) {
            return false;
        }
        return true;
    }
    
}
