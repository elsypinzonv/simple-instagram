package com.elsy.simpleinstagram.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

public class ActivityHelper {

    /**
     * Stars a activity and finish the current activity caller.
     * @param activity caller activity.
     * @param classTo class of the activity to redirect.
     */
    public static void sendAndFinish(Activity activity, Class classTo) {
        Intent intent = new Intent().setClass(activity, classTo);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void begin(Activity activity, Class classTo, Bundle extras){
        Intent intent = new Intent().setClass(activity, classTo);
        intent.putExtras(extras);
        ActivityCompat.startActivity(activity, intent, extras);
    }

    public static void begin(Activity activity, Class classTo){
        Intent intent = new Intent().setClass(activity, classTo);
        activity.startActivity(intent);
    }

    /**
     * Helper method to determine if a object is null.
     * This class throws a {@link NullPointerException} whether the object is null.
     *
     * @param reference object to test
     * @param <T> concrete class of the object to test.
     * @return The {@code reference} if not null.
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
