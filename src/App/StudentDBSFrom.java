package App;

import javax.swing.*;
import java.awt.*;

public class StudentDBSFrom extends JFrame{
    private JPanel panel1;
    private JButton button1;
    private JButton button2;

    public StudentDBSFrom(String title){
        super(title);
        this.add(panel1);
        this.add(button1);
        createUIComponents();
        this.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension dim1 = new Dimension(20,100);
        button2.setSize(dim1);
    }

    public static void main(){
        StudentDBSFrom ctnp = new StudentDBSFrom("学生社团管理系统");

    }
}
