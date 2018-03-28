import java.util.ArrayList;
import java.util.Random;
 
public class CreateArithmetic {
     //private ArrayList<Double> list=new ArrayList<Double>();
    double result=0;
     public String creat(){
         Random random=new Random();
         int a=random.nextInt(101);
         int b=random.nextInt(101);
         int c=random.nextInt(4);
         char character='+';
         switch(c){
            case 0:character='+'; result=a+b; break;
            case 1:character='-'; if(a<b){int temp=a;a=b;b=temp;} result=a-b; break;
            case 2:character='x'; result=a*b; break;
            case 3:character='/'; result=a/b; break;
         }
         return "ÌâÄ¿£º"+a+character+b+"=?";
     }
    
}