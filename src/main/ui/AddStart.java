package ui;

import askuser.AskBusNumber;
import askuser.AskBusStop;
import askuser.AskTime;
import timecalculator.ArriveTimeCalculator;
import timecalculator.DestTimeCalculator;
import timecalculator.TimeCalculator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddStart extends JPanel implements ActionListener {

    private int destnum = 0;
    private String busnum = "";
    private String time = "";
    private String busstop = "";
    private int waittime;
    JFrame frame;
    private int tabnumber;
    JPanel currentPane;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private FlowLayout flayout = new FlowLayout(FlowLayout.CENTER, 5, 0);

    public AddStart() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JPanel pnlTab = new JPanel(flayout);
        JButton addTab = new JButton("+ Add New Starting Point");
        addTab.setOpaque(false); //
        addTab.setBorder(null);
        addTab.setContentAreaFilled(false);
        addTab.setFocusPainted(false);
        addTab.setFocusable(false);
        pnlTab.add(addTab);
        addTab.setFocusable(false);
        addTab.addActionListener(this);
        tabbedPane.setVisible(true);
        add(tabbedPane);
        JComponent panel1 = makeTextPanel("");
        tabbedPane.addTab("Tab 1", null, panel1,
                "Does nothing");
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, pnlTab);
    }

    //EFFECTS: If button is pressed, intiate startpointdialog, otherwise initiate adddestdialog
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) (e.getSource());
        if (source instanceof JButton) {
            startPointDialog();
        } else {
            addDestDialog();
        }
    }

    //EFFECTS: Sets up Add Destination Dialog
    private void addDestDialog() {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        labels.add(new JLabel("Select Bus Stop", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);
        JComboBox<String> busstopList = busstopComboBox();
        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        controls.add(busstopList);
        p.add(controls, BorderLayout.CENTER);
        int input = JOptionPane.showOptionDialog(null, p, "New Destination",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        String testbusstop = (String) busstopList.getSelectedItem();

        destdialogaction(input, testbusstop, busstopList);
    }

    //MODIFIES: this
    //EFFECTS: Initiates actions done after pressing "OK"
    private void destdialogaction(int input, String testbusstop, JComboBox<String> busstopList) {
        if (input == JOptionPane.OK_OPTION) {
            if (checkInputs(testbusstop)) {
                destnum = destnum + 1;
                busstop = (String) busstopList.getSelectedItem();
                TimeCalculator tc = new ArriveTimeCalculator(busstop, busnum, time);
                waittime = tc.giveWaitTime(time, tc.giveArriveTime(time, busnum, busstop), waittime);
                time = tc.giveArriveTime(time, busnum, busstop);
                addDestComp();
            }
        }
    }

    //EFFECTS: Sets up Add Start Point dialog
    public void startPointDialog() {
        JPanel p = new JPanel(new BorderLayout(5, 5));

        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        labels.add(new JLabel("Enter Current Time", SwingConstants.RIGHT));
        labels.add(new JLabel("Enter Bus Number", SwingConstants.RIGHT));
        labels.add(new JLabel("Select Bus Stop", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField enterTime = new JTextField("");
        JTextField enterBusnum = new JTextField("");

        JComboBox<String> busstopList = busstopComboBox();
        p.add(controls, BorderLayout.CENTER);
        controls.add(enterTime);
        controls.add(enterBusnum);
        controls.add(busstopList);

        startpointdialogaction(busstopList, enterBusnum, enterTime, p);
    }

    //MODIFIES: this
    //EFFECTS: Initiates actions done after pressing "OK"
    private void startpointdialogaction(JComboBox<String> busstopList, JTextField enterBusnum,
                                        JTextField enterTime, JPanel p) {
        int input = JOptionPane.showOptionDialog(null, p, "New Starting Point",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        String testbusstop = (String) busstopList.getSelectedItem();
        String testbusnum = enterBusnum.getText();
        String testtime = enterTime.getText();
        if (input == JOptionPane.OK_OPTION) {
            if (checkInputs(testtime, testbusnum, testbusstop)) {
                busstop = (String) busstopList.getSelectedItem();
                busnum = enterBusnum.getText();
                time = enterTime.getText();
                waittime = 0;
                destnum = 0;
                createStartTab();
            }
        }
    }

    //EFFECTS: Creates combobox with bus stops
    private JComboBox<String> busstopComboBox() {
        String[] busStops = {"Nanaimo Station", "Granville", "UBC Exchange"};
        JComboBox<String> busstopList = new JComboBox<>(busStops);
        busstopList.setSelectedIndex(0);
        return busstopList;
    }

    AskBusNumber abn = new AskBusNumber();
    AskBusStop abs = new AskBusStop();

    //EFFECTS: Checks user inputs and returns message dialogs in case of error for Starting Points
    private boolean checkInputs(String time, String busnum, String busstop) {
        if (Objects.equals(time, "") | Objects.equals(busnum, "") | Objects.equals(busstop, "")) {
            JOptionPane.showMessageDialog(frame, "Please fill in all the blanks.", "Input error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!AskTime.isStringTime(time)) {
            JOptionPane.showMessageDialog(frame, "Invalid time (must be HH:MM, 24 hour).", "Input error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!abn.isStringBusNumber(busnum)) {
            JOptionPane.showMessageDialog(frame, "Invalid Bus Number.", "Input error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!abs.doesBusGoToStop(busstop, busnum)) {
            JOptionPane.showMessageDialog(frame,
                    "Bus does not go to this stop.", "Input error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    //EFFECTS: Checks user inputs and returns message dialogs in case of error for Destinations
    private boolean checkInputs(String busstop) {
        AskBusStop abs = new AskBusStop();
        if (Objects.equals(time, "") | Objects.equals(busnum, "") | Objects.equals(busstop, "")) {
            JOptionPane.showMessageDialog(frame, "Add starting point.", "Input error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!abs.doesBusGoToStop(busstop, busnum)) {
            JOptionPane.showMessageDialog(frame,
                    "Bus does not go to this stop.", "Input error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates start tab
    private void createStartTab() {
        tabnumber = tabnumber + 1;
        String title = "Route " + tabnumber;
        JPanel mainPane = new JPanel();
        currentPane = mainPane;
        TitledBorder titled = BorderFactory.createTitledBorder("Starting Point");
        addCompForBorder(titled, mainPane);
        tabbedPane.add(title, mainPane);
        initTabComponent(tabnumber);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        setSize(new Dimension(40, 20));
        setVisible(true);
    }

    //EFFECTS: Given a border, adds labels inside it
    void addCompForBorder(Border border, Container container) {
        JLabel inputs = new JLabel("Time: " + time + " | Bus Number: " + busnum + " | Bus Stop: " + busstop);
        ArriveTimeCalculator atc = new ArriveTimeCalculator(busstop, busnum, time);
        JLabel message = new JLabel(atc.giveMessage(busstop, busnum, time));
        JPanel comp = new JPanel(new GridLayout(2, 1), true);
        comp.add(inputs);
        comp.add(message);
        comp.setBorder(border);

        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(comp);
    }

    //EFFECTS: Adds labels inside Destination border, and creates new Destination border
    private void addDestComp() {
        JLabel inputs = new JLabel("Bus Stop: " + busstop);
        DestTimeCalculator dtc = new DestTimeCalculator(busstop, busnum, time, waittime);
        JLabel message = new JLabel(dtc.giveMessage(busstop, busnum, time, waittime));
        JPanel comp = new JPanel(new GridLayout(2, 1), true);
        comp.add(inputs);
        comp.add(message);
        TitledBorder border = BorderFactory.createTitledBorder("Destination " + destnum);
        comp.setBorder(border);

        currentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        currentPane.add(comp);
    }

    //MODIFIES: this
    //EFFECTS: Creates new buttontab
    private void initTabComponent(int i) {
        tabbedPane.setTabComponentAt(i,
                new ButtonTab(tabbedPane));
    }

    //EFFECTS: Makes new text panel
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
