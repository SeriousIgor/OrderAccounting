package com.springstudy.controllers;

import com.springstudy.models.Card;
import com.springstudy.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/card")
public class CardController extends ModelController<Card> {
    @Autowired
    public CardController(CardService cardService) {
        super(cardService);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Collection<Optional<Card>>> getCardsByClientId(
            @PathVariable Integer clientId
    ) {
        CardService cardService = (CardService) this.service;
        return ResponseEntity.ok(cardService.getRecordsByClientId(clientId));
    }
}
