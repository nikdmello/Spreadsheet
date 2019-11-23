package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import edu.cs3500.spreadsheets.controller.ControllerViewRequester;
import edu.cs3500.spreadsheets.model.Coord;

public class GUIView extends JFrame implements SpreadsheetView, ActionListener {
  private ModelToTable mtt;
  private GUITableGraphics table;
  private ControllerViewRequester cvr;
  private JPanel topBar;
  private JTextField formulaBar;

  public GUIView(ModelToTable mtt, ControllerViewRequester cvr) {
    super();
    this.mtt = mtt;
    this.cvr = cvr;

    //initialize the top bar
    JButton acceptButton = new JButton("Ok");
    acceptButton.setPreferredSize(new Dimension(50, 30));
    acceptButton.addActionListener(this);
    acceptButton.setActionCommand("OkPressed");
    JButton delButton = new JButton("X");
    delButton.setPreferredSize(new Dimension(50, 30));
    delButton.addActionListener(this);
    delButton.setActionCommand("Del");

    formulaBar = new JTextField();
    formulaBar.setPreferredSize(new Dimension(540, 30));
    FlowLayout tbarlay = new FlowLayout();
    topBar = new JPanel();
    topBar.setLayout(tbarlay);
    topBar.add(acceptButton);
    topBar.add(delButton);
    topBar.add(formulaBar);
    topBar.setVisible(true);

    //initialize table
    table = new GUITableGraphics(mtt, formulaBar);
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
    table.updateTable(table.selectedCell().col, table.selectedCell().row);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    if(action.equals("OkPressed")){
      if(formulaBar.getText().equals("")){
        return;
      }
      if(table.selectedCell() == null){
        return;
      }
      this.cvr.requestCell(table.selectedCell().row,
              table.selectedCell().col, formulaBar.getText());
    }
    if(action.equals("Del")){
      this.cvr.delCell(table.selectedCell().row, table.selectedCell().col);
      this.formulaBar.setText("");
    }
  }
}

