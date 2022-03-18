package com.miguelbarrios.util;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
	public static List<String> getFilesInDir(String dirPath) {
		List<String> files = new ArrayList<String>();

		File[] content = new File(dirPath).listFiles();
		for (File file : content) {
		    if (file.isFile()) {
		        files.add(file.getName());
		    }
		}
		return files;
	}
}
