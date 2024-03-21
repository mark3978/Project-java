//package chatting.aplication;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

//import javax.swing.ImageIcon;

public class Server implements ActionListener {
    JButton b1;
    JPanel p2;
    JTextField t1;
    static JFrame frame = new JFrame();
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dos;

    Server() {
        frame.setLayout(null);

        JPanel p1 = new JPanel();

        p1.setBackground(new Color(7, 94, 45));
        p1.setBounds(0, 0, 500, 75);
        frame.add(p1);
        p1.setLayout(null);

        p2 = new JPanel();
        p2.setBounds(0, 77, 498, 580);
         p2.setBackground(Color.WHITE);
        frame.add(p2);

        // image 1 arrow
        ImageIcon arrow1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image arrow2 = arrow1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon arrow3 = new ImageIcon(arrow2);
        JLabel l1 = new JLabel(arrow3);
        l1.setBounds(5, 20, 25, 25);
        p1.add(l1);

        ImageIcon dp1 = new ImageIcon(ClassLoader.getSystemResource("icons/sukuna.png"));
        Image dp2 = dp1.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon dp3 = new ImageIcon(dp2);
        JLabel l2 = new JLabel(dp3);
        l2.setBounds(40, 20, 40, 40);
        p1.add(l2);

        ImageIcon video1 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));//add  image path
        Image video2 = video1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon video3 = new ImageIcon(video2);
        JLabel l3 = new JLabel(video3);
        l3.setBounds(300, 20, 25, 25);
        p1.add(l3);

        ImageIcon phone1 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));//add image path
        Image phone2 = phone1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon phone3 = new ImageIcon(phone2);
        JLabel l4 = new JLabel(phone3);
        l4.setBounds(360, 20, 25, 25);
        p1.add(l4);

        ImageIcon morevert1 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png")); // add imagae path
        Image morevert2 = morevert1.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon morevert3 = new ImageIcon(morevert2);
        JLabel l5 = new JLabel(morevert3);
        l5.setBounds(450, 20, 10, 50);
        p1.add(l5);

        JLabel l6 = new JLabel("user1");
        l6.setBounds(110, 15, 100, 20);
        l6.setForeground(Color.GREEN);
        l6.setFont(new Font("SAN_SERIF", Font.ITALIC, 20));
        p1.add(l6);

        JLabel l7 = new JLabel("Active NOW");
        l7.setBounds(110, 40, 100, 20);
        l7.setForeground(Color.GREEN);
        l7.setFont(new Font("SAN_SERIF", Font.ITALIC, 16));
        p1.add(l7);

        t1 = new JTextField();
        t1.setBounds(0, 658, 358, 50);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        t1.addActionListener(this);
        frame.add(t1);

        b1 = new JButton("send");
        b1.setBounds(360, 658, 140, 50);
        b1.setBackground(new Color(7, 94, 45));
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
        b1.addActionListener(this);
        frame.add(b1);

        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e2) {
                System.exit(0);
                frame.setVisible(false);
            }
        });

        frame.getContentPane().setBackground(Color.WHITE);
        frame.setUndecorated(true);
        frame.setSize(500, 700);
        frame.setLocation(100, 50);
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
        new Server();

        try {
            try (ServerSocket seckt = new ServerSocket(5001)) {
                while (true) {
                    Socket s = seckt.accept();
                    DataInputStream din = new DataInputStream(s.getInputStream());
                    dos = new DataOutputStream(s.getOutputStream());

                    while (true) {
                        String msg = din.readUTF();
                        JPanel panel1 = formatLabel(msg);

                        JPanel left = new JPanel(new BorderLayout());
                        left.add(panel1, BorderLayout.LINE_START);
                        vertical.add(left);
                        frame.validate();
                    }

                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
