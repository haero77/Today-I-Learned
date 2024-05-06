package org.example.ch03_polymorphism_and_abstract_type;

public class FlowController {

	private boolean useFile;

	public FlowController(boolean useFile) {
		this.useFile = useFile;
	}

	public void process() {
		final ByteSource source = ByteSourceFactory.getInstance().create();

		final byte[] data = source.read();

		final Encryptor encryptor = new Encryptor();
		final byte[] encryptedData = encryptor.encrypt(data);

		final FileDataWriter writer = new FileDataWriter();
		writer.write(encryptedData);
	}

}
