package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Menu extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JTextArea output;
    static AddStart newContentPane = new AddStart();
    private JScrollPane scrollPane;

    //MODIFIES: this
    //EFFECTS: Creates a new menu with menu item "Add Destination"
    public JMenuBar createMenuBar() {
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuBar.add(menu);
        JMenuItem menuAddStart = new JMenuItem("+ Add Starting Point",
                KeyEvent.VK_T);
        menuAddStart.addActionListener(newContentPane);
        //menu.add(menuAddStart); now is an add tab button
        JMenuItem menuAddDest = new JMenuItem("+ Add Destination",
                KeyEvent.VK_T);
        menuAddDest.addActionListener(newContentPane);
        menu.add(menuAddDest);
        return menuBar;
    }

    //MODIFIES: this
    //EFFECTS: Creates a new contentpane
    public Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        return contentPane;
    }

    //MODIFIES: this
    //EFFECTS: Creates and sets up JFrame and sets visible
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Bus Route Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Menu demo = new Menu();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.setSize(450, 550);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
