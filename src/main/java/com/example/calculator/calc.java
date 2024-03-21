package com.example.calculator;

import java.io.IOException;

class calc {
    public static String calc(String input) throws IOException {
        Converter converter = new Converter();
        String ans = new String();
        char[] charInput = input.toCharArray();
        int first;
        int second;
        char Sign = ' ';
        int trueSign = 0;
        String[] comp = input.split(" ");

        if (comp.length > 3)
            throw new IOException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (comp.length <= 1) throw new IOException("Строка не является математической операцией");

        for (char c : charInput) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                Sign = c;
                trueSign++;
            }
        }

        if (Sign == '+') {
            comp = input.split(" \\+ ");
        } else if (Sign == '-') {
            comp = input.split(" - ");
        } else if (Sign == '*') {
            comp = input.split(" \\* ");
        } else if (Sign == '/') {
            comp = input.split(" / ");
        }

        if (converter.roman(comp[0]) && converter.roman(comp[1])) {
            first = Integer.parseInt(comp[0]);
            second = Integer.parseInt(comp[1]);
            ans = Integer.toString(converter.calcInArabic(first, second, Sign));
        } else if (converter.roman(comp[0]) == converter.roman(comp[1])) {
            first = converter.romanToArabic(comp[0]);
            second = converter.romanToArabic(comp[1]);
            int result = converter.calcInArabic(first, second, Sign);
            if (result > 0)
                return converter.arabicToRoman(result);
            else
                throw new ArithmeticException("В римской системе нет отрицательных чисел");
        } else {
            throw new IOException("Используются одновременно разные системы счисления");
        }
        return ans;
    }
}