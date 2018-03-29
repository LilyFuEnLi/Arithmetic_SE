import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
public class Calculate_SE {
	 ArrayList<String> IntegerQ=new ArrayList<String>(); //��������ʽ����
	 ArrayList<String> IntegerA=new ArrayList<String>(); 
	final static ArrayList<String> FractionQ = new ArrayList<String>();
	final static ArrayList<String> FractionA = new ArrayList<String>();
    private static LinkedList<String> operators=new LinkedList<>();	//���ڼ�¼������
    private static LinkedList<String> output=new LinkedList<>();    //���ڼ�¼���
    private static StringBuilder sb=new StringBuilder();    //����չʾ��׺���ʽ
	static int M=50;
	static int [][]N=new int[M][M]; //�������ʽ������
	static char [][]C=new char[M][M];//�������ʽ���ַ�
	static int []CN=new int[M];      //��¼����ʽ�����ָ���
	static char [] Str={'+','-','*','/'};
	Scanner scanner=new Scanner(System.in);
	
//-------------------�����������ʽ,���÷���������---------------------------------------
	public void calaulate_AE(int number) throws IOException {
		for(int i=0;i<number;i++)
		{
			//CN[i]=(int)(Math.random()*2+4);
			CN[i]=5;
			for(int j=0;j<CN[i];j++)
			{
				N[i][j]=(int) (Math.random()*100+1);
			}
			for(int k=0;k<CN[i]-1;k++)
			{
				C[i][k]=Str[(int)(Math.random()*3)];	
			}
			for(int k=0;k<CN[i]-1;k++)
			{
			   while(C[i][0]==C[i][1])
					C[i][1]=Str[(int)(Math.random()*3)];
				if(C[i][k]=='-' && (N[i][k]<N[i][k+1]))
				{
					int temp=0;
					temp=N[i][k];
					N[i][k+1]=temp;
					N[i][k]=temp;
				}
				if(C[i][k]=='/' && (N[i][k]%N[i][k+1]!=0))
				{
					int temp=N[i][k];
					N[i][k] = N[i][k] <N[i][k+1]? N[i][k]: N[i][k+1];
					N[i][k+1] = temp > N[i][k+1]? temp: N[i][k+1];
					for(int num = N[i][k]; num >= 1; num--)
					{
						if(N[i][k] % num == 0 && N[i][k+1] % num == 0)
						{
							N[i][k+1]=num;
							break;
						}
					}
				}
			}
			String AE=new String();
			LinkedList<String> list=new LinkedList<>();
			list.add(String.valueOf('('));
			list.add(String.valueOf(N[i][0]));
			list.add(String.valueOf(C[i][0]));
			list.add(String.valueOf(N[i][1]));
			list.add(String.valueOf(')'));
			list.add(String.valueOf(C[i][1]));
			AE+='('+String.valueOf(N[i][0])+String.valueOf(C[i][0])+String.valueOf(N[i][1])+')'+String.valueOf(C[i][1]);
				for(int j=2;j<CN[i]-2;j++)
				{
						list.add(String.valueOf(N[i][j]));
						list.add(String.valueOf(C[i][j]));
						AE+=String.valueOf(N[i][j])+String.valueOf(C[i][j]);
				}
				list.add(String.valueOf('('));
				list.add(String.valueOf(N[i][CN[i]-2]));
				list.add(String.valueOf(C[i][CN[i]-2]));
				list.add(String.valueOf(N[i][CN[i]-1]));
				list.add(String.valueOf(')'));
				AE+='('+String.valueOf(N[i][CN[i]-2])+String.valueOf(C[i][CN[i]-2])+String.valueOf(N[i][CN[i]-1])+')'+'=';
				String sum=transferToPostfix(list);
				char fir =sum.charAt(0);
				if(fir!='-')
				{
					IntegerA.add(sum);
					IntegerQ.add(AE);
				}
		}
		 File f=new File("zhengshu.txt");
         BufferedWriter bw=new BufferedWriter(new FileWriter(f));
         for(int i=0;i<IntegerQ.size();i++){
             bw.write(IntegerQ.get(i));
             bw.newLine();
         }
         bw.close();
		 Iterator it1 = IntegerQ.iterator();  
         while (it1.hasNext()) {  
             System.out.println(it1.next());  
         } 
         Iterator it11 = IntegerA.iterator();  
         while (it11.hasNext()) {  
             System.out.println(it11.next());  
         } 
	}
//--------------------------------------------------------------------------
	public void fenshu() throws IOException
	{
		 int num1,num2,num3;
         for(int i=0;i<40;i++){
         num1=1+(int)(Math.random()*10);
         num2=1+(int)(Math.random()*10);
         num3=1+(int)(Math.random()*10);
 	   int M,Z;
        int x1,x2,x3;
            x1=1+(int)(Math.random()*num1);
            x2=1+(int)(Math.random()*num2);
            x3=1+(int)(Math.random()*num3);
                Z=x1*num2*num3+x2*num1*num3+x3*num1*num2;
                M=num1*num2*num3;
                String d=simplification(Z,M);
                String s=x1+"/"+num1+"+"+x2+"/"+num2+"+"+x3+"/"+num3+"=";
                FractionQ.add(s);
                FractionA.add(d);
         }
         File f=new File("fenshu.txt");
         BufferedWriter bw=new BufferedWriter(new FileWriter(f));
         for(int i=0;i<FractionQ.size();i++){
             bw.write(FractionQ.get(i));
             bw.newLine();
         }
         bw.close();
         Iterator it1 = FractionQ.iterator();  
         while (it1.hasNext()) {  
             System.out.println(it1.next());  
         } 
         Iterator it11 = FractionA.iterator();  
         while (it11.hasNext()) {  
             System.out.println(it11.next());  
         } 
	}
	
