package com.example.cookbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.R;
import com.example.cookbook.model.RecipeList;

import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private ArrayList<RecipeList> recipeLists;
    private Context context;

    public RecipeListAdapter(ArrayList<RecipeList> recipeLists, Context context) {
        this.recipeLists = recipeLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_categories_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(recipeLists.get(position).getImageId());
        holder.title.setText(recipeLists.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recipeLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_recipe1);
            title = itemView.findViewById(R.id.text_recipe1);
            }
        }
    }