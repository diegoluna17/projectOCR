/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ocr.ocr.Respository;

import com.ocr.ocr.Entity.TextoOCR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Diego
 */

@Repository
public interface TextoOCRRepository extends JpaRepository<TextoOCR, Integer>{
    
}
