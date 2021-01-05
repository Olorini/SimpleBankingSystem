package com.github.olorini.bank.utils;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class CardUtils {

	public static String generateNumberLine(int size) {
		String chars = "0123456789";
		return new Random()
				.ints(size, 0, chars.length())
				.mapToObj(i -> "" + chars.charAt(i))
				.collect(Collectors.joining());
	}

	public static int getCheckSum(String truncatedCardNumber) {
		int luhnSum = getLuhnSum(truncatedCardNumber);
		return (luhnSum % 10 == 0) ? 0 : 10 - luhnSum % 10;
	}

	public static boolean isValidCardNumber(String cardNumber) {
		String truncatedCardNumber = cardNumber.substring(0, cardNumber.length() - 1);
		int checkSum = Integer.parseInt(cardNumber.substring(cardNumber.length() - 1));
		int luhnSum = getLuhnSum(truncatedCardNumber);
		return ((checkSum + luhnSum) % 10 == 0);
	}

	private static int getLuhnSum(String truncatedCardNumber) {
		int[] digits = Arrays
				.stream(truncatedCardNumber.split(""))
				.mapToInt(Integer::parseInt)
				.toArray();

		for (int i = 0; i < digits.length; i++) {
			if (i % 2 == 0) {
				digits[i] *= 2;
			}
			if (digits[i] > 9) {
				digits[i] -= 9;
			}
		}
		return Arrays.stream(digits).sum();
	}
}
