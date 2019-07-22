package client.guiClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextingFrame extends JFrame {
    private JButton submit;
    private JTextField field;
    private String text="";

    public String getText() {
        return text;
    }

    public TextingFrame(){
        super("SEND MESSAGES TO THE SERVER ");
        setSize(800,400);
        setLocation(new Point(300,200));
        setLayout(null);
        setResizable(true);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponent();
        initEvent();


    }
    private void initComponent(){
      field=new JTextField();
      field.setBounds(150,10,100,40);
      submit=new JButton("submit");
      submit.setBounds(300,100, 140,50);
      add(submit);
      add(field);

    }
    private void initEvent(){
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text=field.getText();
            }
        });

    }


    // Test Test
    public static void main(String[] args) {
        TextingFrame textingFrame=new TextingFrame();
        textingFrame.setVisible(true);
    }
}
