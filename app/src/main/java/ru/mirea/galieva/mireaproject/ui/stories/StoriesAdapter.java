package ru.mirea.galieva.mireaproject.ui.stories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.galieva.mireaproject.R;

import static android.os.Build.VERSION_CODES.R;
public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyViewHolder> {

    List<String> stories;
    Context context;

    public StoriesAdapter (Context ct, ArrayList<String> stories) {
        this.context = ct;
        this.stories = stories;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemStory;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);
            tvItemStory = itemView.findViewById(ru.mirea.galieva.mireaproject.R.id.textViewStory);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvItemStory.setText(stories.get(stories.size()-1));
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(ru.mirea.galieva.mireaproject.R.layout.storiesitem, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

}
