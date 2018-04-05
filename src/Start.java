import java.awt.BorderLayout;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class Start extends JFrame {
	int number = 0, temp = 0;
	int NUM = 0;
	Calculate_SE CA = new Calculate_SE();
	int rightSum = 0;
	List<String> newList = new ArrayList<>();// ��һ�ַ���
	ArrayList<String> list1 = new ArrayList<String>();
	ArrayList<String> list2 = new ArrayList<String>();
	ArrayList<String> list3 = new ArrayList<String>();
	ArrayList<String> list4 = new ArrayList<String>();
	private JPanel panel5 = new JPanel();
	private JPanel panel1 = new JPanel();
	private JLabel label = new JLabel();
	private JLabel label1 = new JLabel();
	private JLabel Timelabel = new JLabel();
	private JLabel Time = new JLabel();
	JTextField text = new JTextField(10);

	public Start() {
		setLayout(new GridLayout(5, 2, 5, 5));
		addAnswer();
		JPanel panel2 = new JPanel();
		panel2.add(new JLabel("������𰸣�"));
		panel2.add(text);
		this.add(panel2);
		Timer frame2 = new Timer("��ʱ��");
		// ---------------------------------------����-------------------------

		JRadioButton buttonInteger = new JRadioButton("����");// JRadioButton
		buttonInteger.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				frame2.pack();
				frame2.setVisible(true);
				try {
					CA.calaulate_AE(40);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String shizi = CA.IntegerQ.get(number);
				NUM++;
				label1.setText(shizi);
			}
		});
		// --------------------------------����---------------------------------
		JRadioButton buttonFenshu = new JRadioButton("����");
		buttonFenshu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				frame2.pack();
				frame2.setVisible(true);
				try {
					CA.fenshu();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String shizi = CA.FractionQ.get(number);
				NUM++;
				label1.setText(shizi);
			}
		});
		// =========================================================
		JButton buttonFenXi = new JButton("�������");
		buttonFenXi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chart_H frame = new Chart_H();
				frame.setTitle("�ɼ�����ͼ");
				frame.setBounds(100, 100, 400, 420);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});

		JButton buttonAgain = new JButton("������һ��");
		buttonAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start frame = new Start();
				frame.setTitle("Сѧ��������ѵ��С����");
				frame.setSize(600, 400);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setVisible(true); // ���½���
				number += 20;
				dispose();

			}
		});
		// ----------------------------------------�ύ�𰸰�ť���趨--------------------------
		JButton buttonSubmit = new JButton("�ύ��");
		buttonSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
				if (buttonInteger.isSelected()) {
					String question = label1.getText();
					String result = text.getText();
					String ifRight = "";
					String time;
					if ((CA.IntegerA.get(number)).equals(result)) {
						ifRight = "��ȷ";
						rightSum++;
					} else {
						ifRight = "����";
					}
					list1.add(question);
					list2.add(result);
					list3.add(CA.IntegerA.get(number));
					list4.add(ifRight);
					text.setText("");

				}

				if (buttonFenshu.isSelected()) {
					String question = label1.getText();
					String result = text.getText();
					String ifRight = "";
					if (CA.FractionA.get(number).equals(result)) {
						ifRight = "��ȷ";
						rightSum++;
					} else {
						ifRight = "����";
					}
					list1.add(question);
					list2.add(result);
					list3.add(CA.FractionA.get(number));
					list4.add(ifRight);
					text.setText("");
				}
				// -----------------------------------------
				// temp=number-1;
				String score = String.valueOf(rightSum * 5);
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
				File file1 = new File("time.txt");
				FileReader fr1 = null;
				BufferedReader br1 = null;
				String time = "";
				try {
					fr1 = new FileReader(file1);
					br1 = new BufferedReader(fr1);
					String line = "";
					while ((line = br1.readLine()) != null) {
						time = line;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						br1.close();
						fr1.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

				int size = list1.size();
				int errorSum = size - rightSum;
				JFrame resultFrame = new JFrame();
				resultFrame.setTitle("������");
				resultFrame.setSize(500, 500);
				resultFrame.setLocationRelativeTo(null);
				resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				resultFrame.setVisible(true);
				JPanel panel7 = new JPanel();
				Timelabel.setText("������ʱΪ��" + time +" "+"���������� " + size + " " + "��ȷ����" + rightSum + " " + " ���յ÷֣�" + rightSum * 5);
				panel7.add(Timelabel);
				resultFrame.add(panel7, BorderLayout.NORTH);
				JPanel panel = new JPanel();
				Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
				JTable table = new JTable();
				String[] columns = { "     �� Ŀ        ", "  ��Ĵ�  ","  ��ȷ��  ","  ״ ̬  " };
				int[] columnWidth={160,120,120,50};
				DefaultTableModel model = new DefaultTableModel(columns, 0);
				table.setModel(model);
				JScrollPane scrollPane = new JScrollPane(table);   //֧�ֹ��� 
				TableColumnModel columnModel=table.getColumnModel();
				int count=columnModel.getColumnCount();
				javax.swing.table.TableColumn column =columnModel.getColumn(0);
				column.setPreferredWidth(columnWidth[0]);
				for(int i=0;i<20;i++)
				{
					String[] co={list1.get(i),list2.get(i),list3.get(i),list4.get(i)};
					model.addRow(co);
				}
				JTableHeader myt=table.getTableHeader();
				panel.add(myt, BorderLayout.NORTH);
				panel.add(table,BorderLayout.CENTER);
				JPanel panel6 = new JPanel();
				panel6.add(buttonFenXi);
				panel6.add(buttonAgain);
				resultFrame.add(panel);
				resultFrame.add(panel6, BorderLayout.SOUTH);

			}
		});

		// -------------------------------------��һ��𰸰�ť���趨-------------------------------------------------------
		JButton buttonNext = new JButton("��һ��");
		buttonNext.addActionListener(new ActionListener() {// ����ʱ�������
			public void actionPerformed(ActionEvent e) {
				if (buttonInteger.isSelected()) {
					String question = label1.getText();
					String result = text.getText();
					String ifRight = "";
					if ((CA.IntegerA.get(number)).equals(result)) {
						ifRight = "��ȷ";
						rightSum++;
					} else {
						ifRight = "����";
					}
					list1.add(question);
					list2.add(result);
					list3.add(CA.IntegerA.get(number));
					list4.add(ifRight);
					text.setText("");
					number++;
					String shizi = CA.IntegerQ.get(number);
					NUM++;
					label1.setText(shizi);
					if (NUM == 20) {
						JOptionPane.showMessageDialog(null, "����Ϊ��20���⣬������������ύ�𰸡�", "�������",
								JOptionPane.INFORMATION_MESSAGE);
						text.setText(result);
					}
				}

				if (buttonFenshu.isSelected()) {
					String question = label1.getText();
					String result = text.getText();
					String ifRight = "";
					if (CA.FractionA.get(number).equals(result)) {
						ifRight = "��ȷ";
						rightSum++;
					} else {
						ifRight = "����";
					}
					list1.add(question);
					list2.add(result);
					list3.add(CA.FractionA.get(number));
					list4.add(ifRight);
					text.setText("");
					number++;
					String shizi = CA.FractionQ.get(number);
					NUM++;
					label1.setText(shizi);
					if (NUM == 20) {
						JOptionPane.showMessageDialog(null, "����Ϊ��20���⣬������������ύ�𰸡�", "�������",
								JOptionPane.INFORMATION_MESSAGE);
						text.setText(result);
					}
				}
			}
		});

		// -------------------------��������ť�����------------------------------

		JPanel panel3 = new JPanel();
		panel3.add(buttonFenshu);
		panel3.add(buttonInteger);
		add(panel3);

		JPanel panel4 = new JPanel();
		panel4.add(buttonNext);
		panel4.add(buttonSubmit);
		add(panel4);
		// ------------------------------------------------
	}

	public void addAnswer() {
		Font font = new Font(Font.DIALOG, Font.PLAIN, 20);
		label.setFont(font);
		File file = new File("test.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
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
		int n = newList.size() - 1;
		String answer = newList.get(n);
		label.setText(answer);

		JLabel score = new JLabel();
		score.setText("������һ�ֳɼ��ǣ�");
		panel1.add(score);
		panel1.add(label);
		add(panel1);
		String shizi = " ";
		label1.setText(shizi);
		Font font1 = new Font(Font.DIALOG, Font.PLAIN, 20);
		label1.setFont(font1);
		panel5.add(label1);
		add(panel5);
	}
	
}
