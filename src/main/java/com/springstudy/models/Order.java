package com.springstudy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springstudy.enums.OrderStatus;
import com.springstudy.enums.PaymentMethod;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Orders")
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "orders_sequence",
            sequenceName = "orders_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_sequence"
    )
    @Column(
            name = "order_id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "order_name",
            nullable = false
    )
    private String name;

    @Column(
            name = "description"
    )
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "order_status",
            nullable = false
    )
    private OrderStatus orderStatus;

    @Column(
            name = "order_price"
    )
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "payment_method",
            nullable = false
    )
    private PaymentMethod paymentMethod;

    @Column(
            name = "order_date",
            nullable = false
    )
    private LocalDateTime orderDate;

    @Column(
            name = "is_deleted",
            nullable = false,
            columnDefinition = "Boolean default 'false'"
    )
    private Boolean isDeleted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(referencedColumnName = "client_id", nullable = false)
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "service_enrolled",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services = new HashSet<>();

    public Order() {

    }

    public Order(String name, String description, OrderStatus orderStatus, BigDecimal price, PaymentMethod paymentMethod, LocalDateTime orderDate, User user, Client client) {
        this.name = name;
        this.description = description;
        this.orderStatus = orderStatus;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.user = user;
        this.client = client;
    }

    public Order(String name, OrderStatus orderStatus, BigDecimal price, PaymentMethod paymentMethod, LocalDateTime orderDate, User user, Client client, Set<Service> services) {
        this.name = name;
        this.orderStatus = orderStatus;
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.user = user;
        this.client = client;
        this.services = services;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        this.services.add(service);
    }

    public void removeService(Service service) {
        this.services.remove(service);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", orderStatus=" + orderStatus +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", isDeleted=" + isDeleted +
                ", user=" + user +
                ", client=" + client +
                ", enrolledServices=" + services +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
