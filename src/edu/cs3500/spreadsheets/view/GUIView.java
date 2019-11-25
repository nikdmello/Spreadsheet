package edu.cs3500.spreadsheets.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import edu.cs3500.spreadsheets.controller.ControllerViewRequester;

public class GUIView extends JFrame implements SpreadsheetView, ActionListener {
  private ModelToTable mtt;
  private GUITableGraphics table;
  private ControllerViewRequester cvr;
  private JPanel topBar;
  private JPanel bottomBar;
  private JTextField formulaBar;
  private JTextField fileBar;

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

    //initialize bottom bar
    //initialize the top bar
    JButton loadtButton = new JButton("Load");
    loadtButton.setPreferredSize(new Dimension(70, 30));
    loadtButton.addActionListener(this);
    loadtButton.setActionCommand("load");
    JButton saveButton = new JButton("Save");
    saveButton.setPreferredSize(new Dimension(70, 30));
    saveButton.addActionListener(this);
    saveButton.setActionCommand("save");
    fileBar = new JTextField();
    fileBar.setPreferredSize(new Dimension(500, 30));
    FlowLayout bbarlay = new FlowLayout();
    bottomBar = new JPanel();
    bottomBar.setLayout(bbarlay);
    bottomBar.add(loadtButton);
    bottomBar.add(saveButton);
    bottomBar.add(fileBar);
    bottomBar.setVisible(true);

    //add them to the frame
    getContentPane().add(topBar, BorderLayout.NORTH);
    getContentPane().add(table, BorderLayout.CENTER);
    getContentPane().add(bottomBar, BorderLayout.SOUTH);

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
    table.updateTable();
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
    if(action.equals("load")){
      if(!fileBar.getText().equals("")){
        this.cvr.loadFile(fileBar.getText());
      }
    }
    if(action.equals("save")){
      if(!fileBar.getText().equals("")){
        this.cvr.saveFile(fileBar.getText());
      }
    }
  }
}

