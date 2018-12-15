package project5;
/**
 * This class contains methods that convert between binary strings, hexadecimal strings, and decimal numbers
 * 
 * @author zhaoruochen
 *
 */
public class Converter extends Object {
	
	/**
	 * Converts binary strings to decimal numbers. More specifically given a valid string representing a binary number 
	 * returns a non-negative decimal integer with the same value.
	 * @param binary - the binary string to be converted
	 * @return the decimal number equal in value to the binary string passed as the parameter
	 * @throws IllegalArgumentException - if the binary string passed to the function is invalid
	 */
	public static int binaryToDecimal(String binary) throws IllegalArgumentException {
		// check if input is valid
		if (!checkBinary(binary)) {
			throw new IllegalArgumentException("The binary string passed to the function is not valid");
		}
		return binaryToDecimal2(binary);
	}

	/**
	 * Helper method for binaryToDecimal, calculates the decimal value for a binary string recursively
	 * @param binary - the binary string to be converted
	 * @return the decimal number equal in value to the binary string passed as the parameter
	 */
	private static int binaryToDecimal2(String binary) {
		if (binary.length() == 2) {
			return 0;
		} else {
			return ((binary.charAt(binary.length() - 1) == '0') ? 0 : 1)
					+ 2 * binaryToDecimal2(binary.substring(0, binary.length() - 1));
		}
	}

	/**
	 * Converts decimal numbers to binary strings. More specifically given a non-negative decimal integer returns the 
	 * string representing the binary number with the same value.
	 * @param decimal - the decimal number to be converted
	 * @return the binary string equal in value to the decimal number passed as the parameter or null if the decimal is negative
	 */
	public static String decimalToBinary(int decimal) {
		// check if decimal is positive
		if (decimal < 0) {
			return null;
		}
		return decimalToBinary2(decimal);
	}

	/**
	 * Helper method for decimalToBinary, converts a decimal integer to a binary string by recursively test if it is a power of 2
	 * @param decimal - the decimal number to be converted
	 * @return the binary string equal in value to the decimal number passed as the parameter
	 */
	private static String decimalToBinary2(int decimal) {
		// base case - 0 or 1
		if (decimal == 0) {
			return "0b0";
		}
		if (decimal == 1) {
			return "0b1";
		}
		// other cases - recursive call
		if (decimal % 2 == 0) {
			return decimalToBinary(decimal / 2) + "0";
		} else {
			return decimalToBinary((decimal - 1) / 2) + "1";
		}
	}

	/**
	 * Converts binary strings to hexadecimal strings. More specifically given a valid string representing a binary 
	 * number returns the string representing the hexadecimal number with the same value.
	 * @param binary - the binary string to be converted
	 * @return the hexadecimal string equal in value to the binary string passed as the parameter 
	 * or null if the binary string is not valid
	 */
	public static String binaryToHex(String binary) {
		// check if it's a valid string
		if (!checkBinary(binary)) {
			return null;
		}
		return binaryToHex2(binary);
	}

	/**
	 * Helper method for binaryToHex. It converts a binary string to the hexadecimal string of the equal value by first padding
	 * the hexadecimal string with 0s, and then recursively converting the last four characters to one character of the 
	 * hexadecimal string output
	 * @param binary - the binary string to be converted
	 * @return the hexadecimal string equal in value to the binary string passed as the parameter
	 */
	private static String binaryToHex2(String binary) {
		// pad the binary sequence with zeros
		// now the binary sequence length is in the form of (4n+2)
		if ((binary.length() - 2) % 4 != 0 && binary.length() > 2) {
			String repeated = "";
			for (int i = 0; i < 4 - (binary.length() - 2) % 4; i++) {
				repeated += "0";
			}
			binary = "0x" + repeated + binary.substring(2);
		}
		// base cases (when n=0)
		if (binary.length() == 2) {
			return "0x";
		}
		// else (when n>0)
		return binaryToHex2(binary.substring(0, binary.length() - 4))
				+ binaryToHexSimple(binary.substring(binary.length() - 4, binary.length()));
	}

	/**
	 * Helper method for binaryToHex2, looks up the corresponding hexadecimal value with an binary string input of length 4
	 * @param binary - the length 4 binary string to be converted
	 * @return the corresponding hexadecimal value as a string
	 */
	private static String binaryToHexSimple(String binary) {
		if (binary.equals("0000"))
			return "0";
		if (binary.equals("0001"))
			return "1";
		if (binary.equals("0010"))
			return "2";
		if (binary.equals("0011"))
			return "3";
		if (binary.equals("0100"))
			return "4";
		if (binary.equals("0101"))
			return "5";
		if (binary.equals("0110"))
			return "6";
		if (binary.equals("0111"))
			return "7";
		if (binary.equals("1000"))
			return "8";
		if (binary.equals("1001"))
			return "9";
		if (binary.equals("1010"))
			return "A";
		if (binary.equals("1011"))
			return "B";
		if (binary.equals("1100"))
			return "C";
		if (binary.equals("1101"))
			return "D";
		if (binary.equals("1110"))
			return "E";
		else
			return "F";
	}

