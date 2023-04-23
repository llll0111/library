package com.group.libraryapp.service.user;

import com.group.libraryapp.DTO.user.reponse.UserResponse;
import com.group.libraryapp.DTO.user.request.UserCreateRequest;
import com.group.libraryapp.DTO.user.request.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserJDBCRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJDBCRepository repository;

    public UserServiceV1(UserJDBCRepository repository) {
        this.repository = repository;
    }

    public void saveUser(UserCreateRequest req) {
        repository.saveUser(req.getName(), req.getAge());

    }

    public List<UserResponse> getUsers() {
        return repository.getUsers();
    }

    public void updateUser(UserUpdateRequest upreq) {

        if (repository.isUserNot(upreq.getId())) {
            throw new IllegalArgumentException(String.format("없는 이름입니다"));
        }

        repository.updateUser(upreq.getName(), upreq.getId());

    }


    public void deleteuser(String name) {

        if (repository.isUserNot(name)) {
            throw new IllegalArgumentException(String.format("없는 이름입니다"));
        }

        repository.deleteuser(name);
    }


}
