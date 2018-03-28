import javax.swing.*;    
import java.awt.HeadlessException;    
import java.awt.BorderLayout;    
import java.awt.FlowLayout;    
import java.awt.Font;    
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;    

/**  
 * ��ʱ��  
 */    
public class Timer extends JFrame {    
   
    private static final long serialVersionUID = 1L;  
    private static final String INITIAL_LABEL_TEXT = "00:00:00 000";     
    // �����߳�    
    private CountingThread thread = new CountingThread();    
    // ��¼����ʼʱ��    
    private long programStart = System.currentTimeMillis();    
    // ����һ��ʼ������ͣ��    
    private long pauseStart = programStart;    
    // ������ͣ����ʱ��    
    private long pauseCount = 0;    
    private JLabel label = new JLabel(INITIAL_LABEL_TEXT);    
    private JButton startPauseButton = new JButton("��ʼ");    
    private JButton resetButton = new JButton("����");    
    String time;
    private ActionListener startPauseButtonListener = new ActionListener() {    
        public void actionPerformed(ActionEvent e) {    
            if (thread.stopped) {    
                pauseCount += (System.currentTimeMillis() - pauseStart);    
                thread.stopped = false;    
                startPauseButton.setText("��ͣ");    
            } else {    
                pauseStart = System.currentTimeMillis();    
                thread.stopped = true;    
                startPauseButton.setText("����");    
            }    
        }    
    };   
    private ActionListener resetButtonListener = new ActionListener() {    
        public void actionPerformed(ActionEvent e) {    
            pauseStart = programStart;    
            pauseCount = 0;    
            thread.stopped = true;    
            label.setText(INITIAL_LABEL_TEXT);    
            startPauseButton.setText("��ʼ");    
        }    
    };    
    public Timer(String title) throws HeadlessException {    
        super(title);    
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        setLocation(300, 300);    
        setResizable(false);    
     
        setupBorder();    
        setupLabel();    
        setupButtonsPanel();    
        startPauseButton.addActionListener(startPauseButtonListener);    
        resetButton.addActionListener(resetButtonListener);    
        thread.start(); // �����߳�һֱ��������    
    }    
    // Ϊ���������ӱ߿�    
    private void setupBorder() {    
        JPanel contentPane = new JPanel(new BorderLayout());    
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));    
        this.setContentPane(contentPane);    
    }    
    // ���ð�ť    
    private void setupButtonsPanel() {    
        JPanel panel = new JPanel(new FlowLayout());    
        panel.add(startPauseButton);    
        panel.add(resetButton);    
        add(panel, BorderLayout.SOUTH);    
    }    
    // ���ñ�ǩ    
    private void setupLabel() {    
        label.setHorizontalAlignment(SwingConstants.CENTER);    
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));    
        this.add(label, BorderLayout.CENTER);    
    }   
     
    private class CountingThread extends Thread {    
     
        public boolean stopped = true;    
     
        private CountingThread() {    
            setDaemon(true);    
        }    
     
        @Override    
        public void run() {    
            while (true) {    
                if (!stopped) {    
                    long elapsed = System.currentTimeMillis() - programStart - pauseCount;    
                    label.setText(format(elapsed));    
                    time=label.getText();
                    File f=new File("result1.txt");
                    BufferedWriter bw;
					try {
						bw = new BufferedWriter(new FileWriter(f));
						bw.write(time);
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }    
     
                try {    
                    sleep(1);  // 1�������һ����ʾ  
                } catch (InterruptedException e) {    
                    e.printStackTrace();    
                    System.exit(1);    
                }    
            }    
        }    
     
        // ����������ʽ��    
        private String format(long elapsed) {    
            int hour, minute, second, milli;    
     
            milli = (int) (elapsed % 1000);    
            elapsed = elapsed / 1000;    
     
            second = (int) (elapsed % 60);    
            elapsed = elapsed / 60;    
     
            minute = (int) (elapsed % 60);    
            elapsed = elapsed / 60;    
     
            hour = (int) (elapsed % 60);    
     
            return String.format("%02d:%02d:%02d:%03d", hour, minute, second, milli);    
        }    
    }    
}    