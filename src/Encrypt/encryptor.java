package Encrypt;

import java.io.File;

public class encryptor {
    public static void encrypt(String fileUrl,String key){
        File file = new File(fileUrl);
        String path = file.getPath();
        if(!file.exists()){
            System.out.println("error!the file isnot exst!");
            return ;
        }
    }
}
