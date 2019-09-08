package com.example.searchbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText name, sur, mark,id;
    Button btn1,view,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHelper(this);
        id = (EditText) findViewById(R.id.tv0);
        name = (EditText) findViewById(R.id.tv1);
        sur = (EditText) findViewById(R.id.tv2);
        mark = (EditText) findViewById(R.id.tv3);
        btn1 = (Button) findViewById(R.id.insert);
        view = (Button) findViewById(R.id.view);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        addData();
        viewData();
        UpdateData();
        DeleteData();
    }

    private void DeleteData() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Integer deletedRow=mydb.deleteData(id.getText().toString());
              if(deletedRow > 0)
                  Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(MainActivity.this,"Not Deleted",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void UpdateData()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updated= mydb.updateData(id.getText().toString(),name.getText().toString(),sur.getText().toString(),mark.getText().toString());
                if(updated==true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Not updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addData()
    {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isInserted= mydb.insert(name.getText().toString(),
                        sur.getText().toString(),
                        mark.getText().toString());
               if(isInserted==true)
                   Toast.makeText(MainActivity.this,"Data is Inserted",Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity.this,"Data is not Inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void viewData()
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getData();
                if(res.getCount()==0) {
                    showMessage("Error","No Data Found");
                    return;
                }

                    StringBuffer buffer=new StringBuffer();
                    while (res.moveToNext())
                    {
                        buffer.append("ID :"+ res.getString(0)+"\n"+"Name :"+ res.getString(1)+"\n"+"Surname :"+res.getString(2)+"\n"+"Marks :"+res.getString(3));
                    }
                    showMessage("Data",buffer.toString());

            }
        });
    }
    public void showMessage(String title,String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
