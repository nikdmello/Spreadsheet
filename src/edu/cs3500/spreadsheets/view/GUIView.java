package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class GUIView extends JFrame implements SpreadsheetView {
  private ModelToTable mtt;
  private GUITableGraphics table;
  private JPanel topBar;

  public GUIView(ModelToTable mtt) {
    super();
    this.mtt = mtt;

    //initialize the top bar
    JButton acceptButton = new JButton("Ok");
    acceptButton.setPreferredSize(new Dimension(50, 30));
    JTextField formulaBar = new JTextField();
    formulaBar.setPreferredSize(new Dimension(590, 30));
    FlowLayout tbarlay = new FlowLayout();
    topBar = new JPanel();
    topBar.setLayout(tbarlay);
    topBar.add(acceptButton);
    topBar.add(formulaBar);
    topBar.setVisible(true);

    //initialize table
    table = new GUITableGraphics(mtt);
    table.setPreferredSize(new Dimension(640, 480));

    //add them to the frame
    getContentPane().add(topBar, BorderLayout.NORTH);
    getContentPane().add(table, BorderLayout.CENTER);

    //make the window
    this.setTitle("Microsoft Excel v2.0");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    this.pack();
    this.setVisible(true);
  }

  @Override
  public void render() {
    this.table = new GUITableGraphics(mtt);
  }
}

