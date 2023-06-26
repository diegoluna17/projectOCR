package com.trabajofinal.ocr.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trabajofinal.ocr.Entity.Compara;
import com.trabajofinal.ocr.Repository.comparaRepository;

@Service
public class comparaService {
	
	@Autowired
	private comparaRepository comparaRepository;
	
	public Optional<Compara> getOne(int id){
		return comparaRepository.findById(id);
	}
	
    public void save(Compara esCopiaOriginal){
    	comparaRepository.save(esCopiaOriginal);
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
