package com.example.meshalalsaleh_midt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDB;

    EditText find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnSearch = (Button)findViewById(R.id.btnSearch);
        Button btnM1 = (Button)findViewById(R.id.btnM1);

        myDB=new DatabaseHelper(this);

        find = (EditText)findViewById(R.id.type2);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            Cursor cursor;
            StringBuffer buffer=new StringBuffer();
            @Override
            public void onClick(View v) {
                if (find.getText().toString().equals("")) {
                    cursor = myDB.getListContents();
                }
                else if (!(find.getText().toString().equals(""))) {
                    cursor = myDB.getSpecificResult(find.getText().toString());
                }

                if (cursor.getCount()==0)
                    Toast.makeText(MainActivity2.this, "Nothing is found", Toast.LENGTH_LONG).show();
                else {

                    while (cursor.moveToNext()) {
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
                            buffer.append(cursor.getColumnName(i) + ": " + cursor.getString(i) + "\n");
                        }
                        buffer.append("\n");

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();

                    buffer.delete(0, buffer.capacity());

                }
            }
        });

        btnM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });
    }
}