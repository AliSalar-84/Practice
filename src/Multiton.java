import java.util.HashMap;

public class Multiton {

    private Multiton() {}

    private static final HashMap<String, Multiton> instance = new HashMap<>();

    public static Multiton getInstance(String key) {
        if (!instance.containsKey(key)) {
            instance.put(key, new Multiton());
        }
        return instance.get(key);
    }

}
