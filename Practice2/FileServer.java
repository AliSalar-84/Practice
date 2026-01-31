import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class FileServer {

    static Logger logger = Logger.getLogger("FileServer");

    static Map<Integer, Path> files = Map.of(
            1, Path.of("C:\\Users\\Ali\\Downloads\\Object-Oriented Analysis and Design with Applications " +
                    "(3rd -- Grady Booch, Robert A. Maksimchuk, Michael W. Engel, Bobbi -- ( WeLib.org ).pdf"),
            2, Path.of("C:\\Users\\Ali\\Downloads\\Formal Languages & Automata Introduction 7th Ed.pdf"),
            3, Path.of("C:\\Users\\Ali\\Downloads\\Data Structures and Algorithms in Java -- Michael T. Goodrich; " +
                    "Roberto Tamassia; Michael H. Goldwasser -- ( WeLib.org ).pdf"),
            4, Path.of("C:\\Users\\Ali\\Downloads\\Telegram Desktop\\Clean_Code_A_Handbook_of_Agile_Software_Craftsmanship,_Second_Edition.pdf"),
            5, Path.of("C:\\Users\\Ali\\Downloads\\Telegram Desktop\\SCWCD (Web Component Developer Certification) 2005.pdf")
    );

    public static void clientDownloadHandler(Socket socket) {

        try (Socket listener = socket;
             OutputStream out = new BufferedOutputStream(listener.getOutputStream());
             DataInputStream dis = new DataInputStream(listener.getInputStream())) {

            int option = dis.readInt();
            Optional<Path> fileOption = Optional.ofNullable(files.get(option))
                    .filter(Files::exists);

            if (fileOption.isEmpty()) {
                System.out.println("Resource not found");
                return;
            }

            logger.info("Sending file: " + fileOption.get().
                    getFileName().toString());

            Files.copy(fileOption.get(), out);
            out.flush();
            logger.info("File sent successfully to: " + socket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (ServerSocket listener = new ServerSocket(666)) {
            logger.info("Listening on port 666");

            while (true) {
                Socket client = listener.accept();
                logger.info("Client connected: " + client.getInetAddress());
                Thread.ofVirtual().start(() -> clientDownloadHandler(client));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
