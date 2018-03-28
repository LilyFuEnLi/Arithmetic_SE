import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
 
public class Start extends JFrame{
	int number=0,temp=0;
	int NUM=0;
	Calculate_SE CA=new Calculate_SE();
    int rightSum=0;
    CreateArithmetic createArithmeticnew=new CreateArithmetic();
    List<String> newList = new ArrayList<>();//��һ�ַ���
    private JPanel panel5=new JPanel();
    
    private JPanel panel1=new JPanel();
    private JLabel label=new JLabel();
    private JLabel label1=new JLabel();
    //private JLabel panel4=new JPanel();
    JTextField text=new JTextField(10);
   // ArrayList<String> list=new ArrayList<String>();
    ArrayList<String> list=new ArrayList<String>();
    /*public static void main(String[] args) {
      JFrame log=new JFrame();
      
      JFrame frame=new  Start();
      frame.setTitle("Сѧ��������ѵ��С����");
      frame.setSize(400,200);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }*/
    public Start()
    {
        setLayout(new GridLayout(5,2,5,5));
        addAnswer();
        JPanel panel2=new JPanel();
        //panel2.setSize(10,100);
        panel2.add(new JLabel("������𰸣�"));
        panel2.add(text);
        this.add(panel2);
        //---------------------------------------����-------------------------
        
        JRadioButton buttonInteger=new JRadioButton("����");//JRadioButton
        buttonInteger.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				CA.calaulate_AE(5);
				while(CA.Arithmetic12.get(number)=="0")
				{
					number++;
				}
				String shizi = CA.Arithmetic11.get(number);
				NUM++;
		        label1.setText(shizi);
			}
		});
 //--------------------------------����---------------------------------
        JRadioButton buttonFenshu = new JRadioButton("����");
        buttonFenshu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				CA.fenshu();
				String shizi = CA.fraction.get(number);
				NUM++;
		        label1.setText(shizi);
			}
		});
       //=========================================================
        JButton buttonFenXi = new JButton("�������");
        buttonFenXi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start frame = new Start();
			    frame.setTitle("Сѧ��������ѵ��С����");
			      frame.setSize(600,400);
			      frame.setLocationRelativeTo(null);
			      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			      frame.setVisible(true);
			    frame.setVisible(true);        // ���½���
			    number+=20;
			    dispose(); 	
			}
		});
        
        JButton buttonAgain = new JButton("������һ��");
       
 //----------------------------------------�ύ�𰸰�ť���趨--------------------------
        JButton buttonSubmit=new JButton("�ύ��");
        buttonSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 if(buttonInteger.isSelected())
          	   {
          		   String question=label1.getText();
          		   String result=text.getText();
          		   String ifRight="";
          		   if((CA.Arithmetic12.get(number-1)).equals(result)){
          			   ifRight="��ȷ";
          			   rightSum++;
          		   }else{
          			   ifRight="����";
          		   }
                 String message=question+"�� "+"��Ĵ𰸣�"+result+"�� "+"��ȷ�𰸣� "+CA.Arithmetic12.get(number-1)+"�� ״̬�� "+ifRight;
                 list.add(message);
                 text.setText("");
          
                 }
             
             if(buttonFenshu.isSelected())
      	   {
      		   String question=label1.getText();
      		   String result=text.getText();
      		   String ifRight="";
      		   if(CA.FraAnswer.get(number-1).equals(result)){
      			   ifRight="��ȷ";
      			   rightSum++;
      		   }else{
      			   ifRight="����";
      		   }
             String message=question+"�� "+"��Ĵ𰸣�"+result+"�� "+"��ȷ�𰸣� "+CA.FraAnswer.get(number-1)+"�� ״̬�� "+ifRight;
             list.add(message);
             text.setText("");
             }
           //-----------------------------------------
             temp=number-1;
             String score=String.valueOf(rightSum*5);
             newList.add(score);
             File file = new File("test.txt");
             BufferedWriter bw = null;
                 try {   
                      bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.txt", true))); 
                      bw.newLine();
                      bw.write(score);   
                  } catch (Exception e1) {   
                      e1.printStackTrace();   
                  } finally {   
                     try {   
                          bw.close();   
                      } catch (IOException e1) {   
                          e1.printStackTrace();   
                      }   
                  }   
            
                int size=list.size();
                int errorSum=size-rightSum;
                JFrame resultFrame=new JFrame();
                resultFrame.setTitle("������");
                resultFrame.setSize(500,500);
                resultFrame.setLocationRelativeTo(null);
                resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                resultFrame.setVisible(true);
                JPanel panel=new JPanel();
                Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
                panel.add(new JLabel("���������� "+size+" "+"��ȷ����"+rightSum+" "+" ���յ÷֣�"+rightSum*5));
                setLayout(new GridLayout(100,1,5,5));
                for(int i=0;i<size;i++){
                    panel.add(new JLabel(list.get(i)));
                }
                panel.add(buttonFenXi);
                panel.add(buttonAgain);
                resultFrame.add(panel);
               
            }});

 //-------------------------------------��һ��𰸰�ť���趨-------------------------------------------------------
        JButton buttonNext=new JButton("��һ��");
        buttonNext.addActionListener(new ActionListener() {//����ʱ�������
           public void actionPerformed(ActionEvent e) {
        	   if(buttonInteger.isSelected())
        	   {
        		   String question=label1.getText();
        		   String result=text.getText();
        		   String ifRight="";
        		   if((CA.Arithmetic12.get(number)).equals(result)){
        			   ifRight="��ȷ";
        			   rightSum++;
        		   }else{
        			   ifRight="����";
        		   }
               String message=question+"�� "+"��Ĵ𰸣�"+result+"�� "+"��ȷ�𰸣� "+CA.Arithmetic12.get(number)+"�� ״̬�� "+ifRight;
               list.add(message);
               text.setText("");
               if(number==0)
            	   number++;
               while(CA.Arithmetic12.get(number)=="0")
				{
					number++;
				}
               String shizi=CA.Arithmetic11.get(number);
               NUM++;
               number++;
               label1.setText(shizi);
               if(NUM==20)
               {
               	JOptionPane.showMessageDialog(null, "�������", "����Ϊ��20���⣬������������ύ�𰸡�", JOptionPane.INFORMATION_MESSAGE);
               }           
               }
           
           if(buttonFenshu.isSelected())
    	   {
    		   String question=label1.getText();
    		   String result=text.getText();
    		   String ifRight="";
    		   if(CA.FraAnswer.get(number).equals(result)){
    			   ifRight="��ȷ";
    			   rightSum++;
    		   }else{
    			   ifRight="����";
    		   }
           String message=question+"�� "+"��Ĵ𰸣�"+result+"�� "+"��ȷ�𰸣� "+CA.FraAnswer.get(number)+"�� ״̬�� "+ifRight;
           list.add(message);
           text.setText("");
           if(number==0)
        	   number++;
           String shizi=CA.fraction.get(number);
           NUM++;
           number++;        
            label1.setText(shizi);
            if(NUM==5)
            {
            	JOptionPane.showMessageDialog(null, "�������", "����Ϊ��20���⣬������������ύ�𰸡�", JOptionPane.INFORMATION_MESSAGE);
            }
           }
           }        
              });
   
   //-------------------------��������ť�����------------------------------     
        
        JPanel panel3=new JPanel();
        //button.setSize(10,10);
       
        panel3.add(buttonFenshu);
        panel3.add(buttonInteger);
        add(panel3);
        
        JPanel panel4 = new JPanel();
        panel4.add(buttonNext);
        panel4.add(buttonSubmit);
        add(panel4);
        
        
    //------------------------------------------------
    }

    public void addAnswer(){
        //System.out.println("hah");
        //panel1.setSize(0,50);
        Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
        label.setFont(font);
       // String answer=createArithmeticnew.creat();
        File file = new File("test.txt");
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null) {
                newList.add(line);
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
         
        for(String str : newList) {
            System.out.println(str);
        }
        int n=newList.size()-1;
        String answer=newList.get(n);
        //number++;
        label.setText(answer);
        
        JLabel score = new JLabel();
        score.setText("������һ�ֳɼ��ǣ�");
        panel1.add(score);
        panel1.add(label);     
        add(panel1);
        
        //String shizi = CA.Arithmetic11.get(number);
        String shizi = " ";
        label1.setText(shizi);
        panel5.add(label1);
        add(panel5);
    }
}