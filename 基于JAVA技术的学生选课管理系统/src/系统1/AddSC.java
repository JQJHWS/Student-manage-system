package 系统1;
import java.awt.*;

import javax.swing.*;

import java.sql.*;
import java.util.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.event.*;

//定义了一个公开的类ADDSC，它继承自JPanel并实现了ActionListener接口，允许类响应动作事件
public class AddSC extends JPanel implements ActionListener{
	
//	定义了三个JTextField对象用于输入课程号、学号和成绩，以及一个JButton对象用于触发录入操作。
	JTextField 课号,学号,成绩;
	JButton 录入;
	
	

public AddSC(){
	
//	尝试设置GUI的外观和感觉为系统默认，如果失败则打印错误信息
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
//	初始化课程号、学号和成绩的输入框以及录入按钮
	课号=new JTextField(12);
	学号=new JTextField(12);
	成绩=new JTextField(12);
	录入=new JButton("录入");
	
//	为录入按钮添加动作监听器，监听器为当前对象（ADDSC类实例）
	录入.addActionListener(this);
	
//	创建四个水平方向的Box容器，用于布局组件。
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	
//	向这四个Box中添加标签和输入框或按钮。
	box1.add(new JLabel("课号:"));
	box1.add(课号);
	box2.add(new JLabel("学号:"));
	box2.add(学号);
	box3.add(new JLabel("成绩:"));
	box3.add(成绩);
	box4.add(录入);
	
//	创建一个垂直方向的Box容器，并将之前的四个水平Box添加到其中，最后添加垂直方向的空白填充。
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	
//	创建一个JPanel并将垂直Box添加到该面板中。
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	
//	设置当前面板的布局为BorderLayout，并将messPanel添加到布局的中心位置。
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);
	
//	调用validate()方法，重新布局面板。
	validate();
}

//这是ActionListener接口的实现方法，用于处理动作事件。
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	
//	判断事件的来源是否为录入按钮。
	if(obj==录入){
		
//		如果课程号或学号为空，则弹出提示信息。
		if(课号.getText().equals("")||学号.getText().equals("")){
			JOptionPane.showMessageDialog(this,"填写课号与学号才能录入！" );
		}
		
		
//		初始化SQL语句变量和结果集变量。
		else
		{
		Statement stmt=null;
		ResultSet rs=null,rs1=null,rsC=null,rsS=null;
		String sql,sql1,sqlS,sqlC;
		
//		定义用于查询和插入的SQL语句。
		    sqlC="select * from C where Cno='"+课号.getText()+"'";
		    sqlS="select * from S where Sno='"+学号.getText()+"'";
		    sql1="select * from SC where Cno='"+课号.getText()+"' and Sno='"+学号.getText()+"'";
		    sql="insert into SC values('"+课号.getText()+"','"+学号.getText()+"','"+成绩.getText()+"')";
	   try{
		   Connection dbConn1 = DatabaseConnector.getConnection();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rsC=stmt.executeQuery(sqlC);
			if(rsC.next()){
				rsS=stmt.executeQuery(sqlS);
				if(rsS.next()){
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"该学生以选该课程号，无法添加");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"添加成功");
			}
			rs1.close();
			}
				else{JOptionPane.showMessageDialog(this,"该学生不存在，无法添加");}
				rsS.close();
			}
			else{JOptionPane.showMessageDialog(this,"该课程不存在，无法添加");}
			rsC.close();
			stmt.close();
	   }
	   catch(SQLException e){
		   System.out.print("SQL Exception occur.Message is:"+e.getMessage());
		   }
	   }
	}
}

}

