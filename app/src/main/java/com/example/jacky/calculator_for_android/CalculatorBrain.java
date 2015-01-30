package com.example.jacky.calculator_for_android;

/**
 * Created by jacky on 2015/1/29.
 */
public class CalculatorBrain {
    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;
//    private double mCalculatorMeomry;

    //operator types
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";

    public static final String CLEAR = "C";
    public static final String TOGGLESIGN = "+/-";
    public static final String DECIMALPOINT = ".";
    public static final String EQUALS = "=";

    CalculatorBrain() {
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
        //    mCalculatorMeomry = 0;
    }

    public void setmOperand(double operand) {
        mOperand = operand;
    }

    public double getmOperand() {
        return mOperand;
    }

    public double getResult() {
        return mWaitingOperand;
    }


    public String toString() {
        return Double.toString(mOperand);
    }

    public void setmWaitingOperand(double operand) {
        mWaitingOperand = operand;
    }

    public void setmWaitingOperator(String operator) {
        mWaitingOperator = operator;
    }

    public String getmWaitingOperator() {
        return mWaitingOperator;
    }

    public void clear() {
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
    }

    protected void performOperation(String operator) {
        if (operator.equals(ADD)) {
            mWaitingOperand += mOperand;
        } else if (operator.equals(SUB)) {
            mWaitingOperand -= mOperand;
        } else if (operator.equals(MUL)) {
            mWaitingOperand *= mOperand;
        } else if (operator.equals(DIV)) {
            if (mOperand != 0) {
                mWaitingOperand /= mOperand;
            }
        }
        else{
            mWaitingOperand = mOperand;
        }
    }
}
