import java.util.Arrays;
import java.util.Scanner;

public class Calculator {


    static String[] romanNums = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    static int[] arabicNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        try {
            String[] parts = line.split(" ");
            if (parts.length != 3)
                throw new IllegalArgumentException("Введенная строка не соответствует формату 'число операция число'");

            boolean isRoman = isRoman(parts[0]) && isRoman(parts[2]);

            if (isRoman(parts[0]) != isRoman(parts[2]))
                throw new IllegalArgumentException("Числа разных типов");

            int num1 = isRoman ? romanToArabic(parts[0]) : Integer.parseInt(parts[0]);
            int num2 = isRoman ? romanToArabic(parts[2]) : Integer.parseInt(parts[2]);

            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10)
                throw new IllegalArgumentException("Числа вне диапазона от 1 до 10");

            switch (parts[1]) {
                case "+":
                    printResult(num1 + num2, isRoman);
                    break;
                case "-":
                    printResult(num1 - num2, isRoman);
                    break;
                case "*":
                    printResult(num1 * num2, isRoman);
                    break;
                case "/":
                    printResult(num1 / num2, isRoman);
                    break;
                default:
                    throw new IllegalArgumentException("Некорректный знак операции");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isRoman(String str) {
        return Arrays.asList(romanNums).contains(str);
    }

    private static int romanToArabic(String str) {
        return Arrays.asList(romanNums).indexOf(str) + 1;
    }

    private static void printResult(int res, boolean isRoman) {
        if (!isRoman || res > 0)
            System.out.println(isRoman ? arabicToRoman(res) : res);
        else
            throw new IllegalArgumentException("Результат не может быть меньше 1 для римских чисел");
    }

    private static String arabicToRoman(int number) {
        String[] romanCharacters = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] arabicNumbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuilder result = new StringBuilder();

        int i = 0;
        while (number > 0 && i < arabicNumbers.length) {
            if (number >= arabicNumbers[i]) {
                number -= arabicNumbers[i];
                result.append(romanCharacters[i]);
            } else {
                i++;
            }
        }

        return result.toString();
    }
}