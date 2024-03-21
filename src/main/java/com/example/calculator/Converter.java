package com.example.calculator;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;

class Converter {
    private HashMap<Character, Integer> romanNum = new HashMap<>();

    public Converter() {
        romanNum.put('I', 1);
        romanNum.put('V', 5);
        romanNum.put('X', 10);
        romanNum.put('L', 50);
        romanNum.put('C', 100);
        romanNum.put('D', 500);
        romanNum.put('M', 1000);
    }

    public boolean number(int num) {
        return num >= 1 && num <= 10;
    }

    public int calcInArabic(int first, int second, char Sign) throws IOException {
        int ans = -1;
        if (number(first) && number(second)) {
            switch (Sign) {
                case ('+') -> ans = first + second;
                case ('-') -> ans = first - second;
                case ('*') -> ans = first * second;
                case ('/') -> {
                    try {
                        ans = first / second;
                    } catch (ArithmeticException | InputMismatchException e) {
                        System.err.println("Exception : " + e);
                    }
                }
                default -> throw new IllegalArgumentException("Неверный знак операции");
            }
        } else {
            throw new IOException("Числа не находятся в диапазоне от 1 до 10");
        }
        return ans;
    }

    public boolean roman(String number) {
        return !romanNum.containsKey(number.charAt(0));
    }

    public int romanToArabic(String roman) {
        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = romanNum.get(roman.charAt(i));
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }
        return result;
    }

    public String arabicToRoman(int arabic) {
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < arabicValues.length; i++) {
            while (arabic >= arabicValues[i]) {
                roman.append(romanSymbols[i]);
                arabic -= arabicValues[i];
            }
        }
        return roman.toString();
    }
}
