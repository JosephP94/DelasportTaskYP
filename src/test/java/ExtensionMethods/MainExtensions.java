package ExtensionMethods;

import com.google.gson.Gson;

public class MainExtensions {

    private static final Gson GSON = new Gson();
    /**
     * Converts a JSON string into an object of the specified class type.
     *
     * @param json the JSON string to convert
     * @param clazz the class type to convert to
     * @param <T> the type of the class
     * @return the converted object of type T
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }
}
