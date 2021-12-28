package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Chitiethdbh;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T16:05:25")
@StaticMetamodel(TblHoadonbanhang.class)
public class TblHoadonbanhang_ { 

    public static volatile SingularAttribute<TblHoadonbanhang, Long> maKH;
    public static volatile SingularAttribute<TblHoadonbanhang, Date> ngayLap;
    public static volatile SingularAttribute<TblHoadonbanhang, String> TenKH;
    public static volatile SingularAttribute<TblHoadonbanhang, Float> tongTien;
    public static volatile CollectionAttribute<TblHoadonbanhang, Chitiethdbh> chitiethdbhCollection;
    public static volatile SingularAttribute<TblHoadonbanhang, String> TenNV;
    public static volatile SingularAttribute<TblHoadonbanhang, Long> maHDBH;
    public static volatile SingularAttribute<TblHoadonbanhang, Long> maNV;

}