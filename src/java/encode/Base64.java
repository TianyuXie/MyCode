public class Base64 {

	private static final byte[] STANDARD_ENCODE_TABLE = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
	
	private static final byte[] DECODE_TABLE;
	
	static {
		DECODE_TABLE = new byte[256];
		
		for (byte index = 0; index < STANDARD_ENCODE_TABLE.length; ++index) {
			DECODE_TABLE[STANDARD_ENCODE_TABLE[index]] = index;
		}
	}
	
	private static final byte PAD = '=';
	
	private static final int MASK_6BITS = 0x3f;
	private static final int MASK_8BITS = 0xff;
	
	public static String decodeBase64String(String base64String) {
		if (base64String == null || base64String.length() % 4 != 0) {
			return base64String;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < base64String.length(); index += 4) {
			sb.append(decode(base64String.getBytes(), index));
		}
		
		return sb.toString();
	}
	
	private static String decode(byte[] binaryData, int index) {
		final byte[] indexs = new byte[4];
		indexs[0] = DECODE_TABLE[binaryData[index + 0]];
		indexs[1] = DECODE_TABLE[binaryData[index + 1]];
		indexs[2] = DECODE_TABLE[binaryData[index + 2]];
		indexs[3] = DECODE_TABLE[binaryData[index + 3]];
		
		final byte[] buffer;
		if (binaryData[index + 2] == PAD) {
			buffer = new byte[1];
			buffer[0] = (byte)((indexs[0] << 2) | (indexs[1] >> 4) & MASK_8BITS);
		} else if (binaryData[index + 3] == PAD) {
			buffer = new byte[2];
			buffer[0] = (byte)((indexs[0] << 2) | (indexs[1] >> 4) & MASK_8BITS);
			buffer[1] = (byte)((indexs[1] << 4 | indexs[2] >> 2) & MASK_8BITS);
		} else {
			buffer = new byte[3];
			buffer[0] = (byte)((indexs[0] << 2) | (indexs[1] >> 4) & MASK_8BITS);
			buffer[1] = (byte)((indexs[1] << 4 | indexs[2] >> 2) & MASK_8BITS);
			buffer[2] = (byte)((indexs[2] << 6 | indexs[3]) & MASK_8BITS);
		}
		
		return new String(buffer);
	}
	
	public static String encodeBase64String(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int index = 0; index < input.length() ; index += 3) {
			sb.append(encode(input.getBytes(), index));
		}
		
		return sb.toString();
	}
	
	private static String encode(byte[] binaryData, int index) {
		final byte[] buffer = new byte[4];
		
		if (binaryData.length - index < 3) {
			switch (binaryData.length - index) {
			case 1: // 8 bits = 6 + 2
				buffer[0] = STANDARD_ENCODE_TABLE[(binaryData[index + 0] >> 2) & MASK_6BITS];
				buffer[1] = STANDARD_ENCODE_TABLE[(binaryData[index + 0] << 4) & MASK_6BITS];
				buffer[2] = PAD;
				buffer[3] = PAD;
				break;
			case 2: // 16 bits = 6 + 6 + 4
				buffer[0] = STANDARD_ENCODE_TABLE[(binaryData[index + 0] >> 2) & MASK_6BITS];
				buffer[1] = STANDARD_ENCODE_TABLE[((binaryData[index + 0] << 4) | (binaryData[index + 1] >> 4)) & MASK_6BITS];
				buffer[2] = STANDARD_ENCODE_TABLE[(binaryData[index + 1] << 2) & MASK_6BITS];
				buffer[3] = PAD;
				break;
			default:
				throw new IllegalStateException("Impossible reach here.");
			}
		} else {
			// 16 bits = 6 + 6 + 4
			buffer[0] = STANDARD_ENCODE_TABLE[(binaryData[index + 0] >> 2) & MASK_6BITS];
			buffer[1] = STANDARD_ENCODE_TABLE[((binaryData[index + 0] << 4) | (binaryData[index + 1] >> 4)) & MASK_6BITS];
			buffer[2] = STANDARD_ENCODE_TABLE[((binaryData[index + 1] << 2) | (binaryData[index + 2] >> 6)) & MASK_6BITS];
			buffer[3] = STANDARD_ENCODE_TABLE[binaryData[index + 2] & MASK_6BITS];
		}
		
		return new String(buffer);
	}
}
