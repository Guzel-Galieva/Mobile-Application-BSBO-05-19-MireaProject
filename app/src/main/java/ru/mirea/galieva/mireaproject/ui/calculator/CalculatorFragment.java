package ru.mirea.galieva.mireaproject.ui.calculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.galieva.mireaproject.R;

public class CalculatorFragment extends Fragment implements View.OnClickListener{
    EditText editText1;
    EditText editText2;
    TextView textView;
    Button buttonpl;
    Button buttonmi;
    Button buttonmu;
    Button buttondi;
    Button buttonreset;
    String operat = "";
    private CalculatorViewModel calculatorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        WebView webview = view.findViewById(R.id.webView);
        editText1 = view.findViewById(R.id.editText1);
        editText2 = view.findViewById(R.id.editText2);
        buttonpl = view.findViewById(R.id.buttonpl);
        buttonmi = view.findViewById(R.id.buttonmi);
        buttonmu = view.findViewById(R.id.buttonmu);
        buttondi = view.findViewById(R.id.buttondi);
        textView = view.findViewById(R.id.textView);
        buttonpl.setOnClickListener(this);
        buttonmi.setOnClickListener(this);
        buttonmu.setOnClickListener(this);
        buttondi.setOnClickListener(this);
        buttonreset = view.findViewById(R.id.buttonreset);
        buttonreset.setOnClickListener(this);
        calculatorViewModel =
                new ViewModelProvider(this).get(CalculatorViewModel.class);
        calculatorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        float n1 = 0;
        float n2 = 0;
        float res = 0;
        if (TextUtils.isEmpty(editText1.getText().toString())
                || TextUtils.isEmpty(editText2.getText().toString())) {
            return;
        }
        n1 = Float.parseFloat(editText1.getText().toString());
        n2 = Float.parseFloat(editText2.getText().toString());
        switch (v.getId()) {
            case R.id.buttonpl:
                operat = "+";
                res = n1 + n2;
                textView.setText(n1 + "  " + operat + "  " + n2 + "  =  " + res);
                break;
            case R.id.buttonmi:
                operat = "-";
                res = n1 - n2;
                textView.setText(n1 + "  " + operat + "  " + n2 + "  =  " + res);
                break;
            case R.id.buttonmu:
                operat = "*";
                res = n1 * n2;
                textView.setText(n1 + "  " + operat + "  " + n2 + "  =  " + res);
                break;
            case R.id.buttondi:
                operat = "/";
                res = n1 / n2;
                textView.setText(n1 + "  " + operat + "  " + n2 + "  =  " + res);
                break;
            case R.id.buttonreset:
                editText1.setText("");
                editText2.setText("");
                textView.setText("");
                break;
            default:
                break;
        }
    }

}
