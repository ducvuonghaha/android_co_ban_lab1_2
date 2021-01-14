package com.dcvg.baitap_lab1_lab2;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText edtInputNumber;
    private Button btnRoll;
    private TextView tvResult;
    private TextView tvNoti;
    private TextView tvKQ;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        tvResult.setVisibility(View.GONE);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtInputNumber.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                    tvNoti.setText("Vui lòng nhập số");
                } else {
                tvResult.setTextColor(Color.parseColor("#FF018786"));
                tvKQ.setTextColor(Color.parseColor("#FF018786"));
                tvNoti.setTextColor(Color.parseColor("#FF018786"));
                tvNoti.setText("Đang quay số");
                final int min = 10;
                final int max = 99;
                final int duration = 100;
                final Handler handler = new Handler();
                final int[] random_number = {0};
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        random_number[0] = new Random().nextInt(max - min + 1) + min;
                        tvResult.setVisibility(View.VISIBLE);
                        tvResult.setText(String.valueOf(random_number[0]));
                        handler.postDelayed(this, duration);
                    }
                };
                handler.post(runnable);

                final Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            animation = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.sample_animation);
                            tvResult.startAnimation(animation);
                            handler.removeCallbacksAndMessages(null);
                            String input_number = edtInputNumber.getText().toString().trim();
                            if (Integer.parseInt(input_number) == random_number[0]) {
                                tvResult.setTextColor(Color.parseColor("#0580E1"));
                                tvKQ.setTextColor(Color.parseColor("#0580E1"));
                                tvNoti.setText("Chúc mừng bạn đã thắng xổ số");
                                tvNoti.setTextColor(Color.parseColor("#0580E1"));
                            } else {
                                tvResult.setTextColor(Color.parseColor("#F41606"));
                                tvKQ.setTextColor(Color.parseColor("#F41606"));
                                tvNoti.setText("Chúc bạn may mắn lần sau");
                                tvNoti.setTextColor(Color.parseColor("#F41606"));
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                            tvNoti.setText("Vui lòng nhập số");
                        }
                    }
                }, 5000);

                }
            }
        });
    }

    private void initView() {
        edtInputNumber = (EditText) findViewById(R.id.edtInputNumber);
        btnRoll = (Button) findViewById(R.id.btnRoll);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvNoti = (TextView) findViewById(R.id.tvNoti);
        tvKQ = (TextView) findViewById(R.id.tvKQ);
    }
}