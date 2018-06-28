# Simple Instagram

## <a name="About">About</a>
This is an interview project. The Android app should be like the Instagram application but
simpler. 

## <a name="Build">Build Variants</a>
- **prodDebug**
Use it to Run the project using the endpoint: https://photomaton.herokuapp.com/api/photo

- **mockDebug**
Use it to Run the project using a mock JSON instead of the endpoint.

## <a name="Functionalities">Functionalities</a>
- Gets the objects(Post) from a JSON document in the endpoint:  https://photomaton.herokuapp.com/api/photo
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
- Shows the Post detail When the user taps into a photo. (landscape, portrait)
- Creates a Post
    - Opens the camera and take a picture
    - Adds the information (title, comment)
    - Saves the information in the Realm database.
    - Displays it on the home list

NOTE:
It uses the Picasso library to get the Images from the URL in the Post object. Picasso downloads the images from the URL and saves them in its cache. Picasso gets the image from the URL again when the picture is not cached anymore.
Since all the objects are using the same URL (https://unsplash.it/600/300/?random), the list of pictures shows the same image.

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
