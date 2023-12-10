package org.leombprojects.order.repositories;

import org.leombprojects.order.models.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsModel, Long> {
    List<ProductsModel> findByCourse(String course);
    ProductsModel findByCode(String code);
}
