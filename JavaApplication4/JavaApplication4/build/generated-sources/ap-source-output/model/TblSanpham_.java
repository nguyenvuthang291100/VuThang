package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Chitiethdbh;
import model.Chitiethdnh;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T16:05:25")
@StaticMetamodel(TblSanpham.class)
public class TblSanpham_ { 

    public static volatile SingularAttribute<TblSanpham, String> mau;
    public static volatile SingularAttribute<TblSanpham, Long> maSP;
    public static volatile SingularAttribute<TblSanpham, Integer> sLTon;
    public static volatile SingularAttribute<TblSanpham, String> hinhAnh;
    public static volatile SingularAttribute<TblSanpham, String> size;
    public static volatile SingularAttribute<TblSanpham, String> tenSP;
    public static volatile CollectionAttribute<TblSanpham, Chitiethdnh> chitiethdnhCollection;
    public static volatile CollectionAttribute<TblSanpham, Chitiethdbh> chitiethdbhCollection;
    public static volatile SingularAttribute<TblSanpham, String> TenDM;
    public static volatile SingularAttribute<TblSanpham, Float> gia;
    public static volatile SingularAttribute<TblSanpham, Long> maDM;

}