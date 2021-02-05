package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectTest {
	public static void main(String[] args) {
        //DB接続用定数
        String DATABASE_NAME = "java_db";
        String PROPATIES = "?characterEncoding=UTF-8&serverTimezone=JST";
        String URL = "jdbc:mySQL://localhost3306/java_db";
        //DB接続用・ユーザ定数
        String USER = "root";
        String PASS = "hAtAhAtA6364";

        try {
            //MySQL に接続する
            Class.forName("com.mysql.cj.jdbc.Driver");
            //データベースに接続
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            // データベースに対する処理
            System.out.println("データベースに接続に成功");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
