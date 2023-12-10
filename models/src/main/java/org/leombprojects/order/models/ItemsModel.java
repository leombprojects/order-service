package org.leombprojects.order.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="`ITEMS`", schema="sch-emp-calories")
public class ItemsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`ID_PRODUCT`")
    private ProductsModel products;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`ID_ORDER`")
    private OrdersModel orders;
    @Column(name = "`VERSION`")
    private Integer version;
}
