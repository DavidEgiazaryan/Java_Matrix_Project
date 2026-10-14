//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите матрицу A:");
        ComplexMatrix a = readMatrix(scanner);

        System.out.println("Введите матрицу B:");
        ComplexMatrix b = readMatrix(scanner);

        ComplexMatrix result = null;
        int choice;
        do {
            System.out.println("\n1. Сложить A и B");
            System.out.println("2. Умножить A на B");
            System.out.println("3. Разделить A на B");
            System.out.println("4. Транспонировать A");
            System.out.println("5. Транспонировать B");
            System.out.println("6. Определитель A");
            System.out.println("7. Определитель B");
            System.out.println("8. Ввести новую матрицу A");
            System.out.println("9. Ввести новую матрицу B");
            System.out.println("10. Показать A и B");
            System.out.println("11. Использовать результат как матрицу A");
            System.out.println("0. Выход");
            System.out.print("Выберите операцию: ");
            choice = readInt(scanner);

            try {
                switch (choice) {
                    case 1:
                        System.out.println("A + B:");
                        result = a.add(b);
                        System.out.println(result);
                        break;
                    case 2:
                        System.out.println("A * B:");
                        result = a.multiply(b);
                        System.out.println(result);
                        break;
                    case 3:
                        System.out.println("A / B:");
                        result = a.divide(b);
                        System.out.println(result);
                        break;
                    case 4:
                        System.out.println("Транспонированная A:");
                        result = a.transpose();
                        System.out.println(result);
                        break;
                    case 5:
                        System.out.println("Транспонированная B:");
                        result = b.transpose();
                        System.out.println(result);
                        break;
                    case 6:
                        System.out.println("Определитель A: " + a.determinant());
                        break;
                    case 7:
                        System.out.println("Определитель B: " + b.determinant());
                        break;
                    case 8:
                        System.out.println("Введите матрицу A:");
                        a = readMatrix(scanner);
                        break;
                    case 9:
                        System.out.println("Введите матрицу B:");
                        b = readMatrix(scanner);
                        break;
                    case 10:
                        System.out.println("Матрица A:");
                        System.out.println(a);
                        System.out.println("Матрица B:");
                        System.out.println(b);
                        break;
                    case 11:
                        if (result == null) {
                            System.out.println("Сначала выполните операцию с матрицами.");
                        } else {
                            a = result;
                            System.out.println("Результат сохранён как матрица A.");
                        }
                        break;
                    case 0:
                        System.out.println("Программа завершена.");
                        break;
                    default:
                        System.out.println("Такого пункта меню нет.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        } while (choice != 0);

        scanner.close();
    }

    private static ComplexMatrix readMatrix(Scanner scanner) {
        System.out.print("Количество строк: ");
        int rows = readInt(scanner);
        while (rows <= 0) {
            System.out.print("Введите положительное количество строк: ");
            rows = readInt(scanner);
        }

        System.out.print("Количество столбцов: ");
        int cols = readInt(scanner);
        while (cols <= 0) {
            System.out.print("Введите положительное количество столбцов: ");
            cols = readInt(scanner);
        }

        ComplexMatrix matrix = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("Элемент [" + (i + 1) + "][" + (j + 1) + "]:");

                System.out.print("Действительная часть: ");
                double real = readDouble(scanner);

                System.out.print("Мнимая часть: ");
                double imaginary = readDouble(scanner);

                matrix.set(i, j, new Complex_number(real, imaginary));
            }
        }

        return matrix;
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Введите целое число: ");
                scanner.next();
            }
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Введите число: ");
                scanner.next();
            }
        }
    }
}
