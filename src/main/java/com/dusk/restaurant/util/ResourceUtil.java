package com.dusk.restaurant.util;

import com.dusk.restaurant.App;

import java.net.URL;
import java.util.Objects;

public class ResourceUtil {

    public static String getResourceFrom(String name) {
        return getResource(name).toExternalForm();
    }

    public static URL getResource(String name) {
        return Objects.requireNonNull(App.class.getResource(name));
    }

}
