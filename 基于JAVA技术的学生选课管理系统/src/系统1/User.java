package ϵͳ1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;


public class User extends JFrame{
JFrame jf=new JFrame();
private JLabel use,password;
private JTextField k1;//�û��������
private JPasswordField k2;//���������
private JButton b1,b2;

//��¼����
	public User(){
		 // ���ô��ڱ��⡣
		this.setTitle("ϵͳ��¼"); 
		// ��ȡ������岢���ò��֡�
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		// ��ʼ�������ñ�ǩ��
		use=new JLabel("�û���:");
		use.setSize(4, 2);
		use.setLocation(4, 2);
		use.setFont(new Font("Serif",Font.PLAIN,30));
		password=new JLabel("����:");
		password.setFont(new Font("Serif",Font.PLAIN,30));
		 // ��ʼ�������Ͱ�ť��
		k1=new JTextField(12);
		k2=new JPasswordField(12);
		b1=new JButton("��¼");
		b2=new JButton("�˳�");
	
		//	���õ�¼����
		// ���õ�¼���˳���ť���¼���������
		BHandler b=new BHandler();
		EXIT d=new EXIT();
		b1.addActionListener(b);
		b2.addActionListener(d);
		// ���ô���ͼ�ꡣ
		Image img0 = new ImageIcon("image\\3.jpg").getImage(); 
		  this.setIconImage(img0);
			//��ӿؼ�
		  // ��������������塣
		c.add(use);
		c.add(k1);
		c.add(password);
		c.add(k2);
		c.add(b1);
		c.add(b2);
		
		 // ���ô��ڴ�С�Ϳɼ��ԡ�ע�⣺setBounds��setSize���������ÿ��ܻ������ͻ������ʹ������֮һ��
		setBounds(150,150,250,150);// ���д���ʵ���ϱ������setSize�����ˡ�
		setSize(500, 375);// ���ô��ڴ�С��
		setVisible(true);// ���ô���Ϊ�ɼ���
		
		// ���ô��ڲ��ɵ�����С��
	    setResizable(false);
		
	 // ����Ĭ�ϵĹرղ�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ���ñ���ͼƬ��
		String path = "image\\3.jpg"; // ����ͼƬ��·���������·�����߾���·��������ͼƬ����"java��Ŀ��"���ļ��£�
		ImageIcon background = new ImageIcon(path); // ����ͼƬ
		JLabel label = new JLabel(background); // �ѱ���ͼƬ��ʾ��һ����ǩ����    
		setSize(500,375);
		label.setBounds(0,0,this.getWidth(),this.getHeight()); // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
//		��ֻ�Ǽ򵥵������һ����ǩ��Ϊ������
		JPanel imagePanel = (JPanel) this.getContentPane(); // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel.setOpaque(false);// ʹ�������͸�����Ա���ʾ����ͼƬ��
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		
		this.getLayeredPane().setLayout(new BorderLayout()); // ���ò�����ȷ������������������
		
	    setSize(500,375);}
	//��¼��ť����
	private class BHandler implements ActionListener{
			public void actionPerformed(ActionEvent event){
				if(k1.getText().equals("")||k2.getText().equals("")){
					JOptionPane.showMessageDialog(User.this,"�û������벻��Ϊ�գ�" );
				}
				else{
					java.awt.Toolkit.getDefaultToolkit().beep();
					Statement stmt=null;
					ResultSet rs=null;
					String sql;
				    sql="select * from admin where username='"+k1.getText()+"'";
				   try{
					   Connection dbConn1 = DatabaseConnector.getConnection();
						stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
						rs=stmt.executeQuery(sql);					
						if(rs.next()){
							String xm=rs.getString("password");
							if(k2.getText().equals(xm.trim())){JOptionPane.showMessageDialog(User.this,"��¼�ɹ�");
							java.awt.Toolkit.getDefaultToolkit().beep();
							dispose();
								Menu menu = new Menu();  
								menu.showMenu(); 
								// new Menu();//������		
								Music.playMusic("D://JDK(old)//202204071022 ����Ң//����JAVA������ѧ��ѡ�ι���ϵͳ//��������.wav");
								}
							else{JOptionPane.showMessageDialog(User.this,"�������");}
						}
						else{JOptionPane.showMessageDialog(User.this,"�û�������");}
						rs.close();
						stmt.close();
				   }
				   catch(SQLException e){
					   JOptionPane.showMessageDialog(User.this,"SQL Exception occur.Message is:"+e.getMessage());
					   }
				}			
		}
		}

	//�˳���������
	private class EXIT implements ActionListener{
		public void actionPerformed(ActionEvent even){
			System.exit(0);
		}
	}


	

////�������ݿⷽ��
//public static Connection CONN(){
//	   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //����JDBC����
//	   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=student";   //���ӷ����������ݿ�test
//	   String userName = "sa";   //Ĭ���û���
//	   String userPwd = "040517";   //����
//	   Connection dbConn=null;
//	   try {
//	   Class.forName(driverName);
//	   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
//	   System.out.println("Connection Successful!");   //������ӳɹ� ����̨���Connection Successful!
//	   } catch (Exception e) {
//	   e.printStackTrace();
//	   }
//	   return dbConn;
//}
}//�������
