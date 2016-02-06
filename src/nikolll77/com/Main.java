package nikolll77.com;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File file=new File("e://myfile.txt");
        File file2=new File("e://chankedfile.txt");
        File file3=new File("e://unchankedfile.txt");




        int chunkSize=10;

        Chunker chunker = new Chunker(chunkSize);

        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file2);
        InputStream is = fis;
        OutputStream os = fos;
        chunker.enCode(is,os);
        os.flush();

        is = new FileInputStream(file2);
        os = new FileOutputStream(file3);
        chunker.deCode(is,os);
        os.flush();


/*        byte[] buf=new byte[chunkSize];
        int len;
        while ((len=is.read(buf))>0){
            String s = new String(buf);
            System.out.print(s);
        }*/



    }

}
