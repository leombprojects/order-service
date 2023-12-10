package org.leombprojects.order.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.leombprojects.models.swagger.api.CaloriesSummaryDto;
import org.leombprojects.models.swagger.api.ErrorDto;
import org.leombprojects.models.swagger.api.OrderDto;
import org.leombprojects.models.swagger.model.OrderApiDelegate;
import org.leombprojects.order.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController implements OrderApiDelegate {

    private final OrderService orderService;

    @Override
    public ResponseEntity<CaloriesSummaryDto> updateOrder(OrderDto orderDto) {
        ResponseEntity responseEntity;

        try {
            CaloriesSummaryDto caloriesSummaryDto = orderService.updateOrder(orderDto);

            responseEntity = ResponseEntity.ok(caloriesSummaryDto);
        }
        catch (IllegalArgumentException e){
            responseEntity = ResponseEntity.badRequest().body(ErrorDto.builder().code("400").description(e.getMessage()).build());
        }
        catch (Exception e){
            responseEntity = ResponseEntity.badRequest().body(ErrorDto.builder().code("500").description("Internal Server Error").build());
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<CaloriesSummaryDto> createNewOrder(OrderDto orderDto) {

        ResponseEntity responseEntity;

        try {
            CaloriesSummaryDto caloriesSummaryDto = orderService.createOrder(orderDto);

            responseEntity = ResponseEntity.ok(caloriesSummaryDto);
        }
        catch (IllegalArgumentException e){
            responseEntity = ResponseEntity.badRequest().body(ErrorDto.builder().code("400").description(e.getMessage()).build());
        }
        catch (Exception e){
            responseEntity = ResponseEntity.badRequest().body(ErrorDto.builder().code("500").description("Internal Server Error").build());
        }

        return responseEntity;
    }
}
