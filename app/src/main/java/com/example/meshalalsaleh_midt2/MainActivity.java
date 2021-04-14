package com.example.meshalalsaleh_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;

    EditText name, id , email, num;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsert = (Button)findViewById(R.id.btnInsert);
        Button btnMove2 = (Button)findViewById(R.id.btnMove2);
        Button btnMove3 = (Button)findViewById(R.id.btnMove3);

        myDB=new DatabaseHelper(this);


        name = (EditText)findViewById(R.id.putName);
        id = (EditText)findViewById(R.id.putID);
        email = (EditText)findViewById(R.id.putEmail);
        num = (EditText)findViewById(R.id.putNum);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myDB.addData(Integer.parseInt(id.getText().toString()),name.getText().toString(),email.getText().toString(),Integer.parseInt(num.getText().toString()))==false)
                    Toast.makeText(MainActivity.this,"Data was not entered into the table \nPlease check your input!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data was successfully entered into the table",Toast.LENGTH_LONG).show();

            }
        });

        btnMove2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));

            }
        });

        btnMove3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });





    }
}