package ru.mirea.galieva.mireaproject.ui.slideshow;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.galieva.mireaproject.R;
import ru.mirea.galieva.mireaproject.databinding.FragmentWebviewBinding;

public class WebViewFragment extends Fragment {

    private WebViewViewModel webViewViewModel;
    private FragmentWebviewBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        webViewViewModel =
                new ViewModelProvider(this).get(WebViewViewModel.class);

        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        WebView webview = view.findViewById(R.id.webView);
        webview.getSettings().getJavaScriptEnabled();
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("http://www.ya.ru");
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    if (keyCode == KeyEvent.KEYCODE_BACK){
                        if (webview != null){
                            if(webview.canGoBack()){
                                webview.goBack();
                            }else{
                                getActivity().onBackPressed();
                            }
                        }
                    }
                }
                return true;
            }
        });

        return view;
    }

}
