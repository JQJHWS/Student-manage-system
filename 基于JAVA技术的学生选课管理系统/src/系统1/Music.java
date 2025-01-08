package 系统1;

import javax.sound.sampled.*;  
import java.io.File;  
import java.io.IOException;  
  
public class Music {  
//	 定义一个静态方法playMusic，它接受一个字符串参数filePath，表示音频文件的路径。
    public static void playMusic(String filePath) {  
        try {  
        	// 创建一个File对象，表示要播放的音频文件。
            File audioFile = new File(filePath); 
            
         // 通过AudioSystem.getAudioInputStream方法获取一个指向音频文件的AudioInputStream对象。
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);  
            
         // 获取音频流的格式，这通常包括编码类型、采样率、样本大小、通道数等信息。
            AudioFormat format = audioStream.getFormat();  
            
         // 创建一个DataLine.Info对象，指定我们想要的数据行类型为Clip（一个可以播放音频片段的简单接口）。
            // DataLine是处理音频数据流的接口，Clip是其一个具体实现。
            DataLine.Info info = new DataLine.Info(Clip.class, format);  
            
            // 通过AudioSystem.getLine方法获取一个符合我们指定信息的Clip对象。
            Clip audioClip = (Clip) AudioSystem.getLine(info);  
            
         // 打开Clip对象，并传入音频流。这将准备Clip对象以播放音频。
            audioClip.open(audioStream);  
            
         // 开始播放音频。
//            audioClip.start();  
         // 开始循环播放音频。参数为-1表示无限循环。
            audioClip.loop(-1);  
            
         // 注意：这里缺少了关闭Clip对象的代码。通常，你应该在音频播放完毕后关闭它，
            // 或者在不再需要时通过某种机制（如监听器）来关闭它。
            // 在这个例子中，Clip对象可能会在主线程结束后被垃圾回收器回收
            // 可以让音频播放完再关闭，或者设置一个监听器来在播放完时关闭它  
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) { 
        	
        	// 捕获并处理可能发生的异常：
            // UnsupportedAudioFileException：如果音频文件的格式不被支持。
            // LineUnavailableException：如果无法获取所需类型的数据行（如Clip）。
            // IOException：在读取音频文件时发生I/O错误。
            // 通过打印堆栈跟踪来记录异常信息。
            ex.printStackTrace();  
        }  
    }  
}