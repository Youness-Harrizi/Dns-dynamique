package client.guiClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame extends JFrame {
    protected static String serverName;
    protected static String domainName;
    protected static String password;
    protected static int port;

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
        setSize(400,200);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(false);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponent();
        initEvent();
    }

    private void initComponent(){
       // btnTutup.setBounds(300,130, 80,25);
        submitButton.setBounds(300,100, 80,25);

        txtA.setBounds(100,10,100,20);
        txtB.setBounds(100,35,100,20);
        txtC.setBounds(100,65,100,20);
        txtD.setBounds(100,95,100,20);

        lblA.setBounds(20,10,100,20);
        lblB.setBounds(20,35,100,20);
        lblC.setBounds(20,65,100,20);
        lblD.setBounds(20,95,100,20);


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


}