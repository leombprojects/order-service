package org.leombprojects.order.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="`ORDERS`", schema="sch-emp-calories")
public class OrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`")
    private Long id;
    @Column(name = "`DATE`")
    private Date date;
    @Column(name = "`CALORIES_TOTAL`")
    private Integer caloriesTotal;
    @ManyToOne
    @JoinColumn(name = "`ID_USER`")
    private UsersModel usersModel;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<ItemsModel> itemsList;
}
