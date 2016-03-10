package com.example.ml.calculator;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String STATE_FIRST_NUMBER = "firstNumber";
    private static final String STATE_SECOND_NUMBER = "secondNumber";
    private static final String STATE_DISPLAY = "display";
    private static final String STATE_OPERATION = "operationType";
    private static final String STATE_STEP = "step";
    private static final String STATE_DOT_PRESSED = "dotPressed";

    private CalculatorLogic calculatorLogic;

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.display);

        calculatorLogic = new CalculatorLogic();

        // Do I need to restore state?
        if(savedInstanceState != null) {
            if (BuildConfig.DEBUG) { Log.d(TAG, "Restoring state"); }

            calculatorLogic.setFirstNumber(savedInstanceState.getString(STATE_FIRST_NUMBER));
            calculatorLogic.setSecondNumber(savedInstanceState.getString(STATE_SECOND_NUMBER));
            calculatorLogic.setDisplay(savedInstanceState.getString(STATE_DISPLAY));
            calculatorLogic.setOperationType(savedInstanceState.getInt(STATE_OPERATION));
            calculatorLogic.setStep(savedInstanceState.getInt(STATE_STEP));
            calculatorLogic.setDotPressed(savedInstanceState.getBoolean(STATE_DOT_PRESSED));
        }

        display.setText(calculatorLogic.getDisplay());
    }

    public void number(View v) {
        calculatorLogic.number(v.getId());
        display.setText(calculatorLogic.getDisplay());
    }

    public void operation(View v) {
        calculatorLogic.operation(v.getId());
        display.setText(calculatorLogic.getDisplay());
    }

    public void clear(View v) {
        calculatorLogic.clear();
        display.setText(calculatorLogic.getDisplay());
    }

    public void calculate(View v) {
        calculatorLogic.calculate();
        display.setText(calculatorLogic.getDisplay());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_FIRST_NUMBER, calculatorLogic.getFirstNumber());
        savedInstanceState.putString(STATE_SECOND_NUMBER, calculatorLogic.getSeconNmber());
        savedInstanceState.putString(STATE_DISPLAY, calculatorLogic.getDisplay());
        savedInstanceState.putInt(STATE_OPERATION, calculatorLogic.getOperationType());
        savedInstanceState.putInt(STATE_STEP, calculatorLogic.getStep());
        savedInstanceState.putBoolean(STATE_DOT_PRESSED, calculatorLogic.getDotPressed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onStart called"); }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onResume called"); }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onPause called"); }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onStop called"); }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) { Log.d(TAG, "onDestroy called"); }
    }

}
