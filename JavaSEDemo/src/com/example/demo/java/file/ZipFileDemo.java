package com.example.demo.java.file;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileDemo {
    public static void main(String[] args) throws Exception {
//        unzip(args[0]);
//        zip();
        ZipCompress("D:\\Tmp\\upload","D:\\Tmp\\upload.zip");
    }

    /**
     * zip文件压缩
     * @param inputFile 待压缩文件夹/文件名
     * @param outputFile 生成的压缩包名字
     */

    public static void ZipCompress(String inputFile, String outputFile) throws Exception {
        //创建zip输出流
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outputFile));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        File input = new File(inputFile);
        compress(out, bos, input,null);
        bos.close();
        out.close();
    }
    /**
     * @param name 压缩文件名，可以写为null保持默认
     */
    //递归压缩
    public static void compress(ZipOutputStream out, BufferedOutputStream bos, File input, String name) throws IOException {
        if (name == null) {
            name = input.getName();
        }
        //如果路径为目录（文件夹）
        if (input.isDirectory()) {
            //取出文件夹中的文件（或子文件夹）
            File[] flist = input.listFiles();

            if (flist.length == 0)//如果文件夹为空，则只需在目的地zip文件中写入一个目录进入
            {
                out.putNextEntry(new ZipEntry(name + "/"));
            } else//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
            {
                for (int i = 0; i < flist.length; i++) {
                    compress(out, bos, flist[i], name + "/" + flist[i].getName());
                }
            }
        } else//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        {
            out.putNextEntry(new ZipEntry(name));
            FileInputStream fos = new FileInputStream(input);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int len;
            //将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf,0,len);
            }
            bis.close();
            fos.close();
        }
    }

    private static void zip() throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("D:\\Tmp\\upload.zip"));
        doZip(out,new File("D:\\Tmp\\upload"),"");
    }


    private static void doZip(ZipOutputStream out, File f, String base)
            throws Exception { // 方法重载
        if (f.isDirectory()) { // 测试此抽象路径名表示的文件是否是一个目录
            File[] fl = f.listFiles(); // 获取路径数组
            out.putNextEntry(new ZipEntry(base + "/")); // 写入此目录的entry
            base = base.length() == 0 ? "" : base + "/"; // 判断参数是否为空
            for (int i = 0; i < fl.length; i++) { // 循环遍历数组中文件
                doZip(out, fl[i], base + fl[i]);
            }
        } else {
            out.putNextEntry(new ZipEntry(base)); // 创建新的进入点
            // 创建FileInputStream对象
            FileInputStream in = new FileInputStream(f);
            int b; // 定义int型变量
            System.out.println(base);
            while ((b = in.read()) != -1) { // 如果没有到达流的尾部
                out.write(b); // 将字节写入当前ZIP条目
            }
            in.close(); // 关闭流
        }
    }

    private static void unzip(String path) throws IOException {
        File dir =  new File(path);
        System.out.println("File path : "+dir.getPath());
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        ZipInputStream zin = new ZipInputStream(in,Charset.forName("gbk"));
        ZipFile zipFile = new ZipFile(dir);
        ZipEntry ze;
        while((ze = zin.getNextEntry()) != null){
            if(!ze.isDirectory()){
                System.out.println("Zip file path : "+ze.getName());
                InputStream is = zipFile.getInputStream(ze);
            }
        }
        zin.closeEntry();
    }
}
