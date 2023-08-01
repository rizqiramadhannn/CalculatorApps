package com.example.calculatorx;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workingsTV;
    TextView resultsTV;

    String workings = "";
    Double result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews(){
        workingsTV = (TextView)findViewById((R.id.workingTextView));
        resultsTV = (TextView)findViewById((R.id.resultTextView));
    }

    private void setWorkings(String givenValue){
        if (result != null){
            if (result % 1 == 0){
                workings = String.valueOf(result.intValue());
            } else {
                workings = result.toString();
            }

            result = null;
            resultsTV.setText("");
        }
        workings += givenValue;
        workingsTV.setText(workings);
    }

    boolean leftBracket = true;
    public void parenthesisOnClick(View view) {
        if (leftBracket){
            setWorkings("(");
            leftBracket = false;
        } else {
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void negateOnClick(View view) {
        if (workings.charAt(0) == '-'){
            workings = workings.substring(1);
            workingsTV.setText(workings);
        } else {
            workings = "-" + workings;
            workingsTV.setText(workings);
        }
    }

    public void clearOnClick(View view) {
        workingsTV.setText("");
        workings = "";
        result = null;
        resultsTV.setText("");
    }

    public void equalOnClick(View view) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        workings = workings.replace('x', '*');
        try {
            result = (double)engine.eval(workings);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if (result != null && result % 1 != 0.0){
            resultsTV.setText(String.valueOf(result.doubleValue()));
        }  else if (result != null){
            resultsTV.setText(String.valueOf(result.intValue()));
        }
    }

    public void dotOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }

    public void addOnClick(View view) {
        setWorkings("+");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void subtractOnClick(View view) {
        setWorkings("-");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void multiplyOnClick(View view) {
        setWorkings("x");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void divideOnClick(View view) {
        setWorkings("/");
    }

    public void modularOnClick(View view) {
        setWorkings("%");
    }


    public void deleteOnClick(View view) {
        workings = workings.substring(0, workings.length() - 1);
        workingsTV.setText(workings);
    }
}
