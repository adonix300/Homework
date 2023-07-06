package Numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> positiveNumbers = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) > 0) {
                positiveNumbers.add(numbers.get(i));
            }
        }

        for (int i = 0; i < positiveNumbers.size(); i++) {
            if (positiveNumbers.get(i) % 2 != 0) {
                positiveNumbers.remove(positiveNumbers.get(i));
                i--;
            }
        }

        Collections.sort(positiveNumbers);

        positiveNumbers.forEach(System.out::println);

    }
}
