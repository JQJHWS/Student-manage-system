package ϵͳ1;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class UpdateC extends JPanel implements ActionListener{
	String save=null;
	JTextField �κ�1,�κ�,����;
	JButton �޸�,����;
	
public UpdateC(){
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	
	�κ�1=new JTextField(12);
	�κ�=new JTextField(12);
	����=new JTextField(12);
	�޸�=new JButton("�޸�");
	����=new JButton("����");
	
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	box1.add(new JLabel("�κ�:",JLabel.CENTER));
	box1.add(�κ�);
	box2.add(new JLabel("����:",JLabel.CENTER));
	box2.add(����);
	box3.add(�޸�);
	box5.add(new JLabel("�κ�:",JLabel.CENTER));
	box5.add(�κ�1);
	box5.add(����);
	
	�޸�.addActionListener(this);
    ����.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box5);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//�ָ�
	add(splitV,BorderLayout.CENTER);
	validate();
}

public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlSC=null;
	
	if(obj==����){if(�κ�1.getText().equals(""))JOptionPane.showMessageDialog(this,"����д��ѯ�Ŀκţ�" );
	else{
	    sql1="select * from C where Cno='"+�κ�1.getText()+"'";
	    try{
	    	Connection dbConn1 = DatabaseConnector.getConnection();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){�κ�.setText(rs1.getString("Cno").trim());
	                   ����.setText(rs1.getString("Cname").trim());
	                   save=�κ�1.getText();	    	
	    }
	    else{JOptionPane.showMessageDialog(this,"û������κŵĿγ�" );}
	    stmt.close();
	    rs1.close();
	    }catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	    }
	}
	else{
	if(obj==�޸�){if(save==null){JOptionPane.showMessageDialog(this,"��û������Ҫ�޸ĵĿγ�" );}
	else{
		if(�κ�.getText().equals("")||����.getText().equals("")){
			JOptionPane.showMessageDialog(this,"�γ���Ϣ���������޸ģ�" );
		}
		else{sql="update C set Cno='"+�κ�.getText()+"',Cname='"+����.getText()+"' where Cno='"+save+"'";
		if(save.trim().equals(�κ�.getText().trim())){
		try{
			Connection dbConn1 = DatabaseConnector.getConnection();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"�޸����" );
			�κ�.setText("");
            ����.setText("");
            stmt.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
	}
		else{sql1="select * from C where Cno='"+�κ�.getText()+"'";
		try{
			Connection dbConn1 = DatabaseConnector.getConnection();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"�Ѵ��ڴ˿κſγ�" );
		    }
		    else{sqlSC="update SC set Sno='"+�κ�.getText()+"' where Cno='"+save+"'";
		    stmt.executeUpdate(sql);
		    stmt.executeUpdate(sqlSC);
		    	save=null;
			JOptionPane.showMessageDialog(null,"�޸����" );
			�κ�.setText("");
            ����.setText("");}
		    stmt.close();
		    rs1.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
		}
	}}}}
}




}

