package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.TblSanpham;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T16:05:25")
@StaticMetamodel(TblDanhmuc.class)
public class TblDanhmuc_ { 

    public static volatile SingularAttribute<TblDanhmuc, String> tenDM;
    public static volatile CollectionAttribute<TblDanhmuc, TblSanpham> tblSanphamCollection;
    public static volatile SingularAttribute<TblDanhmuc, Long> maDM;

}