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
		System.out.print("请输入运算式个数：");
		Scanner scanner=new Scanner(System.in);
		number=scanner.nextInt();
		System.out.print("开始答题，要仔细哦！(*^▽^*)：\n");
		Calculate_SE CA=new Calculate_SE();
		CA.calaulate_AE(number);
		System.out.print("正确答案位于result.txt文件中，请及时查询并复习!\n");
		 File f=new File("result.txt");
         BufferedWriter bw=new BufferedWriter(new FileWriter(f));
         bw.write("201571030108");
         bw.newLine();
         for(int i=0;i<CA.Arithmetic.size();i++){
             bw.write(CA.Arithmetic.get(i));
             bw.newLine();
         }
         bw.close();
	}
}