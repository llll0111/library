package com.group.libraryapp.service.book;

import com.group.libraryapp.DTO.book.request.BookLoanRequest;
import com.group.libraryapp.DTO.book.request.BookRequest;
import com.group.libraryapp.DTO.book.request.BookReturnRequest;
import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository repository;
    private final UserLoanRepository userloan;
    private final UserRepository userrepository;

    public BookService(BookRepository repository, UserLoanRepository userloan, UserRepository userrepository) {
        this.repository = repository;
        this.userloan = userloan;
        this.userrepository = userrepository;
    }

    @Transactional
    public void saveBook(BookRequest request) {
        repository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {

        // 1. 책정보를 가져옴
        Book book = repository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출기록정보를 확인해서 대출중인지 확인
        // 3. 만약에 확인했는데 대출중이면 예외를 발생
        if (userloan.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException(String.format("대출중인 책입니다"));
        }

        // 4.유저정보를 가져온다
        User user = userrepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());
        // 5. 유저정보와 책정보를 기반으로 UserLoan를 저장
        //userloan.save(new UserLoan(user,book.getName()));

    }

    @Transactional
    public void deleteUserHistory() {
        User user = userrepository.findByName("ABC")
                .orElseThrow(IllegalArgumentException::new);
        user.removeOneHistory();
    }


    //    @Transactional
//    public void returnBook(BookLoanRequest request) {
//
//        Book book = repository.findByName(request.getBookName())
//                .orElseThrow(IllegalArgumentException::new);
//
//        if (userloan.existsByBookNameAndIsReturn(book.getName(),true)){
//            throw new IllegalArgumentException(String.format("반납된 책입니다"));
//        }
//        User user = userrepository.findByName(request.getUserName())
//                .orElseThrow(IllegalArgumentException::new);
//
//        UserLoan loan = userloan.findByUserIdAndBookNameAndIsReturn(user.getId(),book.getName(),false)
//                .orElseThrow(IllegalArgumentException::new);
//        loan.returnBook(true);
//
//
//    }
    @Transactional
    public void returnBook(BookReturnRequest request) {

        User user = userrepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        System.out.println("hello");

        user.returnBook(request.getBookName());
//        UserLoan loan = userloan.findByUserIdAndBookName(user.getId(),request.getBookName())
//                .orElseThrow(IllegalArgumentException::new);
//        loan.returnBook();


    }


}
