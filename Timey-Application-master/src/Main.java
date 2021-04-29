import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static int HEIGHT = 310;


    public static void main(String[] args) throws ParseException {
/*        UserManager uM = UserManager.getInstance();
        User current = uM.getUser("admin");
        User c = uM.login("","");
        Admin currentUser = (Admin) current;
        currentUser.removeManager("shakil");*/
/*        String month = "03/2020";
        Date d = new Date();
        DateFormat date = new SimpleDateFormat("MM/yyyy").parse(month);

        DateFormat time = new SimpleDateFormat("mm");

        int mins = Integer.parseInt(time.format(d));
        System.out.println(date.format(d));
        System.out.println(mins);*/

        String month = "09/2020";
        Date date1 = new SimpleDateFormat("MM/yyyy").parse(month);
        System.out.println(date1);

        DateFormat dateFormat1 = new SimpleDateFormat("MMMM yyyy");
        System.out.println(dateFormat1.format(date1));

        //login();
        //scroll();
        //test();
        //verifyPayslips();

        //Payslip p = new Payslip(1, "10:00","20:00","24/04/2020","shakil", "AB123456Z", 12.5);
        //p.tickPayslip();
    }

    public static int index;

    public static void verifyPayslips(){
        ArrayList<Integer> nums = new ArrayList<>();
        Integer[] a = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        nums.addAll(Arrays.asList(a));
        index = -1;
        JFrame frame = new JFrame("Application");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(300,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton down = new JButton("Down");
        JButton up = new JButton("Up");
        JButton home = new JButton("Home");
        up.setBounds(200,225,100,50);
        down.setBounds(0, 225, 100,50);
        home.setBounds(100,225,100,50);

        JPanel panel1 = new JPanel(null);
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(0,0,300,200);
        JPanel panel2 = new JPanel(null);
        panel2.setBackground(Color.WHITE);
        panel2.setBounds(0,300,300,200);

        ArrayList<JPanel> panels = new ArrayList<>();
        panels.add(panel1);
        panels.add(panel2);

        down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(nums.size()-index<=panels.size()){
                    print("Stop Scrolling");
                }
                else{
                    index=index+1;
                    for (int i=0; i<panels.size(); i++){
                        JTextPane j = new JTextPane();
                        j.setBounds(0,0,100,30);
                        j.setText(Integer.toString(nums.get(i+index)));
                        panels.get(i).add(j);
                    }
                }
            }
        });

        up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (index <= 0){
                    print("at Top");
                }
                else{
                    index = index - 1;
                    for (int i=0; i<panels.size(); i++){
                        JTextPane j = new JTextPane();
                        j.setBounds(0,0,100,30);
                        j.setText(Integer.toString(nums.get(i+index)));
                        panels.get(i).add(j);
                    }
                }
            }
        });

        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                login();
            }
        });

        frame.add(panel1);
        frame.add(panel2);
        frame.add(down);
        frame.add(up);
        frame.add(home);
    }

    public static void test(){
        JFrame frame = new JFrame();
        frame.setLayout(null);
        JPanel panel = new JPanel();
        for (int i = 0; i < 10; i++) {
            //panel.add(new JButton("Hello-" + i));
            JPanel j = new JPanel(null);
            j.setBounds(i*50,0, 50, 10);
            int index = i;
            j.setBackground(Color.WHITE);
            JButton b = new JButton("Hello-"+i);
            j.add(b);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(index);
                }
            });
            panel.add(j);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 30, 300, 50);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void scroll(){
        int num = 6;
        JFrame jF = new JFrame("Hello World");
        jF.setVisible(true);
        jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jF.setSize(400,600);
        jF.setResizable(false);
        jF.setLayout(null);

        JPanel container = new JPanel();
        container.setBounds(10,10,380,540);
        container.setLayout(null);
        container.setBackground(Color.DARK_GRAY);
        container.scrollRectToVisible(new Rectangle());

        JScrollBar scroll = new JScrollBar(Adjustable.VERTICAL);
        scroll.setBounds(370,0,10,540);
        container.add(scroll);
        for (int i = 0; i<num; i++){
            JPanel j = new JPanel();
            j.setBackground(Color.WHITE);
            j.setBounds(0,100*i,300,100);
            j.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
            container.add(j);
        }
        jF.add(container);

        scroll.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.println("Hello");
            }
        });
    }

    public static void login(){
        JFrame login = new JFrame("Login Screen");
        login.setVisible(true);
        login.setSize(400,600);
        login.setLayout(null);
        login.setResizable(false);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextField uname = new JTextField();
        JPasswordField pword = new JPasswordField();
        JButton on = new JButton("Click Me");
        JButton off = new JButton("Off");
        on.setBounds(10,10,100,40);
        off.setBounds(290,10,100,40);
        uname.setBounds(10,60,100,40);
        login.add(on);
        login.add(off);
        login.add(uname);
        //on.addActionListener();
        on.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String line = uname.getText();
                System.out.println("Action Man");
                if (line.equals("shakil")) {
                    login.setVisible(false);
                    verifyPayslips();
                }
                else{
                    uname.setText("");
                }
            }
        });
        off.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void adminScreen(){
        JFrame jFrame = new JFrame("Admin");
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setSize(1280,720);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.getContentPane().setBackground(Color.CYAN);
        JButton on = new JButton("Click Me");
        on.setBounds(10,10,100,40);
        JPanel jPanel = new JPanel();
        jPanel.setBounds(10,60, 1200, 300);
        JPanel jPanel1 = new JPanel();
        jPanel1.setBounds(10,60+HEIGHT, 1200, 300);
        jFrame.add(on);
        jFrame.add(jPanel);
        jFrame.add(jPanel1);
        ArrayList<Payslip> pay = new ArrayList<>();
        on.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jPanel.setVisible(false);
                pay.get(0);
            }
        });
    }

    public static void print(String x){
        System.out.println(x);
    }
}
