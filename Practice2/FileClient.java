import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileClient {

    private static final String[] FILE_OPTIONS =

            {
                    "Object Oriented Analysis and Design",
                    "PeterLinz, Formal Language and automata",
                    "Data Structures with Java",
                    "Clean Architecture",
                    "Sun SCWD"

            };

    public static void main(String... args) {
        JFrame frame = new JFrame("ClientMenu");
        frame.setSize(450, 500);
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var fixedLocation = new Point((int) (screenSize.width - (1.1 * frame.getWidth()))
                , (int) (screenSize.height - (1.1 * frame.getHeight())));
        frame.setLocation(fixedLocation);
        frame.setResizable(false);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                frame.setLocation(fixedLocation);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox<String> options = new JComboBox<>(FILE_OPTIONS);
        JButton download = new JButton("Download");

        var panel = new JPanel();
        panel.add(options);
        panel.add(download);
        frame.add(panel);

        download.addActionListener(e -> {
                    var option = options.getSelectedIndex() + 1;
            try (Socket socket = new Socket("localhost", 666);
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var is = socket.getInputStream()) {

                dos.writeInt(option);
                dos.flush();

                var fileName = "download_" + FILE_OPTIONS[option - 1].replaceAll("[^a-zA-Z0-9.-]", "_") + ".pdf";
                var parent = Path.of("C:\\Users\\Ali\\Downloads");
                var savePath = parent.resolve(fileName);
                Files.copy(is, savePath);

                JOptionPane.showMessageDialog(frame, "Download completed: " + savePath.getFileName());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Server is not running or download failed: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

                }

        );

        frame.setVisible(true);
    }

}
