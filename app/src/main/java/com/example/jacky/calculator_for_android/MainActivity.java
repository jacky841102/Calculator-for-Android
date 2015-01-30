package com.example.jacky.calculator_for_android;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView mCalculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private CalculatorBrain mCalculatorBrain;
    private static final String DIGITS = "0123456789.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalculatorBrain = new CalculatorBrain();
        mCalculatorDisplay = (TextView) findViewById(R.id.result);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.buttonAddition).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonToggleSign).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonDecimalPoint).setOnClickListener(this);

    }


    @Override
    public void onClick(View v){
        String buttonPressed = ((Button) v).getText().toString();
        if(buttonPressed.equals("C")){
            mCalculatorBrain.clear();
            mCalculatorDisplay.setText(Double.toString(mCalculatorBrain.getResult()));
            userIsInTheMiddleOfTypingANumber = false;
        }
        else if(buttonPressed.equals("+/-")){
            mCalculatorBrain.setmWaitingOperand(-Double.parseDouble(mCalculatorDisplay.getText().toString()));
            mCalculatorBrain.setmOperand(mCalculatorBrain.getResult());
            mCalculatorDisplay.setText(Double.toString(mCalculatorBrain.getResult()));
        }
        else if(buttonPressed.equals("=")){
            mCalculatorBrain.setmOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
            mCalculatorBrain.performOperation(mCalculatorBrain.getmWaitingOperator());
            userIsInTheMiddleOfTypingANumber = true;
            mCalculatorDisplay.setText(Double.toString(mCalculatorBrain.getResult()));
            mCalculatorBrain.setmWaitingOperator("");
        }
        else if(DIGITS.contains(buttonPressed)){
//            if(Double.parseDouble(mCalculatorDisplay.getText().toString()) == (int)Double.parseDouble(mCalculatorDisplay.getText().toString()))
            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && mCalculatorDisplay.getText().toString().contains(".")) {
                    // ERROR PREVENTION
                    // Eliminate entering multiple decimals
                } else {
                    mCalculatorDisplay.append(buttonPressed);
                }

            } else {
                if(mCalculatorBrain.getmWaitingOperator().equals("")) mCalculatorBrain.clear();

                if (buttonPressed.equals(".")) {
                    // ERROR PREVENTION
                    // This will avoid error if only the decimal is hit before an operator, by placing a leading zero
                    // before the decimal
                    mCalculatorDisplay.setText(0 + buttonPressed);
                } else {
                    mCalculatorDisplay.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }
        }
        else{
            if(userIsInTheMiddleOfTypingANumber){
                mCalculatorBrain.setmOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
                System.out.println("mOperand is "+mCalculatorBrain.getmOperand());
                System.out.println("mWaitingOperation is "+mCalculatorBrain.getResult());
                System.out.println("mWaitingOperator is "+mCalculatorBrain.getmWaitingOperator());
                mCalculatorBrain.performOperation(mCalculatorBrain.getmWaitingOperator());
                System.out.println("mWaitingOperation is "+mCalculatorBrain.getResult());
                mCalculatorBrain.setmWaitingOperator(buttonPressed);
                userIsInTheMiddleOfTypingANumber = false;
            }
            mCalculatorBrain.setmWaitingOperator(buttonPressed);
            mCalculatorDisplay.setText(Double.toString(mCalculatorBrain.getResult()));
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
