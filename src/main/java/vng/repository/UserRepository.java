package vng.repository;

import org.springframework.data.repository.CrudRepository;

import vng.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}