/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaswingdev.form;


import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import config.koneksi;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author root
 */
public class Form_transaksi extends javax.swing.JPanel {
     DefaultTableModel tm;
    String filename=null;
    
    public Statement st;
    public ResultSet rs;
    public DefaultTableModel tabmodel;
    public DefaultTableModel tabmodel2;
    public int stok;
    Connection con = koneksi.getKoneksi();
    
    Map param = new HashMap();
    
    public Form_transaksi() {
        initComponents();
        tampil();        
        tampiltmp();
        
    }
    
    private void reset(){
        tagihan.setText("");
        nama.setText("");
        harga.setText("");
        jumlah.setText("");
        subtotal.setText("");
        bayar.setText("");
        kembalian.setText("");
    }
    
    private String formatCurrency(double value) {
    Locale localeID = new Locale("id", "ID"); // Indonesia locale
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeID);
    return currencyFormatter.format(value);
    }
    public void tampil(){
        try{
            Object[] baris = {"Id_menu", "Nama_menu", "harga_menu", "satuan_menu", "Kategori"};
            tabmodel = new DefaultTableModel(null, baris){
                  public boolean isCellEditable(int row, int column){
                return false;
                }
            };
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            st = con.createStatement();
            rs = st.executeQuery("select * from tbl_menu");
            while (rs.next()){
                String id = rs.getString("id_menu");
                String tgl = rs.getString("nama_menu");
                String barang = formatCurrency(rs.getInt("harga_menu"));
//                String barang = rs.getString("harga_menu");
                String ttl = rs.getString("satuan_menu");
                String kat = rs.getString("kategori");
                String[] row = {id, tgl, barang, ttl, kat};
                tabmodel.addRow(row);
            }
            tabelbarang.setModel(tabmodel);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private String isformatCurrency(double value) {
    Locale localeID = new Locale("id", "ID");
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeID);
    return currencyFormat.format(value);
    }
    public void tampiltmp(){
        try {
        Object[] baris = {"id", "Nama", "harga", "jumlah", "total"};
        tabmodel2 = new DefaultTableModel(null, baris);
        st = con.createStatement();
        rs = st.executeQuery("select * from tmp_transaksi");
        while (rs.next()) {
            String id = rs.getString("no");
            String tgl = rs.getString("nama_menu");

            // Format harga and total as currency
            double hargaDouble = rs.getDouble("harga");
            String hargaFormatted = isformatCurrency(hargaDouble);

            int barang = rs.getInt("jumlah");
            double totalDouble = rs.getDouble("total");
            String totalFormatted = formatCurrency(totalDouble);

            String[] row = {id, tgl, hargaFormatted, String.valueOf(barang), totalFormatted};
            tabmodel2.addRow(row);
        }
        tabeldaftar.setModel(tabmodel2);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }
    public String status="";
    
    public void total_tmp(){
    BigDecimal total = BigDecimal.ZERO;

    try {
        st = con.createStatement();
        rs = st.executeQuery("select * from tmp_transaksi");

        while (rs.next()) {
            BigDecimal totalBarang = rs.getBigDecimal("total");
            total = total.add(totalBarang);
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID")); // Set the locale to Indonesian
        String formattedTotal = currencyFormat.format(total);

        tagihan.setText(formattedTotal);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public void find(){
        try{
            Object[] baris = {"Id Menu", "Nama_menu", "harga_menu", "satuan_menu", "Kategori"};
            tabmodel = new DefaultTableModel(null, baris);
            st = con.createStatement();
            rs = st.executeQuery("select * from tbl_menu where id_menu like '%."
                    +cari.getText()+"%' or nama_menu like '%" 
                    +cari.getText() + "%'or harga_menu like '%" 
                    +cari.getText() + "%'or satuan_menu like '%" 
                    +cari.getText() + "%'or kategori like '%" 
                    +cari.getText() + "%'");
            while (rs.next()){
                String id = rs.getString("id_menu");
                String tgl = rs.getString("nama_menu");
                String barang = rs.getString("harga_menu");
                String ttl = rs.getString("satuan_menu");
                String kat = rs.getString("kategori");
                String[] row = {id, tgl, barang, ttl, kat};
                tabmodel.addRow(row);
            }
            tabelbarang.setModel(tabmodel);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void findd(){
        try{
            Object[] baris = {"id", "Nama", "harga", "jumlah", "total"};
            tabmodel2 = new DefaultTableModel(null, baris);
            st = con.createStatement();
            rs = st.executeQuery("select * from tmp_transaksi where nama_menu like '%"
                    +txtcari.getText()+"%' or no like '%" 
                    +txtcari.getText() + "%'or harga like '%" 
                    +txtcari.getText() + "%'or jumlah like '%" 
                    +txtcari.getText() + "%'or total like '%" 
                    +txtcari.getText() + "%'");
            while (rs.next()){
                String id = rs.getString("no");
                String tgl = rs.getString("nama_menu");
                String barang = rs.getString("harga");
                String ttl = rs.getString("jumlah");
                String kat = rs.getString("total");
                String[] row = {id, tgl, barang, ttl, kat};
                tabmodel2.addRow(row);
            }
            tabeldaftar.setModel(tabmodel2);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void simpanTransaksi(int total, int kembalian) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String kodeTransaksi = "TRX" + LocalDateTime.now().format(formatter);        
        LocalDate tanggalBayar = LocalDate.now();        
        st = con.createStatement();
        st.executeUpdate("INSERT INTO riwayat_transaksi (kode_transaksi, total_bayar, kembalian, tanggal_bayar) VALUES ('" + kodeTransaksi + "', " + total + ", " + kembalian + ", '" + Date.valueOf(tanggalBayar) + "')");

        st.executeUpdate("UPDATE tbl_menu SET satuan_menu = satuan_menu - (SELECT jumlah FROM tmp_transaksi WHERE no = '" + kode.getText() + "') WHERE id_menu = (SELECT id_menu FROM tmp_transaksi WHERE no = '" + kode.getText() + "')");
        
        st.executeUpdate("DELETE FROM tmp_transaksi");

        
        reset();
        tampil();
        tampiltmp();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal menyimpan transaksi ke riwayat transaksi: " + e.getMessage());
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

        cari = new javax.swing.JTextField();
        kode = new javax.swing.JTextField();
        kode_bar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        harga = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jumlah = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        subtotal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        tambah = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelbarang = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabeldaftar = new javax.swing.JTable();
        tagihan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        hapus = new javax.swing.JButton();
        bayar = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        btnBayar = new javax.swing.JButton();
        newt = new javax.swing.JButton();
        cetak = new javax.swing.JButton();
        kembalian = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        nama2 = new javax.swing.JTextField();
        JmlBalik = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setAlignmentX(3.0F);
        setPreferredSize(new java.awt.Dimension(870, 629));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });
        add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 420, 20));

        kode.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        kode.setEnabled(false);
        kode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeActionPerformed(evt);
            }
        });
        add(kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 90, 40));

        kode_bar.setEnabled(false);
        kode_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kode_barActionPerformed(evt);
            }
        });
        add(kode_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 80, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Kode Transaksi");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, 40));
        add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 180, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Nama Barang");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 100, 40));

        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });
        add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 180, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Harga ");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 70, 40));

        jumlah.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jumlahFocusGained(evt);
            }
        });
        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });
        jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jumlahKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahKeyTyped(evt);
            }
        });
        add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 160, 180, 40));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jumlah");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 80, 40));

        subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtotalActionPerformed(evt);
            }
        });
        subtotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                subtotalKeyReleased(evt);
            }
        });
        add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 180, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Subtotal");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, 80, 40));

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 260, 80, 30));

        tambah.setText("Tambah");
        tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahMouseClicked(evt);
            }
        });
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 260, 110, 30));

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });
        add(txtcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 180, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Cari Barang");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, 90, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Daftar pembelian");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, 20));

        tabelbarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelbarangMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelbarang);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 500, 190));

        tabeldaftar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabeldaftar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeldaftarMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabeldaftar);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 850, 100));

        tagihan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tagihan.setEnabled(false);
        tagihan.setSelectionColor(new java.awt.Color(0, 0, 0));
        tagihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagihanActionPerformed(evt);
            }
        });
        tagihan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tagihanKeyReleased(evt);
            }
        });
        add(tagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 160, 40));
        tagihan.getAccessibleContext().setAccessibleName("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Total Tagihan");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, 20));

        hapus.setText("Hapus");
        hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hapusMouseClicked(evt);
            }
        });
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 470, -1, -1));
        hapus.getAccessibleContext().setAccessibleDescription("");

        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });
        bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bayarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bayarKeyTyped(evt);
            }
        });
        add(bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 160, 40));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(5, 62, 74));
        jLabel29.setText("Cash");
        add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, -1, 32));

        btnBayar.setText("Bayar");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        add(btnBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 610, -1, -1));

        newt.setText("New Transaksi");
        newt.setEnabled(false);
        newt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newtMouseClicked(evt);
            }
        });
        newt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newtActionPerformed(evt);
            }
        });
        add(newt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 610, -1, -1));

        cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icons8-print-30.png"))); // NOI18N
        cetak.setText("Cetak bukti pembayaran");
        cetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakMouseClicked(evt);
            }
        });
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });
        add(cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 600, 220, 40));

        kembalian.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kembalian.setEnabled(false);
        kembalian.setSelectionColor(new java.awt.Color(0, 0, 0));
        kembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalianActionPerformed(evt);
            }
        });
        add(kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 560, 160, 40));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(5, 62, 74));
        jLabel30.setText("Kembalian");
        add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, -1, 32));

        nama2.setBackground(new java.awt.Color(242, 242, 242));
        nama2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama2ActionPerformed(evt);
            }
        });
        add(nama2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, -1, -1));

        JmlBalik.setBackground(new java.awt.Color(242, 242, 242));
        JmlBalik.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        JmlBalik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JmlBalikActionPerformed(evt);
            }
        });
        add(JmlBalik, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 470, -1, -1));

        jButton1.setText("CARI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 60, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyReleased
    
    }//GEN-LAST:event_cariKeyReleased

    private void kodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeActionPerformed

    private void kode_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kode_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_barActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void jumlahFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jumlahFocusGained

    }//GEN-LAST:event_jumlahFocusGained

    private void jumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyPressed

    }//GEN-LAST:event_jumlahKeyPressed
    private double getHargaBarangFromDatabase() {
        double harga_barang = 0.0;
        try {
        // Ganti koneksi dan query sesuai dengan struktur dan skema database Anda
        st = con.createStatement();
        
        // Ubah query menjadi PreparedStatement untuk parameterized query
        String query = "SELECT harga_menu FROM tbl_menu WHERE nama_menu = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, nama.getText()); // Ubah sesuai dengan komponen yang menunjukkan nama barang

        // Eksekusi query
        rs = ps.executeQuery();

        if (rs.next()) {
            harga_barang = rs.getDouble("harga_menu");
        }

        rs.close();
        ps.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return harga_barang;
    }
    private void jumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyReleased
        if (jumlah.getText().equals("") || jumlah.getText().equals("0")) {
        subtotal.setText("Rp 0");
    } else {
        try {
            // Mengambil nilai harga dari database berdasarkan ID atau nama barang
            double hargaBarang = getHargaBarangFromDatabase(); // Menggunakan metode baru untuk mengambil harga dari database

            // Mengalikan harga barang dengan jumlah barang yang ingin dibeli
            int jumlahBarang = Integer.parseInt(jumlah.getText());
            double total = hargaBarang * jumlahBarang;

            // Format total sebagai mata uang
            DecimalFormat currencyFormat = new DecimalFormat("Rp#,##0.00");
            String formattedTotal = currencyFormat.format(total);

            // Menetapkan subtotal ke label subtotal
            subtotal.setText(formattedTotal);
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_jumlahKeyReleased

    private void jumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_jumlahKeyTyped

    private void subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotalActionPerformed

    private void subtotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_subtotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_subtotalKeyReleased

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        reset();
    }//GEN-LAST:event_resetActionPerformed
    private double parseCurrencyToDecimal(String currencyString) {
        try {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            Number number = format.parse(currencyString);
            return number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
    
    
    private void tambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahMouseClicked
        if (status.equals("")) {
        if (jumlah.getText().equals("") || jumlah.getText().equals("") || nama.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dan Masukan jumlah makanan!");
        } else {
            try {
                st = con.createStatement();
                rs = st.executeQuery("select * from tbl_menu where id_menu='"
                        + tabmodel.getValueAt(tabelbarang.getSelectedRow(), 0).toString() + "'");
                if (rs.next()) {
                    int satuanMenu = rs.getInt("satuan_menu");
                    int jumlahBarang = Integer.parseInt(jumlah.getText());
                    String namaMenu = nama.getText();

                    
                    if (satuanMenu <= jumlahBarang) {
                        
                        BigDecimal hargaBarang = rs.getBigDecimal("harga_menu");
                        BigDecimal jumlahBarangDecimal = BigDecimal.valueOf(jumlahBarang);

                        // Calculate total as decimal
                        BigDecimal total = hargaBarang.multiply(jumlahBarangDecimal);
                        
                        st = con.createStatement();
                        st.executeUpdate("insert into tmp_transaksi (no, nama_menu, harga, jumlah, total) "
                                + "values('" + kode_bar.getText() + "','" + nama.getText() + "',"
                                + "'" + hargaBarang + "',"
                                + "'" + jumlahBarang + "',"
                                + "'" + total + "')");
                        
                        
                        tampiltmp();
                        tampil();
                        total_tmp();
                        nama.setText("");
                        harga.setText(BigDecimal.ZERO.toString());
                        jumlah.setText("");
                        subtotal.setText(BigDecimal.ZERO.toString());
                        
                    } else {
                        // Get harga from the database as decimal
                        BigDecimal hargaBarang = rs.getBigDecimal("harga_menu");
                        BigDecimal jumlahBarangDecimal = BigDecimal.valueOf(jumlahBarang);

                        // Calculate total as decimal
                        BigDecimal total = hargaBarang.multiply(jumlahBarangDecimal);
                        
                        // Insert into tmp_transaksi table
                        st = con.createStatement();
                        st.executeUpdate("insert into tmp_transaksi (no, nama_menu, harga, jumlah, total) "
                                + "values('" + kode_bar.getText() + "','" + nama.getText() + "',"
                                + "'" + hargaBarang + "',"
                                + "'" + jumlahBarang + "',"
                                + "'" + total + "')");

                        tampiltmp();
                        tampil();
                        total_tmp();
                        nama.setText("");
                        harga.setText(BigDecimal.ZERO.toString());
                        jumlah.setText("");
                        subtotal.setText(BigDecimal.ZERO.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } else {
        // Rest of your code for the else condition
    }
    }//GEN-LAST:event_tambahMouseClicked

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
       
        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from tbl_menu where nama_menu = '"+nama.getText()+"'");
            
            while (rs.next()) {
                int a = Integer.parseInt(rs.getString("satuan_menu"));
                int j = Integer.parseInt(jumlah.getText());

                int rj = a-j;
                
                try {
                    
                    if (a < j) {
                        // Handle the case where the quantity is more than available stock
                        
                        JOptionPane.showMessageDialog(null, "Jumlah melebihi stok yang tersedia!");
                    } else {
                    st.executeUpdate("update tbl_menu set satuan_menu ='"+rj+"' where nama_menu='"+nama.getText()+"'");
                    }
                    } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal Update  Stock");
                }
            }
        } catch (Exception e) {
        }
           
             
    }//GEN-LAST:event_tambahActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        findd();
    }//GEN-LAST:event_txtcariKeyReleased

    private void tabelbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbarangMouseClicked
        String selectedMenuName = tabmodel.getValueAt(tabelbarang.getSelectedRow(), 1).toString();
        String selectedMenuPrice = tabmodel.getValueAt(tabelbarang.getSelectedRow(), 2).toString();
        String selectedMenuId = tabmodel.getValueAt(tabelbarang.getSelectedRow(), 0).toString();

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from tbl_menu where id_menu='" + selectedMenuId + "'");
            if (rs.next()) {
                int stock = rs.getInt("satuan_menu");

                if (stock > 0) {
                    nama.setText(selectedMenuName);
                    harga.setText(selectedMenuPrice);
                    kode_bar.setText(selectedMenuId);
                } else {
                    // Notify the user that the stock is empty
                    JOptionPane.showMessageDialog(null, "Stok menu '" + selectedMenuName + "' habis!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tabelbarangMouseClicked

    private void tabeldaftarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldaftarMouseClicked
        JmlBalik.setText(tabmodel2.getValueAt(tabeldaftar.getSelectedRow(), 3).toString());
        nama2.setText(tabmodel2.getValueAt(tabeldaftar.getSelectedRow(), 1).toString());      // TODO add your handling code here:
    }//GEN-LAST:event_tabeldaftarMouseClicked

    private void tagihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagihanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tagihanActionPerformed

    private void tagihanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tagihanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tagihanKeyReleased

    private void hapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusMouseClicked
        try {
            st =con.createStatement();
            st.executeUpdate("delete from tmp_transaksi WHERE no = '"+tabmodel2.getValueAt
                (tabeldaftar.getSelectedRow(),0)+"'");
//            reset();
            tampil();
            tampiltmp();
            total_tmp();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_hapusMouseClicked

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from tbl_menu where nama_menu = '"+nama2.getText()+"'");
            while (rs.next()) {
                int a = Integer.parseInt(rs.getString("satuan_menu"));
                int j = Integer.parseInt(JmlBalik.getText());

                int rj = j+a;

                try {
                    st.executeUpdate("update tbl_menu set satuan_menu ='"+rj+"' where nama_menu='"+nama2.getText()+"'");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Gagal Update  Stock");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeyTyped
        // TODO add your handling code here:
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_bayarKeyTyped

    private void bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeyReleased

    }//GEN-LAST:event_bayarKeyReleased

    private void bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarKeyPressed
        //        int kembalian = 0;
        //        kembalian =Integer.parseInt(bayar.getText()) - Integer.parseInt(tagihan.getText().substring(3));
        //        if (kembalian<0) {
            //            this.kembalian.setText("Uang kurang");
            //        }else{
            //        this.kembalian.setText(String.valueOf(kembalian));
            //        }
    }//GEN-LAST:event_bayarKeyPressed
    private int getTotalTagihanFromDB() throws SQLException {
    int totalTagihan = 0;
    
    // Adjust this query based on your database schema
    String query = "SELECT SUM(total) AS total FROM tmp_transaksi";
    
    try (PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            totalTagihan = rs.getInt("total");
        }
    }
    return totalTagihan;
    }
    private int getTotalTagihanFromDatabase() {
    int totalTagihan = 0;
    try {
        // Assuming you have a method to fetch the total from the database
        totalTagihan = getTotalTagihanFromDB();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception according to your requirements
    }
    return totalTagihan;
}
    private String strformatCurrency(int value) {
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID")); // Use the correct locale for your currency
    return currencyFormat.format(value);
    }
    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        if (!bayar.getText().equals("")) {
        try {
            // Retrieve total tagihan from the database
            int totalTagihan = getTotalTagihanFromDatabase();

            int jumlahBayar = Integer.parseInt(bayar.getText());

            if (jumlahBayar < totalTagihan) {
                JOptionPane.showMessageDialog(null, "Maaf! Uang Anda kurang");
            } else {
                int kembalianLabel = jumlahBayar - totalTagihan;
                String formattedKembalian = strformatCurrency(kembalianLabel);
                
                JOptionPane.showMessageDialog(null, "Terima kasih sudah membayar");
                status = "ada";

                this.kembalian.setText(formattedKembalian);
                simpanTransaksi(totalTagihan, kembalianLabel);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Isi jumlah uang yang dibayarkan");
    }
    }//GEN-LAST:event_btnBayarActionPerformed

    private void newtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_newtMouseClicked

    private void newtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newtActionPerformed
        // TODO add your handling code here:
        new Form_transaksi().setVisible(true);
        this.hide();
    }//GEN-LAST:event_newtActionPerformed

    private void kembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianActionPerformed

    private void nama2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama2ActionPerformed

    private void JmlBalikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JmlBalikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JmlBalikActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        // TODO add your handling code here:
            Riwayat_Transaksi riwayat = new Riwayat_Transaksi();
            riwayat.setVisible(true);
        //
    }//GEN-LAST:event_cetakActionPerformed

    private void cetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakMouseClicked
        
    }//GEN-LAST:event_cetakMouseClicked

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tm=new DefaultTableModel (null,
            new Object[] {"id_menu","nama_menu","harga_menu","satuan_menu","kategori"});
        tabelbarang.setModel(tm);
        tm.getDataVector().removeAllElements();
        try
        {
            PreparedStatement s= con.prepareStatement("SELECT *from tbl_menu where id_menu LIKE '%"
                + cari.getText().toString()+"%'");
            ResultSet r= s.executeQuery();
            while(r.next())
            {
                Object[] data={
                    r.getString(1),
                    r.getString(2),
                    r.getString(3),
                    r.getString(4),
                    r.getString(5)
                };
                tm.addRow(data);
            }
        }
        catch(Exception e)
        {
            System.out.print("ERROR KUERI KE DATABASE:\n" + e + "\n\n");
        }
                          
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JmlBalik;
    private javax.swing.JTextField bayar;
    private javax.swing.JButton btnBayar;
    private javax.swing.JTextField cari;
    private javax.swing.JButton cetak;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField kembalian;
    private javax.swing.JTextField kode;
    private javax.swing.JTextField kode_bar;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nama2;
    private javax.swing.JButton newt;
    private javax.swing.JButton reset;
    private javax.swing.JTextField subtotal;
    private javax.swing.JTable tabelbarang;
    private javax.swing.JTable tabeldaftar;
    private javax.swing.JTextField tagihan;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