	/**
	 * Converts hexadecimal strings to binary strings. More specifically given a valid string representing a hexadecimal 
	 * number returns the string representing the binary number with the same value.
	 * @param hex - the hexadecimal string to be converted
	 * @return the binary string equal in value to the hexadecimal string passed as the parameter or null if the 
	 * hexadecimal string is not valid
	 */
	public static String hexToBinary(String hex) {
		// check if string is valid
		if (!checkHex(hex)) {
			return null;
		}
		// if the hex string is 8 characters, remove the first 0 in binary string output
		// for it to be valid
		if (hex.length() == 10) {
			String hex2 = hexToBinary2(hex);
			return "0x" + hex2.substring(3);
		}
		return hexToBinary2(hex);
	}

	/**
	 * Helper method for hexToBinary, converts a hexadecimal string to a corresponding binary string recursively by 
	 * converting each character in the hexadecimal string to a length-4 binary string
	 * @param hex - the hexadecimal string to be converted
	 * @return the binary string equal in value to the hexadecimal string passed as the parameter
	 */
	private static String hexToBinary2(String hex) {
		// base case
		if (hex.equals("0x")) {
			return "0b";
		}
		// recursive call
		return hexToBinary2(hex.substring(0, hex.length() - 1)) + hexToBinarySimple(hex.charAt(hex.length() - 1));
	}

	/**
	 * Helper method for hexToBinary2, converts a character in the hexadecimal string to a length-4 binary string by looking
	 * up each character
	 * @param hex - the character in the hexadecimal string to be converted
	 * @return the length-4 binary string equal in value to the hexadecimal character passed as parameter
	 */
	private static String hexToBinarySimple(char hex) {
		if (hex == '0')
			return "0000";
		if (hex == '1')
			return "0001";
		if (hex == '2')
			return "0010";
		if (hex == '3')
			return "0011";
		if (hex == '4')
			return "0100";
		if (hex == '5')
			return "0101";
		if (hex == '6')
			return "0110";
		if (hex == '7')
			return "0111";
		if (hex == '8')
			return "1000";
		if (hex == '9')
			return "1001";
		if (hex == 'A')
			return "1010";
		if (hex == 'B')
			return "1011";
		if (hex == 'C')
			return "1100";
		if (hex == 'D')
			return "1101";
		if (hex == 'E')
			return "1110";
		else
			return "1111";
	}

	/**
	 * Helper method to check if a binary String is valid. i.e. It checks if it starts with "0b", only contains "0" and "1", and is 
	 * a sequence of 1 to 31 binary characters
	 * @param binary - the binary string to be checked
	 * @return true if the binary string is valid, false otherwise
	 */
	private static boolean checkBinary(String binary) {
		// check if length is good
		if (binary.length() <= 2 || binary.length() > 33) {
			return false;
		} else {
			// check if it starts with "0b"
			if (!binary.substring(0, 2).equals("0b")) {
				return false;
			}
			// check if everything is 0 or 1
			for (int i = 2; i < binary.length(); i++) {
				if (binary.charAt(i) != '0' && binary.charAt(i) != '1') {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Helper method to check if the hexadecimal string is valid. i.e. it checks if the string starts with "0x", contains only 0-9 
	 * or upper case A-F, has 1 to 8 hexadecimal characters.
	 * @param hex - the hexadecimal string to be checked
	 * @return true if the hexadecimal string is valid, false otherwise
	 */
	private static boolean checkHex(String hex) {
		// check if length is good
		if (hex.length() <= 2 || hex.length() > 10) {
			return false;
		} else {
			// check if it starts with "0b"
			if (!hex.substring(0, 2).equals("0x")) {
				return false;
			}
			// check if everything is 0 or 1
			for (int i = 2; i < hex.length(); i++) {
				if (hex.charAt(i) != '0' && hex.charAt(i) != '1' && hex.charAt(i) != '2' && hex.charAt(i) != '3'
						&& hex.charAt(i) != '4' && hex.charAt(i) != '5' && hex.charAt(i) != '6' && hex.charAt(i) != '7'
						&& hex.charAt(i) != '8' && hex.charAt(i) != '9' && hex.charAt(i) != 'A' && hex.charAt(i) != 'B'
						&& hex.charAt(i) != 'C' && hex.charAt(i) != 'D' && hex.charAt(i) != 'E') {
					return false;
				}
			}
		}
		return true;
	}

}
