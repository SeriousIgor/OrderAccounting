package com.springstudy.listeners;

import com.springstudy.enums.CardType;
import com.springstudy.models.Card;

public class CardListener extends AbstractModelListener<Card> {
    @Override
    public void beforePersist(Card card) {
        if (card.getCardType().equals(CardType.DISCOUNT_PERCENTAGE)) {
            card.setBonusAmount(null);
        } else if (card.getCardType().equals(CardType.BONUS_ACCUMULATIVE)) {
            card.setDiscountPercentage(null);
        }
    }
}
