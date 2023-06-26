package com.trabajofinal.ocr.Controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trabajofinal.ocr.Entity.Compara;
import com.trabajofinal.ocr.Entity.Ocr;
import com.trabajofinal.ocr.Service.OcrService;
import com.trabajofinal.ocr.Service.comparaService;

@RestController
@RequestMapping("/ocr")
@CrossOrigin("http://localhost:4200")
public class OcrController {
	
	@Autowired
	private OcrService ocrService;
	
	@Autowired 
	private comparaService comparaService;

	@GetMapping("/getOcr")
	public ResponseEntity<Ocr> getOcr(){
		Ocr ocr = ocrService.getOne(1).get();
		return new ResponseEntity<Ocr>(ocr, HttpStatus.OK);
	}
	
	@PostMapping("/procesar")
	public ResponseEntity<?> procesarImagen(@RequestPart("img") MultipartFile img) throws IOException{
		String resultado = ocrService.procesar(img.getInputStream());
		System.out.println("Resultado del procesar: " + resultado);
		Ocr ocr = ocrService.getOne(1).get();
		ocr.setTexto(resultado);
		ocrService.save(ocr);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/comparar")
    public ResponseEntity<String> compararImagenesPorHash(@RequestParam("img1") MultipartFile imagen1, @RequestParam("img2") MultipartFile imagen2) throws IOException, NoSuchAlgorithmException {
        Boolean sonIguales = comparaService.compararImagenesPorHash(imagen1.getInputStream(), imagen2.getInputStream());
        String esCopiaOriginal;
        if(sonIguales) {
        	esCopiaOriginal = "Las imágenes son copias identicas";
        }
        else {
        	esCopiaOriginal = "Las imágenes no son copias identicas";
        }
		return new ResponseEntity<String>(esCopiaOriginal, HttpStatus.OK);
    }
	

}
