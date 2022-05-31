package com.tacocloud.order;

import com.tacocloud.taco.Taco;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") OrderDto orderDto, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        Order order = mapToOrder(orderDto);
        orderService.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    private Order mapToOrder(OrderDto orderDto) {
        List<Taco> tacos =
                orderDto.getTacoIds()
                        .stream()
                        .map(tacoId -> {
                            Taco taco = new Taco();
                            taco.setId(tacoId);
                            return taco;
                        })
                        .toList();

        Order order = new Order();
        order.setDeliveryName(orderDto.getName());
        order.setDeliveryStreet(orderDto.getStreet());
        order.setDeliveryCity(orderDto.getCity());
        order.setDeliveryState(orderDto.getState());
        order.setDeliveryZipCode(orderDto.getZip());
        order.setCreditCardNumber(orderDto.getCcNumber());
        order.setCreditCardExpiration(orderDto.getCcExpiration());
        order.setCreditCardVerificationValue(orderDto.getCcCVV());
        order.setTacos(tacos);
        return order;
    }

}
