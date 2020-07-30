package kr.co.jhta.di.service;

import java.io.PrintWriter;

public class PdfOutput implements Output{

	public void write(String text) {
		System.out.println("pdf : " + text);
	}
	
	
}
