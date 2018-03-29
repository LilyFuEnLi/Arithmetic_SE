
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Main extends JFrame{

	JButton jb1,jb2;
	JTextField Name,Number;
	JLabel jl1,jl2;
	JPanel jp1,jp2,jp3;
	final static ArrayList<String> name = new ArrayList<String>();
	final static ArrayList<String> number = new ArrayList<String>();
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
			} catch (Exception e) {
			e.printStackTrace();
			}
	
		Main test6=new Main();	
	}
	public Main()
	{
		//创建组件
		jb1=new JButton("确认");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String N=Name.getText();
				String M=Number.getText();
				name.add(N);
				number.add(M);
				       
					    Start frame = new Start();
					    frame.setTitle("小学四则运算训练小程序");
					      frame.setSize(600,400);
					      frame.setLocationRelativeTo(null);
					      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					      frame.setVisible(true);
					    frame.setVisible(true);     // 打开新界面
					    dispose();        // 关闭当前界面
					    try {    
				            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    
				        } catch (Exception e) {    
				            e.printStackTrace();    
				        }    
			}
		});
		jb2=new JButton("取消");
		
		Name=new JTextField(10);
		Number=new JTextField(10);
		
		jl1=new JLabel("姓名：");
		jl2=new JLabel("学号：");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		//设置布局管理器
		this.setLayout(new GridLayout(3,1,5,5));
		//添加组件
		jp1.add(jl1);
		jp1.add(Name);
		
		jp2.add(jl2); 
		jp2.add(Number);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		//设置窗体属性
		this.setTitle("登录界面");
		this.setSize(280, 160);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}