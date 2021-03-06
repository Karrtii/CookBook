# CookBook 📙

## Table of Contents
- [CookBook Description](#cookbook-description)
- [MoSCoW](#moscow)
- [Video Demonstration](#video-demonstration)
- [Curriculum Covered](#curriculum-covered)
- [What could have been improved](#what-could-have-been-improved)

<!--
  - [Login Page](#login-page)
  - [Exercises](#exercises)
  - [Workout](#workout)
  - [Calendar](#calendar)
- [Video Demonstration](#video-demonstration)

-->

## CookBook Description

<img width="182" alt="logo" src="https://user-images.githubusercontent.com/71009398/118670051-92f06d00-b7f6-11eb-9592-f00310768e11.png">

CookBook is an Android application where users can view and search for recipes, and create their own recipes. A friend of mine always complains that he constantly has to eat frozen food because he does not know any recipes and how to cook. So, this app if for anybody looking for an easy way to find recipes and eat actual meals.

## MoSCoW

| Must Have | Implemented |
| --- | --- |
| Users must be able to search for recipes based on dish name | :heavy_check_mark: |
| Users can view recipes based on categories | :heavy_check_mark: |
| Users must be able to create their own recipes | :heavy_check_mark: |
| Users must be able to view their own recipes at anytime | :heavy_check_mark: |
| When user create their recipes, they must be able to include ingridients and steps | :heavy_check_mark: |
| A user must be able to register themselves with an email and password | :heavy_check_mark: |
| A user must be able to log in with their email and password | :heavy_check_mark: |


| Should Have | Implemented |
| --- | --- |
| Users can see pictures of searched recipes | :heavy_check_mark: |
| When creating recipes, users can include an image of the dish | :x: |
| Users can favourite recipes, so that they can view them whenever | :heavy_check_mark: |

| Could Have | Implemented |
| --- | --- |
| Deleting created recipes | :heavy_check_mark: |
| Users can rate recipes | :x: |
| Users can see the rating of recipes | :x: |

| Won't Have | Implemented |
| --- | --- |
| Users being able to see other users' profiles |:x: |
| Commenting on recipes | :x: |
| Profile customization | :x: |
| Updating created recipes | :x: |

<!--

### Must have:
1. Users must be able to search for recipes based on dish name. - DONE
2. Users can view recipes based on categories - DONE
3. Users must be able to create their own recipes. - DONE
4. Users must be able to view their own recipes at anytime. - DONE
5. When user create their recipes, they must be able to include ingridients and steps. - DONE 
6. A user must be able to register themselves with an email and password. - DONE 
7. A user must be able to log in with their email and password. - DONE 

### Should have:
7. Users can see pictures of searched recipes. - DONE
8. When creating recipes, users can include an image of the dish. - FAILED
9. Users can favourite recipes, so that they can view them whenever. - DONE 

### Could have:
10. Users can rate recipes. - FAILED
11. Users can see the rating of recipes. -FAILED
12. Deleting created recipes. - DONE

### Won't have:
13. Users being able to see other users' profiles.
14. Commenting on recipes.
15. Profile customization.
16. Updating created recipes.

-->

## Video Demonstration

Youtube link - https://www.youtube.com/watch?v=zpE_CeONH_0

## Curriculum covered

| Curriculum | Covered |
| --- | --- |
| Layouts & Android basics | :heavy_check_mark: |
| Activities & Resources | :heavy_check_mark: |
| Intents & Fragments | :heavy_check_mark: |
| User Experience | :heavy_check_mark: |
| RecyclerViews | :heavy_check_mark: |
| Application Architecture | :heavy_check_mark: |
| Local Data Storage | :heavy_check_mark: |
| Networking | :heavy_check_mark: |
| Google Services | :heavy_check_mark: |
| Testing & Publishing | :x: |

<!--
Layouts & Android basics - YES <br />
Activities & Resources - YES <br />
Intents & Fragments - YES <br />
User Experience - YES <br />
RecyclerViews - YES <br />
Application Architecture - YES <br />
Local Data Storage - YES <br />
Networking - YES <br />
Google Services - YES <br />
Testing & Publishing - NO <br />
-->

## What could have been improved
1. Inserting an image when creating your own recipe

Could not be done because I could not save an image in SQLite. The implicit intent works like a charm, but nothing else does. I could have used Blob, but I didnt. I tried to save the path of the image from the gallery as a string, but it just didnt work with Glide, eventhough the path of the image and the path returned from the database were same, and there were no errors thrown. So i really didnt know what went wrong here. The code is still available and commented out in AddRecipeFragment under view folder.

I could have just removed the image all together and just have title, ingredients and steps, which would be more user friendly as it would not confuse users. But I still left it in there just to show that I did try to do it.

Temporary solution: Generating random food pictures from drawable folder.

![image](https://user-images.githubusercontent.com/71009398/118670901-4ce7d900-b7f7-11eb-866b-8d33da26d1b7.png)

2. Having search bar in the top bar

I tried to do it by following the material design guidelines, but I could not navigate between fragments and pass the data entered in the search bar in the top bar since the top bar is 'part of main acitvity' and everything else are fragments (i could be completely wrong about this). I am able to do only from one fragment, but if the search bar is in top bar, one should be able to search from any fragment.

Temporary solution: Created a new fragment just for searching, included in bottom nav menu.

![image](https://user-images.githubusercontent.com/71009398/118670968-5d984f00-b7f7-11eb-8245-254a8d0ee2df.png)

3. Have user added recipes be searched and viewed by other users.

The reason this is not implemented is because I wanted to show that I can work with local data storage as well. But if I would have implemented it, I would have saved all user added recipes in Firebase Realtime database, and call them when retrieving recipes from API.

4. Rating recipes and viewing them

Ran out of time before implementing this. Should be doable though. Solution would be to use Firebase Realtime Database and store ratings there and retrive.

5. Could add confirmation pop up when deleting a recipe.
6. Have the back button working in the top bar.
