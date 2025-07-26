package _21_generic;

import java.io.Serializable;

public class CastingGenericDTOSample<T> implements Serializable {

	public static void main(String[] args) {
		CastingGenericDTOSample sample = new CastingGenericDTOSample();
		sample.checkCastingDTO();
	}

	private void checkCastingDTO() {
		CastingGenericDTO<String> dto1 = new CastingGenericDTO<>();
		dto1.setObject(new String());

		CastingGenericDTO<StringBuffer> dto2 = new CastingGenericDTO<>();
		dto2.setObject(new StringBuffer());

		CastingGenericDTO<StringBuilder> dto3 = new CastingGenericDTO<>();
		dto3.setObject(new StringBuilder());

		/**
		 * 실행 시에 다른 타입으로 형 변환하여 예외가 발생하는 일이 없다.
		 * (잘못된 타입으로 치환 시 형변환 자체가 안되므로)
		 */
		String object = dto1.getObject();
		StringBuffer object2 = dto2.getObject();
		StringBuilder object1 = dto3.getObject();
	}

}
