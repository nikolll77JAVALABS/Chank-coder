package nikolll77.com;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Chunker {

    int chunkSize;
    public Chunker() {
        this.chunkSize=50;
    }

    public Chunker(int chunkSize) {
        this.chunkSize=chunkSize;
    }

    public void enCode(InputStream is,OutputStream os) throws IOException {

        int len;
        byte[] buf = new byte[chunkSize];
        //len=is.read(buf);
        while ((len=is.read(buf))>0){
            byte[] head = (Integer.toHexString(len)+"\r\n").getBytes();
            os.write(head);
            os.write(buf);
            os.write(("\r\n").getBytes());
        }
        os.write(("0\r\n\r\n").getBytes());
    }

    public void deCode(InputStream is,OutputStream os) throws IOException {
        int i=0,k=0;
        byte[] buf = new byte[4048]; //Типа весь файл целиком ...
        byte[] buf2 = new byte[chunkSize+2];// 2="/r/n"

        int len=is.read(buf);
        for (int j=0;j<=len;j++){
          buf2[i]=buf[j];
          i++;//длинна 2-го буфера
          if ((buf[j]==13)&&(buf[j+1]==10)) { //парсим до "/r/n"
              k++;
              System.out.println(k+"  "+i);
 /*             if ((k%2)<>0)  {
                  int dataLen = Integer.parseInt(new String(buf), 16);
              }*/
              if ((k%2)==0)  //если четная строка (в нечетной строке размер)
                 os.write(buf2,1,i-2); //забираем данные
              i=0;
          }

        }


    }


}
