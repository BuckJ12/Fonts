import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontViewer {

    private static int fontIndex = 0;
    private static String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private static JLabel label = new JLabel();
    private static JPanel panel = new JPanel();
    private static JTextArea outputTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Font Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JButton nextButton = new JButton("Next Font");
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	changeFont(-1);
                }
            });
            
            

            JButton prevButton = new JButton("Previous Font");
            prevButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeFont(1);
                }
            });
            outputTextArea = new JTextArea();
            outputTextArea.setEditable(false);
            outputTextArea.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ\nabcdefghijklmnopqrstuvwxyz");

            frame.add(prevButton, BorderLayout.WEST);
            panel.add(label);
            frame.add(panel, BorderLayout.SOUTH);
            frame.add(nextButton, BorderLayout.EAST);
            frame.add(outputTextArea, BorderLayout.CENTER);

            updateFont();

            frame.setPreferredSize(new Dimension(1200, 900));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void updateFont() {
        String fontName = fonts[fontIndex];
        label.setText(fontName);
        label.setFont(new Font(fontName, Font.PLAIN, 20));
        outputTextArea.setFont(new Font(fontName, Font.PLAIN, 32));
    }
    
    private static void changeFont(int dist) {
    	fontIndex = (fontIndex - dist + fonts.length) % fonts.length;
        updateFont();
    }
}
