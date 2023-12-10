package org.leombprojects.order.services;

import org.leombprojects.models.swagger.api.CaloriesSummaryDto;
import org.leombprojects.models.swagger.api.OrderDto;

public interface OrderService {
    CaloriesSummaryDto createOrder(OrderDto orderDto);
    CaloriesSummaryDto updateOrder(OrderDto orderDto);

}
