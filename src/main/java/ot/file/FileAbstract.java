package ot.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import ot.io.FileIf;

public class FileAbstract implements FileIf {

	@Override
	public void write(InputStream is, final String path) {
		// TODO 自動生成されたメソッド・スタブ
		Path filepath = Paths.get(path);

		try {
			Files.copy(is, filepath, StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException ie) {
			// エラー処理
			System.out.println(ie);
		}
	}

	@Override
	public InputStream read(final String path) {
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(new File(path));
		}catch(FileNotFoundException fe) {
			// エラー処理
			System.out.println(fe);
		}

		return fis;
	}

}
