package App;

import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDBSFrom{
    private JPanel panel1 = new JPanel();
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();


    public StudentDBSFrom() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension dim1 = new Dimension(20,100);
        button2.setSize(dim1);
    }

    public static void main(String[] args){
        JFrame ctnp = new JFrame("社团管理系统");

        ctnp.setContentPane(new StudentDBSFrom().panel1);
        ctnp.setSize(400,400);
        ctnp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ctnp.setVisible(true);
    }
}
