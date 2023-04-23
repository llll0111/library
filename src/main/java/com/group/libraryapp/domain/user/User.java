package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id // 이 필드는 primary key로 간주
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key는 자동생성되는 값이다
    private Long id = null;



    // JPA를 사용하기위해서는 기본생성자 필수

    @Column(nullable = false, length = 20, name = "name") // 컬럼 이름과 변수 이름이 같으면 생략가능
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UserLoan> userloan = new ArrayList<>();

    protected User() {}

    public User(String name, Integer age) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }

        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void removeOneHistory(){
        userloan.removeIf(history -> "책1".equals(history.getBookName()));
    }
    public void loanBook(String bookname){
        this.userloan.add(new UserLoan(this,bookname));
    }
    public void returnBook(String bookname){
        UserLoan target = this.userloan.stream()
                .filter(history -> history.getBookName().equals(bookname))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        target.returnBook();
    }




}
