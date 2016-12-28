package com.soulraven.ccase.trainer;

import com.soulraven.ccase.data.CluesDao;
import com.soulraven.ccase.data.CluesDaoImpl;
import com.soulraven.ccase.domain.Clue;
import sun.java2d.pipe.SpanIterator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TrainerFrame extends JFrame {

    private JLabel bgLabel;
    private JScrollPane leftPane;
    private JList levelsList;
    private JScrollPane rightPane;
    private JList cluesList;
    private List<Clue> clues;

    public TrainerFrame() {
        init();
    }

    private void init() {
        clues = new CluesDaoImpl().getAllClues();

        getContentPane().setLayout(new BorderLayout(2, 2));
        getContentPane().add(getBgLabel(), BorderLayout.CENTER);
        getContentPane().add(getLeftPane(), BorderLayout.WEST);
        getContentPane().add(getRightPane(), BorderLayout.EAST);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JLabel getBgLabel() {
        if (bgLabel == null) {
            bgLabel = new JLabel();
            bgLabel.setIcon(new ImageIcon("1.png"));
            bgLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (getCluesList().getSelectedIndex() != -1) {
                        Clue c = (Clue) getCluesList().getSelectedValue();
                        c.setLocation(new Point(e.getX(), e.getY()));
                        getCluesList().repaint();
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                }
            });
        }
        return bgLabel;
    }

    private JScrollPane getLeftPane() {
        if (leftPane == null) {
            leftPane = new JScrollPane();
            leftPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            leftPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            leftPane.setViewportBorder(new LineBorder(Color.RED));
            leftPane.setViewportView(getLevelsList());
        }
        return leftPane;
    }

    private JList getLevelsList() {
        if (levelsList == null) {
            levelsList = new JList(new String[]{"Level 1", "Level 2"});
            levelsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            levelsList.setSelectedIndex(0);
            levelsList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    System.out.println(e.getFirstIndex());
                    System.out.println(e.getLastIndex());
                    if (levelsList.getSelectedIndex() == -1) {
                        levelsList.setSelectedIndex(e.getFirstIndex());
                        System.out.println("reverting selection");
                    }
                }
            });
        }
        return levelsList;
    }

    private JScrollPane getRightPane() {
        if (rightPane == null) {
            rightPane = new JScrollPane();
            rightPane.setPreferredSize(new Dimension(250, 80));
            rightPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            rightPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            rightPane.setViewportBorder(new LineBorder(Color.BLUE));
            rightPane.setViewportView(getCluesList());
        }
        return rightPane;
    }

    private JList getCluesList() {
        if (cluesList == null) {
            cluesList = new JList(clues.toArray(new Clue[]{}));
            cluesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        return cluesList;
    }

    public static void main(String[] args) {
        new TrainerFrame().setVisible(true);
    }
}
