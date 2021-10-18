package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText Amount = (EditText) findViewById(R.id.Initial_Amount);
        final EditText Interest = (EditText) findViewById(R.id.Interest_Rate);
        final EditText Year = (EditText) findViewById(R.id.Year);

        final EditText TotalEMI = (EditText) findViewById(R.id.Total_EMI);
        final EditText TotalInterest = (EditText) findViewById(R.id.Total_Interest);

        Button Calculate = (Button) findViewById(R.id.Calculate);

        Calculate.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String st1 = Amount.getText().toString();
                String st2 = Interest.getText().toString();
                String st3 = Year.getText().toString();

                float p = Float.parseFloat(st1);
                float i = Float.parseFloat(st2);
                float y = Float.parseFloat(st3);
                float Monthly = calculate_months(y);
                float totalEMI = EMI(p, i, y);
                float initial_Interest = calculate_initial_interest(totalEMI, Monthly);
                float Total_Interest = calculate_initial_interest(initial_Interest, p);

                TotalEMI.setText(String.valueOf(totalEMI));
                TotalInterest.setText(String.valueOf(Total_Interest));
            }
        });
    }
    public float calculate_months(float y) {
        return (float)(y * 12);
    }
    public float calculate_initial_interest(float emi, Float Months) {
        return (float)(emi * Months);
    }
    public float EMI(float p, float i, float y){
        float rate = (float) (i / 12 / 100);
        float Months = (float) (12*y);
        float x = (float)(Math.pow(1 + rate, Months));
        float dm = (float) (x-1);
        return (float) (p * i * x)/(float) dm;
    }
    public float calculate_total_int(float TA, float Principal) {
        return (float)(TA - Principal);
    }
}