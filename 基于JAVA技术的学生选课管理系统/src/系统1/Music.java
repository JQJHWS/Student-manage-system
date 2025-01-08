package ϵͳ1;

import javax.sound.sampled.*;  
import java.io.File;  
import java.io.IOException;  
  
public class Music {  
//	 ����һ����̬����playMusic��������һ���ַ�������filePath����ʾ��Ƶ�ļ���·����
    public static void playMusic(String filePath) {  
        try {  
        	// ����һ��File���󣬱�ʾҪ���ŵ���Ƶ�ļ���
            File audioFile = new File(filePath); 
            
         // ͨ��AudioSystem.getAudioInputStream������ȡһ��ָ����Ƶ�ļ���AudioInputStream����
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);  
            
         // ��ȡ��Ƶ���ĸ�ʽ����ͨ�������������͡������ʡ�������С��ͨ��������Ϣ��
            AudioFormat format = audioStream.getFormat();  
            
         // ����һ��DataLine.Info����ָ��������Ҫ������������ΪClip��һ�����Բ�����ƵƬ�εļ򵥽ӿڣ���
            // DataLine�Ǵ�����Ƶ�������Ľӿڣ�Clip����һ������ʵ�֡�
            DataLine.Info info = new DataLine.Info(Clip.class, format);  
            
            // ͨ��AudioSystem.getLine������ȡһ����������ָ����Ϣ��Clip����
            Clip audioClip = (Clip) AudioSystem.getLine(info);  
            
         // ��Clip���󣬲�������Ƶ�����⽫׼��Clip�����Բ�����Ƶ��
            audioClip.open(audioStream);  
            
         // ��ʼ������Ƶ��
//            audioClip.start();  
         // ��ʼѭ��������Ƶ������Ϊ-1��ʾ����ѭ����
            audioClip.loop(-1);  
            
         // ע�⣺����ȱ���˹ر�Clip����Ĵ��롣ͨ������Ӧ������Ƶ������Ϻ�ر�����
            // �����ڲ�����Ҫʱͨ��ĳ�ֻ��ƣ�������������ر�����
            // ����������У�Clip������ܻ������߳̽�������������������
            // ��������Ƶ�������ٹرգ���������һ�����������ڲ�����ʱ�ر���  
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) { 
        	
        	// ���񲢴�����ܷ������쳣��
            // UnsupportedAudioFileException�������Ƶ�ļ��ĸ�ʽ����֧�֡�
            // LineUnavailableException������޷���ȡ�������͵������У���Clip����
            // IOException���ڶ�ȡ��Ƶ�ļ�ʱ����I/O����
            // ͨ����ӡ��ջ��������¼�쳣��Ϣ��
            ex.printStackTrace();  
        }  
    }  
}