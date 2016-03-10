package com.example.ml.calculator;

import android.util.Log;

import java.util.Objects;

/**
 * Created by Martin on 07/03/16.
 */
public class CalculatorLogic {

    private static final String TAG = "CalculatorLogic";

    private static String firstNumber = "";
    private static String secondNumber = "";
    private static String display = "";
    private static int operationType = 0;
    private static int step = 1;
    private static boolean dotPressed = false;

    public CalculatorLogic() { }

    public String getFirstNumber() { return firstNumber; }
    public void setFirstNumber(String s) { firstNumber = s; }
    public String getSeconNmber() { return secondNumber; }
    public void setSecondNumber(String s) { secondNumber = s; }
    public String getDisplay() { return display; }
    public void setDisplay(String s) { display = s; }
    public int getOperationType() { return operationType; }
    public void setOperationType(int i) { operationType = i; }
    public int getStep() { return step; }
    public void setStep(int i) { step = i; }
    public boolean getDotPressed() { return dotPressed; }
    public void setDotPressed(boolean b) { dotPressed = b; }

    public void number(int i) {
        if(step == 3) {
            clear();
        }
        String numberString = "";
        switch (i) {
            case (R.id.button0):
                if(step == 1) {
                    if(firstNumber.equals("") || !firstNumber.equals("0")) {
                        numberString = "0";
                    }
                } else if (step == 2) {
                    if(secondNumber.equals("") || !secondNumber.equals("0")) {
                        numberString = "0";
                    }
                }
                break;
            case (R.id.button1):
                numberString = "1";
                break;
            case (R.id.button2):
                numberString = "2";
                break;
            case (R.id.button3):
                numberString = "3";
                break;
            case (R.id.button4):
                numberString = "4";
                break;
            case (R.id.button5):
                numberString = "5";
                break;
            case (R.id.button6):
                numberString = "6";
                break;
            case (R.id.button7):
                numberString = "7";
                break;
            case (R.id.button8):
                numberString = "8";
                break;
            case (R.id.button9):
                numberString = "9";
                break;
            case (R.id.buttondot):
                if(!dotPressed) {
                    dotPressed = true;
                    if(step == 1 && firstNumber.length() == 0 || step == 2 && secondNumber.length() == 0) {
                        numberString = "0.";
                    } else {
                        numberString = ".";
                    }
                }
                break;
        }
        switch (step) {
            case (1):
                firstNumber += numberString;
                break;
            case (2):
                secondNumber += numberString;
                break;
        }
        display += numberString;
    }

    public void operation(int i) {
        if(step == 3) {
            firstNumber = display;
            secondNumber = "";
        }

        String operationString = "";
        step = 2;
        dotPressed = false;

        if(operationType != 0) {
            display = firstNumber;
        }

        switch (i) {
            case (R.id.buttonmul):
                operationString = " * ";
                operationType = 1;
                break;
            case (R.id.buttondiv):
                operationString = " / ";
                operationType = 2;
                break;
            case (R.id.buttonplu):
                operationString = " + ";
                operationType = 3;
                break;
            case (R.id.buttonmin):
                operationString = " - ";
                operationType = 4;
                break;
        }

        if(firstNumber.charAt(firstNumber.length() - 1) == '.') {
            String newNumber = firstNumber.substring(0, firstNumber.length()-1);
            firstNumber = newNumber;
            display = newNumber;
        }
        display += operationString;
    }

    public void calculate() {
        if(firstNumber.length() == 0 || secondNumber.length() == 0)
            return;

        step = 3;
        Double d = 0d;

        switch (operationType) {
            case(1):
                d = Double.valueOf(firstNumber) * Double.valueOf(secondNumber);
                break;
            case (2):
                if(Float.parseFloat(secondNumber) != 0) {
                    d = Double.valueOf(firstNumber) / Double.valueOf(secondNumber);
                } else {
                    d = 0d;
                }
                break;
            case (3):
                d = Double.valueOf(firstNumber) + Double.valueOf(secondNumber);
                break;
            case (4):
                d = Double.valueOf(firstNumber) - Double.valueOf(secondNumber);
                break;
        }

        String a = d.toString();

        if(a.charAt(a.length()-1) == '0' &&
                a.charAt(a.length()-2) == '.') {
            display = d.toString().substring(0, d.toString().length()-2);
        } else {
            display = d.toString();
        }
    }

    public void clear() {
        firstNumber = "";
        secondNumber = "";
        operationType = 0;
        display = "";
        dotPressed = false;
        step = 1;
    }
}
