package com.walecode.activitytrackerapp.repositories;


import com.walecode.activitytrackerapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User>findByEmailAndPassword(String email, String password);

}
