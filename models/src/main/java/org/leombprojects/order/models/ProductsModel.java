package org.leombprojects.order.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="`PRODUCTS`", schema="sch-emp-calories")
public class ProductsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`")
    private Long id;
    @Column(name = "`NAME`")
    private String name;
    @Column(name = "`CODE`")
    private String code;
    @Column(name = "`DESC`")
    private String desc;
    @Column(name = "`CALORIES`")
    private Integer calories;
    @Column(name = "`COURSE`")
    private String course;
    @Column(name = "`IMAGE`")
    private String image;
}
