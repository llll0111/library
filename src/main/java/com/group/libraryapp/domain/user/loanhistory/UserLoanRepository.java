package com.group.libraryapp.domain.user.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanRepository extends JpaRepository<UserLoan, Long> {

    // Select * From UserLoanRepository Where Bookname = ? and isReturn = ?
    boolean existsByBookNameAndIsReturn(String name,boolean isReturn);

//    Optional<UserLoan> findByUserIdAndBookName
//            (Long userid,String name);


}
