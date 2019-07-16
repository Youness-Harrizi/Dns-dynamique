package client.guiClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame extends JFrame {
    protected static String serverName;
    protected static String domainName;
    protected static String password;
    protected static int port;
    public Font font= new Font("Arial",Font.BOLD,15);

    public String getServerName() {
        return serverName;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getPassword() {
        return password;
    }

    private JButton submitButton  = new JButton("submit");

    private JTextField txtA = new JTextField();
    private JTextField txtB = new JTextField();
    private JPasswordField txtC = new JPasswordField(20);
    private JTextField txtD = new JTextField();


    private JLabel lblA = new JLabel("server name :");
    private JLabel lblB = new JLabel("domain name :");
    private JLabel lblC = new JLabel("password :");
    private JLabel lblD = new JLabel("port :");


    public MyFrame(){
        setTitle("Penjumlahan");
        setSize(800,400);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponent();
        initEvent();
    }

    private void initComponent(){
       // btnTutup.setBounds(300,130, 80,25);
        submitButton.setBounds(300,100, 140,50);
        submitButton.setFont(font);

        txtA.setBounds(150,10,100,40);
        txtB.setBounds(150,55,100,40);
        txtC.setBounds(150,105,100,40);
        txtD.setBounds(150,155,100,40);

        lblA.setBounds(20,10,100,20);lblA.setFont(font);
        lblB.setBounds(20,55,100,20);lblB.setFont(font);
        lblC.setBounds(20,105,100,20);lblC.setFont(font);
        lblD.setBounds(20,155,100,20);lblD.setFont(font);


        add(submitButton);
        //add(btnTambah);

        add(lblA);
        add(lblB);
        add(lblC);
        add(lblD);

        add(txtA);
        add(txtB);
        add(txtC);
        add(txtD);
    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){

            }
        });

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                serverName=txtA.getText();
                domainName=txtB.getText();
                password=txtC.getText();
                port=Integer.parseInt(txtD.getText());
                MyFrame.super.dispose();
            }
        });


    }

    public static int getPort() {
        return port;
    }
}