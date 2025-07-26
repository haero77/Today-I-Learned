package org.example.ch03_polymorphism_and_abstract_type;

public class ByteSourceFactory {

	private static final ByteSourceFactory INSTANCE = new ByteSourceFactory();

	public static ByteSourceFactory getInstance() {
		return INSTANCE;
	}

	// 객체 생성 기능을 위한 오퍼레이션 정의
	public ByteSource create() {
		if (useFile()) {
			return new FileDataReader();
		}
		return new SocketDataReader();
	}

	private boolean useFile() {
		final String useFileValue = System.getProperty("useFile");
		return Boolean.parseBoolean(useFileValue);
	}

}
