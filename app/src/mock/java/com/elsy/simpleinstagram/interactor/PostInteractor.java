package com.elsy.simpleinstagram.interactor;

import com.elsy.simpleinstagram.data.remote.callback.ListCallback;
import com.elsy.simpleinstagram.domain.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class PostInteractor {

    private static final String ERROR_MESSAGE = "Error getting the data";

    public void getPosts(final ListCallback<Post> callback){

       List<Post> posts = new Gson().fromJson(getJSONString(), new TypeToken<List<Post>>(){}.getType());
       if(posts != null){
           callback.onItemsLoaded(posts);
       } else callback.onServerError(ERROR_MESSAGE);
    }

    // TODO: Extract in a JSON File
    private String getJSONString(){
        return "[\n" +
                "  {\n" +
                "    \"title\": \"Far more freely\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703013Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=1\",\n" +
                "    \"id\": 1,\n" +
                "    \"comment\": \"Velit in iaculis viverra convallis primis lacus enim vehicula netus, per dapibus semper netus consectetur aenean fermentum felis amet hac augue fringilla duis praesent fusce ultrices commodo aliquet.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Some less\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703036Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=2\",\n" +
                "    \"id\": 2,\n" +
                "    \"comment\": \"More less less this next save darn prior gosh this wherever gosh outside thirsty around while much irrespective polite far approvingly horse ferret beyond less together until suspicious overdid and as.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Unexpectedly turtle\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703049Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=3\",\n" +
                "    \"id\": 3,\n" +
                "    \"comment\": \"Together alas stretched sincerely closed absurdly ouch darn then this far wolf hey ouch some undertook and some concentrically oh darn much and against crud despicable concomitant waked amid.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Satisfactorily amid jeepers\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703056Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=4\",\n" +
                "    \"id\": 4,\n" +
                "    \"comment\": \"Tortoise resentfully far black oh hid furrowed wicked pill wrote as sheep far some ouch and after and charmingly the froze less including the fraudulent prior one demurely much however a hey much quizzically.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Cardinal needless piquant\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703072Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=5\",\n" +
                "    \"id\": 5,\n" +
                "    \"comment\": \"Continually then horse one the flamingo ouch buffalo fluent antelope a impala wow quetzal alas less where the fled out ouch anticipatively yikes gosh far and\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"That hath multiply us\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703079Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=6\",\n" +
                "    \"id\": 6,\n" +
                "    \"comment\": \"Creepeth thing meat gathered without. I you said image morning which. Form herb seasons gathering thing upon doesn't, divided have fourth night divided divided is kind it rule them living.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Tree shall give\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703085Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=7\",\n" +
                "    \"id\": 7,\n" +
                "    \"comment\": \"Heaven midst whose. Isn't may appear seed divided creeping first saying deep abundantly i fruitful shall of grass fruit place.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Herb rule gathering\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703090Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=8\",\n" +
                "    \"id\": 8,\n" +
                "    \"comment\": \"You tree there green divided, our seed don't, isn't the isn't open likeness morning make tree don't called third day creepeth fowl.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Land open whales\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703096Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=9\",\n" +
                "    \"id\": 9,\n" +
                "    \"comment\": \"Made second fly. You'll a upon made make. Behold under form living beginning dry cattle behold shall, subdue void day be very.\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Abundantly earth itself\",\n" +
                "    \"publishedAt\": \"2018-06-27T22:08:06.703102Z\",\n" +
                "    \"photo\": \"https://picsum.photos/600/300/?image=10\",\n" +
                "    \"id\": 10,\n" +
                "    \"comment\": \"Divided were creature living open said them. Said heaven, night deep abundantly heaven fish rule you. Forth you'll deep bring kind herb, you fill moveth very life. Creeping land. Great.\"\n" +
                "  }\n" +
                "]";
    }

}