    public static String simplification(int a,int b){
        int y = 1;
        for(int i=a;i>=1;i--){
            if(a%i==0&&b%i==0){
                y = i;
                break;
            }
        }
        int z = a/y;
        int m = b/y;
        if(z==0) {
            return "0";
        }
        if(m==1)
        	return ""+z;
        return z+"/"+m;
    }   
	//------------------------��׺���ʽתΪ��׺���ʽ---------------------------------------------------
    private static String transferToPostfix(LinkedList<String> list){
        Iterator<String> it=list.iterator();
        while (it.hasNext()) 
        {
            String s = it.next();
            if (isOperator(s)) 
            {
                if (operators.isEmpty()) 
                {
                    operators.push(s);
                }
                else 
                {//�������Ĳ�����Ϊ��")"�����ȼ���ջ��Ԫ�ص����ȼ��߻�һ�����򽫲�����ѹ��ջ
                    if (priority(operators.peek())<=priority(s)&&!s.equals(")")) 
                    {
                        operators.push(s);
                    }
                    else if(!s.equals(")")&&priority(operators.peek())>priority(s))
                    {
                        while (operators.size()!=0&&priority(operators.peek())>=priority(s)&&!operators.peek().equals("(")) 
                        {
                            if (!operators.peek().equals("(")) 
                            {
                                String operator=operators.pop();
                                sb.append(operator).append(" ");
                                output.push(operator);
                            }
                        }
                        operators.push(s);
                    }
                    //�������Ĳ�������")"���򵯳���ջ����ʼ��һ��"("����֮ǰ�����в�����
                    else if (s.equals(")")) 
                    {
                        while (!operators.peek().equals("(")) 
                        {
                            String operator=operators.pop();
                            sb.append(operator).append(" ");
                            output.push(operator);
                        }
                        //����"("
                        operators.pop();
                    }
                }
            }
            //�����Ϊ�ǲ�����
            else 
            {
                sb.append(s).append(" ");
                output.push(s);
            }
        }
        if (!operators.isEmpty()) 
        {
            Iterator<String> iterator=operators.iterator();
            while (iterator.hasNext()) 
            {
                String operator=iterator.next();
                sb.append(operator).append(" ");
                output.push(operator);
                iterator.remove();
            }
        }
        String sum= calculate();
       return sum;
    }
    
  //-------------------���ݺ�׺���ʽ������----------------------------------------------
    private static String calculate(){
        LinkedList<String> mList=new LinkedList<>();
        String[] postStr=sb.toString().split(" ");
        for (String s:postStr) 
        {
            if (isOperator(s))
            {
                if (!mList.isEmpty())
                {
                    int num1=Integer.valueOf(mList.pop());
                    int num2=Integer.valueOf(mList.pop());
                    int newNum=cal(num2,num1,s);
                    mList.push(String.valueOf(newNum));
                }
            }
            else 
            { //������ѹ��ջ��
                mList.push(s);
            }
        }
        if (!mList.isEmpty())
        {
            return mList.pop();
        }
		return null;
    }

  //--------------------------------�ж��Ƿ������----------------------------------------------------
    private static boolean isOperator(String oper){
        if (oper.equals("+")||oper.equals("-")||oper.equals("/")||oper.equals("*")||oper.equals("(")||oper.equals(")")) 
        {
            return true;
        }
        return false;
    }
 //-----------------------��������������ȼ�-----------------------------------------------------
    private static int priority(String s){
        switch (s) {
            case "+":return 1;
            case "-":return 1;
            case "*":return 2;
            case "/":return 2;
            case "(":return 3;
            case ")":return 3;
            default :return 0;
        }
    }
//------------------------����------------------------------------------------------------------------
    private static int cal(int num1,int num2,String operator){
        switch (operator){
            case "+":return num1+num2;
            case "-":return num1-num2;
            case "*":return num1*num2;
            case "/":return num1/num2;
            default :return 0;
        }
}

	}
