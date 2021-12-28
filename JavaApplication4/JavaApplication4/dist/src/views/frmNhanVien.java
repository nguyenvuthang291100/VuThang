/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;




import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import controller.DAO;
import controller.NhanvienXLDL;
import controller.UrlKN;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import java.util.Locale;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.TblDanhmuc;
import model.TblNhanvien;
import model.nhanvien;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import static views.frmSP.tenFile;

/**
 *
 * @author Thang
 */
public class frmNhanVien extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmNhanVien
     */
    DefaultTableModel tablemodel;
    NhanvienXLDL nvDL;
    UrlKN uk;
    DAO dao;

    public frmNhanVien() {
        initComponents();
        nvDL = new NhanvienXLDL();
        uk = new UrlKN();
        dao = new DAO(uk.knURL());
        loadnv();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
    }

    void loadnv() {

        ArrayList<TblNhanvien> list = new ArrayList();
        list.clear();
        try {
            list = nvDL.getListNV();
            tablemodel = (DefaultTableModel) tblNhanvien.getModel();
            for (TblNhanvien nv : list) {
                tablemodel.addRow(new Object[]{nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(), nv.getNamSinh(), nv.getDiaChi(), nv.getSdt(), nv.getHinhAnh(), nv.getNgayVaoLam(), nv.getTaiKhoan(), nv.getMatKhau(), nv.getQuyen()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }

    }

    void hienthi(int i) {
        TableModel model = tblNhanvien.getModel();
        txtManv.setText(model.getValueAt(i, 0).toString());
        txtTenNV.setText(model.getValueAt(i, 1).toString());
        cboGioitinh.setSelectedItem(model.getValueAt(i, 2).toString());
        txtnamsinh.setText(model.getValueAt(i, 3).toString());
        txtDiachi.setText(model.getValueAt(i, 4).toString());
        txtSDT.setText(model.getValueAt(i, 5).toString());
        String src = "src/img/" + model.getValueAt(i, 6).toString();
        loadanh(lblNV, src);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(i, 7).toString());
        } catch (ParseException ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateNgayVaoLam.setDate(date);

        txtTaikhoan.setText(model.getValueAt(i, 8).toString());
        txtMatkhau.setText(model.getValueAt(i, 9).toString());
        cboQuyen.setSelectedItem(model.getValueAt(i, 10).toString());

    }

    private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ngayvaolam = dateFormat.format(DateNgayVaoLam.getDate());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đúng định dạng ngày vào làm");
            return false;
        }

        if (tenFile == "") {
            sb.append("Vui lòng chọn file ảnh\n");
        }
        if (txtTenNV.getText().equals("")) {
            sb.append("Vui lòng nhập họ tên\n");
            txtTenNV.requestFocus();
        }
        if (txtDiachi.getText().equals("")) {
            sb.append("Vui lòng nhập địa chỉ\n");
            txtDiachi.requestFocus();
        }

        if (txtSDT.getText().equals("")) {
            sb.append("Vui lòng nhập số điện thoại\n");
            txtSDT.requestFocus();
        }
        if (txtnamsinh.getText().equals("")) {
            sb.append("Vui lòng nhập năm sinh\n");
            txtnamsinh.requestFocus();
        }
        if (txtTaikhoan.getText().equals("")) {
            sb.append("Vui lòng nhập tài khoản\n");
            txtTaikhoan.requestFocus();
        }
        if (txtMatkhau.getText().equals("")) {
            sb.append("Vui lòng nhập mật khẩu\n");
            txtMatkhau.requestFocus();
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
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
        txtTenNV = new javax.swing.JTextField();
        rSButtonMetro9 = new rojerusan.RSButtonMetro();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        txtnamsinh = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cboGioitinh = new javax.swing.JComboBox<>();
        txtTaikhoan = new javax.swing.JTextField();
        txtMatkhau = new javax.swing.JTextField();
        cboQuyen = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanvien = new rojeru_san.complementos.RSTableMetro();
        jPanel2 = new javax.swing.JPanel();
        btnSua = new rojerusan.RSButtonMetro();
        btnThem = new rojerusan.RSButtonMetro();
        btnXoa = new rojerusan.RSButtonMetro();
        btnLuu = new rojerusan.RSButtonMetro();
        DateNgayVaoLam = new com.toedter.calendar.JDateChooser();
        lblNV = new javax.swing.JLabel();
        txtDiachi = new javax.swing.JTextField();
        btnHuy = new rojerusan.RSButtonMetro();
        btnTaiAnh = new rojerusan.RSButtonMetro();
        txtTimkiem = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();
        btnIntheNV = new rojerusan.RSButtonMetro();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Thông Tin Nhân Viên");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 388, -1));

        jPanel9.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, -1, -1));

        txtTenNV.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 370, 36));

        rSButtonMetro9.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonMetro9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px_1.png"))); // NOI18N
        rSButtonMetro9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro9ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonMetro9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 40, 20, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Mã nhân viên:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, 29));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Tên nhân viên:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, 29));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Giới tính:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, 29));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Năm sinh:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, 29));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Địa chỉ:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, -1, 29));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Số điện thoại:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, -1, 29));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Ngày vào làm:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 170, -1, 29));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Hình ảnh:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 100, -1, 29));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Tài khoản:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 230, -1, 29));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("Mật khẩu:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 280, -1, 29));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("Quyền:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 330, -1, 29));

        txtManv.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtManv.setEnabled(false);
        jPanel1.add(txtManv, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 250, 36));

        txtnamsinh.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtnamsinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 370, 36));

        txtSDT.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 110, 370, 36));

        cboGioitinh.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboGioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jPanel1.add(cboGioitinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 250, 33));

        txtTaikhoan.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtTaikhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 230, 370, 36));

        txtMatkhau.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtMatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 280, 370, 36));

        cboQuyen.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboQuyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản Lý", "Nhân Viên" }));
        jPanel1.add(cboQuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 330, 250, 33));

        tblNhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Năm Sinh", "Địa Chỉ", "Số Điện Thoại", "Hình Ảnh", "Ngày Vào Làm", "Tài Khoản", "Mật Khẩu", "Quyền"
            }
        ));
        tblNhanvien.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tblNhanvien.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblNhanvien.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblNhanvien.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblNhanvien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblNhanvien.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblNhanvien.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblNhanvien.setRowHeight(22);
        tblNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanvien);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 1700, 520));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 100, 10, 280));

        btnSua.setBackground(new java.awt.Color(255, 153, 0));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 390, 115, 50));

        btnThem.setBackground(new java.awt.Color(255, 153, 0));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 390, 115, 50));

        btnXoa.setBackground(new java.awt.Color(255, 153, 0));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 390, 115, 50));

        btnLuu.setBackground(new java.awt.Color(255, 153, 0));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 390, 115, 50));

        DateNgayVaoLam.setDateFormatString("yyyy-MM-dd");
        DateNgayVaoLam.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(DateNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 170, 370, 30));
        jPanel1.add(lblNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 140, 210, 230));

        txtDiachi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtDiachi, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 370, 36));

        btnHuy.setBackground(new java.awt.Color(255, 153, 0));
        btnHuy.setText("Khởi Tạo");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 390, 120, 50));

        btnTaiAnh.setBackground(new java.awt.Color(255, 153, 0));
        btnTaiAnh.setText("Tải ảnh");
        btnTaiAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiAnhActionPerformed(evt);
            }
        });
        jPanel1.add(btnTaiAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 100, 70, 30));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTimkiem.setPlaceholder("Search...\n");
        jPanel1.add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 30, 300, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Tìm Kiếm:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 40, -1, -1));

        btnIntheNV.setBackground(new java.awt.Color(255, 153, 0));
        btnIntheNV.setText("In Thẻ Nhân Viên");
        btnIntheNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIntheNVActionPerformed(evt);
            }
        });
        jPanel1.add(btnIntheNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 390, 150, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1775, 1000));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static String tenFile;
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

    private void tblNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanvienMouseClicked
        // TODO add your handling code here:
        int i = tblNhanvien.getSelectedRow();
        hienthi(i);
    }//GEN-LAST:event_tblNhanvienMouseClicked
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String ngayvaolam = dateFormat.format(DateNgayVaoLam.getDate());
            Connection conn = dao.getConnect();
            String caulenh = "delete from tbl_nhanvien where MaNV=" + txtManv.getText();
            boolean a = dao.Lenh(caulenh, conn);
            if (a) {
                tblNhanvien.removeAll();
                tablemodel.setRowCount(0);
                loadnv();
                JOptionPane.showMessageDialog(this, "Xóa Thành Công");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa không thành công");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtDiachi.setText("");
        txtManv.setText("");
        txtMatkhau.setText("");
        txtSDT.setText("");
        txtTaikhoan.setText("");
        txtTenNV.setText("");
        txtnamsinh.setText("");
        tblNhanvien.removeAll();
        tablemodel.setRowCount(0);
        txtTimkiem.setText("");
        DateNgayVaoLam.setDate(null);
        lblNV.setIcon(null);
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

    private void btnTaiAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        loadanh(lblNV, f.toString());
        String fileName = f.getAbsolutePath();
        String newPath = "src/img/";
        File diretory = new File(newPath);
        if (!diretory.exists()) {
            diretory.mkdirs();
        }
        File sourceFile = null;
        File destinationFile = null;
        String extension = fileName.substring(fileName.lastIndexOf('\\') + 1);
        sourceFile = new File(fileName);
        destinationFile = new File(newPath + extension);
        tenFile = extension;
        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_btnTaiAnhActionPerformed

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
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String ngayvaolam = dateFormat.format(DateNgayVaoLam.getDate());
                    try {
                        Connection conn = dao.getConnect();
                        String caulenh = "INSERT INTO tbl_nhanvien VALUE(Null,'" + txtTenNV.getText() + "','" + cboGioitinh.getSelectedItem() + "'," + txtnamsinh.getText() + ",'" + txtDiachi.getText() + "','" + txtSDT.getText() + "','" + tenFile + "','" + ngayvaolam + "', '" + txtTaikhoan.getText() + "', '" + txtMatkhau.getText() + "','" + cboQuyen.getSelectedItem() + "')";
                        boolean a = dao.Lenh(caulenh, conn);
                        if (a) {
                            tblNhanvien.removeAll();
                            tablemodel.setRowCount(0);
                            loadnv();
                            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm không thành công");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(frmKL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sua) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    String ngayvaolam = dateFormat.format(DateNgayVaoLam.getDate());
                    try {
                        Connection conn = dao.getConnect();
                         String hinhanh = tenFile;
                        String caulenh;
                        if(hinhanh!=null && !hinhanh.equals("")){
                        caulenh = "update tbl_nhanvien set TenNV='" + txtTenNV.getText() + "',GioiTinh='" + cboGioitinh.getSelectedItem() + "',NamSinh=" + txtnamsinh.getText() + ",DiaChi='" + txtDiachi.getText() + "',SDT='" + txtSDT.getText() + "',HinhAnh='" + tenFile + "',NgayVaoLam='" + ngayvaolam + "', TaiKhoan='" + txtTaikhoan.getText() + "', MatKhau='" + txtMatkhau.getText() + "',Quyen='" + cboQuyen.getSelectedItem() + "' where MaNV=" + txtManv.getText();
                        }else caulenh = "update tbl_nhanvien set TenNV='" + txtTenNV.getText() + "',GioiTinh='" + cboGioitinh.getSelectedItem() + "',NamSinh=" + txtnamsinh.getText() + ",DiaChi='" + txtDiachi.getText() + "',SDT='" + txtSDT.getText() + "',NgayVaoLam='" + ngayvaolam + "', TaiKhoan='" + txtTaikhoan.getText() + "', MatKhau='" + txtMatkhau.getText() + "',Quyen='" + cboQuyen.getSelectedItem() + "' where MaNV=" + txtManv.getText();
                        boolean a = dao.Lenh(caulenh, conn);
                        if (a) {
                            tblNhanvien.removeAll();
                            tablemodel.setRowCount(0);
                            loadnv();
                            JOptionPane.showMessageDialog(this, "sửa Thành Công");
                        } else {
                            JOptionPane.showMessageDialog(this, "sửa không thành công");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void rSButtonMetro9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro9ActionPerformed
        // TODO add your handling code here:
        tblNhanvien.removeAll();
        tablemodel.setRowCount(0);
        String tk = txtTimkiem.getText();
        ArrayList<TblNhanvien> list = new ArrayList();
        list.clear();
        try {
            list = nvDL.timkiemNV(tk);
            tablemodel = (DefaultTableModel) tblNhanvien.getModel();
            for (TblNhanvien nv : list) {
                tablemodel.addRow(new Object[]{nv.getMaNV(), nv.getTenNV(), nv.getGioiTinh(), nv.getNamSinh(), nv.getDiaChi(), nv.getSdt(), nv.getHinhAnh(), nv.getNgayVaoLam(), nv.getTaiKhoan(), nv.getMatKhau(), nv.getQuyen()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }//GEN-LAST:event_rSButtonMetro9ActionPerformed
    private static JasperReport report;

    private static ByteArrayInputStream imageToInpuStream(ImageIcon img) throws Exception {
        BufferedImage bimage = new BufferedImage(img.getImage().getWidth(null), img.getImage().getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img.getImage(), 0, 0, null);
        bGr.dispose();
        BufferedImage bimg = bimage;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bimg, "jpg", baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }

    private static Collection getData() throws Exception {
        ArrayList<nhanvien> arr = new ArrayList<>();
        //     arr.add(new DataPrint("001", "Iamge 001", imageToInpuStream(new ImageIcon(PrintImage.class.getResource("printImages/i1.jpg")))));
        nhanvien fp = new nhanvien();

        arr.add(fp);
        return arr;
    }

    public static void print(long ma,String hoten, String gioiTinh, String namsinh) {
        try {
            ArrayList<nhanvien> li = new ArrayList<>();
            nhanvien rp = new nhanvien();
            rp.setGioiTinh(gioiTinh);
            rp.setMaNhanVien(ma);
            rp.setNamSinh(namsinh);
            rp.setTenNhanVien(hoten);
            rp.setImage((InputStream) (new FileInputStream("src/img/qr.png")));
            li.add(rp);
            JRBeanCollectionDataSource jcd = new JRBeanCollectionDataSource(li);
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
    private void btnIntheNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIntheNVActionPerformed
        // TODO add your handling code here:
        try {
            String QrCodeData = txtManv.getText();
            String filePath = "src/img/qr.png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(QrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200,hintMap );

            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
            System.out.println("Qr code has been generated at the location " + filePath);

            report = JasperCompileManager.compileReport("src/views/ReportNhanVien.jrxml");
            print(Long.parseLong(txtManv.getText()),txtTenNV.getText(),cboGioitinh.getSelectedItem().toString(),txtnamsinh.getText());
        } catch (Exception e) {
            System.out.println(e);

        }
    }//GEN-LAST:event_btnIntheNVActionPerformed
    void loadanh(JLabel lbl, String fileanh) {
        ImageIcon icon = new ImageIcon(fileanh);
        Image image = icon.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(image));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgayVaoLam;
    private rojerusan.RSButtonMetro btnHuy;
    private rojerusan.RSButtonMetro btnIntheNV;
    private rojerusan.RSButtonMetro btnLuu;
    private rojerusan.RSButtonMetro btnSua;
    private rojerusan.RSButtonMetro btnTaiAnh;
    private rojerusan.RSButtonMetro btnThem;
    private rojerusan.RSButtonMetro btnXoa;
    private javax.swing.JComboBox<String> cboGioitinh;
    private javax.swing.JComboBox<String> cboQuyen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNV;
    private rojerusan.RSButtonMetro rSButtonMetro9;
    private rojeru_san.complementos.RSTableMetro tblNhanvien;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtManv;
    private javax.swing.JTextField txtMatkhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTaikhoan;
    private javax.swing.JTextField txtTenNV;
    private app.bolivia.swing.JCTextField txtTimkiem;
    private javax.swing.JTextField txtnamsinh;
    // End of variables declaration//GEN-END:variables
}
