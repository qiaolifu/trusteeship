package com.core.utils;

import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class DatabaseUtil {

    public static String getXShellCom(TDatabase database, TUser user) throws IOException {


        String dirPath = "/trusteeship/" + user.getUser() + "/";
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
//
//        Runtime runtime = Runtime.getRuntime();
//        String initCom = "if [ ! -d /trusteeship  ];then \n" +
//                "  mkdir /trusteeship\n" +
//                "  if [ ! -d /trusteeship/" + user.getUser() + "/  ];then\n" +
//                "  mkdir /trusteeship/" + user.getUser() + "/\n" +
//                "fi\n" +
//                "fi;";
//        try {
//            Process process = runtime.exec(initCom);
//            process.waitFor();
//            process.destroy();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Date date = new Date();
        StringBuilder com = new StringBuilder();
        com.append("mysql -u").append(database.getUser())
                .append(" -p").append(database.getPassword())
                .append(" -h ").append(database.getUrl())
                .append(" -e 'show databases;' | grep -Ev 'Database|information_schema|mysql|performance_schema' | xargs mysqldump -u")
                .append(database.getUser()).append(" -p")
                .append(database.getPassword()).append(" -h ")
                .append(database.getUrl()).append(" --column-statistics=0 --databases > /trusteeship/")
                .append(user.getUser()).append("/")
                .append("`date +%Y-%m-%d_%H-%M-%S`").append(".sql");
        System.out.println(com);
        return com.toString();
    }


    public static void recover(String filename, TUser user, TDatabase database) {
        Runtime runtime = Runtime.getRuntime();
        StringBuilder com = new StringBuilder();
        com.append("mysql -u").append(database.getUser())
                .append(" -p").append(database.getPassword())
                .append(" -h ").append(database.getUrl())
                .append(" source").append(" /trusteeship/")
                .append(user.getUser()).append("/")
                .append(filename).append(".sql");
        try {
            Process process = runtime.exec(com.toString());
            process.waitFor();
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
