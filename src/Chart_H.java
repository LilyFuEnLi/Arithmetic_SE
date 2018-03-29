
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Chart_H extends JFrame{
	List<String> newList = new ArrayList<>();
	private Random ran;
	int number=0;
	public Chart_H(){
		super();
		 File file = new File("test.txt");
	        FileReader fr = null;
	        BufferedReader br = null;
	        try {
	            fr = new FileReader(file);
	            br = new BufferedReader(fr);
	            String line = "";
	            while((line = br.readLine()) != null) {
	                newList.add(line);
	                number++;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                br.close();
	                fr.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		ran = new Random();
	}
	@Override
	public void paint(Graphics g){
		//int number=newList.size()-1;
		int Width = getWidth();
		int Height = getHeight();
		int leftMargin = 20;//����ͼ��߽�
		int topMargin = 50;//����ͼ�ϱ߽�
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);//���ư�ɫ����
		g2.fillRect(0, 0, Width, Height-100);//���ƾ���ͼ
		g2.setColor(Color.LIGHT_GRAY);
		for(int i=0;i<=10;i++)
		{//���ƻ�ɫ���ߺͰٷֱ�
			g2.drawString((100-10*i)+"", 5, topMargin+30*i);//д�°ٷֱ�
			g2.drawLine(5, topMargin+30*i, Width, topMargin+30*i);//���ƻ�ɫ����
		}
		g2.setColor(Color.PINK);
		for(int i=0;i<=number;i++)
		{//��������ͼ
			if(newList.get(i)!="")
			{
				int value =Integer.parseInt(newList.get(i));
				int step = (i+1)*40;//����ÿ������ͼ��ˮƽ���Ϊ40
				//���ƾ���
				g2.fillRoundRect(leftMargin+step*2-5,(100-value)*3+50, 40, 300-(100-value)*3, 40, 10);
				//�г���������
				g2.drawString(newList.get(i), leftMargin+step*2, (100-value)*3+50);
				g2.drawString("��"+(i+1)+"��", leftMargin+step*2, 380);
			}	
		}
	}
}
