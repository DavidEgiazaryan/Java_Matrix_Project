//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Complex_number a = new Complex_number(2, 3);
        Complex_number b = new Complex_number(1, -4);

        System.out.println("Первое число: " + a);
        System.out.println("Второе число: " + b);
        System.out.println("Сумма: " + a.add(b));
        System.out.println("Разность: " + a.subtract(b));
        System.out.println("Произведение: " + a.multiply(b));
        System.out.println("Частное: " + a.divide(b));
    }
}