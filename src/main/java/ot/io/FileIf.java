package ot.io;

import java.io.InputStream;

public interface FileIf {
	public void write(InputStream is, final String path);
	public InputStream read(final String path);
}
