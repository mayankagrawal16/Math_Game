package com.mayank.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button add,sub,mul,rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.buttonAdd);
        sub=findViewById(R.id.buttonSubtract);
        mul=findViewById(R.id.buttonMultiply);
        rem=findViewById(R.id.buttonRemainder);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game_Addition.class);
                startActivity(intent);
                finish();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game_Subtraction.class);
                startActivity(intent);
                finish();
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game_Multiplication.class);
                startActivity(intent);
                finish();
            }
        });
        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Game_Remainder.class);
                startActivity(intent);
                finish();
            }
        });
    }
}