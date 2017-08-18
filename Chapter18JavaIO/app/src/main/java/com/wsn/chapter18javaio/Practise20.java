package com.wsn.chapter18javaio;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by power on 2017/8/18,018.
 */

public class Practise20 {
    public interface Strategy {
        void process(File file);
    }

    private ProcessFiles.Strategy strategy;
    //    private String ext;
    private String regex;
    private Pattern pattern;

    public Practise20(ProcessFiles.Strategy strategy, String regex) {
        this.strategy = strategy;
//        this.ext = ext;
        pattern = Pattern.compile(regex);
        this.regex = regex;
    }


    public void processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(root.getAbsolutePath(), regex))
            strategy.process(file.getCanonicalFile());
    } /* Demonstration of how to use it:*/

    /**



     * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     * @param src byte[] data
     * @return hex string
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) throws IOException{
        new ProcessFiles(new ProcessFiles.Strategy() {
            public void process(File file) {
                try {
                    byte[] b = BinaryFile.read(file);
//                    byte[] b1 = Arrays.copyOfRange(b, 0, 10);
                    System.out.println(file.getName() + " " + bytesToHexString(Arrays.copyOfRange(b, 0, 10)));
                }catch (Exception e){
                    e.printStackTrace();
                }

//                long lastModiy = file.lastModified();
//                long time = System.currentTimeMillis()-3*3600*1000;
//                if(lastModiy<time)
//                    System.out.println(time+" "+lastModiy+" "+file);
            }
        }, ".*.class").processDirectoryTree(new File("."));
    }
}
