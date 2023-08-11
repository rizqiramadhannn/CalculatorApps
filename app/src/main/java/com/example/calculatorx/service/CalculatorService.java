package com.example.calculatorx.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.calculatorx.ICaltulatorXAidlInterface;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorService extends Service {
    private final ICaltulatorXAidlInterface.Stub mBinder = new ICaltulatorXAidlInterface.Stub() {
        @Override
        public double evaluateExpression(String expression) {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
            expression = expression.replace('x', '*');
            try {
                return (double) engine.eval(expression);
            } catch (ScriptException e) {
                return Double.NaN;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
