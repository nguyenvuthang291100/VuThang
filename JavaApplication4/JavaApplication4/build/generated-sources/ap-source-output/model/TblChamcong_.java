package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.TblTinhluong;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T16:05:25")
@StaticMetamodel(TblChamcong.class)
public class TblChamcong_ { 

    public static volatile SingularAttribute<TblChamcong, Date> gioRa;
    public static volatile SingularAttribute<TblChamcong, Date> ngay;
    public static volatile SingularAttribute<TblChamcong, String> ghiChu;
    public static volatile CollectionAttribute<TblChamcong, TblTinhluong> tblTinhluongCollection;
    public static volatile SingularAttribute<TblChamcong, Date> gioVao;
    public static volatile SingularAttribute<TblChamcong, Long> maChamCong;
    public static volatile SingularAttribute<TblChamcong, String> TenNV;
    public static volatile SingularAttribute<TblChamcong, Long> maNV;

}