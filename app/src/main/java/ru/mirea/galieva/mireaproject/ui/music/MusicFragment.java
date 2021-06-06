package ru.mirea.galieva.mireaproject.ui.music;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.mirea.galieva.mireaproject.MyService;
import ru.mirea.galieva.mireaproject.R;

public class MusicFragment extends Fragment {

    private MusicViewModel mViewModel;
    private MyService playerService;
    private Button buttonpl;
    private Button buttonst;

    public static MusicFragment newInstance() {
        return new MusicFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        playerService = new MyService();
        View root = inflater.inflate(R.layout.fragment_music, container, false);

        buttonpl = root.findViewById(R.id.button1);
        buttonst = root.findViewById(R.id.button2);
        buttonpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startService(
                        new Intent(getActivity(), MyService.class)
                );
            }
        });

        buttonst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(
                        new Intent(getActivity(), MyService.class)
                );
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        // TODO: Use the ViewModel
    }


}

