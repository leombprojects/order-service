package org.leombprojects.order.repositories;

import org.leombprojects.order.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersModel, Long> {
    UsersModel findByUserAndPassword(String user, String password);
    UsersModel findByUser(String user);
}
