package com.soulraven.ccase.trainer;

import com.soulraven.ccase.data.CluesDao;
import com.soulraven.ccase.domain.Clue;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TrainerFrame extends JFrame {

    private JLabel bgLabel;
    private JScrollPane leftPane;
    private JPanel levelsPanel;
    private JScrollPane rightPane;
    private JPanel cluesPanel;
    private List<Clue> clues;

    public TrainerFrame() {
        init();
    }

    private void init() {
        clues = new CluesDao().readAllClues();

        getContentPane().setLayout(new BorderLayout(2, 2));
        getContentPane().add(getBgLabel(), BorderLayout.CENTER);
        getContentPane().add(getLeftPane(), BorderLayout.WEST);
        getContentPane().add(getRightPane(), BorderLayout.EAST);
//        getContentPane().add(getRightPanel(), BorderLayout.EAST);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JLabel getBgLabel() {
        if (bgLabel == null) {
            bgLabel = new JLabel();
            bgLabel.setIcon(new ImageIcon("1.png"));
        }
        return bgLabel;
    }

    public JScrollPane getLeftPane() {
        if (leftPane == null) {
            leftPane = new JScrollPane();
            leftPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            leftPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            leftPane.setViewportBorder(new LineBorder(Color.RED));
            leftPane.setViewportView(getLevelsPanel());
        }
        return leftPane;
    }

    public JPanel getLevelsPanel() {
        if (levelsPanel == null) {
            levelsPanel = new JPanel();
            levelsPanel.setLayout(new BoxLayout(levelsPanel, BoxLayout.Y_AXIS));
            JButton comp = new JButton();
            comp.setText("Level 1");
            comp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // save? & load new bg & clues
                }
            });
            levelsPanel.add(comp);
        }
        return levelsPanel;
    }

    public JScrollPane getRightPane() {
        if (rightPane == null) {
            rightPane = new JScrollPane();
            rightPane.setPreferredSize(new Dimension(250, 80));
            rightPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            rightPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            rightPane.setViewportBorder(new LineBorder(Color.BLUE));
            rightPane.setViewportView(getCluesPanel());
        }
        return rightPane;
    }

    public JPanel getCluesPanel() {
        if (cluesPanel == null) {
            cluesPanel = new JPanel();
            cluesPanel.setLayout(new BoxLayout(cluesPanel, BoxLayout.Y_AXIS));
            for (Clue clue : clues) {
                JToggleButton comp = buildToggleButton(clue);
                cluesPanel.add(comp);
            }
        }
        return cluesPanel;
    }

    private JToggleButton buildToggleButton(Clue clue) {
        JToggleButton comp = new JToggleButton();
        comp.setText(clue.getText());
        comp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton source = (JToggleButton) e.getSource();
                if (source.isSelected()) {
                    Component[] components = cluesPanel.getComponents();
                    for (Component c : components) {
                        if (JToggleButton.class.isAssignableFrom(c.getClass())) {
                            JToggleButton child = (JToggleButton) c;
                            if (child != source) {
                                child.setSelected(false);
                            }
                        }
                    }
                }
            }
        });
        return comp;
    }

    public static void main(String[] args) {
        new TrainerFrame().setVisible(true);
    }
}
