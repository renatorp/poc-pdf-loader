package main;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;

import main.loaders.PDFBoxLoader;
import main.loaders.TikaLoader;

public class Main {

	public static void main(String[] args) throws Exception {
		parsePBox("/home/renatorp/Documents/Materiais/TI/Livros e apostilas/Livro Programação em Shell Linux 8 edição - Julio Cezar Neves.pdf");
		parsePBox("/home/renatorp/Documents/Materiais/BONS LIVROS FAZEM GRANDES LIDERES/O LIVRO DE OURO DA LIDERANÇA - John C. Maxwell.pdf");
		System.out.println("end");
	}
	
	private static void parseTika(String fileName) throws Exception {
		parseDocument(fileName, true);
	}

	private static void parsePBox(String fileName) throws Exception {
		parseDocument(fileName, false);
	}
	
	private static void simulateCopyToOutputStream(String fileName) throws Exception {
		File newFile = new File("teste.pdf");
		File originalFile = new File(fileName);
		FileInputStream is = new FileInputStream(originalFile);
		FileUtils.copyInputStreamToFile(is, newFile);
		System.out.println(Files.probeContentType(newFile.toPath()));
		System.out.println(Files.probeContentType(originalFile.toPath()));
	}
	
	private static void parseDocument(String fileName, boolean tika) throws Exception {
		File file = new File(fileName);
		if (tika) {
			TikaLoader.load(file);
		} else {
			PDFBoxLoader.load(file);
		}
	}
	
	private static String getFileSizeMegaBytes(File file) {
		return (double) file.length() / (1024 * 1024) + " mb";
	}
}
