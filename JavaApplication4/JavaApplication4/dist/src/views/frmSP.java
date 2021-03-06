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
import controller.DanhmucXLDL;
import controller.SanPhamXLDL;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import model.TblSanpham;
import model.nhanvien;
import model.tbl_barcode;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import static views.frmNhanVien.print;
import static views.frmNhanVien.tenFile;

/**
 *
 * @author Thang
 */
public class frmSP extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmSP
     */
    DefaultTableModel tablemodel;
    SanPhamXLDL nvDL;
    DanhmucXLDL DMDL;
    UrlKN uk;
    DAO dao;

    public frmSP() {
        initComponents();
        nvDL = new SanPhamXLDL();
        DMDL = new DanhmucXLDL();
        uk = new UrlKN();
        dao = new DAO(uk.knURL());
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadnv();
        loadDM();
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
    }

    void loadnv() {

        ArrayList<TblSanpham> list = new ArrayList();
        list.clear();
        try {
            list = nvDL.getListSP();
            tablemodel = (DefaultTableModel) tblSanpham.getModel();
            for (TblSanpham nv : list) {
                tablemodel.addRow(new Object[]{nv.getMaSP(), nv.getTenSP(), nv.getTenDM(), nv.getSize(), nv.getMau(), nv.getGia(), nv.getHinhAnh(), nv.getSLTon()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }

    }

    void loadDM() {
        try {
            ArrayList<TblDanhmuc> DM = DMDL.getListDM();
            for (TblDanhmuc dm : DM) {
                cboMadanhmuc.addItem(dm);
            }
        } catch (SQLException ex) {

        }
    }

    private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();

        if (txtTensp.getText().equals("")) {
            sb.append("Vui l??ng nh???p t??n s???n ph???m\n");
            txtTensp.requestFocus();
        }
        if (txtGia.getText().equals("")) {
            sb.append("Vui l??ng nh???p gi??\n");
            txtGia.requestFocus();
        }

        if (txtSoluong.getText().equals("")) {
            sb.append("Vui l??ng nh???p s??? l?????ng t???n\n");
            txtSoluong.requestFocus();
        }
        if (tenFile == "") {
            sb.append("Vui l??ng ch???n file ???nh\n");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Th??ng b??o", JOptionPane.ERROR_MESSAGE);
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
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanpham = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        txtMasp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTensp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbltk = new rojerusan.RSButtonMetro();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboMadanhmuc = new javax.swing.JComboBox<>();
        txtGia = new javax.swing.JTextField();
        txtSoluong = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new rojerusan.RSButtonMetro();
        btnSua = new rojerusan.RSButtonMetro();
        btnXoa = new rojerusan.RSButtonMetro();
        btnLuu = new rojerusan.RSButtonMetro();
        lblanh = new javax.swing.JLabel();
        btnHuy = new rojerusan.RSButtonMetro();
        jLabel13 = new javax.swing.JLabel();
        txtTimkiem = new app.bolivia.swing.JCTextField();
        cboSize = new javax.swing.JComboBox<>();
        cboMau = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btnMV = new rojerusan.RSButtonMetro();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("S???n Ph???m");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, -1, -1));

        tblSanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? S???n Ph???m", "T??n S???n Ph???m", "Danh M???c", "Size", "M??u", "Gi??", "H??nh ???nh", "S??? L?????ng T???n"
            }
        ));
        tblSanpham.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tblSanpham.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblSanpham.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblSanpham.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblSanpham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblSanpham.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblSanpham.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblSanpham.setRowHeight(22);
        tblSanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanphamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanpham);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 1690, 580));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("M?? s???n ph???m:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, 29));

        txtMasp.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtMasp.setEnabled(false);
        jPanel1.add(txtMasp, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 290, 36));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("T??n s???n ph???m:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, 29));

        txtTensp.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtTensp, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, 290, 36));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Danh m???c:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, -1, 29));

        lbltk.setBackground(new java.awt.Color(255, 255, 255));
        lbltk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px_1.png"))); // NOI18N
        lbltk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbltkActionPerformed(evt);
            }
        });
        jPanel1.add(lbltk, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 30, 20, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Size:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, -1, 29));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("M??u:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, -1, 29));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("H??nh ???nh:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 70, -1, 29));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("S??? l?????ng t???n:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, -1, 29));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Gi??:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, -1, 29));

        cboMadanhmuc.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboMadanhmuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMadanhmucActionPerformed(evt);
            }
        });
        jPanel1.add(cboMadanhmuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 290, 33));

        txtGia.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 240, 270, 36));

        txtSoluong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtSoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoluongActionPerformed(evt);
            }
        });
        jPanel1.add(txtSoluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 290, 270, 36));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, -1, 190));

        btnThem.setBackground(new java.awt.Color(255, 153, 0));
        btnThem.setText("Th??m");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 130, 119, 45));

        btnSua.setBackground(new java.awt.Color(255, 153, 0));
        btnSua.setText("S???a");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 130, 119, 45));

        btnXoa.setBackground(new java.awt.Color(255, 153, 0));
        btnXoa.setText("X??a");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 200, 119, 45));

        btnLuu.setBackground(new java.awt.Color(255, 153, 0));
        btnLuu.setText("L??u");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 200, 119, 45));
        jPanel1.add(lblanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 100, 260, 270));

        btnHuy.setBackground(new java.awt.Color(255, 153, 0));
        btnHuy.setText("Kh???i T???o");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 270, 119, 45));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("T??m Ki???m:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 30, 230, -1));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTimkiem.setPlaceholder("Search...\n");
        jPanel1.add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 20, 350, 40));

        cboSize.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L", "XL", "XXL" }));
        jPanel1.add(cboSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 140, 270, 40));

        cboMau.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cboMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tr???ng", "??en", "?????", "Cam", "X??m", "Xanh L??", "Xanh D????ng", "T??m", "V??ng", "H???ng", "N??u" }));
        jPanel1.add(cboMau, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 190, 270, 40));

        jButton1.setText("T???i ???nh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 70, -1, -1));

        btnMV.setBackground(new java.awt.Color(255, 153, 0));
        btnMV.setText("In M?? V???ch");
        btnMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMVActionPerformed(evt);
            }
        });
        jPanel1.add(btnMV, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 270, 119, 45));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 0, 1770, 1000));

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

    private void txtSoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoluongActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSoluongActionPerformed
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
        int chon = JOptionPane.showConfirmDialog(this, "B???n C?? Mu???n X??a?", "xoa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_NO_OPTION) {
            TblDanhmuc dm = (TblDanhmuc) cboMadanhmuc.getSelectedItem();
            String MaDM = String.valueOf(dm.getMaDM());
            Connection conn = dao.getConnect();
            String caulenh = "delete from tbl_sanpham where MaSP=" + txtMasp.getText();
            boolean a = dao.Lenh(caulenh, conn);
            if (a) {
                tblSanpham.removeAll();
                tablemodel.setRowCount(0);
                loadnv();
                JOptionPane.showMessageDialog(this, "X??a Th??nh C??ng");
            } else {
                JOptionPane.showMessageDialog(this, "X??a kh??ng th??nh c??ng");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblSanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanphamMouseClicked
        // TODO add your handling code here:
        int i = tblSanpham.getSelectedRow();
        hienthi(i);
    }//GEN-LAST:event_tblSanphamMouseClicked

    private void cboMadanhmucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMadanhmucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMadanhmucActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtGia.setText("");
        txtMasp.setText("");
        txtSoluong.setText("");
        txtTensp.setText("");
        tblSanpham.removeAll();
        tablemodel.setRowCount(0);
        txtTimkiem.setText("");
        lblanh.setIcon(null);
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        loadanh(lblanh, f.toString());
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
    }//GEN-LAST:event_jButton1ActionPerformed

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
        int chon = JOptionPane.showConfirmDialog(this, "B???n C?? Mu???n L??u?", "L??u", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_NO_OPTION) {
            if (KTDL()) {
                if (them) {
                    TblDanhmuc dmm = (TblDanhmuc) cboMadanhmuc.getSelectedItem();
                    String MaDM = String.valueOf(dmm.getMaDM());
                    try {
                        Connection conn = dao.getConnect();
                        String caulenh = "INSERT INTO tbl_sanpham VALUE(Null,'" + txtTensp.getText() + "','" + MaDM + "','" + cboSize.getSelectedItem() + "','" + cboMau.getSelectedItem() + "','" + txtGia.getText() + "','" + tenFile + "','" + txtSoluong.getText() + "')";
                        boolean a = dao.Lenh(caulenh, conn);
                        if (a) {
                            tblSanpham.removeAll();
                            tablemodel.setRowCount(0);
                            loadnv();
                            JOptionPane.showMessageDialog(this, "Th??m Th??nh C??ng");
                        } else {
                            JOptionPane.showMessageDialog(this, "Th??m kh??ng th??nh c??ng");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(frmSP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (sua) {
                    TblDanhmuc dm = (TblDanhmuc) cboMadanhmuc.getSelectedItem();
                    String madanhmuc = String.valueOf(dm.getMaDM());
                    try {
                        
                        Connection conn = dao.getConnect();
                        String hinhanh = tenFile;
                        String caulenh;
                        if(hinhanh!=null && !hinhanh.equals("")){
                        caulenh = "update tbl_sanpham set TenSP='" + txtTensp.getText() + "',MaDM='" + madanhmuc + "', Size='" + cboSize.getSelectedItem() + "', Mau='" + cboMau.getSelectedItem() + "', Gia='" + txtGia.getText() + "',HinhAnh='" + tenFile + "', SLTon='" + txtSoluong.getText() + "' where MaSP=" + txtMasp.getText();
                        }else{
                        caulenh = "UPDATE tbl_sanpham SET TenSP='" + txtTensp.getText() + "',MaDM='" + madanhmuc + "', Size='" + cboSize.getSelectedItem() + "', Mau='" + cboMau.getSelectedItem() + "', Gia='" + txtGia.getText() + "', SLTon='" + txtSoluong.getText() + "' where MaSP=" + txtMasp.getText();
                        } 
                        boolean a = dao.Lenh(caulenh, conn);
                        if (a) {
                            tblSanpham.removeAll();
                            tablemodel.setRowCount(0);
                            loadnv();
                            JOptionPane.showMessageDialog(this, "s???a Th??nh C??ng");
                        } else {
                            JOptionPane.showMessageDialog(this, "s???a kh??ng th??nh c??ng");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(frmSP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void lbltkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbltkActionPerformed
        // TODO add your handling code here:
        tblSanpham.removeAll();
        tablemodel.setRowCount(0);
        String tk = txtTimkiem.getText();
        ArrayList<TblSanpham> list = new ArrayList();
        list.clear();
        try {
            list = nvDL.timkiemSP(tk);
            tablemodel = (DefaultTableModel) tblSanpham.getModel();
            for (TblSanpham nv : list) {
                tablemodel.addRow(new Object[]{nv.getMaSP(), nv.getTenSP(), nv.getTenDM(), nv.getSize(), nv.getMau(), nv.getGia(), nv.getHinhAnh(), nv.getSLTon()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }

    }//GEN-LAST:event_lbltkActionPerformed
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
        ArrayList<tbl_barcode> arr = new ArrayList<>();
        //     arr.add(new DataPrint("001", "Iamge 001", imageToInpuStream(new ImageIcon(PrintImage.class.getResource("printImages/i1.jpg")))));
        tbl_barcode fp = new tbl_barcode();

        arr.add(fp);
        return arr;
    }

    public static void print(float giaSP) {
        try {
            ArrayList<tbl_barcode> li = new ArrayList<>();
            tbl_barcode rp = new tbl_barcode();
            rp.setGiaSP(giaSP);
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
    private void btnMVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMVActionPerformed
        // TODO add your handling code here:
        try {
            String QrCodeData = txtMasp.getText();
            String filePath = "src/img/qr.png";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(QrCodeData.getBytes(charset), charset),
                    BarcodeFormat.CODE_128, 200, 200,hintMap );

            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
            System.out.println("Qr code has been generated at the location " + filePath);

            report = JasperCompileManager.compileReport("src/views/ReportSanPham.jrxml");
            print(Float.parseFloat(txtGia.getText()));
        } catch (Exception e) {
            System.out.println(e);

        }
    }//GEN-LAST:event_btnMVActionPerformed
    void loadanh(JLabel lbl, String fileanh) {
        ImageIcon icon = new ImageIcon(fileanh);
        Image image = icon.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(image));
    }

    void hienthi(int i) {
        TableModel model = tblSanpham.getModel();
        txtMasp.setText(model.getValueAt(i, 0).toString());
        txtTensp.setText(model.getValueAt(i, 1).toString());
        cboMau.setSelectedItem(model.getValueAt(i, 4).toString());
        String cbosub = model.getValueAt(i, 2).toString();
        for (int j = 0; j < cboMadanhmuc.getItemCount(); j++) {
            if (cboMadanhmuc.getItemAt(j).toString().equalsIgnoreCase(cbosub)) {
                cboMadanhmuc.setSelectedIndex(j);
            }
        }
        cboSize.setSelectedItem(model.getValueAt(i, 3).toString());
        txtGia.setText(model.getValueAt(i, 5).toString());
        String src = "src/img/" + model.getValueAt(i, 6).toString();
        loadanh(lblanh, src);
        txtSoluong.setText(model.getValueAt(i, 7).toString());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonMetro btnHuy;
    private rojerusan.RSButtonMetro btnLuu;
    private rojerusan.RSButtonMetro btnMV;
    private rojerusan.RSButtonMetro btnSua;
    private rojerusan.RSButtonMetro btnThem;
    private rojerusan.RSButtonMetro btnXoa;
    private javax.swing.JComboBox<TblDanhmuc> cboMadanhmuc;
    private javax.swing.JComboBox<String> cboMau;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel lblanh;
    private rojerusan.RSButtonMetro lbltk;
    private rojeru_san.complementos.RSTableMetro tblSanpham;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTensp;
    private app.bolivia.swing.JCTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
