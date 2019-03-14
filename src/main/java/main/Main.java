package main;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;

import main.loaders.PDFBoxLoader;
import main.loaders.TikaLoader;

public class Main {

	public static void main(String[] args) throws Exception {
		parsePBox("/home/renato/Downloads/big.pdf");
		parsePBox("/home/renato/Downloads/big.pdf");
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
		long start = System.currentTimeMillis();
		try {
			File file = new File(fileName);
			if (tika) {
				TikaLoader.load(file);
			} else {
				PDFBoxLoader.load(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			long end = System.currentTimeMillis();
			System.out.println("Computed in " + BigDecimal.valueOf(end - start).divide(BigDecimal.valueOf(1000l)).toString() + " seconds");
		}
	}
	
	private static String getFileSizeMegaBytes(File file) {
		return (double) file.length() / (1024 * 1024) + " mb";
	}
}
