package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;
import lotto.util.ValidationUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        try {
            int purchaseAmount = inputView.getPurchaseAmount();

            List<Lotto> purchasedLottos = issueLottos(purchaseAmount);

            outputView.printPurchasedLottos(purchasedLottos);

            // TODO: 3단계 (당첨 번호 입력 및 결과 계산)
            // TODO: 4단계 (당첨 결과 계산 및 출력)


        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
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


}
