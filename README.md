# Simple Instagram

## <a name="About">About</a>
This is an interview project. The Android app should be like the Instagram application but
simpler. 

## <a name="Functionalities">Functionalities</a>
- Get the objects(Post) from a json document in the endpoint:  https://photomaton.herokuapp.com/api/photo
``` Gson
[
    {
        "title": "Far more freely",
        "publishedAt": "2018-06-25T16:37:13.855733Z",
        "photo": "https://unsplash.it/600/300/?random",
        "id": 1,
        "comment": "Velit in iaculis viverra convallis primis lacus enim vehicula netus, per dapibus semper netus consectetur aenean fermentum felis amet hac augue fringilla duis praesent fusce ultrices commodo aliquet."
    }
 ]
    
```
- Displays the Posts pictures in the Home.
- Show the Post detail When the user taps into a photo. (landscape, portrait)
- Create a Post
    - Open the camera and take a picture
    - Add the information (title, comment)
    - Save the information in the Realm database.
    - Display it in the home list

NOTE:
Using Picasso the images for the same URL are only download one time while the cache is not Full. 
For this reason, The images are the same for each object with the URL "https://unsplash.it/600/300/?random".

## <a name="Libraries">Libraries</a>

- AppCompat (https://developer.android.com/tools/support-library/features.html)
- Design (https://developer.android.com/tools/support-library/features.html)
- RecyclerView (https://developer.android.com/tools/support-library/features.html)
- CardView (https://developer.android.com/tools/support-library/features.html)
- Constraint (https://developer.android.com/tools/support-library/features.html)
- Picasso (http://square.github.io/picasso/)
- Retrofit (http://square.github.io/retrofit/)
- OkHttp (http://square.github.io/okhttp/)
- GSON (https://github.com/google/gson)
- Realm (https://realm.io/blog/realm-for-android/)
