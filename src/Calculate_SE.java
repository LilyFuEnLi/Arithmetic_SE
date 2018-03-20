import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
public class Calculate_SE {
	 ArrayList<String> Arithmetic=new ArrayList<String>(); //��������ʽ����
    private static LinkedList<String> operators=new LinkedList<>();	//���ڼ�¼������
    private static LinkedList<String> output=new LinkedList<>();    //���ڼ�¼���
    private static StringBuilder sb=new StringBuilder();    //����չʾ��׺���ʽ
	static int M=20;
	static int [][]N=new int[M][M]; //�������ʽ������
	static char [][]C=new char[M][M];//�������ʽ���ַ�
	static int []CN=new int[M];      //��¼����ʽ�����ָ���
	static char [] Str={'+','-','*','/'};
	Scanner scanner=new Scanner(System.in);
	
//-------------------�����������ʽ,���÷���������---------------------------------------
	public void calaulate_AE(int number) {
		int Right=0;
		float R = 0;
		int F=0;
		while(F==0)
		{
		for(int i=0;i<number;i++)
		{
			CN[i]=(int)(Math.random()*2+4);
			int Bracket=(int)(Math.random()*CN[i]-2);
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
				if(fir=='-')
					F=0;
				else
					F=1;
				System.out.print(AE);
				String SUM=scanner.nextLine();
				if(SUM.equals(sum))
				{
					System.out.print("�ش���ȷ��Very Good��(*^��^*)��\n");
					Right++;
				}
				else
					System.out.print("�ش����Ҫ����Ŷ��(*^��^*)��\n");
				float R1 = (float)Right;
				float R2 = (float)number;
				R=(float) (R1/R2*100.00);
				AE+=sum;
				Arithmetic.add(AE);
		}
		System.out.print("���δ��⹲��"+number+"�����ش���ȷ"+Right+"������ȷ��Ϊ"+R+"%.\n");
	}
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
