package ru.mirea.galieva.mireaproject.ui.stories;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.galieva.mireaproject.R;
import ru.mirea.galieva.mireaproject.databinding.FragmentStoriesBinding;
import ru.mirea.galieva.mireaproject.ui.stories.StoriesViewModel;

public class StoriesFragment extends Fragment {

    private StoriesViewModel mViewModel;
    private static final String TAG = "StoriesFragment";
    public FloatingActionButton fab;
    final int REQUEST_CODE = 1;
    private String filedStories;
    ArrayList<String> stories;

    RecyclerView recyclerView;

    public static StoriesFragment newInstance() {
        return new StoriesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_stories, container, false);

        stories = new ArrayList<String>();
        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stories.add("Write your story!");
        StoriesAdapter storiesAdapter = new StoriesAdapter(getActivity() ,stories);
        recyclerView.setAdapter(storiesAdapter);

        fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MyDialogFragment myDialogFragment = new MyDialogFragment();
                //myDialogFragment.show(getActivity().getSupportFragmentManager(), "MyDialogFragment");

                Intent intent;

                intent = new Intent(getActivity(), NoteActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE:
                    String note = data.getStringExtra("note");

                    stories.add(note);
                    Log.d(TAG, "onActivityResult: " + note);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StoriesViewModel.class);
    }
}