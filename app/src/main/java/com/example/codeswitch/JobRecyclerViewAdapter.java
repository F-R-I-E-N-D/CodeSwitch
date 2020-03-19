package com.example.codeswitch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JobRecyclerViewAdapter extends RecyclerView.Adapter<JobRecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<JobItem> myItemList;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public ImageView RecyclerImageView;
        public TextView RecyclerTitleText;
        public TextView RecyclerSkillsList;

        public RecyclerViewHolder(View itemView){
            super(itemView);
            RecyclerImageView = itemView.findViewById(R.id.ImageView);
            RecyclerTitleText = itemView.findViewById(R.id.TitleText);
            RecyclerSkillsList = itemView.findViewById(R.id.SkillsText);
        }
    }

    public JobRecyclerViewAdapter(ArrayList<JobItem> jobItemList) {
        myItemList = jobItemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_view, parent, false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        JobItem currentItem = myItemList.get(position);

        holder.RecyclerImageView.setImageResource(currentItem.getJobImageResource());
        holder.RecyclerTitleText.setText(currentItem.getJobTitleText());
        holder.RecyclerSkillsList.setText(currentItem.getJobSkillsText());
    }

    @Override
    public int getItemCount() {
        return myItemList.size();
    }
}
