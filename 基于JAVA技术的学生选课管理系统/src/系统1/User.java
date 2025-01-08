package 系统1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;


public class User extends JFrame{
JFrame jf=new JFrame();
private JLabel use,password;
private JTextField k1;//用户名输入框
private JPasswordField k2;//密码输入框
private JButton b1,b2;

//登录窗口
	public User(){
		 // 设置窗口标题。
		this.setTitle("系统登录"); 
		// 获取内容面板并设置布局。
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		// 初始化并设置标签。
		use=new JLabel("用户名:");
		use.setSize(4, 2);
		use.setLocation(4, 2);
		use.setFont(new Font("Serif",Font.PLAIN,30));
		password=new JLabel("密码:");
		password.setFont(new Font("Serif",Font.PLAIN,30));
		 // 初始化输入框和按钮。
		k1=new JTextField(12);
		k2=new JPasswordField(12);
		b1=new JButton("登录");
		b2=new JButton("退出");
	
		//	设置登录方法
		// 设置登录和退出按钮的事件监听器。
		BHandler b=new BHandler();
		EXIT d=new EXIT();
		b1.addActionListener(b);
		b2.addActionListener(d);
		// 设置窗口图标。
		Image img0 = new ImageIcon("image\\3.jpg").getImage(); 
		  this.setIconImage(img0);
			//添加控件
		  // 添加组件到内容面板。
		c.add(use);
		c.add(k1);
		c.add(password);
		c.add(k2);
		c.add(b1);
		c.add(b2);
		
		 // 设置窗口大小和可见性。注意：setBounds和setSize被连续调用可能会产生冲突，建议使用其中之一。
		setBounds(150,150,250,150);// 这行代码实际上被后面的setSize覆盖了。
		setSize(500, 375);// 设置窗口大小。
		setVisible(true);// 设置窗口为可见。
		
		// 设置窗口不可调整大小。
	    setResizable(false);
		
	 // 设置默认的关闭操作。
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 设置背景图片。
		String path = "image\\3.jpg"; // 背景图片的路径。（相对路径或者绝对路径。本例图片放于"java项目名"的文件下）
		ImageIcon background = new ImageIcon(path); // 背景图片
		JLabel label = new JLabel(background); // 把背景图片显示在一个标签里面    
		setSize(500,375);
		label.setBounds(0,0,this.getWidth(),this.getHeight()); // 把标签的大小位置设置为图片刚好填充整个面板
//		它只是简单地添加了一个标签作为背景。
		JPanel imagePanel = (JPanel) this.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel.setOpaque(false);// 使内容面板透明，以便显示背景图片。
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));// 把背景图片添加到分层窗格的最底层作为背景
		
		this.getLayeredPane().setLayout(new BorderLayout()); // 设置布局以确保背景覆盖整个窗口
		
	    setSize(500,375);}
	//登录按钮方法
	private class BHandler implements ActionListener{
			public void actionPerformed(ActionEvent event){
				if(k1.getText().equals("")||k2.getText().equals("")){
					JOptionPane.showMessageDialog(User.this,"用户名密码不能为空！" );
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
							if(k2.getText().equals(xm.trim())){JOptionPane.showMessageDialog(User.this,"登录成功");
							java.awt.Toolkit.getDefaultToolkit().beep();
							dispose();
								Menu menu = new Menu();  
								menu.showMenu(); 
								// new Menu();//管理窗口		
								Music.playMusic("D://JDK(old)//202204071022 陈仕尧//基于JAVA技术的学生选课管理系统//背景音乐.wav");
								}
							else{JOptionPane.showMessageDialog(User.this,"密码错误");}
						}
						else{JOptionPane.showMessageDialog(User.this,"用户名错误");}
						rs.close();
						stmt.close();
				   }
				   catch(SQLException e){
					   JOptionPane.showMessageDialog(User.this,"SQL Exception occur.Message is:"+e.getMessage());
					   }
				}			
		}
		}

	//退出方法结束
	private class EXIT implements ActionListener{
		public void actionPerformed(ActionEvent even){
			System.exit(0);
		}
	}


	

////连接数据库方法
//public static Connection CONN(){
//	   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //加载JDBC驱动
//	   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=student";   //连接服务器和数据库test
//	   String userName = "sa";   //默认用户名
//	   String userPwd = "040517";   //密码
//	   Connection dbConn=null;
//	   try {
//	   Class.forName(driverName);
//	   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
//	   System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
//	   } catch (Exception e) {
//	   e.printStackTrace();
//	   }
//	   return dbConn;
//}
}//父类结束
