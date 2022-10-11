package com.austinhague.monthMathAPI.classes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class MathSolution {
    @NotNull(message = "You must supply an integer for the first number")
    private Integer operand1;
    @NotNull(message = "You must supply an integer for second number")
    private Integer operand2;

    private String operation;
    private Integer answer;

    public MathSolution() {
    }

    public void add(Integer a, Integer b) {
        this.answer = a + b;
    }

    public void subtract(Integer a, Integer b) {
        this.answer = a - b;
    }

    public void multiply(Integer a, Integer b) {
        this.answer = a * b;
    }

    public void divide(Integer a, Integer b) {
        this.answer = a / b;
    }

    public Integer getOperand1() {
        return operand1;
    }

    public void setOperand1(Integer operand1) {
        this.operand1 = operand1;
    }

    public Integer getOperand2() {
        return operand2;
    }

    public void setOperand2(Integer operand2) {
        this.operand2 = operand2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "MathSolution{" +
                "operand1=" + operand1 +
                ", operand2=" + operand2 +
                ", operation='" + operation + '\'' +
                ", answer=" + answer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathSolution that = (MathSolution) o;
        return Objects.equals(operand1, that.operand1) && Objects.equals(operand2, that.operand2) && Objects.equals(operation, that.operation) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2, operation, answer);
    }
}
