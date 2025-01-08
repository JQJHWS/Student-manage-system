package 系统1;

import java.awt.*;  
import javax.swing.*;  
  

//定义一个名为BackgroundPanel的类，它继承自JPanel。JPanel是Swing中的一个轻量级容器，可以包含其他组件并绘制自己的内容。
public class BackgroundPanel extends JPanel {  
	
//	  声明一个私有成员变量backgroundImage，类型为Image。这个变量用于存储背景图像。
    private Image backgroundImage;  
  
    // 定义一个构造方法，它接受一个Image类型的参数，并将其赋值给backgroundImage成员变量。
    //     这个构造方法允许创建BackgroundPanel实例时指定背景图像。
    public BackgroundPanel(Image image) {  
        this.backgroundImage = image;  
    }  
  
 // 重写JPanel的paintComponent方法。paintComponent方法是Swing组件绘制其内容的标准方法。
//     在这里，我们覆盖了该方法以在面板上绘制背景图像。
    @Override  
    protected void paintComponent(Graphics g) {  
    	
    	// 调用super.paintComponent(g);确保JPanel的默认绘制行为被执行。
        // 这对于绘制背景色、边框等是很重要的。
        super.paintComponent(g);  
        
     // 使用if语句检查backgroundImage是否为null。可以防止在图像未成功加载时尝试绘制它而导致NullPointerException。
        if (backgroundImage != null) {  
        	 // 使用Graphics对象的drawImage方法绘制背景图像。
            // drawImage方法的参数包括要绘制的图像、图像在组件中的x和y坐标、图像的宽度和高度，以及图像的观察者（通常是绘制图像的组件）。
            // 在这里，我们将图像绘制在面板的左上角(0,0)，并将图像的宽度和高度设置为面板的宽度和高度，使图像适应面板的大小。
            // 注意：drawImage方法有多个重载版本，这里使用的是可以自动调整图像大小以适应指定尺寸的版本。
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);  
        }  
    }  
}