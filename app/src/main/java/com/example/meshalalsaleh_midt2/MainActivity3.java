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

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper myDB;

    EditText find2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button btnFetch = (Button)findViewById(R.id.btnFetch);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        Button btnMove31 = (Button)findViewById(R.id.move31);

        myDB=new DatabaseHelper(this);

        find2 = (EditText)findViewById(R.id.type3);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            Cursor cursor;
            StringBuffer buffer=new StringBuffer();
            @Override
            public void onClick(View v) {
                if (find2.getText().toString().equals("")) {
                    cursor = myDB.getListContents();
                }
                else if (!(find2.getText().toString().equals(""))) {
                    cursor = myDB.getSpecificResult(find2.getText().toString());
                }

                if (cursor.getCount()==0)
                    Toast.makeText(MainActivity3.this, "Nothing is found", Toast.LENGTH_LONG).show();
                else {

                    while (cursor.moveToNext()) {
                        for (int i = 0; i < cursor.getColumnCount(); i++) {
                            buffer.append(cursor.getColumnName(i) + ": " + cursor.getString(i) + "\n");
                        }
                        buffer.append("\n");

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();

                    buffer.delete(0, buffer.capacity());

                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result=myDB.dltRow(find2.getText().toString());

                if(result>=1)
                    Toast.makeText(MainActivity3.this,+result+"It was deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity3.this,"It did not delete",Toast.LENGTH_LONG).show();

            }
        });

        btnMove31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
            }
        });

    }
}