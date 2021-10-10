package com.zz.javase;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.*;


//https://www.cnblogs.com/teror/p/15255812.html
public class fileTest {
    public static void main(String[] args) {

    }

    @Test
    public void fileUtilsTest() {
        try {
            //读取文件内容  相当于把一个文件读到了一个字符串中了
//            String readFileToString = FileUtils.readFileToString(new File("data/javase/fileTest/rt.txt"));
//            System.out.println(readFileToString);

            //删除文件夹
            //注意：deleteDirectory方式删除path下的文件时，若path路径下的文件读写流没有关闭，则删除不了
            //forceDelete方式（解决上面的问题，强制删除，文件被其他程序占用时，也删不掉）
            //deleteQuietly 删除指定文件，从不引发异常
//            FileUtils.deleteDirectory(new File("data/javase/fileTest/del_dir_test"));
//            FileUtils.forceDelete(new File("data/javase/fileTest/del_dir_test"));
//            FileUtils.deleteQuietly(new File("D:\\guor\\data"));
//            //清空文件夹 清除该目录下的文件及子目录文件而不删除该目录文件夹。
//            FileUtils.cleanDirectory(new File("D:\\guor\\data"));

            //判断文件内容是否一致  只能判断文件，不能判断文件夹  本质是通过调用IOUtils.contentEquals()方法来对两个文件流进行判断
//            File file1 = new File("data/javase/fileTest/news_test_list.txt");
//            File file2 = new File("data/javase/fileTest/rt.txt");
//            boolean contentEquals1 = FileUtils.contentEquals(file1,file1);
//            boolean contentEquals2 = FileUtils.contentEquals(file1,file2);
//            System.out.println(contentEquals1+","+contentEquals2);

//            //拷贝特定类型的文件
//            FileUtils.copyDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data2"), new FileFilter() {
//                @Override
//                public boolean accept(File pathname) {
//                    return pathname.getName().startsWith("test");
//                }
//            });
//            FileUtils.moveDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data3"));
//            FileUtils.moveFileToDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data3"), true);
//            FileUtils.moveToDirectory(new File("D:\\guor\\data"), new File("D:\\guor\\data4"), true);
              //file1 是否包含file2
//            boolean directoryContains = FileUtils.directoryContains(file1,file2);
//            System.out.println(directoryContains);
//            directoryContains = FileUtils.directoryContains(new File("D:\\guor\\data"), new File("*.txt"));
//            System.out.println(directoryContains);

//            //获取某文件夹下特定格式文件
//            File[] listFiles = new File("D:\\guor\\data").listFiles(new FilenameFilter() {
//                @Override
//                public boolean accept(File dir, String name) {
//                    return name.startsWith("test");
//                }
//            });
//            System.out.println(Arrays.toString(listFiles));

//            //获取当前电脑系统的 temp文件夹路径  //C:\Users\JACK\AppData\Local\Temp
//            File tempDirectory = FileUtils.getTempDirectory();
//            System.out.println(tempDirectory);
//            //获取系统用户文件夹路径  //C:\Users\JACK
//            File userDirectory = FileUtils.getUserDirectory();
//            System.out.println(userDirectory);
//
              //查看是否是新建的文件夹 isFileNewer 的文件最后修改时间只要大于parse的时间那么就为 true
            SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss" );
            Date parse = sdf.parse("20210206134900");
            boolean fileNewer = FileUtils.isFileNewer(new File("data/javase/fileTest/news_test_list.txt"), parse);
            System.out.println(fileNewer);

              //更新文件修改时间，如果不存在，则新建；
//            FileUtils.touch(new File("D:\\guor\\data\\test20211022000000.txt"));
//            //延迟查看文件是否存在
//            boolean waitFor = FileUtils.waitFor(new File("D:\\guor\\data\\testNew20211021000000.txt"), 5);
//            System.out.println(waitFor);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    //根据文件修改时间排序
    public void sort_lastModified() {
        String dir = "data/javase/fileTest";
        File[] listFiles = new File(dir).listFiles();
        List<File> asList = Arrays.asList(listFiles);
        for(File file:listFiles) {
            System.out.println(file);
        }
        Collections.sort(asList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if(o1.lastModified()<o2.lastModified()) {
                    return -1;
                }else if(o1.lastModified()>o2.lastModified()) {
                    return 1;
                }else {
                    return 0;
                }
            }
        });
        System.out.println("sort...");
        for(File file:listFiles) {
            System.out.println(file+","+file.lastModified());
        }
    }

}

