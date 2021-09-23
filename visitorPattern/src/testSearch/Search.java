package testSearch;
/*
使用递归找出某目录("C:\\JavaProducts")下的所有子目录以及子文件
*/

import java.util.*;
import java.io.*;

public class Search {
	public static void main(String[] args) {
		List<String> paths = new ArrayList<String>();
		paths = getAllFilePaths(new File("d:/test/research"), paths, "dd88d21");
		int i = 0;
		for (String path : paths) {
			System.out.println(path);
			i++;
		}
	}

	private static List<String> getAllFilePaths(File filePath, List<String> filePaths, String filter) {
		File[] files = filePath.listFiles();
		if (files == null) {
			return filePaths;
		}
		for (File f : files) {
			if (f.isDirectory()) {
				// filePaths.add(f.getPath());
				getAllFilePaths(f, filePaths, filter);
			} else {
				if (f.getName().contains(filter)) {
					filePaths.add(f.getPath());
				}

			}
		}
		return filePaths;
	}
}