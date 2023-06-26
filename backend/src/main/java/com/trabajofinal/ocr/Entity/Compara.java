package com.trabajofinal.ocr.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Compara {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean esCopiaOriginal;

	public Compara() {}

	public Compara(boolean esCopiaOriginal) {
		this.esCopiaOriginal = esCopiaOriginal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEsCopiaOriginal() {
		return esCopiaOriginal;
	}

	public void setEsCopiaOriginal(boolean esCopiaOriginal) {
		this.esCopiaOriginal = esCopiaOriginal;
	}
	
	
	
	
	
	
}
