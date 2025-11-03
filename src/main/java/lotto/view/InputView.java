package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.Lotto;
import lotto.util.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        String trimmedInput = validateInputNotNull(input);
        validatePurchaseAmount(trimmedInput);
        return Integer.parseInt(trimmedInput);
    }

    private void validatePurchaseAmount(String input) {
        ValidationUtils.validateIsNumeric(input);
        int amount = Integer.parseInt(input);
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.");
        }
        if (amount % ValidationUtils.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 "
                    + ValidationUtils.LOTTO_PRICE
                    + "원 단위여야 합니다.");
        }
    }

    public Lotto getWinningLotto() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        String trimmedInput = validateInputNotNull(input);
        List<Integer> numbers = parseNumbers(trimmedInput);
        return new Lotto(numbers); // Lotto 생성자에서 번호 개수/중복/범위 검증
    }

    private List<Integer> parseNumbers(String input) {
        String[] parts = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String part : parts) {
            String trimmedPart = part.trim();
            ValidationUtils.validateIsNumeric(trimmedPart);
            numbers.add(Integer.parseInt(trimmedPart));
        }
        return numbers;
    }

    public int getBonusNumber(Lotto winningLotto) {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        String trimmedInput = validateInputNotNull(input);
        ValidationUtils.validateIsNumeric(trimmedInput);
        int bonusNumber = Integer.parseInt(trimmedInput);
        validateBonusNumber(bonusNumber, winningLotto);
        return bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber, Lotto winningLotto) {
        ValidationUtils.validateNumberRange(bonusNumber);
        if (winningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨 번호와 중복됩니다.");
        }
    }

    private String validateInputNotNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("[ERROR] 입력을 읽을 수 없습니다.");
        }
        return input.trim();
    }
}