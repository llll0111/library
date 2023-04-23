package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    private User user;

    private String bookName;

    private boolean isReturn;

    protected UserLoan() {}

    public UserLoan(User user,String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void returnBook(){
        this.isReturn= true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
