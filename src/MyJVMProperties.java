import java.util.Properties;

public class MyJVMProperties {

    public static void getJVMProperties() {
        Properties jvm = System.getProperties();
        jvm.forEach((k, v) -> {
            String key = k.toString();
            if (key.contains("java.vm"))
                System.out.println(key + ":" + v);
        });
    }

    public static void getSunProperties() {
        Properties sunProperties = System.getProperties();
        sunProperties.forEach((k, v) -> {
            String key = k.toString();
            if (key.contains("sun"))
                System.out.println(key + ":" + v);
        });
    }

    public static void getUserProperties() {
        Properties userProperties = System.getProperties();
        userProperties.forEach((k, v) -> {
            String key = k.toString();
            if (key.contains("user"))
                System.out.println(key + ":" + v);
        });
    }

    public static void getOSProperties() {
        Properties osProperties = System.getProperties();
        osProperties.forEach((k, v) -> {
            String key = k.toString();
            if (key.contains("os"))
                System.out.println(key + ":" + v);
        });
    }

    public static void getFileAndPathProperties() {
        Properties fileAndPathProperties = System.getProperties();
        fileAndPathProperties.forEach((k, v) -> {
            String key = k.toString();
            if (key.contains("file") || key.contains("path"))
                System.out.println(key + ":" + v);
        });
    }

    public static void main(String[] args) {
        MyJVMProperties.getOSProperties();

    }

}
