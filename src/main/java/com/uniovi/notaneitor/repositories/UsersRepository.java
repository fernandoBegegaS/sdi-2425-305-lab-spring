package com.uniovi.notaneitor.repositories;
import com.uniovi.notaneitor.entities.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long>{
    User findByDni(String dni);
    List<User> findByRole(String role);
}