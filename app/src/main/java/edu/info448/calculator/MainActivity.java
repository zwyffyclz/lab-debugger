package edu.info448.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private Calculator mCalculator;

    private EditText mOperandEditText;

    private TextView mResultTextView;

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the calculator class and all the views
        mCalculator = new Calculator();
        mResultTextView = (TextView) findViewById(R.id.current_value_text_view);
        mOperandEditText = (EditText) findViewById(R.id.operand_edit_text);

        Button addButton = (Button) findViewById(R.id.operation_add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute(Calculator.Operator.ADD);
            }
        });

        Button subButton = (Button) findViewById(R.id.operation_sub_btn);
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute(Calculator.Operator.SUB);
            }
        });

        Button divButton = (Button) findViewById(R.id.operation_div_btn);
        divButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    compute(Calculator.Operator.DIV);
                } catch (IllegalArgumentException iae) {
                    Log.e(TAG, "IllegalArgumentException", iae);
                    mResultTextView.setText(getString(R.string.computationError));
                }
            }
        });

        Button mulButton = (Button) findViewById(R.id.operation_mul_btn);
        mulButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute(Calculator.Operator.MUL);
            }
        });

        Button resetButton = (Button) findViewById(R.id.reset_btn);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mResultTextView.setText(R.string.initial_result_text);
                mCalculator.setValue(0.0);
            }
        });
    }


    private void compute(Calculator.Operator operator) {
        double operand;
        try {
            operand = getOperand(mOperandEditText);
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            mResultTextView.setText(getString(R.string.computationError));
            return;
        }

        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(mCalculator.add(operand));
                break;
            case SUB:
                result = String.valueOf(mCalculator.sub(operand));
                break;
            case DIV:
                result = String.valueOf(mCalculator.div(operand));
                break;
            case MUL:
                result = String.valueOf(mCalculator.mul(operand));
                break;
            default:
                result = getString(R.string.computationError);
                break;
        }
        mResultTextView.setText(result);
        mOperandEditText.setText(null);
    }

    /**
     * @return the operand value which was entered in an {@link EditText} as a double
     */
    private static Double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        return Double.valueOf(operandText);
    }

    /**
     * @return the operand text which was entered in an {@link EditText}.
     */
    private static String getOperandText(EditText operandEditText) {
        return operandEditText.getText().toString();
    }
}
