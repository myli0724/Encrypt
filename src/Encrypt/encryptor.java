package Encrypt;

import java.io.*;

public class encryptor {

    public static void main(String[] args) throws IOException {
        String path = "D:\\Temp\\test\\test.txt";
        String des = encrypt(path,10086);
        decryptor(des,10086);
    }

    public static String encrypt(String fileUrl,int key) throws IOException {
        File file = new File(fileUrl);
        String path = file.getPath();

        if(!file.exists()){
            System.out.println("error!the file is not exist!");
            return "";
        }

        int index = path.lastIndexOf("\\");
        String desPath = path.substring(0,index)+"\\"+"EncryptFile";
        File desFile = new File(desPath);

        InputStream inputStream = new FileInputStream(fileUrl);
        OutputStream outputStream = new FileOutputStream(desPath);

        int data = 0;
        while((data = inputStream.read())>-1){
            outputStream.write(data^key);
        }
        System.out.println("encrypt file path:"+desFile.getPath());

        inputStream.close();
        outputStream.flush();
        outputStream.close();
        return desPath;
    }

    public static void decryptor(String fileUrl,int key) throws IOException {
        File file = new File(fileUrl);
        String path = file.getPath();

        if(!file.exists()){
            System.out.println("error!the file is not exist!");
            return ;
        }

        int index = path.lastIndexOf("\\");
        String desPath = path.substring(0,index)+"\\"+"DecryptFile";
        File desFile = new File(desPath);
        System.out.println("decrypt file path:"+desFile.getPath());

        InputStream inputStream = new FileInputStream(fileUrl);
        OutputStream outputStream = new FileOutputStream(desPath);

        int data = 0;
        while((data = inputStream.read())>-1){
            outputStream.write(data^key);
        }

        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }
}
