package 系统1;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
public class UpdateSC extends JPanel implements ActionListener{
	String saveC=null;
	String saveS=null;
	JTextField 课号1,学号1,学号,课号,成绩;
	JButton 修改,查找;
	
public UpdateSC(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("不能设置外观:   "+e);}
	
	学号1=new JTextField(12);
	课号1=new JTextField(12);
	课号=new JTextField(12);
	学号=new JTextField(12);
	成绩=new JTextField(12);
	修改=new JButton("修改");
	查找=new JButton("查找");
	
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	box1.add(new JLabel("课号:",JLabel.CENTER));
	box1.add(课号);
	box2.add(new JLabel("学号:",JLabel.CENTER));
	box2.add(学号);
	box3.add(new JLabel("成绩:",JLabel.CENTER));
	box3.add(成绩);
	box4.add(修改);
	box5.add(new JLabel("课号:",JLabel.CENTER));
	box5.add(课号1);
	box5.add(new JLabel("学号:",JLabel.CENTER));
	box5.add(学号1);
	box5.add(查找);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	
	修改.addActionListener(this);
    查找.addActionListener(this);
	
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box5);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
	add(splitV,BorderLayout.CENTER);
	validate();
}
public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null,rsC=null,rsS=null;
	String sql,sql1,sqlS,sqlC;
	
	if(obj==查找){if(课号1.getText().equals("")||学号1.getText().equals(""))JOptionPane.showMessageDialog(this,"请填写完成查询的信息！" );
	else{
	    sql1="select * from SC where Cno='"+课号1.getText()+"' and Sno='"+学号1.getText()+"'";
	    try{
	    	Connection dbConn1 = DatabaseConnector.getConnection();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){课号.setText(rs1.getString("Cno").trim());
	                   学号.setText(rs1.getString("Sno").trim());
	                   成绩.setText(rs1.getString("C").trim());
	                   saveC=课号1.getText().trim();	
	                   saveS=学号1.getText().trim();
	    }
	    else{JOptionPane.showMessageDialog(this,"没有这个课号的学生" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	    }
	}
	
	else{
		if(obj==修改){if(saveC==null||saveS==null)JOptionPane.showMessageDialog(this,"还没查找需要修改的学生/课程" );
		else{
			if(课号.getText().equals("")||学号.getText().equals("")){
				JOptionPane.showMessageDialog(this,"课程信息填满才能修改！" );
			}
		else{
			sqlC="select * from C where Cno='"+课号.getText()+"'";
	    sqlS="select * from S where Sno='"+学号.getText()+"'";
	    sql1="select * from SC where Cno='"+课号.getText()+"' and Sno='"+学号.getText()+"'";
	    sql="update SC set Cno='"+课号.getText()+"',Sno='"+学号.getText()+"',C='"+成绩.getText()+"' where Cno='"+saveC+"' and Sno='"+saveS+"'";
   try{
	   Connection dbConn1 = DatabaseConnector.getConnection();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rsC=stmt.executeQuery(sqlC);
		if(rsC.next()){
			rsS=stmt.executeQuery(sqlS);
			if(rsS.next()){
			if(课号.getText().trim().equals(saveC)&& 学号.getText().trim().equals(saveS)){
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"添加成功");
			saveC=null;
			saveS=null;
				}
			else{rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"学生与课程号以存在，无法修改");}
			else{
				stmt.executeUpdate(sql);	
				JOptionPane.showMessageDialog(this,"添加成功");
				saveC=null;
				saveS=null;				
			}
			rs1.close();
			}
		}						
			else{JOptionPane.showMessageDialog(this,"该学生不存在，无法修改");}
			rsS.close();
		}
		else{JOptionPane.showMessageDialog(this,"该课程不存在，无法修改");}
		rsC.close();
		stmt.close();
   }
   catch(SQLException e1){
	   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
	   }
		}
		}
}
}
}

}


