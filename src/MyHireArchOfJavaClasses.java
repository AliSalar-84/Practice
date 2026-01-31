import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Comparator;

public class MyHireArchOfJavaClasses {

    private static long lastModified(Path p) {
        try {
            return Files.getLastModifiedTime(p).toMillis();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Comparator<Path> BY_LAST_MODIFIED_DESC =
            Comparator.comparing(MyHireArchOfJavaClasses::lastModified).reversed();

    public static void main(String[] args) throws Exception {

        Path start = Path.of(System.getProperty("user.dir"));

        try (var s = Files.find(start, Integer.MAX_VALUE,
                (path, attr) -> path.getFileName().toString().endsWith(".java"))) {

            s.map(Path::toAbsolutePath)
                    .sorted(BY_LAST_MODIFIED_DESC)
                    .forEach(System.out::println);
        }
    }

}
