import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
public class Calculate_SE {
	 ArrayList<String> Arithmetic11=new ArrayList<String>(); //所有运算式集合
	 ArrayList<String> Arithmetic12=new ArrayList<String>(); 
	final static ArrayList<String> fraction = new ArrayList<String>();
	final static ArrayList<String> FraAnswer = new ArrayList<String>();
    private static LinkedList<String> operators=new LinkedList<>();	//用于记录操作符
    private static LinkedList<String> output=new LinkedList<>();    //用于记录输出
    private static StringBuilder sb=new StringBuilder();    //用于展示后缀表达式
	static int M=20;
	static int [][]N=new int[M][M]; //存放运算式的数字
	static char [][]C=new char[M][M];//存放运算式的字符
	static int []CN=new int[M];      //记录运算式中数字个数
	static char [] Str={'+','-','*','/'};
	Scanner scanner=new Scanner(System.in);
	
//-------------------生成随机运算式,调用方法计算结果---------------------------------------
	public void calaulate_AE(int number) {
		int Right=0;
		float R = 0;
		int F=0;
		
		//while(F==0)
		//{
		//outer:
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
				/*while(C[i][1]==C[i][2])
					C[i][2]=Str[(int)(Math.random()*3)];
				
				while(N[i][3]==N[i][4])
					N[i][4]=(int) (Math.random()*100+1);
				if(CN[i]==5)
				{
					int a=0,b=0,sum1,sum2;
					if(C[i][0]=='+')
						a=N[i][0]+N[i][1];
					if(C[i][0]=='-')
						a=N[i][0]-N[i][1];
					if(C[i][0]=='*')
						a=N[i][0]*N[i][1];
					if(C[i][0]=='/')
						a=N[i][0]/N[i][1];
					
					if(C[i][3]=='+')
						a=N[i][3]+N[i][4];
					if(C[i][3]=='-')
						a=N[i][3]-N[i][4];
					if(C[i][3]=='*')
						a=N[i][3]*N[i][4];
					if(C[i][3]=='/')
						a=N[i][3]/N[i][4];
					if(C[i][1]=='-')
					{
						while(a-N[i][2]<=0)
						{
							N[i][2]=(int) (Math.random()*100+1);
						}
					}
					if(C[i][1]=='/')
					{
						while(a%N[i][2]!=0)
						{
								int temp=a;
								a = a <N[i][2]? a: N[i][2];
								N[i][2] = temp > N[i][2]? temp: N[i][2];
								for(int num = a; num >= 1; num--)
								{
									if(a % num == 0 && N[i][2] % num == 0)
									{
										N[i][2]=num;
										break;
									}
								}
							}
					}
					
					if(C[i][3]=='-')
					{
						while(N[i][2]-b<=0)
						{
							N[i][3]=(int) (Math.random()*100+1);
						}
					}
					if(C[i][3]=='/')
					{
						while(N[i][2]%b!=0)
						{
								int temp=b;
								b = b <N[i][2]? b: N[i][2];
								N[i][2] = temp > N[i][2]? temp: N[i][2];
								for(int num = b; num >= 1; num--)
								{
									if(b % num == 0 && N[i][2] % num == 0)
									{
										N[i][2]=num;
										break;
									}
								}
							}
					}
				}*/
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
				Arithmetic12.add(sum);
				/*if(fir=='-'){
					Arithmetic11.clear();
					Arithmetic12.clear();
					continue outer;
				}
				/*System.out.print(AE);
				String SUM=scanner.nextLine();
				if(SUM.equals(sum))
				{
					System.out.print("回答真确，Very Good！(*^▽^*)：\n");
					Right++;
				}
				else
					System.out.print("回答错误，要加油哦！(*^▽^*)：\n");
				float R1 = (float)Right;
				float R2 = (float)number;
				R=(float) (R1/R2*100.00);
				AE+=sum;*/
				Arithmetic11.add(AE);
		}
		 Iterator it1 = Arithmetic11.iterator();  
         while (it1.hasNext()) {  
             System.out.println(it1.next());  
         } 
         Iterator it11 = Arithmetic12.iterator();  
         while (it11.hasNext()) {  
             System.out.println(it11.next());  
         } 
		//System.out.print("本次答题共计"+number+"道，回答正确"+Right+"道，正确率为"+R+"%.\n");
	}
	//}
//--------------------------------------------------------------------------
	public void fenshu()
	{
		 int num1,num2,num3;
         for(int i=0;i<21;i++){
         num1=1+(int)(Math.random()*10);//随机生成一个0-100的整数
         num2=1+(int)(Math.random()*10);//随机生成一个0-100的整数
         num3=1+(int)(Math.random()*10);//随机生成一个0-100的整数
 	   int M,Z;
        int x1,x2,x3;
            x1=1+(int)(Math.random()*num1);//生成一个比分母num1小的分子，实现真分数
            x2=1+(int)(Math.random()*num2);//生成一个比分母num2小的分子，实现真分数
            x3=1+(int)(Math.random()*num3);//生成一个比分母num3小的分子，实现真分数
                Z=x1*num2*num3+x2*num1*num3+x3*num1*num2;
                M=num1*num2*num3;
                String d=simplification(Z,M);
                String s=x1+"/"+num1+"+"+x2+"/"+num2+"+"+x3+"/"+num3+"=";
                fraction.add(s);
                FraAnswer.add(d);
         }
         Iterator it1 = fraction.iterator();  
         while (it1.hasNext()) {  
             System.out.println(it1.next());  
         } 
         Iterator it11 = FraAnswer.iterator();  
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
	//------------------------中缀表达式转为后缀表达式---------------------------------------------------
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
                {//如果读入的操作符为非")"且优先级比栈顶元素的优先级高或一样，则将操作符压入栈
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
                    //如果读入的操作符是")"，则弹出从栈顶开始第一个"("及其之前的所有操作符
                    else if (s.equals(")")) 
                    {
                        while (!operators.peek().equals("(")) 
                        {
                            String operator=operators.pop();
                            sb.append(operator).append(" ");
                            output.push(operator);
                        }
                        //弹出"("
                        operators.pop();
                    }
                }
            }
            //读入的为非操作符
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
    
  //-------------------根据后缀表达式计算结果----------------------------------------------
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
            { //数字则压入栈中
                mList.push(s);
            }
        }
        if (!mList.isEmpty())
        {
            return mList.pop();
        }
		return null;
    }

  //--------------------------------判断是否操作符----------------------------------------------------
    private static boolean isOperator(String oper){
        if (oper.equals("+")||oper.equals("-")||oper.equals("/")||oper.equals("*")||oper.equals("(")||oper.equals(")")) 
        {
            return true;
        }
        return false;
    }
 //-----------------------计算操作符的优先级-----------------------------------------------------
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
//------------------------计算------------------------------------------------------------------------
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
