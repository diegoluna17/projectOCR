package com.trabajofinal.ocr.Service;

import java.util.Optional;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trabajofinal.ocr.Entity.Ocr;
import com.trabajofinal.ocr.Repository.ocrRepository;

import jakarta.transaction.Transactional;
import net.sourceforge.tess4j.Tesseract;

@Service
@Transactional
public class OcrService {
	
	@Autowired
	private ocrRepository ocrRepository;
	
	@Autowired Tesseract tesseract;
	
	
    public String procesar(InputStream inputStream) throws IOException{
        BufferedImage image = ImageIO.read(inputStream);
        try{
            return tesseract.doOCR(image); 
        }catch (Exception e){
            e.printStackTrace();
        }
        return "filed";
    }
    
	public Optional<Ocr> getOne(int id){
		return ocrRepository.findById(id);
	}
	
    public void save(Ocr texto){
        ocrRepository.save(texto);
    }
}
