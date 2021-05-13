package com.example.cookbook.adapter;

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

public class FavouritesRecipeListAdapter extends RecyclerView.Adapter<FavouritesRecipeListAdapter.ViewHolder>{

    private ArrayList<RecipeList> recipeLists;
    final private OnListItemClickListenerFavourites onClickListener;

    public FavouritesRecipeListAdapter(ArrayList<RecipeList> recipeLists, OnListItemClickListenerFavourites onClickListener) {
        this.recipeLists = recipeLists;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public FavouritesRecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_favourites_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesRecipeListAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource(recipeLists.get(position).getImageId());
        holder.title.setText(recipeLists.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recipeLists.size();
    }

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

    public interface OnListItemClickListenerFavourites
    {
        void onListItemClickListener(int position);
    }
}
