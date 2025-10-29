package lotto.view;

import lotto.Lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String WINNING_STATISTICS_HEADER = "\n당첨 통계\n---";
    private static final String RATE_OF_RETURN_FORMAT = "총 수익률은 %s%%입니다.";

    // 1단계: 입력 안내 메시지
    public void printPurchaseAmountMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printPurchaseCount(int count) {
        System.out.println("\n" + count + "개를 구매했습니다.");
    }

    public void printPurchasedLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            printLottoNumbers(lotto);
        }
        System.out.println(); // 개행 추가
    }

    private void printLottoNumbers(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }






}
