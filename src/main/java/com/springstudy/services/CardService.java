package com.springstudy.services;

import com.springstudy.models.Card;
import com.springstudy.repositories.CardRepository;
import com.springstudy.repositories.ClientRepository;
import com.springstudy.utils.ServiceUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service("CardService")
public class CardService implements iModelService<Card> {

    private final CardRepository cardRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public CardService(CardRepository cardRepository, ClientRepository clientRepository) {
        this.cardRepository = cardRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Card> getRecord(Integer recordId) throws NotFoundException {
        return this.cardRepository.findById(recordId);
    }

    @Override
    public Collection<Optional<Card>> getRecords(Integer pageNumber, Integer pageSize, String... filters) throws NotFoundException {
        return this.cardRepository.findAllByIsDeletedFalse(
                ServiceUtil.getPagination(pageNumber, pageSize)
        );
    }

    @Override
    public Collection<Optional<Card>> getDeletedRecords(Integer pageNumber, Integer pageSize) throws NotFoundException {
        return this.cardRepository.findAllByIsDeletedTrue(
                ServiceUtil.getPagination(pageNumber, pageSize)
        );
    }

    public Collection<Optional<Card>> getRecordsByClientId(Integer clientId) {
        return this.cardRepository.findAllByClient_Id(clientId);
    }

    @Override
    public Optional<Card> createRecord(String newRecord) throws Exception {
        Card card = ServiceUtil.getParserRecordFromJson(newRecord, Card.class);
        return Optional.of(this.cardRepository.save(card));
    }

    @Override
    public Optional<Card> updateRecord(String updatedRecord) throws Exception {
        return Optional.of(
                this.cardRepository.save(
                        ServiceUtil.getParserRecordFromJson(updatedRecord, Card.class)
                )
        );
    }

    @Override
    public void deleteRecord(Integer recordId, Boolean isSoftDelete) throws Exception {
        if (isSoftDelete != null && isSoftDelete) {
            Optional<Card> cardOptional = Optional.of(this.cardRepository.getReferenceById(recordId));
            Card card = cardOptional.get();
            card.setIsDeleted(true);
            this.cardRepository.save(card);
        } else {
            this.cardRepository.deleteById(recordId);
        }
    }
}
