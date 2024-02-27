
package javaswingdev.form;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
/**
 *
 * @author root
 */
public class StrukPrintable {
    private String kodeTransaksi;
    private String namaPelanggan;
    private String totalBayar;
    private String kembalian;
    private String tanggal;
    
    public StrukPrintable(String kodeTransaksi, String namaPelanggan, String totalBayar, String kembalian, String tanggal) {
        this.kodeTransaksi = kodeTransaksi;
        this.namaPelanggan = namaPelanggan;
        this.totalBayar = totalBayar;
        this.kembalian = kembalian;
        this.tanggal = tanggal;
    }
    
    public void generateStruk(String filePath){
        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph("Selamat Datang Di Nadya Resto"));
            document.add(new Paragraph("================================="));
            document.add(new Paragraph("Kode Transaksi :" + kodeTransaksi));
            document.add(new Paragraph("Nama Pelanggan: " + namaPelanggan));
            document.add(new Paragraph("Total Bayar:" + " " + "Rp." + totalBayar));
            document.add(new Paragraph("---------------------------------"));
            document.add(new Paragraph("Kembalian: " + kembalian));
            document.add(new Paragraph("================================="));
            document.add(new Paragraph("Tanggal Bayar: " + tanggal));
        }catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
