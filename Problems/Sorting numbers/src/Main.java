import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void sort(int[] numbers) {
        if (numbers.length <= 1) {
            return;
        }
        int index = 0;
        int temp = 0;
        do {
            if (numbers[index + 1] < numbers[index]) {
                temp = numbers[index + 1];
                numbers[index + 1] = numbers[index];
                numbers[index] = temp;
                index = 0;
            } else {
                index++;
            }
        } while (index < numbers.length - 1);
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String[] values = scanner.nextLine().split("\\s+");
        int[] numbers = Arrays.stream(values)
                .mapToInt(Integer::parseInt)
                .toArray();
        sort(numbers);
        Arrays.stream(numbers).forEach(e -> System.out.print(e + " "));
    }
}