package ϵͳ1;

//��Щ�е����������Java��ͽӿڣ�����AWT��Swing�����SQL���ݿ�������Լ��¼�����ӿڡ�
import java.awt.*;

import javax.swing.*;

import java.sql.*;
import java.awt.event.*;

//������һ��JPanel�����࣬���ڴ���һ���򵥵�ͼ���û����棨GUI���������û�����γ̺źͿγ�����������Щ��Ϣ¼�뵽���ݿ��С�
//ͬʱ������ʵ����ActionListener�ӿڣ��Դ���ť����¼���


//���д���������һ����ΪAddC�Ĺ����࣬���̳���JPanel��ʵ����ActionListener�ӿڣ�������ദ�����¼���
public class AddC extends JPanel implements ActionListener{
//	����������������JTextField������������γ̺źͿγ�������һ��JButton�������ڴ���¼���������
	JTextField �κ�,����;
	JButton ¼��;
	
//	����AddC��Ĺ��캯�������ڳ�ʼ�����
public AddC(){
//	�����д��볢������Ӧ�ó������ۺ͸о�ΪϵͳĬ����ʽ�����ʧ�ܣ����ӡ������Ϣ��
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch(Exception e){System.err.println("�����������:   "+e);}
	
//	�⼸�д��봴���˿γ̺źͿγ�����������Լ�һ����¼�롱��ť��������ǰ����AddCʵ����ע��Ϊ��ť�Ķ����¼���������
	�κ�=new JTextField(12);
	����=new JTextField(12);
	¼��=new JButton("¼��");
	¼��.addActionListener(this);
	
//	�⼸�д��봴�����ĸ�ˮƽ�����Box���������ڲ��������
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	
//	�⼸�д�����box1��box2�зֱ�����˱�ǩ�Ͷ�Ӧ���ı��ֶΣ���box4������ˡ�¼�롱��ť��
	box1.add(new JLabel("�κ�:"));
	box1.add(�κ�);
	box2.add(new JLabel("����:"));
	box2.add(����);
	box4.add(¼��);
	
//	�⼸�д��봴����һ����ֱ�����Box����������֮ǰ��ˮƽBox������ӵ����У�
//	Box.createVerticalGlue()�����ڵײ���Ӷ���Ĵ�ֱ�ռ䡣
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	
//	�����д��봴����һ��JPanel��������ֱBox������ӵ�������С�
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	
//	�����д���������AddC���Ĳ���ΪBorderLayout������messPanel��ӵ����ֵ�����λ�á�
	setLayout(new BorderLayout());
	add(messPanel,BorderLayout.CENTER);
	
//	���д�����֤���Ĳ��֣�ȷ���������������ȷ��ʾ��
	validate();
}

//����ActionListener�ӿڵ�ʵ�ַ��������ڴ������¼���
public void actionPerformed(ActionEvent c){
	
//	�⼸�д������¼�����Դ�Ƿ�Ϊ��¼�롱��ť
	Object obj=c.getSource();
	if(obj==¼��){
		
//		�⼸�д�����γ̺źͿγ����Ƿ�����д�����δ��д������ʾһ����Ϣ�Ի���
		if(�κ�.getText().equals("")||����.getText().equals("")){
			JOptionPane.showMessageDialog(this,"ѧ����Ϣ��������¼�룡" );
		}
		
//		�⼸�д���������SQL���������������˲�ѯ�Ͳ����SQL��䡣
		Statement stmt=null;
		ResultSet rs=null,rs1=null;
		String sql,sql1;
		    sql1="select * from C where Cno='"+�κ�.getText()+"'";
		    sql="insert into C values('"+�κ�.getText()+"','"+����.getText()+"')";
		    
//		    ��δ��볢�����ӵ����ݿ⣬ִ�в�ѯ�Լ��κ��Ƿ��Ѵ��ڣ���������ڣ���ִ�в��������
//		    ��󣬹رս�����������󣬲������κ�SQL�쳣��
	   try{
		   Connection dbConn1 = DatabaseConnector.getConnection();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"�ÿκ��Դ��ڣ��޷����");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"��ӳɹ�");
			}		
			rs1.close();
			
			stmt.close();
	   }
	   catch(SQLException e){
		   System.out.print("SQL Exception occur.Message is:"+e.getMessage());
		   }
	}
}

//�������ݿⷽ��

////����һ����̬���������ڽ��������ݿ�����ӡ�
//public static Connection CONN(){
////	�⼸�д������������ݿ����ӵĲ���������JDBC�����������ݿ�URL���û��������롣
//	   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //����JDBC����
//	   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=student";   //���ӷ����������ݿ�test
//	   String userName = "sa";   //Ĭ���û���
//	   String userPwd = "040517";   //����
//	   Connection dbConn=null;
//
////	   ��δ��볢�Լ���JDBC�������������ݿ����ӣ�����ɹ������ӡ��Connection Successful!��������̨��
////	   ���ʧ�ܣ����ӡ��ջ������Ϣ����󣬷������ݿ����Ӷ���
//	   try {
//	   Class.forName(driverName);
//	   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
//	   System.out.println("Connection Successful!");   //������ӳɹ� ����̨���Connection Successful!
//	   } catch (Exception e) {
//	   e.printStackTrace();
//	   }
//	   return dbConn;
//}

}


