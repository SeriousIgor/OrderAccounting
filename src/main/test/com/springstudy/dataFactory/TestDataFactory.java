package com.springstudy.dataFactory;

import com.springstudy.enums.OrderStatus;
import com.springstudy.enums.PaymentMethod;
import com.springstudy.models.Client;
import com.springstudy.models.Order;
import com.springstudy.models.User;
import org.jetbrains.annotations.TestOnly;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class TestDataFactory {
    @TestOnly
    public static Client createClient(String name) {
        return new Client(
                "test_" + name,
                "last_" + name,
                String.valueOf((int) (Math.random() * 1000)),
                "mytest" + name + "@mail.com");
    }

    @TestOnly
    public static Collection<Client> createClientList(int numberOfClients) {
        Collection<Client> clients = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            clients.add(
                    createClient(String.valueOf(i))
            );
        }
        return clients;
    }

    @TestOnly
    public static User createUser(String name) {
        return new User(
                name + "mail.com",
                name + "-test",
                "first " + name,
                "last " + name,
                "123Test" + name
        );
    }

    @TestOnly
    public static Order createOrder(String name, Integer clientId, Integer userId) {
        Client client = createClient("test_client");
        client.setId(clientId);
        User user = createUser("test_user");
        user.setId(userId);
        Order order = new Order(
                name,
                name + " description",
                OrderStatus.NEW,
                BigDecimal.valueOf(120312),
                PaymentMethod.CASH,
                LocalDateTime.now(),
                user,
                client
        );
        order.setIsDeleted(false);
        return order;
    }

    @TestOnly
    public static Collection<Order> createOrderList(Integer numberOfOrders, Integer clientId, Integer userid) {
        Collection<Order> orders = new ArrayList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            orders.add(createOrder("test_order", clientId, userid));
        }
        return orders;
    }
}
