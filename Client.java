
// chatting application  one to one
//package chatting.aplication;  // is optional create package

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*; // there all are java packages in predefine
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener { // public is access specifire , class is key word use create class,
                                                // implements is key use for interface classess , ActionListener is
                                                // interface in awt event package
  JButton b1; // JButton is class in swing package
  static JPanel p2; // static is key word access without create object that variable , JPanel is
                    // class in swing package
  static JTextField t1;// JTextField is class in swing package
  static Box vertical = Box.createVerticalBox(); // Box is class in swing package
  static JFrame frame = new JFrame(); // JFrame is class in swing , new is keyword is create class object , frame is
                                      // refference variabel
  static DataOutputStream dos;

  Client() { // Client is Constructor no return type and same name as a class name
    frame.setLayout(null); // frame is object that JFrame class and setLayout is method/function is JFrame
                           // class in
    JPanel p1 = new JPanel(); // JPanel that class creating object
    p1.setBackground(new Color(7, 94, 45));
    p1.setBounds(0, 0, 500, 75);
    frame.add(p1);
    p1.setLayout(null);
    p2 = new JPanel();
    p2.setBounds(0, 77, 498, 580);
    p2.setBackground(Color.WHITE);
    frame.add(p2);

    // add image arrow icon
    ImageIcon arrow1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png")); // add image path
    Image arrow2 = arrow1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon arrow3 = new ImageIcon(arrow2);
    JLabel l1 = new JLabel(arrow3);
    l1.setBounds(5, 20, 25, 25);
    p1.add(l1);

    // add image for profile
    ImageIcon dp1 = new ImageIcon(ClassLoader.getSystemResource("icons/gojo.png")); // add image path
    Image dp2 = dp1.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
    ImageIcon dp3 = new ImageIcon(dp2);
    JLabel l2 = new JLabel(dp3);
    l2.setBounds(40, 20, 40, 40);
    p1.add(l2);

    // add image video icon
    ImageIcon video1 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png")); // add image path
    Image video2 = video1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon video3 = new ImageIcon(video2);
    JLabel l3 = new JLabel(video3);
    l3.setBounds(300, 20, 25, 25);
    p1.add(l3);

    // add image phone icon
    ImageIcon phone1 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png")); // add image path
    Image phone2 = phone1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon phone3 = new ImageIcon(phone2);
    JLabel l4 = new JLabel(phone3);
    l4.setBounds(360, 20, 25, 25);
    p1.add(l4);

    // add image more option icon
    ImageIcon morevert1 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png")); // add image path
    Image morevert2 = morevert1.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
    ImageIcon morevert3 = new ImageIcon(morevert2);
    JLabel l5 = new JLabel(morevert3);
    l5.setBounds(450, 20, 10, 50);
    p1.add(l5);

    // add jlabel for writing same text
    JLabel l6 = new JLabel("Gojo");
    l6.setBounds(110, 15, 100, 20);
    l6.setForeground(Color.GREEN);
    l6.setFont(new Font("SAN_SERIF", Font.ITALIC, 20));
    p1.add(l6);

    // label for writing text
    JLabel l7 = new JLabel("Active NOW");
    l7.setBounds(110, 40, 100, 20);
    l7.setForeground(Color.GREEN);
    l7.setFont(new Font("SAN_SERIF", Font.ITALIC, 16));
    p1.add(l7);

    // add textfield show messagess
    t1 = new JTextField();
    t1.setBounds(0, 658, 358, 50);
    t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
    t1.addActionListener(this);
    frame.add(t1);

    // add button for send

    b1 = new JButton("send");
    b1.setBounds(360, 658, 140, 40);
    b1.setBackground(new Color(7, 94, 84));
    b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
    b1.addActionListener(this);
    frame.add(b1);

    l1.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e2) {
        System.exit(0);
        frame.setVisible(false);
      }
    });

    frame.getContentPane().setBackground(Color.BLACK);
    frame.setUndecorated(true);
    frame.setSize(500, 700);
    frame.setLocation(350, 50);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent e1) {
    try {
      String out = t1.getText();

      JPanel out1 = formatLabel(out);

      p2.setLayout(new BorderLayout());

      JPanel right = new JPanel(new BorderLayout());
      right.add(out1, BorderLayout.LINE_END);
      vertical.add(right);
      vertical.add(Box.createVerticalStrut(15));

      p2.add(vertical, BorderLayout.PAGE_START);
      dos.writeUTF(out);

      t1.setText("");

      frame.repaint(); // when click the send button then frame repaint call/ referesh
      frame.invalidate();
      frame.validate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static JPanel formatLabel(String out) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
    output.setFont(new Font("Tahoma", Font.PLAIN, 14));
    output.setBackground(new Color(37, 211, 102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15, 15, 15, 50));

    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    JLabel time = new JLabel();
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);

    panel.add(output);

    return panel;

  }

  public static void main(String[] vin) {
    new Client();

    try {
      Socket s1 = new Socket("127.0.0.1", 5001);
      DataInputStream din = new DataInputStream(s1.getInputStream());
      dos = new DataOutputStream(s1.getOutputStream());

      while (true) {
        p2.setLayout(new BorderLayout());

        String msg = din.readUTF();
        JPanel panel1 = formatLabel(msg);

        JPanel left = new JPanel(new BorderLayout());
        left.add(panel1, BorderLayout.LINE_START);
        vertical.add(left);
        vertical.add(Box.createHorizontalStrut(15));
        p2.add(vertical, BorderLayout.PAGE_START);
        frame.validate();
      }

    } catch (Exception e1) {
      e1.printStackTrace();
    }

  }
}
