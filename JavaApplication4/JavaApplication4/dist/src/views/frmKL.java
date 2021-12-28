/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controller.DAO;
import controller.KyluongXLDL;
import controller.UrlKN;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.TblKyluong;

/**
 *
 * @author Thang
 */
public class frmKL extends javax.swing.JFrame {

    /**
     * Creates new form frmKL
     */
    DefaultTableModel tablemodel;
    KyluongXLDL nvDL;
    UrlKN uk;
    DAO dao;

    public frmKL() {
        initComponents();
        nvDL = new KyluongXLDL();
        uk = new UrlKN();
        dao = new DAO(uk.knURL());
        loadnv();
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
    }

    void loadnv() {

        ArrayList<TblKyluong> list = new ArrayList();
        list.clear();
        try {
            list = nvDL.getListKL();
            tablemodel = (DefaultTableModel) tblKyluong.getModel();
            for (TblKyluong nv : list) {
                tablemodel.addRow(new Object[]{nv.getMaKL(), nv.getTenKL(), nv.getLuong()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }

    }

    void hienthi(int i) {
        TableModel model = tblKyluong.getModel();
        txtMaKL.setText(model.getValueAt(i, 0).toString());
        txtTenKL.setText(model.getValueAt(i, 1).toString());
        txtLuong.setText(model.getValueAt(i, 2).toString());
    }

    private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();

        if (txtTenKL.getText().equals("")) {
            sb.append("Vui lòng nhập tên kỳ lương\n");
            txtTenKL.requestFocus();
        }
        if (txtLuong.getText().equals("")) {
            sb.append("Vui lòng nhập lương\n");
            txtLuong.requestFocus();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKyluong = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        txtMaKL = new javax.swing.JTextField();
        lbltikmkiem = new rojerusan.RSButtonMetro();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenKL = new javax.swing.JTextField();
        txtLuong = new javax.swing.JTextField();
        btnThem = new rojerusan.RSButtonMetro();
        btnXoa = new rojerusan.RSButtonMetro();
        btnLuu = new rojerusan.RSButtonMetro();
        btnSua = new rojerusan.RSButtonMetro();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        rSButtonMetro2 = new rojerusan.RSButtonMetro();
        btnHuy = new rojerusan.RSButtonMetro();
        txtTimkiem = new app.bolivia.swing.JCTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1900, 1070));
        jPanel1.setPreferredSize(new java.awt.Dimension(1900, 1070));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Kỳ Lương");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 70, -1, -1));

        tblKyluong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Kỳ Lương", "Tên Kỳ Lương", "Lương"
            }
        ));
        tblKyluong.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tblKyluong.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblKyluong.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblKyluong.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblKyluong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblKyluong.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblKyluong.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblKyluong.setRowHeight(22);
        tblKyluong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKyluongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKyluong);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 1870, 770));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Mã kỳ lương:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, 29));

        txtMaKL.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtMaKL.setEnabled(false);
        jPanel1.add(txtMaKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 380, 36));

        lbltikmkiem.setBackground(new java.awt.Color(255, 255, 255));
        lbltikmkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px_1.png"))); // NOI18N
        lbltikmkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbltikmkiemActionPerformed(evt);
            }
        });
        jPanel1.add(lbltikmkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1730, 50, 20, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Lương:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 140, -1, 29));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Tên kỳ lương:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, -1, 29));

        txtTenKL.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtTenKL, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, 330, 36));

        txtLuong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 140, 330, 36));

        btnThem.setBackground(new java.awt.Color(255, 153, 0));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 210, 119, 45));

        btnXoa.setBackground(new java.awt.Color(255, 153, 0));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 210, 119, 45));

        btnLuu.setBackground(new java.awt.Color(255, 153, 0));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 210, 119, 45));

        btnSua.setBackground(new java.awt.Color(255, 153, 0));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 210, 119, 45));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 140, -1, -1));

        rSButtonMetro2.setBackground(new java.awt.Color(255, 153, 0));
        rSButtonMetro2.setIcon(new javax.swing.ImageIcon("E:\\doan2\\frmHome\\icon\\icons8_back_to_30px.png")); // NOI18N
        rSButtonMetro2.setText("Trở lại");
        rSButtonMetro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonMetro2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 109, 43));

        btnHuy.setBackground(new java.awt.Color(255, 153, 0));
        btnHuy.setText("Khởi Tạo");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 210, 119, 45));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTimkiem.setPlaceholder("Search...\n");
        jPanel1.add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 40, 310, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Tìm Kiếm:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 50, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1940, 1140));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonMetro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_rSButtonMetro2ActionPerformed

    private void tblKyluongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKyluongMouseClicked
        // TODO add your handling code here:
        int i = tblKyluong.getSelectedRow();
        hienthi(i);
    }//GEN-LAST:event_tblKyluongMouseClicked

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
            Connection conn = dao.getConnect();
            String caulenh = "delete from tbl_kyluong where MaKL=" + txtMaKL.getText();
            boolean a = dao.Lenh(caulenh, conn);
            if (a) {
                tblKyluong.removeAll();
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
        txtLuong.setText("");
        txtMaKL.setText("");
        txtTenKL.setText("");
        tblKyluong.removeAll();
        tablemodel.setRowCount(0);
        txtTimkiem.setText("");
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
                    try {
                        Connection conn = dao.getConnect();
                        String caulenh = "INSERT INTO tbl_kyluong VALUE(Null,'" + txtTenKL.getText() + "','" + txtLuong.getText() + "')";
                        boolean a = dao.Lenh(caulenh, conn);
                        if (a) {
                            tblKyluong.removeAll();
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
                    try {
                        Connection conn = dao.getConnect();
                        String caulenh = "update tbl_kyluong set TenKL='" + txtTenKL.getText() + "',Luong ='" + txtLuong.getText() + "' where MaKL=" + txtMaKL.getText();
                        boolean a = dao.Lenh(caulenh, conn);
                        if (a) {
                            tblKyluong.removeAll();
                            tablemodel.setRowCount(0);
                            loadnv();
                            JOptionPane.showMessageDialog(this, "sửa Thành Công");
                        } else {
                            JOptionPane.showMessageDialog(this, "sửa không thành công");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(frmKL.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }//GEN-LAST:event_btnLuuActionPerformed
    }
    private void lbltikmkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbltikmkiemActionPerformed
        // TODO add your handling code here:
        tblKyluong.removeAll();
        tablemodel.setRowCount(0);
        String tk = txtTimkiem.getText();
        ArrayList<TblKyluong> list = new ArrayList();
        list.clear();
        try {
            list = nvDL.timkiemKL(tk);
            tablemodel = (DefaultTableModel) tblKyluong.getModel();
            for (TblKyluong nv : list) {
                tablemodel.addRow(new Object[]{nv.getMaKL(), nv.getTenKL(), nv.getLuong()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }//GEN-LAST:event_lbltikmkiemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmKL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmKL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmKL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmKL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmKL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonMetro btnHuy;
    private rojerusan.RSButtonMetro btnLuu;
    private rojerusan.RSButtonMetro btnSua;
    private rojerusan.RSButtonMetro btnThem;
    private rojerusan.RSButtonMetro btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSButtonMetro lbltikmkiem;
    private rojerusan.RSButtonMetro rSButtonMetro2;
    private rojeru_san.complementos.RSTableMetro tblKyluong;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaKL;
    private javax.swing.JTextField txtTenKL;
    private app.bolivia.swing.JCTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}