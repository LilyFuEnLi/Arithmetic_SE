
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Score extends JFrame{

	JButton jb1,jb2;
	JTextField Name,Number;
	//JPasswordField jpwd;
	JLabel jl1,jl2;
	JPanel jp1,jp2,jp3;
	
		//�������
		jb1=new JButton("ȷ��");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String N=Name.getText();
				String M=Number.getText();
				name.add(N);
				number.add(M);
				       
					    Start frame = new Start();
					    frame.setTitle("Сѧ��������ѵ��С����");
					      frame.setSize(600,400);
					      frame.setLocationRelativeTo(null);
					      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					      frame.setVisible(true);
					    frame.setVisible(true);        // ���½���
					    dispose();        // �رյ�ǰ����
				
			}
		});
		jb2=new JButton("ȡ��");
		
		Name=new JTextField(10);
		Number=new JTextField(10);
		
		jl1=new JLabel("������");
		jl2=new JLabel("ѧ�ţ�");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		//���ò��ֹ�����
		this.setLayout(new GridLayout(3,1,5,5));
		//������
		jp1.add(jl1);
		jp1.add(Name);
		
		jp2.add(jl2); 
		jp2.add(Number);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		//���ô�������
		this.setTitle("��¼����");
		this.setSize(280, 160);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
}