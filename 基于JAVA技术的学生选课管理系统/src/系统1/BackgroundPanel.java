package ϵͳ1;

import java.awt.*;  
import javax.swing.*;  
  

//����һ����ΪBackgroundPanel���࣬���̳���JPanel��JPanel��Swing�е�һ�����������������԰�����������������Լ������ݡ�
public class BackgroundPanel extends JPanel {  
	
//	  ����һ��˽�г�Ա����backgroundImage������ΪImage������������ڴ洢����ͼ��
    private Image backgroundImage;  
  
    // ����һ�����췽����������һ��Image���͵Ĳ����������丳ֵ��backgroundImage��Ա������
    //     ������췽��������BackgroundPanelʵ��ʱָ������ͼ��
    public BackgroundPanel(Image image) {  
        this.backgroundImage = image;  
    }  
  
 // ��дJPanel��paintComponent������paintComponent������Swing������������ݵı�׼������
//     ��������Ǹ����˸÷�����������ϻ��Ʊ���ͼ��
    @Override  
    protected void paintComponent(Graphics g) {  
    	
    	// ����super.paintComponent(g);ȷ��JPanel��Ĭ�ϻ�����Ϊ��ִ�С�
        // ����ڻ��Ʊ���ɫ���߿���Ǻ���Ҫ�ġ�
        super.paintComponent(g);  
        
     // ʹ��if�����backgroundImage�Ƿ�Ϊnull�����Է�ֹ��ͼ��δ�ɹ�����ʱ���Ի�����������NullPointerException��
        if (backgroundImage != null) {  
        	 // ʹ��Graphics�����drawImage�������Ʊ���ͼ��
            // drawImage�����Ĳ�������Ҫ���Ƶ�ͼ��ͼ��������е�x��y���ꡢͼ��Ŀ�Ⱥ͸߶ȣ��Լ�ͼ��Ĺ۲��ߣ�ͨ���ǻ���ͼ����������
            // ��������ǽ�ͼ��������������Ͻ�(0,0)������ͼ��Ŀ�Ⱥ͸߶�����Ϊ���Ŀ�Ⱥ͸߶ȣ�ʹͼ����Ӧ���Ĵ�С��
            // ע�⣺drawImage�����ж�����ذ汾������ʹ�õ��ǿ����Զ�����ͼ���С����Ӧָ���ߴ�İ汾��
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);  
        }  
    }  
}