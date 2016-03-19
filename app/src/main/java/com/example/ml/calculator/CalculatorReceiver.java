package com.example.ml.calculator;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Martin on 18/03/16.
 */
public class CalculatorReceiver extends BroadcastReceiver {

    private CalculatorLogic calculatorLogic = new CalculatorLogic();

    private static String display;
    private static String answer;

    @Override
    public void onReceive(Context context, Intent intent) {
        if(isOrderedBroadcast()) {
            Bundle extras = intent.getExtras();
            String symbol = "";
            if (extras != null) {
                symbol = extras.getString("buttonId");
                if(symbol.contains("button") || symbol.contains("dot")) {
                    calculatorLogic.number(symbol);
                } else if (symbol.contains("equ")) {
                    calculatorLogic.calculate();
                } else if (symbol.contains("clear")) {
                    calculatorLogic.clear();
                } else {
                    calculatorLogic.operation(symbol);
                }
            }

            display = calculatorLogic.getDisplay();
            answer = calculatorLogic.getAnswer();

            setResultData(display + "_" + answer);
            setResultCode(Activity.RESULT_OK);
        }
    }
}
