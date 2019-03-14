package main.loaders;

import java.io.File;

import org.apache.pdfbox.preflight.parser.PreflightParser;

public class PDFBoxLoader {
	
	public static void load(File file) throws Exception {
			
			PreflightParser parser = new PreflightParser(file);
	
			parser.parse();
	
			parser.getPreflightDocument().validate();
	}


}
