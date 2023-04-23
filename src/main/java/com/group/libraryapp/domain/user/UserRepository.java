package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    //User findByName(String name);

    // boolean existsByName(String name);

    //long countByAge(Integer age); // age에 해당하는 데이터가 몇명인지

    //List<User> findAllByNameAndAge(String name,int age);
    //List<User> findAllByNameAgeBetween(int startage,int endage);
    // where name = ? AND age = ?;
    // GreaterThan : 초과
    // GreaterThanEqual : 이상

}
