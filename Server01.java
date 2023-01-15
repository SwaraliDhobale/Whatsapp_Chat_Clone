package converse;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
import javax.swing.ImageIcon;
public class Server01 implements ActionListener {
    JTextField t;
    JPanel p1;
    static Box vertical= Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    Server01(){
        f.setLayout(null);
        JPanel p=new JPanel();
        p.setBackground(new Color(18,140,126));
        p.setBounds(0,0,450,55);
        p.setLayout(null);
        f.add(p);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel b=new JLabel(i3);
        b.setBounds(10,14,20,25);
        p.add(b);
        
        b.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel b1=new JLabel(i6);
        b1.setBounds(40,10,35,35);
        p.add(b1);
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel b2=new JLabel(i9);
        b2.setBounds(250,10,30,30);
        p.add(b2);
        
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(30,40,Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel b3=new JLabel(i12);
        b3.setBounds(310,15,20,24);
        p.add(b3);
        
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(10,20,Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel b4=new JLabel(i15);
        b4.setBounds(350,15,15,24);
        p.add(b4);
        
        JLabel name=new JLabel("Simona");
        name.setBounds(90,10,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p.add(name);
        
        JLabel s=new JLabel("Online");
        s.setBounds(90,25,100,18);
        s.setForeground(Color.WHITE);
        s.setFont(new Font("SAN_SERIF",Font.PLAIN,10));
        p.add(s);
        
        p1=new JPanel();
        p1.setBounds(1,50,500,500);
        f.add(p1);
       
        t=new JTextField();
        t.setBounds(8,555,320,40);
        t.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        f.add(t);
        
        JButton send=new JButton();
        send.setBounds(340,550,50,50);
        send.setIcon(new ImageIcon("C:\\Users\\user\\Documents\\NetBeansProjects\\Converse\\src\\icons\\sends.png"));
       
        send.setBackground(Color.GRAY);
        //send.setForeground(Color.LIGHT_GRAY);
        send.addActionListener(this);
        //send.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        f.add(send);
        
        f.setSize(400,600);
        f.setLocation(200,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.GRAY);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try {
            String out = t.getText();
            JPanel p2 = formatLabel(out);
            p1.setLayout(new BorderLayout());
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            p1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);
            t.setText(" ");
            f.repaint();
            f.invalidate();
            f.validate();   
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 18));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(10, 15, 15, 45));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
     
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        return panel;
    }
    public static void main(String[]args){
        new Server01();
        try {
            ServerSocket skt = new ServerSocket(6001);
            while(true) {
                Socket s = skt.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());//reading from socket
                dout = new DataOutputStream(s.getOutputStream());//writing to a socket
                
                while(true) {
                    String msg = in.readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    f.validate();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
    }
}
}

