package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.TblHoadonbanhang;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T16:05:25")
@StaticMetamodel(TblKhachhang.class)
public class TblKhachhang_ { 

    public static volatile SingularAttribute<TblKhachhang, Long> maKH;
    public static volatile CollectionAttribute<TblKhachhang, TblHoadonbanhang> tblHoadonbanhangCollection;
    public static volatile SingularAttribute<TblKhachhang, String> tenKH;
    public static volatile SingularAttribute<TblKhachhang, String> loai;

}