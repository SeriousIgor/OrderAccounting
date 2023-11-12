package com.springstudy.controllers;

import com.springstudy.models.Card;
import com.springstudy.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/card/")
public class CardController extends ModelController<Card> {
    @Autowired
    public CardController(CardService cardService) {
        super(cardService);
    }
}
