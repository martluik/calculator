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
    private static int operationType = 0;
    private static String display = "";
    private static boolean dotPressed = false;
    private static int step = 1;

    // Klassi konstruktor
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

        String n = "";

        switch (i) {
            case (R.id.button0):
                if(step == 1) {
                    if(firstNumber.length() == 0 || (firstNumber.length() > 0 && !Objects.equals(firstNumber, "0"))) {
                        n = "0";
                    } //else if (firstNumber.length() == 1 && firstNumber == "0")
                } else if (step == 2) {
                    if(secondNumber.length() == 0 || (secondNumber.length() > 0 && !Objects.equals(secondNumber, "0"))) {
                        n = "0";
                    }
                }

                break;
            case (R.id.button1):
                n = "1";
                break;
            case (R.id.button2):
                n = "2";
                break;
            case (R.id.button3):
                n = "3";
                break;
            case (R.id.button4):
                n = "4";
                break;
            case (R.id.button5):
                n = "5";
                break;
            case (R.id.button6):
                n = "6";
                break;
            case (R.id.button7):
                n = "7";
                break;
            case (R.id.button8):
                n = "8";
                break;
            case (R.id.button9):
                n = "9";
                break;
            case (R.id.buttondot):
                if(!dotPressed) {

                    dotPressed = true;

                    if(step == 1 && firstNumber.length() == 0 || step == 2 && secondNumber.length() == 0) {
                        n = "0.";
                        Log.d(TAG, "first or second number empty");
                    } else {
                        n = ".";
                    }
                }
                break;
        }

        if (step == 1) {
            firstNumber += n;
        } else if (step == 2) {
            secondNumber += n;
        }

        display += n;

    }

    public void operation(int i) {
        step = 2;
        dotPressed = false;
        String operationString = "";

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
            String s = firstNumber.substring(0, firstNumber.length()-1);
            firstNumber = s;
            display = s;
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
