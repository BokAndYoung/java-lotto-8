package lotto.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationUtils {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_PRICE = 1000;

    public static void validateIsNumeric(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효한 숫자를 입력해야 합니다.");
        }
    }

    public static void validateNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 "
                    + MIN_LOTTO_NUMBER
                    + "부터 "
                    + MAX_LOTTO_NUMBER
                    + " 사이의 숫자여야 합니다.");
        }
    }

    // ------------------- Lotto 전용 검증 -------------------
    public static void validateLottoNumbers(List<Integer> numbers) {
        validateCount(numbers);
        validateDuplicates(numbers);
        validateRange(numbers);
    }

    private static void validateCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private static void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateNumberRange(number);
        }
    }
}
