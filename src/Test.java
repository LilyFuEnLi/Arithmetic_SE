
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
public class Test {
 
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        File file = new File("test.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;
        Iterator<String> iter = list.iterator();
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            while(iter.hasNext()) {
                bw.write(iter.next());
                bw.newLine();
            }
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         
        List<String> newList = new ArrayList<>();
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
    }
     
 
}