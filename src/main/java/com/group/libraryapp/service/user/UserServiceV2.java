package com.group.libraryapp.service.user;

import com.group.libraryapp.DTO.user.reponse.UserResponse;
import com.group.libraryapp.DTO.user.request.UserCreateRequest;
import com.group.libraryapp.DTO.user.request.UserUpdateRequest;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository repository;

    public UserServiceV2(UserRepository repository) {
        this.repository = repository;
    }

    // 아래 있는 함수가 시작될떄 start transaction을 해준다 (트랜잭션 시작)
    // 함수가 예외 없이 잘끝나면 commit
    // 혹시라도 문제가 있다면 rollback
    // IOException은 롤백 안됨
    @Transactional
    public void saveUser(UserCreateRequest req) {
        repository.save(new User(req.getName(), req.getAge()));

    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {

        return repository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());

//        return repository.findAll().stream()
//                .map(user -> new UserResponse(user.getId(),user.getName(),user.getAge()))
//                .collect(Collectors.toList());


    }

    @Transactional
    public void updateUser(UserUpdateRequest upreq) {

        User user = repository.findById(upreq.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(upreq.getName());
        //repository.save(user);

    }

    @Transactional
    public void deleteuser(String name) {

        //User user = repository.findByName(name);

        User user = repository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);

//        if (user == null) {
//            throw new IllegalArgumentException();
//        }
        repository.delete(user);

//        if(!repository.existsByName(name)){
//            throw new IllegalArgumentException();
//        }
//
//        User user = repository.findByName(name);

    }


}
