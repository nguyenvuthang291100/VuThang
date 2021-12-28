package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.TblChamcong;
import model.TblHoadonbanhang;
import model.TblHoadonnhaphang;
import model.TblTinhluong;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T16:05:25")
@StaticMetamodel(TblNhanvien.class)
public class TblNhanvien_ { 

    public static volatile SingularAttribute<TblNhanvien, String> taiKhoan;
    public static volatile CollectionAttribute<TblNhanvien, TblHoadonnhaphang> tblHoadonnhaphangCollection;
    public static volatile SingularAttribute<TblNhanvien, Integer> sdt;
    public static volatile SingularAttribute<TblNhanvien, String> matKhau;
    public static volatile SingularAttribute<TblNhanvien, Date> ngayVaoLam;
    public static volatile CollectionAttribute<TblNhanvien, TblHoadonbanhang> tblHoadonbanhangCollection;
    public static volatile CollectionAttribute<TblNhanvien, TblChamcong> tblChamcongCollection;
    public static volatile SingularAttribute<TblNhanvien, String> gioiTinh;
    public static volatile SingularAttribute<TblNhanvien, Long> maNV;
    public static volatile SingularAttribute<TblNhanvien, Integer> namSinh;
    public static volatile SingularAttribute<TblNhanvien, String> diaChi;
    public static volatile SingularAttribute<TblNhanvien, String> quyen;
    public static volatile SingularAttribute<TblNhanvien, String> hinhAnh;
    public static volatile SingularAttribute<TblNhanvien, String> tenNV;
    public static volatile CollectionAttribute<TblNhanvien, TblTinhluong> tblTinhluongCollection;

}