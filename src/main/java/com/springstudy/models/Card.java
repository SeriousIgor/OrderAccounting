package com.springstudy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springstudy.enums.CardType;
import com.springstudy.listeners.CardListener;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "card")
@Entity(name = "Card")
@EntityListeners(CardListener.class)
public class Card {
    @Id
    @SequenceGenerator(
            name = "card_sequence",
            sequenceName = "card_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_sequence"
    )
    @Column(
            name = "card_id",
            updatable = false,
            nullable = false
    )
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "card_type",
            updatable = false,
            nullable = false
    )
    private CardType cardType;
    @Column(
            name = "bonus_amount"
    )
    private BigDecimal bonusAmount;
    @Column(
            name = "discount_percentage"
    )
    private BigDecimal discountPercentage;

    @Column(
            name = "is_one_time",
            nullable = false
    )
    private Boolean isOneTime;
    @Column(
            name = "is_deleted",
            columnDefinition="Boolean default 'false'",
            nullable = false
    )
    private Boolean isDeleted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "client_id",
            nullable = false
    )
    Client client;

    public Card() {

    }

    public Card(CardType cardType, BigDecimal bonusAmount, BigDecimal discountPercentage, Boolean isOneTime, Client client, Boolean isDeleted) {
        this.cardType = cardType;
        this.bonusAmount = bonusAmount;
        this.discountPercentage = discountPercentage;
        this.isOneTime = isOneTime;
        this.client = client;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getIsOneTime() {
        return this.isOneTime;
    }

    public void setIsOneTime(Boolean isOneTime) {
        this.isOneTime = isOneTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardType=" + cardType +
                ", bonusAmount=" + bonusAmount +
                ", discountPercentage=" + discountPercentage +
                ", isOneTime=" + isOneTime +
                ", isDeleted=" + isDeleted +
                ", client=" + client +
                '}';
    }
}
