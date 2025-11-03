package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.domain.Rank; // Rank Enum 임포트
import lotto.util.ValidationUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        int purchaseAmount = getValidPurchaseAmount();

        List<Lotto> purchasedLottos = issueLottos(purchaseAmount);
        outputView.printPurchasedLottos(purchasedLottos);

        Lotto winningLotto = getValidWinningLotto();

        int bonusNumber = getValidBonusNumber(winningLotto);

        Map<Rank, Integer> winningResults = calculateResults(purchasedLottos, winningLotto, bonusNumber);
        double rateOfReturn = calculateRateOfReturn(winningResults, purchaseAmount);

        outputView.printWinningStatistics(winningResults);
        outputView.printRateOfReturn(rateOfReturn);
    }


    private int getValidPurchaseAmount() {
        while (true) {
            try {
                return inputView.getPurchaseAmount();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Lotto getValidWinningLotto() {
        while (true) {
            try {
                return inputView.getWinningLotto();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private int getValidBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                return inputView.getBonusNumber(winningLotto);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }


    private List<Lotto> issueLottos(int purchaseAmount) {
        int count = purchaseAmount / ValidationUtils.LOTTO_PRICE;
        outputView.printPurchaseCount(count);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateSingleLotto());
        }
        return lottos;
    }

    private Lotto generateSingleLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                ValidationUtils.MIN_LOTTO_NUMBER,
                ValidationUtils.MAX_LOTTO_NUMBER,
                6
        );
        return new Lotto(numbers);
    }


    private Map<Rank, Integer> calculateResults(List<Lotto> purchasedLottos, Lotto winningLotto, int bonusNumber) {
        Map<Rank, Integer> results = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            results.put(rank, 0);
        }

        for (Lotto lotto : purchasedLottos) {
            Rank rank = determineRank(lotto, winningLotto, bonusNumber);
            results.put(rank, results.get(rank) + 1);
        }
        return results;
    }

    private Rank determineRank(Lotto userLotto, Lotto winningLotto, int bonusNumber) {
        int matchCount = countMatches(userLotto, winningLotto);
        boolean hasBonus = userLotto.contains(bonusNumber);
        return Rank.valueOf(matchCount, hasBonus);
    }

    private int countMatches(Lotto userLotto, Lotto winningLotto) {
        return (int) userLotto.getNumbers().stream()
                .filter(winningLotto::contains)
                .count();
    }

    private double calculateRateOfReturn(Map<Rank, Integer> winningResults, int purchaseAmount) {
        long totalPrize = 0;
        for (Map.Entry<Rank, Integer> entry : winningResults.entrySet()) {
            totalPrize += (long) entry.getKey().getPrize() * entry.getValue();
        }

        if (purchaseAmount == 0) {
            return 0.0;
        }

        return (double) totalPrize / purchaseAmount * 100.0;
    }
}