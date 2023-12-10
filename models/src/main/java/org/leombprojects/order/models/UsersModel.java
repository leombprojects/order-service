package org.leombprojects.order.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="`USERS`", schema="sch-emp-calories")
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`")
    private Long id;

    @Column(name = "`NAME`")
    private String name;
    @Column(name = "`USER`")
    private String user;
    @Column(name = "`PASSWORD`")
    private String password;
}
