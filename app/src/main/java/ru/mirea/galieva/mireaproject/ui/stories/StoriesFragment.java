package ru.mirea.galieva.mireaproject.ui.stories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.galieva.mireaproject.R;
import ru.mirea.galieva.mireaproject.databinding.FragmentStoriesBinding;
import ru.mirea.galieva.mireaproject.ui.stories.StoriesViewModel;

public class StoriesFragment extends Fragment {

    private StoriesViewModel storiesViewModel;
    private FragmentStoriesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        storiesViewModel =
                new ViewModelProvider(this).get(StoriesViewModel.class);

        binding = FragmentStoriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.text;
        storiesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}