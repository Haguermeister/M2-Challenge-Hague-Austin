package com.austinhague.monthMathAPI.controllers;

import com.austinhague.monthMathAPI.classes.MathSolution;
import com.austinhague.monthMathAPI.classes.Month;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
public class MathAndMonthController {

    @RequestMapping(value = "/month/{monthNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Month monthConverter(@PathVariable @Valid int monthNumber) {
        if (monthNumber > 0 && monthNumber < 13) {
            return monthSwitch(monthNumber);
        } else {
            throw new IllegalArgumentException(monthNumber + " is not an valid input. Please enter an integer between 1-12.");
        }
    }

    @RequestMapping(value = "/randomMonth", method = RequestMethod.GET)
    public Month randomMonthGetter() {
        int randomNum = new Random().nextInt(12) + 1;
        return monthSwitch(randomNum);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution addNums(@RequestBody @Valid MathSolution solution) {
        solution.setOperation("add");
        solution.add(solution.getOperand1(), solution.getOperand2());
        return solution;
    }

    @RequestMapping(value = "/subtract", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution subtractNums(@RequestBody @Valid MathSolution solution)  {
        solution.setOperation("subtract");
        solution.subtract(solution.getOperand1(), solution.getOperand2());
        return solution;
    }

    @RequestMapping(value = "/multiply", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution multiplyNums(@RequestBody @Valid MathSolution solution) {
        solution.setOperation("multiply");
        solution.multiply(solution.getOperand1(), solution.getOperand2());
        return solution;
    }

    @RequestMapping(value = "/divide", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MathSolution divideNums(@RequestBody @Valid MathSolution solution)  {
        if (solution.getOperand2() > 0) {
            solution.setOperation("divide");
            solution.divide(solution.getOperand1(), solution.getOperand2());
            return solution;
        } else {
            throw new IllegalArgumentException("Operand2 must be a positive integer");
        }
    }

    public Month monthSwitch(int i) {
        switch (i) {
            case 1:
                return new Month(1, "January");
            case 2:
                return new Month(2, "February");
            case 3:
                return new Month(3, "March");
            case 4:
                return new Month(4, "April");
            case 5:
                return new Month(5, "May");
            case 6:
                return new Month(6, "June");
            case 7:
                return new Month(7, "July");
            case 8:
                return new Month(8, "August");
            case 9:
                return new Month(9, "September");
            case 10:
                return new Month(10, "SPOOKY SEASON");
            case 11:
                return new Month(11, "November");
            case 12:
                return new Month(12, "December");
            default:
                System.out.println("Please enter a number between 1 and 12 to convert to a month.");
                return null;
        }

    }
}
