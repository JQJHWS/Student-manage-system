package ϵͳ1;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
	private JPasswordField ulPasswd;
	 private JLabel j1; 
Addstu ����ѧ������;
Updatastu �޸�ѧ������;
Delstu ɾ��ѧ������;
AddC ���ӿγ̽���;
DelC ɾ���γ̽���;
UpdateC �޸Ŀγ̽���;
AddSC ����ѡ�ν���;
DelSC ɾ��ѡ�ν���;
UpdateSC �޸�ѡ�ν���;
Selstu ѧ����ѯ����;
JPanel pCenter;
CardLayout card=null;
JLabel label=null;
JMenuBar mb=new JMenuBar();//�˵���
JMenu m1=new JMenu("ѧ������");
JMenuItem add1=new JMenuItem("������Ϣ   ");
JMenuItem updata1=new JMenuItem("������Ϣ   ");
JMenuItem delete1=new JMenuItem("ɾ����Ϣ   ");
JMenu m2=new JMenu("�γ̹���");
JMenuItem add2=new JMenuItem("������Ϣ   ");
JMenuItem updata2=new JMenuItem("������Ϣ   ");
JMenuItem delete2=new JMenuItem("ɾ����Ϣ   ");
JMenu m3=new JMenu("ѡ�ι���");
JMenuItem add3=new JMenuItem("������Ϣ   ");
JMenuItem updata3=new JMenuItem("������Ϣ   ");
JMenuItem delete3=new JMenuItem("ɾ����Ϣ   ");
JMenu m4=new JMenu("��ѯ����");
JMenuItem ѧ����ѯ=new JMenuItem("��ѯ��Ϣ   ");
JMenuItem m5=new JMenuItem("ϵͳ�˳�");
// ���ܵ�����
Font t=new Font ("sanerif",Font.PLAIN,12);
public Menu (){
	setVisible(true);  
this.setTitle("202204071022����Ң202204071021�տ���");
try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
catch(Exception e){System.err.println("�����������:   "+e);}
//��ϲ˵�
addMenu1();
addMenu2();
addMenu3();
addMenu4();
addJMenuBar();
setJMenuBar(mb);

label=new JLabel("ѡ�ι���ϵͳ",JLabel.CENTER);
label.setFont(new Font("����",Font.BOLD,12));
label.setHorizontalTextPosition(SwingConstants.CENTER);
label.setForeground(Color.red);
//����¼�
add1.addActionListener(this);
updata1.addActionListener(this);
delete1.addActionListener(this);
m5.addActionListener(this);
add2.addActionListener(this);
delete2.addActionListener(this);
updata2.addActionListener(this);
add3.addActionListener(this);
delete3.addActionListener(this);
updata3.addActionListener(this);
ѧ����ѯ.addActionListener(this);
//����ͼƬ��ǩ������ͼƬ  
//����ͼƬ
Image img2 = new ImageIcon("image\\2.jpg").getImage();  
JLabel q1 = new JLabel(new ImageIcon(img2));  
q1.setBounds(40, 95, 60, 53); // ������Ҫ����λ�úʹ�С 
card=new CardLayout();
pCenter=new JPanel();
pCenter.setLayout(card);
//��ӻ�ӭ�����ǩ������ͼƬ��  
pCenter.add("��ӭ����", q1); // ֱ�ӽ�ͼƬ��ǩ��Ϊ��ӭ����
JLabel p1 = new JLabel();
����ѧ������=new Addstu();
�޸�ѧ������=new Updatastu(); 
ɾ��ѧ������=new Delstu();
���ӿγ̽���=new AddC();
ɾ���γ̽���=new DelC();
�޸Ŀγ̽���=new UpdateC();
����ѡ�ν���=new AddSC();
ɾ��ѡ�ν���=new DelSC();
�޸�ѡ�ν���=new UpdateSC();
ѧ����ѯ����=new Selstu();

Image img3 = new ImageIcon("image\\3.jpg").getImage(); 
p1.setIcon(new ImageIcon(img3)); 
p1.setBounds(40, 95, 60, 53); 
    pCenter.add("��ӭ����",label);
pCenter.add("����ѧ������",����ѧ������);
pCenter.add("�޸�ѧ������",�޸�ѧ������);
pCenter.add("ɾ��ѧ������",ɾ��ѧ������);
pCenter.add("���ӿγ̽���",���ӿγ̽���);
pCenter.add("ɾ���γ̽���",ɾ���γ̽���);
pCenter.add("�޸Ŀγ̽���",�޸Ŀγ̽���);
pCenter.add("����ѡ�ν���",����ѡ�ν���);
pCenter.add("ɾ��ѡ�ν���",ɾ��ѡ�ν���);
pCenter.add("�޸�ѡ�ν���",�޸�ѡ�ν���);
pCenter.add("ѧ����ѯ����", ѧ����ѯ����);

add(pCenter,BorderLayout.CENTER);
validate();

// setVisible(true);


setBounds(400,150,500,375);
    addWindowListener(new WindowAdapter(){//�رճ���ʱ�Ĳ���
public void windowClosing(WindowEvent e){System.exit(0);}
});
validate();

}

private void addJMenuBar() {
mb.add(m1);mb.add(m2);mb.add(m3);mb.add(m4);mb.add(m5);
}
private void addMenu4() {
m4.add(ѧ����ѯ);
     m4.setFont(t);
}
private void addMenu3() {
m3.add(add3);
m3.add(updata3);
m3.add(delete3);
m3.setFont(t);
}
private void addMenu2() {//���˵����뵽�˵�����
m2.add(add2);
m2.add(updata2);
m2.add(delete2);
m2.setFont(t);
}
private void addMenu1() {
m1.add(add1);
m1.add(updata1);
m1.add(delete1);
m1.setFont(t);//����
}

public void actionPerformed(ActionEvent e){
Object obj=e.getSource();
card.show(pCenter, "��ӭ����");
if(obj==m5){System.exit(0);}
else{if(obj==add1){
card.show(pCenter,"����ѧ������");
}
else{if(obj==updata1){
card.show(pCenter,"�޸�ѧ������");
}
else{if(obj==delete1){
card.show(pCenter, "ɾ��ѧ������");
}
else{if(obj==add2){
card.show(pCenter, "���ӿγ̽���");
}
else{if(obj==delete2){
card.show(pCenter, "ɾ���γ̽���");
}
else{if(obj==updata2){
card.show(pCenter, "�޸Ŀγ̽���");
}
else{if(obj==add3){
card.show(pCenter, "����ѡ�ν���");
}
else{if(obj==delete3){
card.show(pCenter, "ɾ��ѡ�ν���");
}
else{if(obj==updata3){
card.show(pCenter, "�޸�ѡ�ν���");
}
else{if(obj==ѧ����ѯ){
card.show(pCenter, "ѧ����ѯ����");
}}
}}}}}}}}}}
public static void main(String[] args) {
	new User().setVisible(true);
	// new Menu();
//Music.playMusic("D://JDK(old)//202204071022 ����Ң//����JAVA������ѧ��ѡ�ι���ϵͳ//��������.wav");
}
public void showMenu() {  
    setVisible(true); // ��������ʾ����  
}  
}


