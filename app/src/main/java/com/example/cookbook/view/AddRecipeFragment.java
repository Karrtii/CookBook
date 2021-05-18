package com.example.cookbook.view;

import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.cookbook.R;
import com.example.cookbook.model.AddRecipe;
import com.example.cookbook.viewmodel.AddRecipeViewModel;

import java.io.File;
import java.util.Random;

public class AddRecipeFragment extends Fragment {

    private AddRecipeViewModel addRecipeViewModel;

    EditText recipeTitle, ingredients, steps;
    ImageView image;
    Button addImageButton, addRecipeButton;

    private String path;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    private static final int RESULT_OK = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        addRecipeViewModel  =
                new ViewModelProvider(this).get(AddRecipeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_recipe, container, false);

        recipeTitle = root.findViewById(R.id.addRecipeTitle);
        ingredients = root.findViewById(R.id.addIngredients);
        steps = root.findViewById(R.id.addSteps);
        image = root.findViewById(R.id.addImage);
        addImageButton = root.findViewById(R.id.addImageButton);
        addRecipeButton = root.findViewById(R.id.addRecipeButton);


        //Setting the picture to a random food pictures included in @drawable since I cannot save and get the image from gallery in SQLite
        final TypedArray imgs = getResources().obtainTypedArray(R.array.randomFoodImages);
        final Random rand = new Random();
        final int rndInt = rand.nextInt(imgs.length());
        final int resID = imgs.getResourceId(rndInt, 0);
        image.setImageResource(resID);


        addImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, IMAGE_PICK_CODE);
        });


        addRecipeButton.setOnClickListener(v -> {
            if(!(recipeTitle == null && ingredients == null && steps == null))
            {
                addRecipeViewModel.insert(new AddRecipe(recipeTitle.getText().toString(), ingredients.getText().toString(), steps.getText().toString()));
                Navigation.findNavController(getView()).navigate(R.id.action_navigation_add_recipe_to_navigation_yourRecipes);
            }
        });

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE)
        {
            image.setImageURI(data.getData());
            path = getRealPathFromURI(data.getClipData().getItemAt(0).getUri());
        }
    }

    private String getRealPathFromURI(Uri contentURI) {

        String thePath = "no-path-found";
        String[] filePathColumn = {MediaStore.Images.Media.DISPLAY_NAME};
        Cursor cursor = getActivity().getContentResolver().query(contentURI, filePathColumn, null, null, null);
        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            thePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return  thePath;
    }
}
