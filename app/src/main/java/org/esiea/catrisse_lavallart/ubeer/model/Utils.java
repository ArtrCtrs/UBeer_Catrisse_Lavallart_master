package org.esiea.catrisse_lavallart.ubeer.model;

import android.graphics.drawable.Drawable;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by arthu on 10/12/2018.
 */

public class Utils {

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "loadedImage");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
