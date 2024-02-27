
package javaswingdev.form;
import static com.itextpdf.xmp.XMPMetaFactory.reset;
import java.sql.*;
import config.koneksi;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import static java.time.zone.ZoneRulesProvider.refresh;
import static org.apache.commons.io.RandomAccessFiles.reset;
import static sun.security.krb5.Config.refresh;

/**
 *
 * @author root
 */
public class Form_Food extends javax.swing.JPanel {
    Connection con;
   DefaultTableModel tm;
   String filename=null;
    /**
     * Creates new form Form_Food
     */
    public Form_Food() {
        initComponents();
        
        connect();
        read_table();
    }
    
      private void reset(){
        txtid.setText("");
        txtnama.setText("");
        txtharga.setText("");
        txtstok.setText("");
        cbkategori.setSelectedItem("");   
    }
public void connect() {
    con=null;
    try{
        con=koneksi.getKoneksi();
    }catch (Exception e) {       
       System.out.print("error koneksi database" + e);
   }
}


private boolean isMenuExist(String idMenu) throws SQLException{
    PreparedStatement ps = con.prepareStatement("SELECT id_menu FROM tbl_menu WHERE id_menu = ?");
    ps.setString(1, idMenu);
    ResultSet rs = ps.executeQuery();
    return rs.next();
}
private boolean isMenuNameExists(String menuName) throws SQLException {
    PreparedStatement ps = con.prepareStatement("SELECT nama_menu FROM tbl_menu WHERE nama_menu = ?");
    ps.setString(1, menuName);
    ResultSet rs = ps.executeQuery();
    return rs.next(); // Returns true if a row is found, indicating that the menu name already exists
}
private String formatCurrency(double value) {
    Locale localeID = new Locale("id", "ID"); // Indonesia locale
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeID);
    return currencyFormatter.format(value);
}
private double parseCurrency(String currencyString) throws ParseException {
    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

    formatRp.setCurrencySymbol("Rp. ");
    formatRp.setMonetaryDecimalSeparator(',');
    formatRp.setGroupingSeparator('.');

    kursIndonesia.setDecimalFormatSymbols(formatRp);

    Number number = kursIndonesia.parse(currencyString);
    return number.doubleValue();
}




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblmenu = new javax.swing.JTable();
        txtid = new javax.swing.JTextField();
        txtnama = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        txtstok = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cbkategori = new javax.swing.JComboBox<>();
        reset = new javax.swing.JButton();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(870, 629));

        jButton1.setText("CARI");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblmenu.setBackground(new java.awt.Color(204, 204, 204));
        tblmenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblmenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KODE", "NAMA MENU", "HARGA", "KATEGORI", "STOK"
            }
        ));
        tblmenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblmenu.setRowSelectionAllowed(false);
        tblmenu.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblmenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblmenuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblmenu);

        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        txtnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnamaActionPerformed(evt);
            }
        });

        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });

        jLabel5.setText("kategori");

        jLabel4.setText("stok");

        jLabel3.setText("harga menu");

        jLabel2.setText("nama menu");

        jLabel1.setText("kode menu");

        jButton2.setText("SIMPAN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("UBAH");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
                jButton3Plr(evt);
            }
        });

        jButton4.setText("HAPUS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbkategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "makanan", "minuman", "snack", "dessert" }));
        cbkategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkategoriActionPerformed(evt);
            }
        });

        reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icons8-refresh-20.png"))); // NOI18N
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel2))
                        .addGap(10, 10, 10))
                    .addComponent(reset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(txtharga, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstok, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnama)
                            .addComponent(cbkategori, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cari, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbkategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(227, 227, 227)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(155, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    public void read_table() {
       tm=new DefaultTableModel(null,
          new Object[] {"KODE","NAMA MENU","HARGA","SATUAN","KATEGORI"});
       tblmenu.setModel(tm);
       tm.getDataVector().removeAllElements();
       try{
           PreparedStatement s= con.prepareStatement("SELECT *FROM tbl_menu");
           ResultSet r=s.executeQuery();
           while (r.next()){
               Object[] data={
                   r.getString(1),
                   r.getString(2),
                   formatCurrency(r.getInt(3)),
                   r.getString(4),
                   r.getString(5)
               };
               tm.addRow(data);
               }
           
           }catch (Exception e){
               System.out.print("error koneksi database:\n" + e + "\n\n");
       }
  }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        tm=new DefaultTableModel (null,
            new Object[] {"id_menu","nama_menu","harga_menu","satuan_menu","kategori"});
        tblmenu.setModel(tm);
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
 private void clearForm() {
    txtnama.setText("");
    txtharga.setText("");
    txtstok.setText("");
    cbkategori.setSelectedItem("");
    txtid.setText("");
}
    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnamaActionPerformed

    private void txthargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaActionPerformed
        
    }//GEN-LAST:event_txthargaActionPerformed
   
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{
            if(isMenuExist(txtid.getText())) {
                JOptionPane.showMessageDialog(this, "Menu Telah Tersedia");
            } else if (isMenuNameExists(txtnama.getText())) {
            JOptionPane.showMessageDialog(this, "Menu with name " + txtnama.getText() + " already exists. Please use a different name.");
            } 
            else {
            BigDecimal hargaInput = new BigDecimal(txtharga.getText());
            String selectedCategory = (String) cbkategori.getSelectedItem();
            PreparedStatement ps=con.prepareStatement("insert into tbl_menu values (?,?,?,?,?)");
            ps.setString(1, txtid.getText());
            ps.setString(2, txtnama.getText());
            ps.setBigDecimal(3, hargaInput);
            ps.setString(4, txtstok.getText());
            ps.setString(5, cbkategori.getSelectedItem().toString());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data telah tersimpan");
            read_table();
            }
        }catch (Exception e) {
            System.out.print("error koneksi database" + e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
            PreparedStatement ps = con.prepareStatement(
                "UPDATE tbl_menu SET nama_menu=?,harga_menu=?,satuan_menu=?,kategori=? WHERE id_menu=?");            
            BigDecimal hargaInput = new BigDecimal(txtharga.getText());
            
            ps.setString(1, txtnama.getText());
//            ps.setString(2, txtharga.getText());
            ps.setBigDecimal(2, hargaInput);
            ps.setString(3, txtstok.getText());
            ps.setString(4, cbkategori.getSelectedItem().toString());
            ps.setString(5, txtid.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data telah di Update");
            read_table();
        }catch(Exception e){
            System.out.print("error koneksi database:\n" + e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

   
    private void jButton3Plr(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3Plr
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3Plr

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int ok= JOptionPane.showConfirmDialog(null,"Cius mau dihapus='"+
            txtid.getText()+"'","punten",JOptionPane.YES_NO_OPTION);
        if (ok==0) {
            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM tbl_menu WHERE id_menu=?");
                ps.setString(1, txtid.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "DATA telah di Delete");
                read_table();
            } catch (Exception e) {
                System.out.print("ERROR KUERI KE DATABASE:\n" + e + "\n\n");
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    
        private double getHargaFromDatabase(String id_menu) {
        double harga = 0.0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT harga_menu FROM tbl_menu WHERE id_menu = ?");
            ps.setString(1, id_menu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                harga = rs.getDouble("harga_menu");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return harga;
        }
    private void tblmenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblmenuMouseClicked
        // TODO add your handling code here:
        int  i = tblmenu.getSelectedRow();
        if (i == -1) {
            return;
        }
        String id_menu= (String) tm.getValueAt (i, 0);
        txtid.setText(id_menu);
        String nama = (String) tm.getValueAt (i,1);
        txtnama.setText(nama);
//        String hargaRp = (String) tm.getValueAt(i, 2);
//        txtharga.setText(hargaRp);
        double hargaDatabase = getHargaFromDatabase(id_menu);
        // Tampilkan harga dari database dalam bentuk decimal di komponen input harga
        txtharga.setText(String.valueOf(hargaDatabase));
        
        String satuan = (String) tm.getValueAt(i, 3);
        txtstok.setText(satuan);
        String kategori = (String) tm.getValueAt(i, 4);
        cbkategori.setSelectedItem(kategori);
        
    }//GEN-LAST:event_tblmenuMouseClicked

    private void cbkategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbkategoriActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
         reset();
    }                                     
    private double parseCurrencyToDecimal(String currencyString) {
        try {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            Number number = format.parse(currencyString);
            return number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0.0;
        }
    }//GEN-LAST:event_resetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cari;
    private javax.swing.JComboBox<String> cbkategori;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reset;
    private javax.swing.JTable tblmenu;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtstok;
    // End of variables declaration//GEN-END:variables
}
