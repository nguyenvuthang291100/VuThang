package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.TblTinhluong;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T16:05:25")
@StaticMetamodel(TblKyluong.class)
public class TblKyluong_ { 

    public static volatile SingularAttribute<TblKyluong, Long> maKL;
    public static volatile SingularAttribute<TblKyluong, Float> luong;
    public static volatile CollectionAttribute<TblKyluong, TblTinhluong> tblTinhluongCollection;
    public static volatile SingularAttribute<TblKyluong, String> tenKL;

}