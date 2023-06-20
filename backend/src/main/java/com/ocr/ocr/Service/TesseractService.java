/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ocr.ocr.Service;
import com.ocr.ocr.Entity.TextoOCR;
import com.ocr.ocr.Respository.TextoOCRRepository;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import javax.imageio.ImageIO;
import jakarta.transaction.Transactional;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Diego
 */
@Service
@Transactional
public class TesseractService {
        
    @Autowired
    private Tesseract tesseract;
    @Autowired
    private TextoOCRRepository repositoryOCR;
    
    public String procesar(InputStream inputStream) throws IOException{
        BufferedImage image = ImageIO.read(inputStream);
        try{
            return tesseract.doOCR(image); 
        }catch (Exception e){
            e.printStackTrace();
        }
        return "filed";
    }
    
    public List<TextoOCR> list(){
        return repositoryOCR.findAll();
    }
    
    public Optional<TextoOCR> getOne(int id){
        return repositoryOCR.findById(id);
    }
    
    public void save(TextoOCR textoOCR){
        repositoryOCR.save(textoOCR);
    }
    
    public String calcularHash(InputStream inputStream) throws IOException, NoSuchAlgorithmException {
        BufferedImage image = ImageIO.read(inputStream);
        try {
            byte[] imageData = convertirImagenABytes(image);
            return calcularHashMD5(imageData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }
    
    public boolean compararImagenesPorHash(InputStream inputStream1, InputStream inputStream2) throws IOException, NoSuchAlgorithmException {
        String hashImagen1 = calcularHash(inputStream1);
        String hashImagen2 = calcularHash(inputStream2);
        
        return hashImagen1.equals(hashImagen2);
    }
    
    private byte[] convertirImagenABytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }
    
    private String calcularHashMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(data);
        return convertirBytesAHex(hashBytes);
    }
    
    private String convertirBytesAHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
    
}
