package fr.ikucuze.gedcom;

public class WrongGedComCharset extends Exception {
	private static final long serialVersionUID = 77871632860400620L;
	private final String charset;

	public WrongGedComCharset(String charset) {
		this.charset = charset;
	}
	
	public String getCharset() {
		return charset;
	}
}
