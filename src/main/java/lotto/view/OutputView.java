package lotto.view;

import lotto.Lotto;
import lotto.domain.Rank; // Rank Enum 임포트

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String WINNING_STATISTICS_HEADER = "\n당첨 통계\n---";
    private static final String RATE_OF_RETURN_FORMAT = "총 수익률은 %s%%입니다.";


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
    }

    private void printLottoNumbers(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }


    public void printWinningStatistics(Map<Rank, Integer> winningResults) {
        System.out.println(WINNING_STATISTICS_HEADER);

        for (Rank rank : Rank.getWinningRanks()) {
            String description = rank.getDescription();
            int count = winningResults.getOrDefault(rank, 0);
            System.out.printf("%s - %d개\n", description, count);
        }
    }


    public void printRateOfReturn(double rateOfReturn) {
        DecimalFormat df = new DecimalFormat("#,##0.0");
        System.out.printf(RATE_OF_RETURN_FORMAT, df.format(rateOfReturn));
        System.out.println(); // 마지막 개행
    }
}