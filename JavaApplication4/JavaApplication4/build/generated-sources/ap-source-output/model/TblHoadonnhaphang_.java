package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Chitiethdnh;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T16:05:25")
@StaticMetamodel(TblHoadonnhaphang.class)
public class TblHoadonnhaphang_ { 

    public static volatile SingularAttribute<TblHoadonnhaphang, Long> maHDNH;
    public static volatile SingularAttribute<TblHoadonnhaphang, Float> tongTien;
    public static volatile CollectionAttribute<TblHoadonnhaphang, Chitiethdnh> chitiethdnhCollection;
    public static volatile SingularAttribute<TblHoadonnhaphang, String> TenNV;
    public static volatile SingularAttribute<TblHoadonnhaphang, Date> ngayNhap;
    public static volatile SingularAttribute<TblHoadonnhaphang, Long> maNV;

}