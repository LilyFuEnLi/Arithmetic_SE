import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
public class Main {
	static int number;
	public static void main(String args[]) throws IOException
	{
		//System.out.print("����������ʽ������");
		//Scanner scanner=new Scanner(System.in);
		//number=scanner.nextInt();
		//System.out.print("��ʼ���⣬Ҫ��ϸŶ��(*^��^*)��\n");
		Calculate_SE CA=new Calculate_SE();
		CA.calaulate_AE(20);
		//CA.fenshu();
	System.out.print("��ȷ��λ��result.txt�ļ��У��뼰ʱ��ѯ����ϰ!\n");
		 File f=new File("result.txt");
         BufferedWriter bw=new BufferedWriter(new FileWriter(f));
         bw.write("201571030108");
         bw.newLine();
         for(int i=0;i<CA.Arithmetic11.size();i++){
             bw.write(CA.Arithmetic11.get(i));
             bw.newLine();
         }
         bw.close();
	}
}