import java.io.IOException;

public class Halt {

    public static void main(String[] args) throws IOException {
        new ProcessBuilder(
                "C:\\Windows\\System32\\shutdown.exe",
                "/s",
                "/f",
                "/t",
                "0"
        ).start();
    }

}
