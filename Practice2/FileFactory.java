import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileFactory {

    public static File createClassFile(String name, String sourceDirectory, String type) {

        var dir = (sourceDirectory == null ||
                sourceDirectory.isBlank()) ? "src" : sourceDirectory;

        File file = new File(dir + "/" + name + ".java");

        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            writeJavaClass(file, name, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

    private static void writeJavaClass(File file, String className, String type) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {

            String declaration = switch (type) {
                case "interface" -> "public interface " + className;
                case "enum" -> "public enum " + className;
                case "record" -> "public record " + className;
                default -> "public class " + className;
            };

            writer.write(declaration + " {\n\n");
            writer.write("    // TODO: Add fields & methods\n\n");
            writer.write("}\n");
        }
    }

    private static final List<String> SOURCE_DIRS = List.of("src", "Practice2", "Practice4", "Test");

    public static void deleteClassFile(String className) {
        String fileName = className + ".java";

        for (String dir : SOURCE_DIRS) {
            File fileDir = new File(dir);

            if (!(findFile(fileDir, fileName) instanceof File target) || !fileDir.exists())
                continue;

            if (target.delete())
                System.out.println("Deleted: " + target);
            else
                System.out.println("Failed to delete: " + target.getAbsolutePath());

            return;
        }

        System.out.println("Source file not found in any source directory: " + fileName);
    }

    private static File findFile(File directory, String fileName) {
        File[] files = directory.listFiles();
        if (files == null) return null;

        for (File file : files) {

            if (file.isDirectory()) {
                File found = findFile(file, fileName);
                if (found != null) return found;
            }

            if (file.isFile() && file.getName().equals(fileName)) {
                return file;
            }
        }

        return null;
    }

}

