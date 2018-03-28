import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;

public class Demo10 extends JFrame{

	//��������ͳ��ͼ
	private Random ran;
	public Demo10(){
		super();
		ran = new Random();
		setTitle("��������ͼ");
		setBounds(100, 100, 400, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void paint(Graphics g){
		int Width = getWidth();
		int Height = getHeight();
		int leftMargin = 20;//����ͼ��߽�
		int topMargin = 50;//����ͼ�ϱ߽�
		Graphics2D g2 = (Graphics2D) g;
		int ruler = Height-topMargin-5;
		int rulerStep = ruler/10;//����ǰ�ĸ߶�����Ϊ10����λ
		g2.setColor(Color.WHITE);//���ư�ɫ����
		g2.fillRect(0, 0, Width, Height);//���ƾ���ͼ
		g2.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<=10;i++){//���ƻ�ɫ���ߺͰٷֱ�
			g2.drawString((100-10*i)+"%", 5, topMargin+(Height-30*i));//д�°ٷֱ�
			g2.drawString((000)+"%", 5, 250);//д�°ٷֱ�
			if(i==5)
			{
				System.out.println(topMargin+rulerStep*i);
				System.out.println(topMargin);
				System.out.println(rulerStep);
			}
			
			g2.drawLine(5, topMargin+rulerStep*i, Width, topMargin+rulerStep*i);//���ƻ�ɫ����
		}
		g2.setColor(Color.PINK);
		for(int i=0;i<4;i++){//��������ͼ
			int value = ran.nextInt(Height-topMargin-10)+10;//����������εİٷֱ�
			int step = (i+1)*40;//����ÿ������ͼ��ˮƽ���Ϊ40
			//���ƾ���
//			g2.drawRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);
			g2.fillRoundRect(leftMargin+step*2, Height-value, 40, value, 40, 10);
			//System.out.println(Height);
			//System.out.println(value);
			//�г���Ʒ�ı��
			g2.drawString("��Ʒ"+(i+1), leftMargin+step*2, Height-value-5);
		}
	}
	public static void main(String[] args) {
		Demo10 demo = new Demo10();
		demo.setVisible(true);
	}
}
