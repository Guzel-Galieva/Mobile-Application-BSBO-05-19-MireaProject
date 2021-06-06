package ru.mirea.galieva.mireaproject.ui.stories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ru.mirea.galieva.mireaproject.R;
import static android.text.TextUtils.isEmpty;

public class NoteActivity extends AppCompatActivity {

    EditText editTextMultiline;
    Button buttonDone;
    Button buttonCancel;

    String note;
    boolean isEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editTextMultiline = findViewById(R.id.editTextTextMultiLine);
        buttonDone = findViewById(R.id.buttonDone);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmpty(editTextMultiline.getText().toString())){
                    isEmpty = true;
                }
                else {
                    note = editTextMultiline.getText().toString();
                    isEmpty = false;
                }
                finish();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEmpty = true;
                finish();
            }
        });
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        if (!isEmpty){
            intent.putExtra("note", note);
            setResult(RESULT_OK, intent);
        }
        super.finish();
    }

}