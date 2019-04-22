package com.example.lbstest.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.lbstest.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static       String         DB_NAME = "mapdb.db";
    private              Context        mContext;
    private static       SQLiteDatabase manager;
    private static final String         TAG     = "DBManager";
    public static void copyDb(Context mContext, String tab_name) {
        InputStream in = null;
        FileOutputStream out = null;

        String path = "/data/data/"+mContext.getPackageName()+"/databases";
        File file = new File(path + "/" +DB_NAME);
        try{
            File file1 = new File(path);
            if (!file1.exists()) {
                file1.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                in = mContext.getAssets().open(DB_NAME);
                out = new FileOutputStream(file);
                int length = -1;
                byte[] buf = new byte[1024];
                while ((length = in.read(buf)) != -1) {
                    out.write(buf,0,length);
                }
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {

            }
        }
    }


    public static SQLiteDatabase dbManager(Context mContext) {
        String dbPath = "/data/data/" + mContext.getPackageName()
                + "/databases/" + DB_NAME;
        manager = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
        return manager;
    }



    public static User login(String username, String password) {
        User user = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM user WHERE username = '"+username+"' AND password = '"+password+"'",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String pwd = cursor.getString(2);
            String sex = cursor.getString(3);
            String phone = cursor.getString(4);
            String role = cursor.getString(5);
            String nickname = cursor.getString(7);
            String status = cursor.getString(8);
            user = new User();
            user.setUsername(name);
            user.setPassword(pwd);
            user.setSex(sex);
            user.setPhone(phone);
            user.setRole(role);
            user.setNickname(nickname);
            user.setStatus(status);
        }
        cursor.close();
        return user;
    }

    public static int insertNewUser(User user) {
        // 先判断有无此用户
        Cursor cursor = manager.rawQuery("select 1 from user where username = '" + user.getUsername() + "' limit 1;", null);
        boolean b = false; // 默认无
        while (cursor.moveToNext()) {
            b = true; // 有此用户
        }
        if (b) {
            return 1; //用户存在
        }
        try {
            manager.execSQL("INSERT INTO user values(NULL,'" + user.getUsername() + "','" + user.getPassword() + "','1','','用户','','','0')");
        } catch (Exception e) {
            e.printStackTrace();
            return 2; // 用户不存在但是插入失败
        }

        return 0; // success
    }

    public static void deleteUserByUserName(String username) {
        manager.execSQL("DELETE FROM user where username = '"+username+"'");
    }

//    UPDATE user SET password = '12534',sex = '0',nickname='a',status='1' WHERE username = 'a2'
    public static void update(User user) {
        manager.execSQL("UPDATE user SET password = '"+user.getPassword()+"',sex='"+user.getSex()+"',nickname='"+user.getNickname()+"'" +
                ",status='"+user.getStatus()+"',phone='"+user.getPhone()+"' where username = '"+user.getUsername()+"'");
    }


    public static User findUserByUserName(String username) {
        User user = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM user WHERE username = '"+username+"'",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String pwd = cursor.getString(2);
            String sex = cursor.getString(3);
            String phone = cursor.getString(4);
            String role = cursor.getString(5);
            String nickname = cursor.getString(7);
            String status = cursor.getString(8);
            user = new User();
            user.setUsername(name);
            user.setPassword(pwd);
            user.setSex(sex);
            user.setPhone(phone);
            user.setRole(role);
            user.setNickname(nickname);
            user.setStatus(status);
        }
        cursor.close();
        return user;
    }

    public static List<User> getAllUser() {
        List<User> mList = new ArrayList<>();
        User user = null;
        Cursor cursor = manager.rawQuery("SELECT * FROM user",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String pwd = cursor.getString(2);
            String sex = cursor.getString(3);
            String phone = cursor.getString(4);
            String role = cursor.getString(5);
            String nickname = cursor.getString(7);
            String status = cursor.getString(8);
            user = new User();
            user.setUsername(name);
            user.setPassword(pwd);
            user.setSex(sex);
            user.setPhone(phone);
            user.setRole(role);
            user.setNickname(nickname);
            user.setStatus(status);
            mList.add(user);
        }
        cursor.close();
        return mList;
    }



}
