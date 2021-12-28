/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;
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
import controller.CTHDBanHangXLDL;
import controller.DAO;
import controller.HDBanHangXLDL;
import controller.InHoaDonBHXLDL;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Chitiethdbh;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import controller.KNCSDL;
import controller.SanPhamXLDL;
import controller.UrlKN;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import model.TblHoadonbanhang;
import model.TblSanpham;
import model.inHoaDonBH;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import static org.eclipse.persistence.config.QueryType.Report;

/**
 *
 * @author Thang
 */
public class frmChitietbanhang extends javax.swing.JFrame implements Runnable, ThreadFactory {

    /**
     * Creates new form frmChitietbanhang
     */
    CTHDBanHangXLDL ctbhDL;
    DefaultTableModel tablemodel;
    UrlKN uk;
    DAO dao;
    SanPhamXLDL SPDL;
    HDBanHangXLDL BHDL;
    static String maHDBH;
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    static String MaHD;
    private static JasperReport report;
    InHoaDonBHXLDL inhd;

    public frmChitietbanhang(String id) {
        initComponents();
        uk = new UrlKN();
        maHDBH = id;
        dao = new DAO(uk.knURL());
        SPDL = new SanPhamXLDL();
        BHDL = new HDBanHangXLDL();
        kn = new KNCSDL();
        inhd = new InHoaDonBHXLDL();
        ctbhDL = new CTHDBanHangXLDL();
        this.setLocationRelativeTo(null);
        loadnv();
        MaHD = id;
        initWebcam();    
        txtMaHDBH.setText("" + maHDBH);
    }

