package com.example.cookbook.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cookbook.R;
import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.model.RecipeList;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class YourRecipesRecipeListAdapter extends RecyclerView.Adapter<YourRecipesRecipeListAdapter.ViewHolder>{


    public ArrayList<AddRecipe> recipeLists;
    final private OnListItemClickListenerYourRecipes onClickListener;
    private Context context;

    public YourRecipesRecipeListAdapter(ArrayList<AddRecipe> recipeLists, OnListItemClickListenerYourRecipes onClickListener, Context context) {
        this.recipeLists = recipeLists;
        this.onClickListener = onClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public YourRecipesRecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_your_recipes_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YourRecipesRecipeListAdapter.ViewHolder holder, int position) {
        //Setting the picture to a random food pictures included in @drawable since I cannot save and get the image from gallery in SQLite
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

    public interface OnListItemClickListenerYourRecipes
    {
        void onListItemClickListener(int position);
    }
}
