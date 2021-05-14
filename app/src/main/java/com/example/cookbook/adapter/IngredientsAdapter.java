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
import com.example.cookbook.model.Ingredients;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{

    public ArrayList<Ingredients> ingredientsList;
    final private OnListItemClickListener onClickListener;
    private Context context;

    public IngredientsAdapter(ArrayList<Ingredients> ingredientsList, OnListItemClickListener onClickListener, Context context) {
        this.ingredientsList = ingredientsList;
        this.onClickListener = onClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_recipe_detail_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        holder.ingredient.setText(ingredientsList.get(position).getIngredients());
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }


    /////////////////////////////////////


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView ingredient;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            ingredient = itemView.findViewById(R.id.ingredients);
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
