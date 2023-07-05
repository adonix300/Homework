package Calculator;

import java.util.function.*;

public class Calculator {
    static Supplier<Calculator> instance = Calculator::new;
    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> divide = (x, y) -> {
        if (y == 0) {
            throw new ArithmeticException("Делитель равен 0");
        }
        return x / y;
    };
    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;
    Predicate<Integer> isPositive = x -> x > 0;
    Consumer<Integer> println = System.out::println;

    public static void main(String[] args) {
        Calculator calculator = Calculator.instance.get();
        int a = calculator.plus.apply(1, 2);
        int b = calculator.minus.apply(1, 1); // b == 0, поэтому выбрасывается исключение ArithmeticException: / by zero
        try { // можем воспользоваться исключением или же проверкой b !=0 через if else
            int c = calculator.divide.apply(a, b);
            calculator.println.accept(c);
        } catch (ArithmeticException e) {
            System.out.println("Делитель равен 0");
        }
    }
}
