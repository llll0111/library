package com.group.libraryapp.controller.calcultor;

import com.group.libraryapp.DTO.Calculator.request.CalculatorAddRequest;
import com.group.libraryapp.DTO.Calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 주어진 class를 controller로 지정
public class CalculatorController {
    @GetMapping("/add")
    public int AddNumbers(CalculatorAddRequest request){
        return request.getNum1() + request.getNum2();
    }

    @PostMapping("/multiply")
    public int MultiplyNumbers(@RequestBody CalculatorMultiplyRequest re){
         return re.getNum1() * re.getNum2();
    }














}
