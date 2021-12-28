/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.ChamcongXLDL;
import controller.DAO;
import controller.KNCSDL;
import controller.KyluongXLDL;
import controller.NhanvienXLDL;
import controller.TinhLuongXLDL;
import controller.UrlKN;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.TblChamcong;
import model.TblDanhmuc;
import model.TblKyluong;
import model.TblNhanvien;
import model.TblTinhluong;
import model.tblPhieuLuong;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import static org.eclipse.persistence.config.QueryType.Report;
import static views.frmChitietbanhang.print;

/**
 *
 * @author Thang
 */
public class frmTinhluong extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmTinhluong
     */
    DefaultTableModel tablemodel;
    TinhLuongXLDL nvDL;
    NhanvienXLDL NDL;
    KyluongXLDL KLDL;
    ChamcongXLDL cc;
    UrlKN uk;
    DAO dao;
    private static JasperReport report;

    public frmTinhluong() {
        initComponents();
        uk = new UrlKN();
        dao = new DAO(uk.knURL());
        nvDL = new TinhLuongXLDL();
        NDL = new NhanvienXLDL();
        KLDL = new KyluongXLDL();
        cc = new ChamcongXLDL();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadnv();
        loadmaNV();
        loadmaKL();

        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
    }

    void loadnv() {

        ArrayList<TblTinhluong> list = new ArrayList();
        list.clear();
        try {
            list = nvDL.getListTL();
            tablemodel = (DefaultTableModel) tblTinhLuong.getModel();
            for (TblTinhluong nv : list) {
                tablemodel.addRow(new Object[]{nv.getMaTL(), nv.getTenNV(), nv.getTenKL(), nv.getSoNgayLam(), nv.getThuong(), nv.getTru(), nv.getThue(), nv.getTongLuong(), nv.getNgayPhat()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }

    }

    void loadmaNV() {
        try {
            ArrayList<TblNhanvien> l = NDL.getListNV();
            for (TblNhanvien nv : l) {
                cboManhanvien.addItem(nv);
            }
        } catch (SQLException ex) {

        }
    }

    void loadmaKL() {
        try {
            ArrayList<TblKyluong> KL = KLDL.getListKL();
            for (TblKyluong kl : KL) {
                cboMakyluong.addItem(kl);
            }
        } catch (SQLException ex) {

        }
    }

    void hienthi(int i) {
        TableModel model = tblTinhLuong.getModel();
        txtMatinhluong.setText(model.getValueAt(i, 0).toString());
        String cbosub1 = model.getValueAt(i, 1).toString();
        for (int j = 0; j < cboManhanvien.getItemCount(); j++) {
            if (cboManhanvien.getItemAt(j).toString().equalsIgnoreCase(cbosub1)) {
                cboManhanvien.setSelectedIndex(j);
            }
        }
        String cbosub = model.getValueAt(i, 2).toString();
        for (int j = 0; j < cboMakyluong.getItemCount(); j++) {
            if (cboMakyluong.getItemAt(j).toString().equalsIgnoreCase(cbosub)) {
                cboMakyluong.setSelectedIndex(j);
            }
        }

        txtSoNgayLV.setText(model.getValueAt(i, 3).toString());
        txtthuong.setText(model.getValueAt(i, 4).toString());
        txtTru.setText(model.getValueAt(i, 5).toString());
        txtThue.setText(model.getValueAt(i, 6).toString());
        txtTongluong.setText(model.getValueAt(i, 7).toString());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(i, 8).toString());
        } catch (ParseException ex) {
            Logger.getLogger(frmTinhluong.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateNgayphat.setDate(date);
    }

    private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ngayPhat = dateFormat.format(DateNgayphat.getDate());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đúng định dạng ngày vào làm");
            return false;
        }
        if (txtSoNgayLV.getText().equals("")) {
            sb.append("Vui lòng nhập số ngày làm việc\n");
            txtSoNgayLV.requestFocus();
        }
        if (txtThue.getText().equals("")) {
            sb.append("Vui lòng nhập thuế\n");
            txtThue.requestFocus();
        }
        if (txtTru.getText().equals("")) {
            sb.append("Vui lòng nhập trừ\n");
            txtTru.requestFocus();
        }
        if (txtthuong.getText().equals("")) {
            sb.append("Vui lòng nhập thưởng\n");
            txtthuong.requestFocus();
        }
        if (txtTongluong.getText().equals("")) {
            sb.append("Vui lòng nhập tổng lương\n");
            txtTongluong.requestFocus();
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    public void getListTinhLuong() throws SQLException {
        TblNhanvien nhanvien = (TblNhanvien) cboManhanvien.getSelectedItem();
        String MaNV = String.valueOf(nhanvien.getMaNV());
        KNCSDL kn = new KNCSDL();
        ArrayList<TblChamcong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "SELECT tbl_chamcong.*FROM tbl_chamcong where month(Ngay)=" + cboMakyluong.getSelectedItem() + " AND MaNV=" + MaNV;
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int giovao = 0;
        int giora = 0;
        int tongsogio = 0;
        while (result.next()) {
            TblChamcong nv = new TblChamcong();
            nv.setMaChamCong(result.getLong(1));
            nv.setMaNV(result.getLong(2));
            nv.setNgay(result.getDate(3));
            nv.setGioVao(result.getTime(4));

            nv.setGioRa(result.getTime(5));
            nv.setGhiChu(result.getString(6));
            tongsogio++;

            list.add(nv);
        }

        int x = tongsogio;
        txtSoNgayLV.setText(x + "");

    }

    public static void print(String ma) {
        try {
            JRBeanCollectionDataSource jcd = new JRBeanCollectionDataSource(getData(ma));
            JasperPrint print = JasperFillManager.fillReport(report, null, jcd);
            if (false) {
                JasperPrintManager.printReport(print, true);    //  print auto
            } else {
                JasperViewer.viewReport(print, false);  //  view
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Collection getData(String ma) throws SQLException {
        KNCSDL kn = new KNCSDL();
        ArrayList<tblPhieuLuong> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "select tl.MaTL, nv.TenNV, kl.TenKL, tl.SoNgayLam,  tl.Thuong, tl.Tru, tl.Thue, tl.TongLuong, tl.NgayPhat FROM tbl_tinhluong tl, tbl_nhanvien nv, tbl_kyluong kl WHERE (nv.MaNV = tl.MaNV and kl.MaKL = tl.MaKL and tl.MaKL=" + ma + ")";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            tblPhieuLuong tl = new tblPhieuLuong();
            tl.setMaTL(result.getString(1));
            tl.setTenKL(result.getString(2));
            tl.setTenNV(result.getString(3));
            tl.setSoNgayLV(result.getString(4));
            tl.setThuong(result.getString(5));
            tl.setTru(result.getString(6));
            tl.setThue(result.getString(7));
            tl.setTongLuong(result.getString(8));
            tl.setNgayPhat(result.getString(9));
            list.add(tl);
        }
        return list;
    }

    void tongtien() {
        float kyluong = Float.parseFloat(lblKL.getText());
        int songaylv = Integer.parseInt(txtSoNgayLV.getText());
        float thuong = Float.parseFloat(txtthuong.getText());
        float tru = Float.parseFloat(txtTru.getText());
        float thue = Float.parseFloat(txtThue.getText());
        float tongTien = (kyluong * songaylv) + thuong - tru - thue;
        txtTongluong.setText("" + tongTien);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtMatinhluong = new javax.swing.JTextField();
        cboMakyluong = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTinhLuong = new rojeru_san.complementos.RSTableMetro();
        jLabel5 = new javax.swing.JLabel();
        cboManhanvien = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtthuong = new javax.swing.JTextField();
        txtThue = new javax.swing.JTextField();
        txtTongluong = new javax.swing.JTextField();
        txtTru = new javax.swing.JTextField();
        lbltikmkiem = new rojerusan.RSButtonMetro();
        txtSoNgayLV = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new rojerusan.RSButtonMetro();
        btnXoa = new rojerusan.RSButtonMetro();
        btnLuu = new rojerusan.RSButtonMetro();
        btnSua = new rojerusan.RSButtonMetro();
        rSButtonMetro2 = new rojerusan.RSButtonMetro();
        DateNgayphat = new com.toedter.calendar.JDateChooser();
        btnHuy = new rojerusan.RSButtonMetro();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtTimkiem = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        lblKL = new javax.swing.JLabel();
        btnTL = new rojerusan.RSButtonMetro();
        btninPhieuLuong = new rojerusan.RSButtonMetro();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Tính Lương");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 70, -1, -1));

        txtMatinhluong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtMatinhluong.setEnabled(false);
        jPanel1.add(txtMatinhluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 340, 36));

        cboMakyluong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboMakyluong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMakyluongItemStateChanged(evt);
            }
        });
        cboMakyluong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMakyluongMouseClicked(evt);
            }
        });
        jPanel1.add(cboMakyluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 100, 38));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Nhân viên:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, -1, 29));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Mã tính lương:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, 29));

        tblTinhLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Tính Lương", "Nhân Viên", "Kỳ Lương", "Số Ngày Làm Việc", "Thưởng", "Trừ", "Thuế", "Tổng Lương", "Ngày Phát"
            }
        ));
        tblTinhLuong.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tblTinhLuong.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblTinhLuong.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblTinhLuong.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblTinhLuong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblTinhLuong.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblTinhLuong.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblTinhLuong.setRowHeight(22);
        tblTinhLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTinhLuongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTinhLuong);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 384, 1670, 580));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Kỳ lương:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, 29));

        cboManhanvien.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboManhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboManhanvienMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cboManhanvienMouseExited(evt);
            }
        });
        cboManhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboManhanvienActionPerformed(evt);
            }
        });
        jPanel1.add(cboManhanvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 340, 38));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Số ngày làm việc:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, 29));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Thưởng:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, -1, 29));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Trừ:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, -1, 29));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Thuế:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 230, -1, 29));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("Tổng lương:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 280, -1, 29));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("Ngày phát:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 330, -1, 29));

        txtthuong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtthuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 110, 340, 36));

        txtThue.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 230, 340, 36));

        txtTongluong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtTongluong.setEnabled(false);
        jPanel1.add(txtTongluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 280, 340, 36));

        txtTru.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTruActionPerformed(evt);
            }
        });
        jPanel1.add(txtTru, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 170, 340, 36));

        lbltikmkiem.setBackground(new java.awt.Color(255, 255, 255));
        lbltikmkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px_1.png"))); // NOI18N
        lbltikmkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbltikmkiemActionPerformed(evt);
            }
        });
        jPanel1.add(lbltikmkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1620, 40, 20, 20));

        txtSoNgayLV.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtSoNgayLV.setEnabled(false);
        jPanel1.add(txtSoNgayLV, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 340, 36));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 91, -1, -1));

        btnThem.setBackground(new java.awt.Color(255, 153, 0));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 100, 119, 45));

        btnXoa.setBackground(new java.awt.Color(255, 153, 0));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 100, 119, 45));

        btnLuu.setBackground(new java.awt.Color(255, 153, 0));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 170, 119, 45));

        btnSua.setBackground(new java.awt.Color(255, 153, 0));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 170, 119, 45));

        rSButtonMetro2.setBackground(new java.awt.Color(255, 153, 0));
        rSButtonMetro2.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMetro2.setText("Kỳ Lương");
        rSButtonMetro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonMetro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 300, 120, 60));

        DateNgayphat.setDateFormatString("yyyy-MM-dd");
        DateNgayphat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(DateNgayphat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 330, 340, 40));

        btnHuy.setBackground(new java.awt.Color(255, 153, 0));
        btnHuy.setText("Khởi Tạo");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 230, 119, 45));

        jPanel10.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 60, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, -1, 270));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTimkiem.setPlaceholder("Search...\n");
        jPanel1.add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 30, 330, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Tìm Kiếm:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 40, -1, -1));

        lblKL.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblKL.setText("jLabel3");
        jPanel1.add(lblKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 220, 40));

        btnTL.setBackground(new java.awt.Color(255, 153, 0));
        btnTL.setText("Tình Lương");
        btnTL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTLActionPerformed(evt);
            }
        });
        jPanel1.add(btnTL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 230, 119, 45));

        btninPhieuLuong.setBackground(new java.awt.Color(255, 153, 0));
        btninPhieuLuong.setText("In Phiếu Lương");
        btninPhieuLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninPhieuLuongActionPerformed(evt);
            }
        });
        jPanel1.add(btninPhieuLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 300, 120, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1760, 990));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonMetro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro2ActionPerformed
        // TODO add your handling code here:
        frmKL kl = new frmKL();
        kl.setVisible(true);
    }//GEN-LAST:event_rSButtonMetro2ActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtMatinhluong.setText("");
        txtSoNgayLV.setText("");
        txtThue.setText("");
        txtTongluong.setText("");
        txtTru.setText("");
        txtthuong.setText("");
        tblTinhLuong.removeAll();
        tablemodel.setRowCount(0);
        txtTimkiem.setText("");
        DateNgayphat.setDate(null);
        Color mauxanh = new Color(255, 153, 0);
        btnThem.setEnabled(true);
        btnThem.setBackground(mauxanh);
        btnSua.setEnabled(true);
        btnSua.setBackground(mauxanh);
        btnXoa.setEnabled(true);
        btnXoa.setBackground(mauxanh);
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
        loadnv();

    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTruActionPerformed

    private void cboManhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboManhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboManhanvienActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        them = true;
        sua = false;
        Color mauxanh = new Color(255, 153, 0);
        btnThem.setBackground(Color.GRAY);
        btnSua.setEnabled(false);
        btnSua.setBackground(Color.GRAY);
        btnXoa.setEnabled(false);
        btnXoa.setBackground(Color.GRAY);
        btnLuu.setEnabled(true);
        btnLuu.setBackground(mauxanh);


    }//GEN-LAST:event_btnThemActionPerformed
    public boolean them, sua;
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        Color mauxanh = new Color(255, 153, 0);
        them = false;
        sua = true;
        btnThem.setEnabled(false);
        btnThem.setBackground(Color.GRAY);
        btnSua.setEnabled(false);
        btnSua.setBackground(Color.GRAY);
        btnXoa.setEnabled(false);
        btnXoa.setBackground(Color.GRAY);
        btnLuu.setEnabled(true);
        btnLuu.setBackground(mauxanh);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa?", "xoa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_NO_OPTION) {
            TblNhanvien nv = (TblNhanvien) cboManhanvien.getSelectedItem();
            String MaNV = String.valueOf(nv.getMaNV());
            TblKyluong kl = (TblKyluong) cboMakyluong.getSelectedItem();
            String MaKL = String.valueOf(kl.getMaKL());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ngayphat = dateFormat.format(DateNgayphat.getDate());
            Connection conn = dao.getConnect();
            String caulenh = "delete from tbl_tinhluong where MaTL=" + txtMatinhluong.getText();
            boolean a = dao.Lenh(caulenh, conn);
            if (a) {
                tblTinhLuong.removeAll();
                tablemodel.setRowCount(0);
                loadnv();
                JOptionPane.showMessageDialog(this, "Xóa Thành Công");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa không thành công");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblTinhLuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTinhLuongMouseClicked
        // TODO add your handling code here:
        int i = tblTinhLuong.getSelectedRow();
        hienthi(i);
    }//GEN-LAST:event_tblTinhLuongMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        Color mauxanh = new Color(255, 153, 0);
        btnThem.setBackground(mauxanh);
        btnSua.setEnabled(true);
        btnSua.setBackground(mauxanh);
        btnXoa.setEnabled(true);
        btnXoa.setBackground(mauxanh);
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Lưu?", "Lưu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_NO_OPTION) {
            if (KTDL()) {
                if (them) {
                    TblNhanvien nv = (TblNhanvien) cboManhanvien.getSelectedItem();
                    String MaNV = String.valueOf(nv.getMaNV());
                    TblKyluong kl = (TblKyluong) cboMakyluong.getSelectedItem();
                    Date dt = new Date();
                    String MaKL = String.valueOf(kl.getMaKL());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String ngayphat = dateFormat.format(DateNgayphat.getDate());
                    try {
                        Connection conn = dao.getConnect();
                        String caulenh = "INSERT INTO tbl_tinhluong VALUE(Null,'" + MaNV + "','" + MaKL + "','" + 18 + "','" + txtSoNgayLV.getText() + "','" + txtthuong.getText() + "','" + txtTru.getText() + "','" + txtThue.getText() + "','" + txtTongluong.getText() + "','" + ngayphat + "')";
                        boolean a = dao.Lenh(caulenh, conn);
                        if (a) {
                            tblTinhLuong.removeAll();
                            tablemodel.setRowCount(0);
                            loadnv();
                            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm không thành công");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(frmTinhluong.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sua) {
                    TblNhanvien nv = (TblNhanvien) cboManhanvien.getSelectedItem();
                    String MaNV = String.valueOf(nv.getMaNV());
                    TblKyluong kl = (TblKyluong) cboMakyluong.getSelectedItem();
                    String MaKL = String.valueOf(kl.getMaKL());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String ngayphat = dateFormat.format(DateNgayphat.getDate());
                    try {
                        Connection conn = dao.getConnect();
                        String caulenh = "update tbl_tinhluong set MaNV='" + MaNV + "',MaKL='" + MaKL + "', MaChamCong='" + 18 + "', SoNgayLam='" + txtSoNgayLV.getText() + "', Thuong='" + txtthuong.getText() + "',Tru='" + txtTru.getText() + "', Thue='" + txtThue.getText() + "', TongLuong='" + txtTongluong.getText() + "', NgayPhat='" + ngayphat + "' where MaTL=" + txtMatinhluong.getText();
                        boolean a = dao.Lenh(caulenh, conn);
                        if (a) {
                            tblTinhLuong.removeAll();
                            tablemodel.setRowCount(0);
                            loadnv();
                            JOptionPane.showMessageDialog(this, "sửa Thành Công");
                        } else {
                            JOptionPane.showMessageDialog(this, "sửa không thành công");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(frmTinhluong.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }//GEN-LAST:event_btnLuuActionPerformed
    }
    private void lbltikmkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbltikmkiemActionPerformed
        // TODO add your handling code here:
        tblTinhLuong.removeAll();
        tablemodel.setRowCount(0);
        String tk = txtTimkiem.getText();
        ArrayList<TblTinhluong> list = new ArrayList();
        list.clear();
        try {
            list = nvDL.timkiemTL(tk);
            tablemodel = (DefaultTableModel) tblTinhLuong.getModel();
            for (TblTinhluong nv : list) {
                tablemodel.addRow(new Object[]{nv.getMaTL(), nv.getTenNV(), nv.getTenKL(), nv.getMaChamCong(), nv.getSoNgayLam(), nv.getThuong(), nv.getTru(), nv.getThue(), nv.getTongLuong(), nv.getNgayPhat()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }//GEN-LAST:event_lbltikmkiemActionPerformed

    private void cboMakyluongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMakyluongItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            TblKyluong ch = (TblKyluong) cboMakyluong.getSelectedItem();
            long madv = ch.getMaKL();
            try {
                TblKyluong dv = KLDL.getKL(madv);
                lblKL.setText(dv.getLuong() + "");
            } catch (SQLException ex) {
                Logger.getLogger(frmTinhluong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_cboMakyluongItemStateChanged

    private void btnTLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTLActionPerformed
        // TODO add your handling code here:
        tongtien();
    }//GEN-LAST:event_btnTLActionPerformed

    private void btninPhieuLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninPhieuLuongActionPerformed
        // TODO add your handling code here:
         try {
            report = JasperCompileManager.compileReport("src/Views/ReportLuong.jrxml");
            TblKyluong ky= (TblKyluong) cboMakyluong.getSelectedItem();
                String ma= String.valueOf(ky.getMaKL());
            print(ma);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btninPhieuLuongActionPerformed

    private void cboManhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboManhanvienMouseClicked
        // TODO add your handling code here:
             try {
            // TODO add your handling code here:
            getListTinhLuong();
        } catch (SQLException ex) {
            Logger.getLogger(frmTinhluong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboManhanvienMouseClicked

    private void cboMakyluongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMakyluongMouseClicked
        // TODO add your handling code here:
   
    }//GEN-LAST:event_cboMakyluongMouseClicked

    private void cboManhanvienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboManhanvienMouseExited
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:
            getListTinhLuong();
        } catch (SQLException ex) {
            Logger.getLogger(frmTinhluong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cboManhanvienMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgayphat;
    private rojerusan.RSButtonMetro btnHuy;
    private rojerusan.RSButtonMetro btnLuu;
    private rojerusan.RSButtonMetro btnSua;
    private rojerusan.RSButtonMetro btnTL;
    private rojerusan.RSButtonMetro btnThem;
    private rojerusan.RSButtonMetro btnXoa;
    private rojerusan.RSButtonMetro btninPhieuLuong;
    private javax.swing.JComboBox<TblKyluong> cboMakyluong;
    private javax.swing.JComboBox<TblNhanvien> cboManhanvien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblKL;
    private rojerusan.RSButtonMetro lbltikmkiem;
    private rojerusan.RSButtonMetro rSButtonMetro2;
    private rojeru_san.complementos.RSTableMetro tblTinhLuong;
    private javax.swing.JTextField txtMatinhluong;
    private javax.swing.JTextField txtSoNgayLV;
    private javax.swing.JTextField txtThue;
    private app.bolivia.swing.JCTextField txtTimkiem;
    private javax.swing.JTextField txtTongluong;
    private javax.swing.JTextField txtTru;
    private javax.swing.JTextField txtthuong;
    // End of variables declaration//GEN-END:variables
}
