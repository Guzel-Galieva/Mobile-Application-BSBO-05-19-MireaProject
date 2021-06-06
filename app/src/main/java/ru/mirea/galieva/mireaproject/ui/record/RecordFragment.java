package ru.mirea.galieva.mireaproject.ui.record;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;

import ru.mirea.galieva.mireaproject.R;
import ru.mirea.galieva.mireaproject.databinding.FragmentRecordBinding;
import ru.mirea.galieva.mireaproject.ui.home.HomeViewModel;

public class RecordFragment extends Fragment {

    private String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};
    private static final int REQUEST_CODE_PERMISSION = 100;
    private boolean isWork;

    private RecordViewModel mViewModel;

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String filename;

    Button buttonRecord;
    Button buttonStopRecord;
    Button buttonPlayRecord;
    Button buttonStopPlayingRecord;

    public static RecordFragment newInstance() {
        return new RecordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_record, container, false);

        isWork = hasPermissions(getActivity(), PERMISSIONS);
        if(!isWork){
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, REQUEST_CODE_PERMISSION);
        }


        filename = Environment.getExternalStorageDirectory() + "/record.3gpp";

        buttonRecord = root.findViewById(R.id.buttonRecord);
        buttonStopRecord = root.findViewById(R.id.buttonStopRecord);
        buttonPlayRecord = root.findViewById(R.id.buttonPlayRecord);
        buttonStopPlayingRecord = root.findViewById(R.id.buttonStopPlayingRecord);

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordStart(root);
            }
        });
        buttonStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordStop(root);
            }
        });

        buttonPlayRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playStart(root);
            }
        });
        buttonStopPlayingRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playStop(root);
            }
        });


        return root;
    }

    public static boolean hasPermissions(Context context, String... permissions){
        if (context != null && permissions != null){
            for (String permission : permissions){
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION){
            isWork = grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RecordViewModel.class);
    }

    public void recordStart(View v){
        try {
            releaseRecorder();

            File outFile = new File(filename);
            if(outFile.exists()){
                outFile.delete();
            }

            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(filename);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void recordStop(View v){
        if(mediaRecorder != null){
            mediaRecorder.stop();
            mediaRecorder.reset();
            releaseRecorder();
        }
    }

    public void playStart(View v){
        try{
            releasePlayer();
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(filename);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void playStop(View v){
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }

    private void releaseRecorder(){
        if(mediaRecorder != null){
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private void releasePlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
        releaseRecorder();
    }

}