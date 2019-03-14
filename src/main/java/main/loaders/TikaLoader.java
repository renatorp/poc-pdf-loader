package main.loaders;

import java.io.File;

import org.apache.tika.Tika;

public class TikaLoader {

	public static void load(File file) throws Exception {
		Tika tika = new Tika();
		String detect = tika.detect(file);
		System.out.println("Detected: " + detect);
	}
}
