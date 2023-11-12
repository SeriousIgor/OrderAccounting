package com.springstudy.models;

import com.springstudy.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity(name = "Ordering")
@Table(name = "ordering")
public class Ordering {
    @Id
    @SequenceGenerator(
            name = "ordering_sequence",
            sequenceName = "ordering_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ordering_sequence"
    )
    @Column(
            name = "ordering_id",
            updatable = false
    )
    private Integer id;
    @Column(
//            columnDefinition = "VARCHAR(255) DEFAULT concat('№-', to_char(ordering_id, 'FM0000'))",
            name = "order_name"
    )
    private String name;

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

    @Column(
            name = "is_deleted",
            nullable = false,
            columnDefinition = "Boolean default 'false'"
    )
    private Boolean isDeleted;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "user_id", nullable = false)
//    private User user;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "client_id", nullable = false)
//    private Client client;
//
//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(
//            name = "service_enrolled",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "service_id")
//    )
//    private Set<Service> enrolledServices = new HashSet<>();
//
    public Ordering() {

    }
//
//    public Order(String name, orderingtatus orderingtatus, BigDecimal price, Boolean isDeleted, User user, Client client, Set<Service> services) {
//        this.name = name;
//        this.orderingtatus = orderingtatus;
//        this.price = price;
//        this.isDeleted = isDeleted;
//        this.user = user;
//        this.client = client;
//        this.enrolledServices = services;
//    }
//
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

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
//
//    public User getUser() {
//        return this.user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Client getClient() {
//        return this.client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }
//
//    public Set<Service> getEnrolledServices() {
//        return enrolledServices;
//    }
//
//    public void enrollService(Service service) {
//        this.enrolledServices.add(service);
//    }
}
