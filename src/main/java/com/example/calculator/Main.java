package com.example.calculator;

import java.io.IOException;
import java.util.Scanner;

import static com.example.calculator.calc.calc;

class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите выражение состоящее из целых чисел в диапазоне от 1 до 10: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }
}