    void tongtien() {
        int soluong = Integer.parseInt(txtSoluong.getText());
        float donGia = Float.parseFloat(lblDG.getText());
        float tongTien = soluong * donGia;
        txtTongtien.setText("" + tongTien);
    }

    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        pnl1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 620));
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
                txtSP.setText(result.getText());

                try {
                    TblSanpham dv = SPDL.getmaSP(txtSP.getText());
                    lblDG.setText(dv.getGia() + "");
                    txtTenSP.setText(dv.getTenSP());
                } catch (SQLException ex) {
                    Logger.getLogger(frmChitietbanhang.class.getName()).log(Level.SEVERE, null, ex);
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
        TableModel model = tblchitiethdbanhang.getModel();
        txtMaHDBH.setText(model.getValueAt(i, 0).toString());
        txtSP.setText(model.getValueAt(i, 1).toString());
        txtTenSP.setText(model.getValueAt(i, 2).toString());
        txtSoluong.setText(model.getValueAt(i, 3).toString());
        txtTongtien.setText(model.getValueAt(i, 4).toString());
    }

    void loadnv() {

        ArrayList<Chitiethdbh> list = new ArrayList();
        list.clear();
        try {
            list = ctbhDL.getListCTBH(maHDBH);
            tablemodel = (DefaultTableModel) tblchitiethdbanhang.getModel();
            int  tongtien = 0;
            for (Chitiethdbh ct : list) {
                tablemodel.addRow(new Object[]{ct.getMaHDBH(), ct.getMaSP(), ct.getTenSP(),  ct.getSoLuong(), ct.getTongTien()});
                tongtien += ct.getTongTien();
            }
            lblTongTien.setText(tongtien + " VND");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
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

        jLabel13 = new javax.swing.JLabel();
        lbltikmkiem = new rojerusan.RSButtonMetro();
        txtTimkiem = new app.bolivia.swing.JCTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        txtTongtien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblchitiethdbanhang = new rojeru_san.complementos.RSTableMetro();
        jPanel2 = new javax.swing.JPanel();
        btnThoat = new rojerusan.RSButtonMetro();
        btnThem = new rojerusan.RSButtonMetro();
        pnl1 = new javax.swing.JPanel();
        btnHuy = new rojerusan.RSButtonMetro();
        btnin = new rojerusan.RSButtonMetro();
        txtMaHDBH = new javax.swing.JTextField();
        btnQuet = new rojerusan.RSButtonMetro();
        btnOFF = new rojerusan.RSButtonMetro();
        lblDG = new javax.swing.JLabel();
        txtSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnHuy1 = new rojerusan.RSButtonMetro();
        jLabel6 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Tìm Kiếm:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1470, 60, -1, -1));

        lbltikmkiem.setBackground(new java.awt.Color(255, 255, 255));
        lbltikmkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_24px_1.png"))); // NOI18N
        lbltikmkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbltikmkiemActionPerformed(evt);
            }
        });
        getContentPane().add(lbltikmkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1850, 60, 20, 20));

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTimkiem.setPlaceholder("Search...\n");
        getContentPane().add(txtTimkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 50, 310, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Chi Tiết Hóa Đơn Bán Hàng");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 501, -1));

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

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Mã hóa đơn:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, 29));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Mã Sản phẩm:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, 29));
        jLabel3.getAccessibleContext().setAccessibleName("MASản phẩm:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Số lượng:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, -1, 29));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Tổng tiền:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 240, -1, 29));

        txtSoluong.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtSoluong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoluongActionPerformed(evt);
            }
        });
        jPanel1.add(txtSoluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 180, 340, 40));

        txtTongtien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTongtien.setEnabled(false);
        jPanel1.add(txtTongtien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 232, 340, 40));

        tblchitiethdbanhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Mã Sản Phẩm", "Sản Phẩm", "Số Lượng", "Tổng Tiền"
            }
        ));
        tblchitiethdbanhang.setColorBackgoundHead(new java.awt.Color(255, 153, 0));
        tblchitiethdbanhang.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblchitiethdbanhang.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tblchitiethdbanhang.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tblchitiethdbanhang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblchitiethdbanhang.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tblchitiethdbanhang.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tblchitiethdbanhang.setRowHeight(22);
        tblchitiethdbanhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblchitiethdbanhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblchitiethdbanhang);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 360, 980, 660));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, -1, 170));

        btnThoat.setBackground(new java.awt.Color(255, 153, 0));
        btnThoat.setIcon(new javax.swing.ImageIcon("E:\\doan2\\frmHome\\icon\\icons8_back_to_30px.png")); // NOI18N
        btnThoat.setText("Trở lại");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });
        jPanel1.add(btnThoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 100, 44));

        btnThem.setBackground(new java.awt.Color(255, 153, 0));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 170, 119, 45));

        pnl1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(pnl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 880, 560));

        btnHuy.setBackground(new java.awt.Color(255, 153, 0));
        btnHuy.setText("Khởi Tạo");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 240, 119, 45));

        btnin.setBackground(new java.awt.Color(255, 153, 0));
        btnin.setText("In Hóa Đơn");
        btnin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninActionPerformed(evt);
            }
        });
        jPanel1.add(btnin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 240, 119, 45));

        txtMaHDBH.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtMaHDBH.setEnabled(false);
        jPanel1.add(txtMaHDBH, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 172, 360, 40));

        btnQuet.setBackground(new java.awt.Color(255, 153, 0));
        btnQuet.setText("Quét");
        btnQuet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetActionPerformed(evt);
            }
        });
        jPanel1.add(btnQuet, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 930, 90, 40));

        btnOFF.setBackground(new java.awt.Color(255, 153, 0));
        btnOFF.setText("Tắt Quét");
        btnOFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOFFActionPerformed(evt);
            }
        });
        jPanel1.add(btnOFF, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 930, 90, 40));

        lblDG.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(lblDG, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, 210, 40));

        txtSP.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtSP.setEnabled(false);
        txtSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSPActionPerformed(evt);
            }
        });
        jPanel1.add(txtSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 232, 360, 40));

        txtTenSP.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txtTenSP.setEnabled(false);
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });
        jPanel1.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 240, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Tên Sản phẩm:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, 29));

        btnHuy1.setBackground(new java.awt.Color(255, 153, 0));
        btnHuy1.setText("Xóa");
        btnHuy1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuy1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnHuy1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 170, 119, 45));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Tổng Tiền: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 990, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 990, 220, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2340, 1230));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        if (webcam.isOpen()) {
            webcam.close();
        }
    }//GEN-LAST:event_btnThoatActionPerformed
