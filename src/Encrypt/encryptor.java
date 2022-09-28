package Encrypt;

import java.io.*;

public class encryptor {

    public static void main(String[] args) throws IOException {
        String path = "D:\\Temp\\test\\test.png";
        String des = encrypt(path,10086);
        delete(path);
        decryptor(des,10086);

    }

    public static String encrypt(String fileUrl,int key) throws IOException {
        File file = new File(fileUrl);
        String path = file.getPath();

        if(!file.exists()){
            throw new IOException("文件未找到或者打开失败！");
        }

        int index = path.lastIndexOf("\\");
//        int indexofType = path.lastIndexOf(".");
//        String filetype = path.substring(indexofType);//.txt文件类型；
//        System.out.println(filetype);
        String desPath = path+".data";
        File desFile = new File(desPath);

        InputStream inputStream = new FileInputStream(fileUrl);
        OutputStream outputStream = new FileOutputStream(desPath);

        int data = 0;
        while((data = inputStream.read())>-1){
            outputStream.write(data^key);  //与密钥进行与操作进行加密；
        }
        System.out.println("encrypt file path:"+desFile.getPath());


        inputStream.close();
        outputStream.flush();
        outputStream.close();

        return desPath;
    }

    public static void backup(String fileUrl) throws IOException {
        // 旧的文件或目录
        File oldName = new File(fileUrl);
        // 新的文件或目录
        File newName = new File(fileUrl+".backup");
        if (newName.exists()) {  //  确保新的文件名不存在
            throw new java.io.IOException("file exists");
        }
        if(oldName.renameTo(newName)) {
            System.out.println("已重命名");
        } else {
            System.out.println("Error");
        }
    }

    public static void delete(String fileUrl){
        try{
            File file = new File(fileUrl);
            if(file.delete()){
                System.out.println(file.getName() + " 文件已被删除！");
            }else{
                System.out.println("文件删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void decryptor(String fileUrl,int key) throws IOException {
        File file = new File(fileUrl);
        String path = file.getPath();

        if(!file.exists()){
            throw new IOException("文件未找到或者打开失败");
        }

        int index = path.lastIndexOf("\\");
        int indexoftype = path.lastIndexOf(".");
        String type0 = path.substring(0,indexoftype);
        String type = type0.substring(type0.lastIndexOf("."));
        System.out.println("type:"+type);
        String desPath = path.substring(0,type0.lastIndexOf("."))+type;
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
