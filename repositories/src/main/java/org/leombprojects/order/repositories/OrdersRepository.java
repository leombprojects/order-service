package org.leombprojects.order.repositories;

import org.leombprojects.order.models.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersModel, Long> {
    OrdersModel findById(Integer id);
}