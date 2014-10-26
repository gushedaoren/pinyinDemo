package airballoon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainPage {
	
	

	public static void main(String[] args) {
		
		
		  // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/tennis";

        // MySQL配置时的用户名
        String user = "root"; 

        // MySQL配置时的密码
        String password = "Nxc123456";

        try { 
         // 加载驱动程序
         Class.forName(driver);

         // 连续数据库
         Connection conn = DriverManager.getConnection(url, user, password);
		
		PinyinUtil pinyinUtil=new PinyinUtil();
		System.out.println(pinyinUtil.HanyuToPinyin("倪聪"));
		
		
		if(!conn.isClosed()) 
            System.out.println("Succeeded connecting to the Database!");

           // statement用来执行SQL语句
           Statement statement = conn.createStatement();
           
        // 要执行的SQL语句
           String sql = "select * from tennis_province ;";
           
        // 结果集
           ResultSet rs = statement.executeQuery(sql);

           System.out.println("-----------------");
           System.out.println("执行结果如下所示:");
           System.out.println("-----------------");
          
           System.out.println("-----------------");
           List<String> sqls=new ArrayList<String>();
        
           while(rs.next()) {
            String id,title;

            // 选择sname这列数据
            id = rs.getString("provinceID");
            title = rs.getString("provinceName");
           
            
           
            title = new String(title.getBytes("utf-8"));
            String pinyin=pinyinUtil.HanyuToPinyin(title);
           
            
            System.out.print("id:"+id+"  title:"+title+" pinyin:"+pinyin+"\n");
            String sql2="update tennis_province set title_pinyin='"+pinyin+"' where provinceID="+id;
            
            sqls.add(sql2);
           
            
            
           }
           
           
           for(String sql3:sqls){
        	   statement.execute(sql3);
           }
		
        }catch(Exception e){
        	e.printStackTrace();
        }

       
	}

}
