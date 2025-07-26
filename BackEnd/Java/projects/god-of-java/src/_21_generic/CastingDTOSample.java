package _21_generic;

public class CastingDTOSample {

	public static void main(String[] args) {
		CastingDTOSample sample = new CastingDTOSample();
		sample.checkCastingDTO();
	}

	private void checkCastingDTO() {
		CastingDTO dto1 = new CastingDTO();
		dto1.setObject(new String());

		CastingDTO dto2 = new CastingDTO();
		dto2.setObject(new StringBuffer());

		CastingDTO dto3 = new CastingDTO();
		dto3.setObject(new StringBuilder());

		/**
		 * 만들 때는 문제 없는데 꺼낼 때가 문제
		 */
		Object object = dto1.getObject();
		if (object instanceof StringBuffer) {
			System.out.println("StringBuffer");
		}
		if (object instanceof StringBuilder) {
			System.out.println("StringBuilder");
		}
	}

}
