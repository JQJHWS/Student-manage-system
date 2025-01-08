package 系统1;

//这些行导入了所需的Java类和接口，包括AWT和Swing组件、SQL数据库操作类以及事件处理接口。
import java.awt.*;

import javax.swing.*;

import java.sql.*;
import java.awt.event.*;

//该类是一个JPanel的子类，用于创建一个简单的图形用户界面（GUI），允许用户输入课程号和课程名，并将这些信息录入到数据库中。
//同时，它还实现了ActionListener接口，以处理按钮点击事件。


//这行代码声明了一个名为AddC的公共类，它继承自JPanel并实现了ActionListener接口，允许该类处理动作事件。
public class AddC extends JPanel implements ActionListener{
//	这两行声明了两个JTextField对象（用于输入课程号和课程名）和一个JButton对象（用于触发录入操作）。
	JTextField 课号,课名;
	JButton 录入;
	
//	这是AddC类的构造函数，用于初始化组件
public AddC(){
//	这两行代码尝试设置应用程序的外观和感觉为系统默认样式，如果失败，则打印错误信息。
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
//	这几行代码创建了课程号和课程名的输入框，以及一个“录入”按钮，并将当前对象（AddC实例）注册为按钮的动作事件监听器。
	课号=new JTextField(12);
	课名=new JTextField(12);
	录入=new JButton("录入");
	录入.addActionListener(this);
	
//	这几行代码创建了四个水平方向的Box容器，用于布局组件。
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	
//	这几行代码向box1和box2中分别添加了标签和对应的文本字段，向box4中添加了“录入”按钮。
	box1.add(new JLabel("课号:"));
	box1.add(课号);
	box2.add(new JLabel("课名:"));
	box2.add(课名);
	box4.add(录入);
	
//	这几行代码创建了一个垂直方向的Box容器，并将之前的水平Box容器添加到其中，
//	Box.createVerticalGlue()用于在底部添加额外的垂直空间。
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	
//	这两行代码创建了一个JPanel，并将垂直Box容器添加到该面板中。
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	
//	这两行代码设置了AddC面板的布局为BorderLayout，并将messPanel添加到布局的中心位置。
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);
	
//	这行代码验证面板的布局，确保所有组件都被正确显示。
	validate();
}

//这是ActionListener接口的实现方法，用于处理动作事件。
public void actionPerformed(ActionEvent c){
	
//	这几行代码检查事件的来源是否为“录入”按钮
	Object obj=c.getSource();
	if(obj==录入){
		
//		这几行代码检查课程号和课程名是否都已填写，如果未填写，则显示一个消息对话框。
		if(课号.getText().equals("")||课名.getText().equals("")){
			JOptionPane.showMessageDialog(this,"学生信息请填满再录入！" );
		}
		
//		这几行代码声明了SQL语句变量，并构建了查询和插入的SQL语句。
		Statement stmt=null;
		ResultSet rs=null,rs1=null;
		String sql,sql1;
		    sql1="select * from C where Cno='"+课号.getText()+"'";
		    sql="insert into C values('"+课号.getText()+"','"+课名.getText()+"')";
		    
//		    这段代码尝试连接到数据库，执行查询以检查课号是否已存在，如果不存在，则执行插入操作。
//		    最后，关闭结果集和语句对象，并捕获任何SQL异常。
	   try{
		   Connection dbConn1 = DatabaseConnector.getConnection();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"该课号以存在，无法添加");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"添加成功");
			}		
			rs1.close();
			
			stmt.close();
	   }
	   catch(SQLException e){
		   System.out.print("SQL Exception occur.Message is:"+e.getMessage());
		   }
	}
}

//连接数据库方法

////这是一个静态方法，用于建立与数据库的连接。
//public static Connection CONN(){
////	这几行代码声明了数据库连接的参数，包括JDBC驱动名、数据库URL、用户名和密码。
//	   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //加载JDBC驱动
//	   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=student";   //连接服务器和数据库test
//	   String userName = "sa";   //默认用户名
//	   String userPwd = "040517";   //密码
//	   Connection dbConn=null;
//
////	   这段代码尝试加载JDBC驱动并建立数据库连接，如果成功，则打印“Connection Successful!”到控制台。
////	   如果失败，则打印堆栈跟踪信息。最后，返回数据库连接对象。
//	   try {
//	   Class.forName(driverName);
//	   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
//	   System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
//	   } catch (Exception e) {
//	   e.printStackTrace();
//	   }
//	   return dbConn;
//}

}


