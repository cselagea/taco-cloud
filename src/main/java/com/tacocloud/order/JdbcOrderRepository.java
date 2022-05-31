package com.tacocloud.order;

import com.tacocloud.taco.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final SimpleJdbcInsert orderInsert;
    private final SimpleJdbcInsert orderTacoInsert;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("orders_tacos");
    }

    @Override
    public Order save(Order order) {
        final Long orderId = saveOrderDetails(order);
        order.setId(orderId);

        for (Taco taco : order.getTacos()) {
            saveTacoToOrder(taco, orderId);
        }

        return order;
    }

    private Long saveOrderDetails(Order order) {
        Map<String, ?> values = buildOrderDataMap(order);
        return orderInsert.executeAndReturnKey(values).longValue();
    }

    private Map<String, ?> buildOrderDataMap(Order order) {
        return Map.ofEntries(
                Map.entry("delivery_name", order.getDeliveryName()),
                Map.entry("delivery_street", order.getDeliveryStreet()),
                Map.entry("delivery_city", order.getDeliveryCity()),
                Map.entry("delivery_state", order.getDeliveryState()),
                Map.entry("delivery_zip", order.getDeliveryZipCode()),
                Map.entry("cc_number", order.getCreditCardNumber()),
                Map.entry("cc_expiry", order.getCreditCardExpiration()),
                Map.entry("cc_verify", order.getCreditCardVerificationValue()),
                Map.entry("placed_at", order.getPlacedAt())
        );
    }

    private void saveTacoToOrder(Taco taco, Long orderId) {
        Map<String, Long> values = Map.ofEntries(
                Map.entry("order_id", orderId),
                Map.entry("taco_id", taco.getId())
        );
        orderTacoInsert.execute(values);
    }

}
