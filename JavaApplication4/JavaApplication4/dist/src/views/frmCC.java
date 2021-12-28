package views;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import controller.ChamcongXLDL;
import controller.DAO;
import controller.NhanvienXLDL;
import controller.UrlKN;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.TblChamcong;
import model.TblNhanvien;

/**
 *
 * @author Thang
 */
public class frmCC extends javax.swing.JInternalFrame implements Runnable, ThreadFactory {

    /**
     * Creates new form frmCC
     */
    DefaultTableModel tablemodel;
    ChamcongXLDL CCDL;
    UrlKN uk;
    DAO dao;
    NhanvienXLDL NDL;
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public frmCC() {
        initComponents();
        CCDL = new ChamcongXLDL();
        uk = new UrlKN();
        dao = new DAO(uk.knURL());
        NDL = new NhanvienXLDL();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        Date dt = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String ngay = dateFormat.format(dt);
        loadnv(ngay);
        initWebcam();
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);

    }
static Date today = new Date();
    void loadnv(String ngay) {

        ArrayList<TblChamcong> list = new ArrayList();
        list.clear();
        try {
            
            list = CCDL.getListCC(ngay);
            tablemodel = (DefaultTableModel) tblChamcong.getModel();
            for (TblChamcong CC : list) {
                tablemodel.addRow(new Object[]{CC.getMaChamCong(), CC.getTenNV(), CC.getNgay(), CC.getGioVao(), CC.getGioRa(), CC.getGhiChu()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 570));

        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }

            if (result != null) {
                txtMaNV.setText(result.getText());
                try {
                    TblNhanvien dv = NDL.getmaNV(txtMaNV.getText());
                    txtTenNV.setText(dv.getTenNV());
                } catch (SQLException ex) {
                    Logger.getLogger(frmCC.class.getName()).log(Level.SEVERE, null, ex);
                }
                 if (webcam.isOpen()) {
            webcam.close();
        }
            }
        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    void hienthi(int i) {
        TableModel model = tblChamcong.getModel();
        txtMachamcong.setText(model.getValueAt(i, 0).toString());
        txtTenNV.setText(model.getValueAt(i, 1).toString());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(i, 2).toString());
        } catch (ParseException ex) {
            Logger.getLogger(frmCC.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateNgay.setDate(date);
        txtGiovao.setText(model.getValueAt(i, 3).toString());
        txtGiora.setText(model.getValueAt(i, 4).toString());
        txtGhichu.setText(model.getValueAt(i, 5).toString());
    }

    private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();

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
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtGhichu = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtGiovao = new javax.swing.JTextField();
        txtGiora = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChamcong = new rojeru_san.complementos.RSTableMetro();
        DateNgay = new com.toedter.calendar.JDateChooser();
        btnThem = new rojerusan.RSButtonMetro();
        rSButtonMetro9 = new rojerusan.RSButtonMetro();
        btnLuu = new rojerusan.RSButtonMetro();
        txtMachamcong = new javax.swing.JTextField();
        btnHuy = new rojerusan.RSButtonMetro();
        jLabel13 = new javax.swing.JLabel();
        txtTimkiem = new app.bolivia.swing.JCTextField();
        btnOFF = new rojerusan.RSButtonMetro();
        btnQuet = new rojerusan.RSButtonMetro();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnSua = new rojerusan.RSButtonMetro();
        btnCenter = new rojerusan.RSButtonMetro();
        btnBack = new rojerusan.RSButtonMetro();
        btnNext = new rojerusan.RSButtonMetro();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Chấm Công");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Tên Nhân viên:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, -1, 29));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Mã chấm công:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, -1, 29));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Ghi chú:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 260, -1, 29));

        txtGhichu.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtGhichu, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 260, 360, 80));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 360, 840, 530));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Giờ vào:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 120, -1, 29));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Ngày:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 210, -1, 32));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Giờ ra:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 170, -1, 29));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 110, -1, 220));

        txtGiovao.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtGiovao.setEnabled(false);
        jPanel1.add(txtGiovao, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 110, 360, 40));

        txtGiora.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtGiora.setEnabled(false);
        txtGiora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioraActionPerformed(evt);
            }
        });
        jPanel1.add(txtGiora, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 160, 360, 40));

        tblChamcong.setBackground(new java.awt.Color(255, 153, 0));
        tblChamcong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Chấm Công", "Nhân Viên", "Ngày", "Giờ Vào", "Giờ Ra", "Ghi Chú"
            }
        ));
        tblChamcong.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tblChamcong.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblChamcong.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblChamcong.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblChamcong.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblChamcong.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblChamcong.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblChamcong.setRowHeight(22);
        tblChamcong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChamcongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChamcong);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 357, 820, 610));

        DateNgay.setDateFormatString("yyyy-MM-dd");
        DateNgay.setEnabled(false);
        DateNgay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(DateNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, 360, 40));

        btnThem.setBackground(new java.awt.Color(255, 153, 0));
        btnThem.setText("Điểm Danh Vào");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 170, 119, 60));

        rSButtonMetro9.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonMetro9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px_1.png"))); // NOI18N
        rSButtonMetro9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro9ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonMetro9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1640, 40, 20, 20));

        btnLuu.setBackground(new java.awt.Color(255, 153, 0));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1549, 255, 120, 60));

        txtMachamcong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtMachamcong.setEnabled(false);
        jPanel1.add(txtMachamcong, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 360, 40));

        btnHuy.setBackground(new java.awt.Color(255, 153, 0));
        btnHuy.setText("Khởi Tạo");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 255, 119, 60));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Tìm Kiếm:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 40, -1, -1));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTimkiem.setPlaceholder("Search...\n");
        jPanel1.add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 30, 300, 40));

        btnOFF.setBackground(new java.awt.Color(255, 153, 0));
        btnOFF.setText("Tắt Quét");
        btnOFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOFFActionPerformed(evt);
            }
        });
        jPanel1.add(btnOFF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 900, 130, 60));

        btnQuet.setBackground(new java.awt.Color(255, 153, 0));
        btnQuet.setText("Quét");
        btnQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetActionPerformed(evt);
            }
        });
        jPanel1.add(btnQuet, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 900, 130, 60));

        txtMaNV.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtMaNV.setEnabled(false);
        jPanel1.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 176, 360, 40));

        txtTenNV.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(txtTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 236, 360, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Mã Nhân viên:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, 29));

        btnSua.setBackground(new java.awt.Color(255, 153, 0));
        btnSua.setText("Điểm Danh Ra");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1550, 170, 119, 60));

        btnCenter.setBackground(new java.awt.Color(255, 153, 0));
        btnCenter.setText("||");
        btnCenter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCenterActionPerformed(evt);
            }
        });
        jPanel1.add(btnCenter, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 30, 40));

        btnBack.setBackground(new java.awt.Color(255, 153, 0));
        btnBack.setText("<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 30, 40));

        btnNext.setBackground(new java.awt.Color(255, 153, 0));
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 30, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1760, 1000));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        them = true;
        sua = false;
        Color mauxanh = new Color(255, 153, 0);
        btnThem.setBackground(Color.GRAY);
        btnSua.setEnabled(false);
        btnSua.setBackground(Color.GRAY);
        btnLuu.setEnabled(true);
        btnLuu.setBackground(mauxanh);


    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtGhichu.setText("");
        txtGiora.setText("");
        txtGiovao.setText("");
        txtTimkiem.setText("");
        txtMachamcong.setText("");
        tblChamcong.removeAll();
        tablemodel.setRowCount(0);
        DateNgay.setDate(null);
        Color mauxanh = new Color(255, 153, 0);
        btnThem.setEnabled(true);
        btnThem.setBackground(mauxanh);
        btnSua.setEnabled(true);
        btnSua.setBackground(mauxanh);
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
        loadnv(title);

    }//GEN-LAST:event_btnHuyActionPerformed
    public boolean them, sua;
    private void tblChamcongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChamcongMouseClicked
        // TODO add your handling code here:
        int i = tblChamcong.getSelectedRow();
        hienthi(i);
    }//GEN-LAST:event_tblChamcongMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        Color mauxanh = new Color(255, 153, 0);
        btnThem.setBackground(mauxanh);
        btnThem.setEnabled(true);
        btnSua.setEnabled(true);
        btnSua.setBackground(mauxanh);
        btnLuu.setEnabled(false);
        btnLuu.setBackground(Color.GRAY);
        int chon = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Lưu?", "Lưu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_NO_OPTION) {

            if (them) {
                Date dt = new Date();
                SimpleDateFormat timed = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String time = timed.format(dt.getTime());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngay = dateFormat.format(dt);
                try {
                    Connection conn = dao.getConnect();
                    String caulenh = "INSERT INTO  tbl_chamcong VALUE (Null,'" + txtMaNV.getText() + "','" + ngay + "','" + time + "','" + "00:00:00" + "','" + txtGhichu.getText() + "')";
                    boolean a = dao.Lenh(caulenh, conn);

                    if (a) {
                        tblChamcong.removeAll();
                        tablemodel.setRowCount(0);
                      
                        loadnv(ngay);
                        JOptionPane.showMessageDialog(this, "Điểm danh VÀO thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Điểm danh VÀO không thành công");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(frmCC.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (sua) {
                Date dt = new Date();
                SimpleDateFormat timed = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String time = timed.format(dt.getTime());
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngay = dateFormat.format(dt);
                try {
                    Connection conn = dao.getConnect();
                    String caulenh = "update tbl_chamcong set MaNV='" + txtMaNV.getText() + "',Ngay='" + ngay + "', GioRa='" + time + "', GhiChu='" + txtGhichu.getText() + "' where MaChamCong=" + txtMachamcong.getText();
                    boolean a = dao.Lenh(caulenh, conn);

                    if (a) {
                        tblChamcong.removeAll();
                        tablemodel.setRowCount(0);
                        loadnv(ngay);

                        JOptionPane.showMessageDialog(this, "Điểm danh RA thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Điểm danh RA không thành công");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(frmCC.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

    }//GEN-LAST:event_btnLuuActionPerformed

    }
    private void rSButtonMetro9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro9ActionPerformed
        // TODO add your handling code here:
        tblChamcong.removeAll();
        tablemodel.setRowCount(0);
        String tk = txtTimkiem.getText();
        ArrayList<TblChamcong> list = new ArrayList();
        list.clear();
        try {
            list = CCDL.timkiemCC(tk);
            tablemodel = (DefaultTableModel) tblChamcong.getModel();
            for (TblChamcong CC : list) {
                tablemodel.addRow(new Object[]{CC.getMaChamCong(), CC.getTenNV(), CC.getNgay(), CC.getGioVao(), CC.getGioRa(), CC.getGhiChu()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }//GEN-LAST:event_rSButtonMetro9ActionPerformed

    private void txtGioraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioraActionPerformed

    private void btnOFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOFFActionPerformed
        // TODO add your handling code here:
        if (webcam.isOpen()) {
            webcam.close();
        }
    }//GEN-LAST:event_btnOFFActionPerformed

    private void btnQuetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetActionPerformed
        // TODO add your handling code here:
        initWebcam();
    }//GEN-LAST:event_btnQuetActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        them = false;
        sua = true;
        Color mauxanh = new Color(255, 153, 0);
        btnSua.setBackground(Color.GRAY);
        btnThem.setEnabled(false);
        btnThem.setBackground(Color.GRAY);
        btnLuu.setEnabled(true);
        btnLuu.setBackground(mauxanh);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date tomorrow = new Date(today.getTime() - (1000 * 60 * 60 * 24));
        today = tomorrow;
        String ngay = dateFormat.format(tomorrow);
        try {
            tblChamcong.removeAll();
            tablemodel.setRowCount(0);
         DateNgay.setDate(today);
            loadnv(ngay);
        } catch (Exception e) {
          
            loadnv(ngay);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCenterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCenterActionPerformed
        // TODO add your handling code here:
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
       try {
            tblChamcong.removeAll();
            tablemodel.setRowCount(0);
            today = new Date();
             DateNgay.setDate(today);
            String ngay = dateFormat.format(today);
            loadnv(ngay);
        } catch (Exception e) {
            today = new Date();
            String ngay = dateFormat.format(today);
            loadnv(ngay);
        }
    }//GEN-LAST:event_btnCenterActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        today = tomorrow;
        String ngay = dateFormat.format(tomorrow);
          try {
            tblChamcong.removeAll();
            tablemodel.setRowCount(0);
             DateNgay.setDate(today);
            loadnv(ngay);
        } catch (Exception e) {
          
            loadnv(ngay);
        }
    }//GEN-LAST:event_btnNextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DateNgay;
    private rojerusan.RSButtonMetro btnBack;
    private rojerusan.RSButtonMetro btnCenter;
    private rojerusan.RSButtonMetro btnHuy;
    private rojerusan.RSButtonMetro btnLuu;
    private rojerusan.RSButtonMetro btnNext;
    private rojerusan.RSButtonMetro btnOFF;
    private rojerusan.RSButtonMetro btnQuet;
    private rojerusan.RSButtonMetro btnSua;
    private rojerusan.RSButtonMetro btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSButtonMetro rSButtonMetro9;
    private rojeru_san.complementos.RSTableMetro tblChamcong;
    private javax.swing.JTextField txtGhichu;
    private javax.swing.JTextField txtGiora;
    private javax.swing.JTextField txtGiovao;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMachamcong;
    private javax.swing.JTextField txtTenNV;
    private app.bolivia.swing.JCTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
