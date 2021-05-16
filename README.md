# CookBook
CookBook is an Android application where users can view and search for recipes, and create their own recipes. 

## MoSCoW
### Must have:
1. Users must be able to search for recipes based on dish name. - DONE
2. Users must be able to create their own recipes. - DONE
3. Users must be able to view their own recipes at anytime. - DONE
4. When user create their recipes, they must be able to include ingridients and steps. - DONE 
5. A user must be able to register themselves with an email, username and password. 
6. A user must be able to log in with their email and password.

### Should have:
7. Users can see pictures of searched recipes. - DONE
8. When creating recipes, users can include an image of the dish. - FAILED
9. Users can favourite recipes, so that they can view them whenever. 

### Could have:
10. Users can rate recipes. - FAILED
11. Users can see the rating of recipes. -FAILED
12. Deleting created recipes. - DONE

### Won't have:
13. Users being able to see other users' profiles.
14. Commenting on recipes.
15. Profile customization.
16. Updating created recipes.

## What could have been improved
1. Inserting an image when creating your own recipe

Could not be done because I could not save an image in SQLite. The implicit intent works like a charm, but nothing else does. I could have used Blob, but I didnt. I tried to save the path of the image from the gallery as a string, but it just didnt work with Glide, eventhough the path of the image and the path returned from the database were same, and there were no errors thrown. So i really didnt know what went wrong here. The code is still available and commented out in AddRecipeFragment under view folder.

Temporary solution: Generating random food pictures from drawable folder.

2. Having search bar in the top bar

I tried to do it by following the material design guidelines, but I could not navigate between fragments and pass the data entered in the search bar in the top bar since the top bar is 'part of main acitvity' and everything else are fragments (i could be completely wrong about this). I am able to do only from one fragment, but if the search bar is in top bar, one should be able to search from any fragment.

Temporary solution: Creeated a new fragment just for searching, included in bottom nav menu.
