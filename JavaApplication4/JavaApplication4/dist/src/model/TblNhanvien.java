/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Thang
 */
@Entity
@Table(name = "tbl_nhanvien")
@NamedQueries({
    @NamedQuery(name = "TblNhanvien.findAll", query = "SELECT t FROM TblNhanvien t"),
    @NamedQuery(name = "TblNhanvien.findByMaNV", query = "SELECT t FROM TblNhanvien t WHERE t.maNV = :maNV"),
    @NamedQuery(name = "TblNhanvien.findByTenNV", query = "SELECT t FROM TblNhanvien t WHERE t.tenNV = :tenNV"),
    @NamedQuery(name = "TblNhanvien.findByGioiTinh", query = "SELECT t FROM TblNhanvien t WHERE t.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "TblNhanvien.findByNamSinh", query = "SELECT t FROM TblNhanvien t WHERE t.namSinh = :namSinh"),
    @NamedQuery(name = "TblNhanvien.findByDiaChi", query = "SELECT t FROM TblNhanvien t WHERE t.diaChi = :diaChi"),
    @NamedQuery(name = "TblNhanvien.findBySdt", query = "SELECT t FROM TblNhanvien t WHERE t.sdt = :sdt"),
    @NamedQuery(name = "TblNhanvien.findByNgayVaoLam", query = "SELECT t FROM TblNhanvien t WHERE t.ngayVaoLam = :ngayVaoLam"),
    @NamedQuery(name = "TblNhanvien.findByQuyen", query = "SELECT t FROM TblNhanvien t WHERE t.quyen = :quyen")})
public class TblNhanvien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaNV")
    private Long maNV;
    @Basic(optional = false)
    @Column(name = "TenNV")
    private String tenNV;
    @Basic(optional = false)
    @Column(name = "GioiTinh")
    private String gioiTinh;
    @Basic(optional = false)
    @Column(name = "NamSinh")
    private int namSinh;
    @Basic(optional = false)
    @Column(name = "DiaChi")
    private String diaChi;
    @Basic(optional = false)
    @Column(name = "SDT")
    private int sdt;
    @Basic(optional = false)
    @Lob
    @Column(name = "HinhAnh")
    private String hinhAnh;
    @Basic(optional = false)
    @Column(name = "NgayVaoLam")
    @Temporal(TemporalType.DATE)
    private Date ngayVaoLam;
    @Basic(optional = false)
    @Lob
    @Column(name = "TaiKhoan")
    private String taiKhoan;
    @Basic(optional = false)
    @Lob
    @Column(name = "MatKhau")
    private String matKhau;
    @Basic(optional = false)
    @Column(name = "Quyen")
    private String quyen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNV")
    private Collection<TblHoadonnhaphang> tblHoadonnhaphangCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNV")
    private Collection<TblTinhluong> tblTinhluongCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNV")
    private Collection<TblChamcong> tblChamcongCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNV")
    private Collection<TblHoadonbanhang> tblHoadonbanhangCollection;

    public TblNhanvien() {
    }

    public TblNhanvien(Long maNV) {
        this.maNV = maNV;
    }

    public TblNhanvien(Long maNV, String tenNV, String gioiTinh, int namSinh, String diaChi, int sdt, String hinhAnh, Date ngayVaoLam, String taiKhoan, String matKhau, String quyen) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.hinhAnh = hinhAnh;
        this.ngayVaoLam = ngayVaoLam;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.quyen = quyen;
    }

    public Long getMaNV() {
        return maNV;
    }

    public void setMaNV(Long maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }

    public Collection<TblHoadonnhaphang> getTblHoadonnhaphangCollection() {
        return tblHoadonnhaphangCollection;
    }

    public void setTblHoadonnhaphangCollection(Collection<TblHoadonnhaphang> tblHoadonnhaphangCollection) {
        this.tblHoadonnhaphangCollection = tblHoadonnhaphangCollection;
    }

    public Collection<TblTinhluong> getTblTinhluongCollection() {
        return tblTinhluongCollection;
    }

    public void setTblTinhluongCollection(Collection<TblTinhluong> tblTinhluongCollection) {
        this.tblTinhluongCollection = tblTinhluongCollection;
    }

    public Collection<TblChamcong> getTblChamcongCollection() {
        return tblChamcongCollection;
    }

    public void setTblChamcongCollection(Collection<TblChamcong> tblChamcongCollection) {
        this.tblChamcongCollection = tblChamcongCollection;
    }

    public Collection<TblHoadonbanhang> getTblHoadonbanhangCollection() {
        return tblHoadonbanhangCollection;
    }

    public void setTblHoadonbanhangCollection(Collection<TblHoadonbanhang> tblHoadonbanhangCollection) {
        this.tblHoadonbanhangCollection = tblHoadonbanhangCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maNV != null ? maNV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNhanvien)) {
            return false;
        }
        TblNhanvien other = (TblNhanvien) object;
        if ((this.maNV == null && other.maNV != null) || (this.maNV != null && !this.maNV.equals(other.maNV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tenNV;
    }
    
}
