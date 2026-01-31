import java.util.logging.Logger;

public class Validator {

    static Logger logger = Logger.getLogger("Validator");

    public static void runValidation(Object object) throws IllegalAccessException {

        Class<?> clazz = object.getClass();

        for (var field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            Valid valid = field.getAnnotation(Valid.class);
            if (valid == null) continue;

            if (field.getType() != int.class) continue;

            int value = (int) field.get(object);

            if (value > valid.max()) {
                logger.warning(
                        object.toString()  + field.getName() + " exceeds maximum by " + (value - valid.max())
                );
            } else if (value < valid.min()) {
                logger.warning(
                        object.toString() + field.getName() + " is below minimum by " + (valid.min() - value)
                );
            }
        }
    }
}
