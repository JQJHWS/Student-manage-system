package ϵͳ1;
import java.awt.*;

import javax.swing.*;

import java.sql.*;
import java.util.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.event.*;

//������һ����������ADDSC�����̳���JPanel��ʵ����ActionListener�ӿڣ���������Ӧ�����¼�
public class AddSC extends JPanel implements ActionListener{
	
//	����������JTextField������������γ̺š�ѧ�źͳɼ����Լ�һ��JButton�������ڴ���¼�������
	JTextField �κ�,ѧ��,�ɼ�;
	JButton ¼��;
	
	

public AddSC(){
	
//	��������GUI����ۺ͸о�ΪϵͳĬ�ϣ����ʧ�����ӡ������Ϣ
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	
//	��ʼ���γ̺š�ѧ�źͳɼ���������Լ�¼�밴ť
	�κ�=new JTextField(12);
	ѧ��=new JTextField(12);
	�ɼ�=new JTextField(12);
	¼��=new JButton("¼��");
	
//	Ϊ¼�밴ť��Ӷ�����������������Ϊ��ǰ����ADDSC��ʵ����
	¼��.addActionListener(this);
	
//	�����ĸ�ˮƽ�����Box���������ڲ��������
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	
//	�����ĸ�Box����ӱ�ǩ��������ť��
	box1.add(new JLabel("�κ�:"));
	box1.add(�κ�);
	box2.add(new JLabel("ѧ��:"));
	box2.add(ѧ��);
	box3.add(new JLabel("�ɼ�:"));
	box3.add(�ɼ�);
	box4.add(¼��);
	
//	����һ����ֱ�����Box����������֮ǰ���ĸ�ˮƽBox��ӵ����У������Ӵ�ֱ����Ŀհ���䡣
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	
//	����һ��JPanel������ֱBox��ӵ�������С�
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	
//	���õ�ǰ���Ĳ���ΪBorderLayout������messPanel��ӵ����ֵ�����λ�á�
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);
	
//	����validate()���������²�����塣
	validate();
}

//����ActionListener�ӿڵ�ʵ�ַ��������ڴ������¼���
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	
//	�ж��¼�����Դ�Ƿ�Ϊ¼�밴ť��
	if(obj==¼��){
		
//		����γ̺Ż�ѧ��Ϊ�գ��򵯳���ʾ��Ϣ��
		if(�κ�.getText().equals("")||ѧ��.getText().equals("")){
			JOptionPane.showMessageDialog(this,"��д�κ���ѧ�Ų���¼�룡" );
		}
		
		
//		��ʼ��SQL�������ͽ����������
		else
		{
		Statement stmt=null;
		ResultSet rs=null,rs1=null,rsC=null,rsS=null;
		String sql,sql1,sqlS,sqlC;
		
//		�������ڲ�ѯ�Ͳ����SQL��䡣
		    sqlC="select * from C where Cno='"+�κ�.getText()+"'";
		    sqlS="select * from S where Sno='"+ѧ��.getText()+"'";
		    sql1="select * from SC where Cno='"+�κ�.getText()+"' and Sno='"+ѧ��.getText()+"'";
		    sql="insert into SC values('"+�κ�.getText()+"','"+ѧ��.getText()+"','"+�ɼ�.getText()+"')";
	   try{
		   Connection dbConn1 = DatabaseConnector.getConnection();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rsC=stmt.executeQuery(sqlC);
			if(rsC.next()){
				rsS=stmt.executeQuery(sqlS);
				if(rsS.next()){
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"��ѧ����ѡ�ÿγ̺ţ��޷����");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"��ӳɹ�");
			}
			rs1.close();
			}
				else{JOptionPane.showMessageDialog(this,"��ѧ�������ڣ��޷����");}
				rsS.close();
			}
			else{JOptionPane.showMessageDialog(this,"�ÿγ̲����ڣ��޷����");}
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

