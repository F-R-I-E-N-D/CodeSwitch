package com.example.codeswitch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.RecyclerViewHolder> implements Filterable {

    private ArrayList<CourseItem> courseItemList;
    private ArrayList<CourseItem> fullCourseItemList;

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CourseItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullCourseItemList);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim(); //lowercase and removes spaces

                for (CourseItem item : fullCourseItemList){
                    if (item.getCourseTitleText().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            courseItemList.clear();
            courseItemList.addAll((List)results.values);
            System.out.println(results.values);
            notifyDataSetChanged();
        }
    };

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

    public CourseRecyclerViewAdapter(ArrayList<CourseItem> courseItemList) {
        this.courseItemList = courseItemList;
        fullCourseItemList = new ArrayList<>(courseItemList);   //make copy of list


    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_view, parent, false);
        RecyclerViewHolder rvh = new RecyclerViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        CourseItem currentItem = courseItemList.get(position);

        holder.RecyclerImageView.setImageResource(currentItem.getCourseImageResource());
        holder.RecyclerTitleText.setText(currentItem.getCourseTitleText());
        holder.RecyclerSkillsList.setText(currentItem.getCourseSkillsText());
    }

    @Override
    public int getItemCount() {
        return courseItemList.size();
    }
}
