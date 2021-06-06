package ru.mirea.galieva.mireaproject.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.galieva.mireaproject.R;
import ru.mirea.galieva.mireaproject.databinding.FragmentSettingsBinding;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment implements View.OnClickListener{
    Button buttonb;
    Button buttonl;
    Button buttonlo;
    Button buttonsa;
    private SharedPreferences preferences;
    String currentTheme = "";
    final String SAVED_TEXT = "saved_text";
    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        buttonb = view.findViewById(R.id.buttonb);
        buttonl = view.findViewById(R.id.buttonl);
        buttonlo = view.findViewById(R.id.buttonlo);
        buttonsa = view.findViewById(R.id.buttonsa);
        buttonl.setOnClickListener(this);
        buttonb.setOnClickListener(this);
        buttonlo.setOnClickListener(this);
        buttonsa.setOnClickListener(this);
        preferences = getActivity().getPreferences(MODE_PRIVATE);
        return view;
    }
    /*public void onSave() {
        Log.d("ghjdthrf", "работает");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SAVED_TEXT, currentTheme);
        editor.apply();
    }
    public void onLoad() {
        String text = preferences.getString(SAVED_TEXT, "light");
        if(text.equals("light")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }else if(text.equals("dark")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonb:
                currentTheme = "dark";
                break;
            case R.id.buttonl:
                currentTheme = "light";
                break;
            case R.id.buttonlo:
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SAVED_TEXT, currentTheme);
                editor.apply();
                break;
            case R.id.buttonsa:
                String text = preferences.getString(SAVED_TEXT, "light");
                if(text.equals("light")){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else if(text.equals("dark")){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            default:
                break;
        }
    }

}