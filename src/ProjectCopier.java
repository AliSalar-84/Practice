import java.io.IOException;
import java.nio.file.*;
import java.nio.file.Files;
import java.util.stream.Stream;

public class ProjectCopier {

    public static void copy(String dest) throws IOException {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        Path father = projectDir.getParent();
        Path destination = father.resolve(dest);

        try (Stream<Path> paths = Files.walk(projectDir)) {
            paths.forEach(p -> {
                try {
                    Path destPath = destination.resolve(projectDir.relativize(p));
                    if (Files.isDirectory(p)) {
                        if (!Files.exists(destPath)) {
                            Files.createDirectories(destPath);
                        }
                    } else {
                        Files.copy(p, destPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}