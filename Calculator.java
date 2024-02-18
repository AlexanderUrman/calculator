
import java.util.Scanner;
import java.util.HashMap;

public class Calculator {

    private static final HashMap<Character, Integer> romanToArabic = new HashMap<>();
    static {
        romanToArabic.put('I', 1);
        romanToArabic.put('V', 5);
        romanToArabic.put('X', 10);
    }

    private static int romanToArabic(String roman) {
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            if (i > 0 && romanToArabic.get(roman.charAt(i)) > romanToArabic.get(roman.charAt(i - 1))) {
                result += romanToArabic.get(roman.charAt(i)) - 2 * romanToArabic.get(roman.charAt(i - 1));
            } else {
                result += romanToArabic.get(roman.charAt(i));
            }
        }
        return result;
    }

    private static String arabicToRoman(int number) {
        if (number <= 0) {
            return "Результат не может быть представлен в римских цифрах";
        }

        String[] romanNumerals = {"X", "IX", "V", "IV", "I"};
        int[] romanValues = {10, 9, 5, 4, 1};
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < romanValues.length; i++) {
            while (number >= romanValues[i]) {
                number -= romanValues[i];
                roman.append(romanNumerals[i]);
            }
        }

        return roman.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение: ");
        String firstInput = scanner.next().toUpperCase();
        char operation = scanner.next().charAt(0);
        String secondInput = scanner.next().toUpperCase();

        boolean isRoman = firstInput.matches("[IVX]+") && secondInput.matches("[IVX]+");

        int firstNumber, secondNumber;

        if (isRoman) {
            firstNumber = romanToArabic(firstInput);
            secondNumber = romanToArabic(secondInput);
        } else {
            firstNumber = Integer.parseInt(firstInput);
            secondNumber = Integer.parseInt(secondInput);
        }

        if (firstNumber < 1 || firstNumber > 10 || secondNumber < 1 || secondNumber > 10) {
            System.out.println("Одно из чисел вне допустимого диапазона (1-10).");
            return;
        }

        int result = 0;
        boolean isValidOperation = true;

        switch (operation) {
            case '+':
                result = firstNumber + secondNumber;
                break;
            case '-':
                result = firstNumber - secondNumber;
                break;
            case '*':
                result = firstNumber * secondNumber;
                break;
            case '/':
                if(secondNumber == 0) {
                    System.out.println("На ноль делить нельзя.");
                    isValidOperation = false;
                } else {
                    result = firstNumber / secondNumber;
                }
                break;
            default:
                System.out.println("Неподдерживаемая операция.");
                isValidOperation = false;
                break;
        }

        if (isValidOperation) {
            if (isRoman) {
                System.out.println("Результат: " + arabicToRoman(result));
            } else {
                System.out.println("Результат: " + result);
            }
        }
    }
}