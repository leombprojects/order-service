package org.leombprojects.order.services.impl;

import lombok.RequiredArgsConstructor;
import org.leombprojects.models.swagger.api.CaloriesSummaryDto;
import org.leombprojects.models.swagger.api.EmployeeDto;
import org.leombprojects.models.swagger.api.ItemOutputDto;
import org.leombprojects.models.swagger.api.OrderDto;
import org.leombprojects.order.models.ItemsModel;
import org.leombprojects.order.models.OrdersModel;
import org.leombprojects.order.models.ProductsModel;
import org.leombprojects.order.repositories.OrdersRepository;
import org.leombprojects.order.repositories.ProductsRepository;
import org.leombprojects.order.repositories.UserRepository;
import org.leombprojects.order.services.OrderService;
import org.leombprojects.order.services.mappers.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;
    private final OrdersRepository ordersRepository;
    private final Mapper mapper;

    @Override
    public CaloriesSummaryDto createOrder(OrderDto orderDto) {

        OrdersModel ordersModel = new OrdersModel();
        ordersModel.setUsersModel(userRepository.findByUser(orderDto.getEmployee().getUsername()));
        ordersModel.setDate(new Date());
        ordersModel.setItemsList(new ArrayList<>());
        ordersModel.setCaloriesTotal(0);

        orderDto.getItemList().forEach(itemInputDto -> {

            ProductsModel productModel = productsRepository.findByCode(itemInputDto.getCode());

            ItemsModel itemsModel = new ItemsModel();
            itemsModel.setOrders(ordersModel);
            itemsModel.setProducts(productModel);
            itemsModel.setVersion(1);

            ordersModel.getItemsList().add(itemsModel);
            ordersModel.setCaloriesTotal(ordersModel.getCaloriesTotal() + productModel.getCalories());
        });

        OrdersModel ordersModelSaved = ordersRepository.save(ordersModel);

        return mapCaloriesSummaryDto(ordersModelSaved);
    }

    @Override
    public CaloriesSummaryDto updateOrder(OrderDto orderDto) {
        OrdersModel orderDb = ordersRepository.findById(orderDto.getOrder());

        ItemsModel maxVersionItem = orderDb.getItemsList().stream().max(Comparator.comparing(ItemsModel::getVersion)).orElse(null);

        orderDb.setItemsList(new ArrayList<>());
        orderDb.setCaloriesTotal(0);
        orderDb.setDate(new Date());
        orderDto.getItemList().forEach(itemInputDto -> {

            ProductsModel productModel = productsRepository.findByCode(itemInputDto.getCode());

            ItemsModel itemsModel = new ItemsModel();
            itemsModel.setOrders(orderDb);
            itemsModel.setProducts(productModel);
            assert maxVersionItem != null;
            itemsModel.setVersion(maxVersionItem.getVersion() + 1);

            orderDb.getItemsList().add(itemsModel);
            orderDb.setCaloriesTotal(orderDb.getCaloriesTotal() + productModel.getCalories());
        });

        OrdersModel orderSaved = ordersRepository.save(orderDb);

        return mapCaloriesSummaryDto(orderSaved);
    }

    private CaloriesSummaryDto mapCaloriesSummaryDto(OrdersModel ordersModel){
        List<ItemOutputDto> itemOutputDtos = mapper.convertToDto(ordersModel.getItemsList().stream().map(ItemsModel::getProducts).collect(Collectors.toList()), ItemOutputDto.class);
        CaloriesSummaryDto caloriesSummaryDto = new CaloriesSummaryDto();
        caloriesSummaryDto.setOrder(ordersModel.getId().intValue());
        caloriesSummaryDto.setCaloriesTotal(ordersModel.getCaloriesTotal());
        caloriesSummaryDto.setItemList(itemOutputDtos);
        caloriesSummaryDto.setEmployee(new EmployeeDto());
        caloriesSummaryDto.getEmployee().setUsername(ordersModel.getUsersModel().getUser());
        return caloriesSummaryDto;
    }
}
