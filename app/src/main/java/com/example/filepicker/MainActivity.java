package com.example.filepicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btnFilePicker;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilePicker = findViewById(R.id.btnFilePicker);
        txtResult = findViewById(R.id.txtResult);

        btnFilePicker.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showFileChooser();
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 100);
        } catch (Exception exception) {
            Toast.makeText(this, "Please install a file manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode,
                                              @Nullable @org.jetbrains.annotations.Nullable Intent data) {

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            String path = uri.getPath();
            File file = new File(path);

            txtResult.setText("Path: " + path + "\n" + "\n" +
                    "File name: " + file.getName());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}