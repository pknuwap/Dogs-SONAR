package savingModule;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveManager {
    int count=0;
    ArrayList<SaveItem> arr;
    public SaveManager() {
        arr=new ArrayList<SaveItem>();
    }
    public void load(String src) throws IOException {
        try {
            BufferedReader r=new BufferedReader(new FileReader(src));
            count=Integer.parseInt(r.readLine());
            for(int i=0;i<count;i++) {
                SaveItem t;
                String key=r.readLine();
                switch(Integer.parseInt(r.readLine())) {
                case 0://string
                    t=new SaveItem(key,r.readLine());
                    break;
                case 1://integer
                    t=new SaveItem(key,Integer.parseInt(r.readLine()));
                    break;
                case 2://double
                    t=new SaveItem(key,Double.parseDouble(r.readLine()));
                    break;
                default:
                    t=new SaveItem(key,null);
                    break;
                }
                arr.add(t);
            }
            r.close();
        }catch(FileNotFoundException e) {
            PrintWriter pw = new PrintWriter(src);
            pw.println(0);
            pw.close();
            this.load(src);
        }
    }
    public void add(SaveItem sv) {
        arr.add(sv);
    }
    public void delete(String key) {
        for(int i=0;i<count;i++) {
            SaveItem t=arr.get(i);
            if(t.key.equals(key)) {
                arr.remove(i);
            }
        }
    }
    public void modify(SaveItem target) {
        delete(target.getKey());
        add(target);
    }
    public void save(String src) {
        try {
            PrintWriter pw = new PrintWriter(src);
            pw.println(count);
            for(int i=0;i<count;i++) {
                SaveItem t = arr.get(i);
                pw.println(t.getKey());
                switch(t.getType()) {
                case INT:
                    pw.println(1);
                    pw.println(t.getValueInt());
                    break;
                case DOUBLE:
                    pw.println(2);
                    pw.println(t.getValueDouble());
                    break;
                case STRING:
                    pw.println(0);
                    pw.println(t.getValueString());
                    break;
                default:
                    break;
                }
            }
            pw.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public SaveItem getItem(String key,SaveItem.Type type) {
        for(int i=0;i<arr.size();i++) {
            if(arr.get(i).getKey().equals(key)) return arr.get(i);
        }
        SaveItem t=new SaveItem();
        switch(type) {
        case BOOLEAN:
            t=new SaveItem(key,false);
            break;
        case DOUBLE:
            t=new SaveItem(key,0.0);
            break;
        case INT:
            t=new SaveItem(key,0);
            break;
        case STRING:
            t=new SaveItem(key,"");
            break;
        }
        return t;
    }


    public static void main(String args[]) {
        Scanner scan=new Scanner(System.in);
        SaveManager sv=new SaveManager();

        scan.nextLine();

    }
}