private boolean KTDL() throws HeadlessException {
        StringBuilder sb = new StringBuilder();

        if (txtSoluong.getText().equals("")) {
            sb.append("Vui lòng nhập số lượng\n");
            txtSoluong.requestFocus();
        }
        if (txtTongtien.getText().equals("")) {
            sb.append("Vui lòng nhập tổng tiền\n");
            txtTongtien.requestFocus();
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString(), "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
       tongtien();
         if (KTDL()) {    
                    Integer mahdbh = Integer.parseInt(txtMaHDBH.getText());
                    try {
                        Connection conn = dao.getConnect();
                        String caulenh = "INSERT INTO chitiethdbh VALUE('" + mahdbh + "','" + txtSP.getText() + "','" + txtSoluong.getText() + "','" + txtTongtien.getText() + "')";
                        boolean a = dao.Lenh(caulenh, conn);
                        String capnhat = "UPDATE tbl_sanpham SET SLTon = SLTon- " + Integer.parseInt(txtSoluong.getText().toString()) + " WHERE MaSP=" + txtSP.getText().toString();
                        boolean b = dao.Lenh(capnhat, conn);
                       String capnhat1 = "UPDATE tbl_hoadonbanhang SET  TongTien = TongTien+ " + Float.parseFloat(txtTongtien.getText().toString()) + " WHERE MaHDBH="+ txtMaHDBH.getText().toString();
                        boolean c = dao.Lenh(capnhat1, conn);
                        if (a) {
                            tblchitiethdbanhang.removeAll();
                            tablemodel.setRowCount(0); 
                            loadnv();
                            JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm không thành công");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(frmChitietbanhang.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                     txtTongtien.setText("");
                     txtSP.setText("");
                     txtSoluong.setText("");
                     txtTenSP.setText("");
                     lblDG.setText("");
                }
         
    }//GEN-LAST:event_btnThemActionPerformed
    
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtMaHDBH.setText("");
        txtSoluong.setText("");
        txtTongtien.setText("");
        txtTimkiem.setText("");
        tblchitiethdbanhang.removeAll();
        tablemodel.setRowCount(0);
        Color mauxanh = new Color(255, 153, 0);
        btnThem.setEnabled(true);
        btnThem.setBackground(mauxanh);
        loadnv();
    }//GEN-LAST:event_btnHuyActionPerformed
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

    private static Collection getData(String ma) throws Exception {
        KNCSDL kn = new KNCSDL();
        ArrayList<inHoaDonBH> list = new ArrayList<>();
        Connection conn = kn.getConnect();
        String sql = "select hdbh.MaHDBH, hdbh.NgayLap, kh.TenKH, nv.TenNV, sp.TenSP, cthdbh.SoLuong, sp.Gia, cthdbh.TongTien, hdbh.TongTien FROM tbl_hoadonbanhang hdbh, tbl_khachhang kh, tbl_nhanvien nv, tbl_sanpham sp, chitiethdbh cthdbh WHERE (hdbh.MaHDBH = cthdbh.MaHDBH and kh.MaKH = hdbh.MaKH and nv.MaNV = hdbh.MaNV and sp.MaSP = cthdbh.MaSP and cthdbh.MaHDBH=" + ma + ")";
        Statement statement = (Statement) conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            inHoaDonBH nv = new inHoaDonBH();
            nv.setMaHD(result.getString(1));
            nv.setNgayLap(result.getString(2));
            nv.setMaKH(result.getString(3));
            nv.setMaNV(result.getString(4));
            nv.setTenSP(result.getString(5));
            nv.setSoLuong(result.getInt(6));
            nv.setDonGia(result.getFloat(7));
            nv.setTongtienSP(result.getFloat(8));
            nv.setTongTienHD(result.getFloat(9));
            list.add(nv);
        }
        return list;
    }

    KNCSDL kn;
    private void btninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninActionPerformed
        // TODO add your handling code here:
     try {
           report = JasperCompileManager.compileReport("src/Views/ReportHoaDonBanHang.jrxml");
          print(txtMaHDBH.getText().toString());
      } catch (Exception e) {
          System.out.println(e);
       }
    }//GEN-LAST:event_btninActionPerformed

    private void tblchitiethdbanhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblchitiethdbanhangMouseClicked
        // TODO add your handling code here:
        int i = tblchitiethdbanhang.getSelectedRow();
        hienthi(i);
    }//GEN-LAST:event_tblchitiethdbanhangMouseClicked
    public boolean them, sua;    
    private void lbltikmkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbltikmkiemActionPerformed
        // TODO add your handling code here:
        tblchitiethdbanhang.removeAll();
        tablemodel.setRowCount(0);
        String tk = txtTimkiem.getText();
        ArrayList<Chitiethdbh> list = new ArrayList();
        list.clear();
        try {
            list = ctbhDL.timkiemCTBH(tk);
            tablemodel = (DefaultTableModel) tblchitiethdbanhang.getModel();
            for (Chitiethdbh ct : list) {
                tablemodel.addRow(new Object[]{ct.getMaHDBH(), ct.getTenSP(), ct.getSoLuong(), ct.getTongTien()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }//GEN-LAST:event_lbltikmkiemActionPerformed

    private void txtSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSPActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtSPActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void txtSoluongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoluongActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtSoluongActionPerformed

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

    private void btnHuy1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuy1ActionPerformed
        // TODO add your handling code here:
        
         int chon = JOptionPane.showConfirmDialog(this, "Bạn Có Muốn Xóa?", "xoa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (chon == JOptionPane.YES_NO_OPTION) {
           Integer mahdbh = Integer.parseInt(txtMaHDBH.getText());
            Connection conn = dao.getConnect();
            String caulenh = "delete from chitiethdbh where MaHDBH=" + txtMaHDBH.getText() + " and MaSP=" + txtSP.getText();
            boolean a = dao.Lenh(caulenh, conn);
           String capnhat = "UPDATE tbl_sanpham SET SLTon = SLTon+ " + Integer.parseInt(txtSoluong.getText().toString()) + " WHERE MaSP=" + txtSP.getText();
            boolean b = dao.Lenh(capnhat, conn);
             String capnhat1 = "UPDATE tbl_hoadonbanhang SET  TongTien = TongTien- " + Float.parseFloat(txtTongtien.getText().toString()) + " WHERE MaHDBH="+ txtMaHDBH.getText().toString();
                        boolean c = dao.Lenh(capnhat1, conn);
            if (a) {
                tblchitiethdbanhang.removeAll();
                tablemodel.setRowCount(0);
                loadnv();
                JOptionPane.showMessageDialog(this, "Xóa Thành Công");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa không thành công");
            }
        }
    }//GEN-LAST:event_btnHuy1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmChitietbanhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmChitietbanhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmChitietbanhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmChitietbanhang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmChitietbanhang(maHDBH).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonMetro btnHuy;
    private rojerusan.RSButtonMetro btnHuy1;
    private rojerusan.RSButtonMetro btnOFF;
    private rojerusan.RSButtonMetro btnQuet;
    private rojerusan.RSButtonMetro btnThem;
    private rojerusan.RSButtonMetro btnThoat;
    private rojerusan.RSButtonMetro btnin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDG;
    private javax.swing.JLabel lblTongTien;
    private rojerusan.RSButtonMetro lbltikmkiem;
    private javax.swing.JPanel pnl1;
    private rojeru_san.complementos.RSTableMetro tblchitiethdbanhang;
    private javax.swing.JTextField txtMaHDBH;
    private javax.swing.JTextField txtSP;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenSP;
    private app.bolivia.swing.JCTextField txtTimkiem;
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables
}
