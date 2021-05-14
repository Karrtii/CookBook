package com.example.cookbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookbook.R;
import com.example.cookbook.model.CategoryList;
import com.example.cookbook.model.RecipeList;
import com.example.cookbook.model.RecipeTitle;

import java.util.ArrayList;

public class RecipesFromCategoryAdapter extends RecyclerView.Adapter<RecipesFromCategoryAdapter.ViewHolder>{

    public ArrayList<RecipeTitle> recipeLists;
    final private OnListItemClickListener onClickListener;
    private Context context;

    public RecipesFromCategoryAdapter(ArrayList<RecipeTitle> recipeLists, OnListItemClickListener onClickListener, Context context) {
        this.recipeLists = recipeLists;
        this.onClickListener = onClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public RecipesFromCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_recipes_from_category_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesFromCategoryAdapter.ViewHolder holder, int position) {
        //holder.image.setImageResource(recipeLists.get(position).getImageId());
        Glide.with(context).load(recipeLists.get(position).getImageUrl()).into(holder.image);
        holder.title.setText(recipeLists.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recipeLists.size();
    }


    /////////////////////////////////////


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            image = itemView.findViewById(R.id.image_recipe1);
            title = itemView.findViewById(R.id.text_recipe1);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onListItemClickListener(getAdapterPosition());
        }
    }

    ////////////////////////////////////////////

    public interface OnListItemClickListener
    {
        void onListItemClickListener(int position);
    }

}
