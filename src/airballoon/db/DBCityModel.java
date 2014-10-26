package airballoon.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class DBCityModel {
	
	

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
		
		
		
		
		if(!conn.isClosed()) 
            System.out.println("Succeeded connecting to the Database!");

           // statement用来执行SQL语句
           Statement statement = conn.createStatement();
           
        // 要执行的SQL语句
           String sql = "select * from tennis_court ;";
           
        // 结果集
           ResultSet rs = statement.executeQuery(sql);

           System.out.println("-----------------");
           System.out.println("执行结果如下所示:");
           System.out.println("-----------------");
          
           System.out.println("-----------------");
           List<String> sqls=new ArrayList<String>();
        
           while(rs.next()) {
            String id,address,province,city,district;

            id=rs.getString("id");
            address = new String(rs.getString("address").getBytes("UTF-8"));
            
            city = new String(rs.getString("city").getBytes("UTF-8"));
         //   System.out.println(province);
          
            	  System.out.println(city);
            	  String sql11="select id from tennis_city where cityName=?;";
            	  
            	  PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql11);
            	  ps.setString(1, city);
            	  ResultSet rs2=ps.executeQuery();
            	  if(rs2.next()){
            		  String cityid=rs2.getString("id");
                	  System.out.println(cityid);
                      String sql2="update tennis_court set city_model='"+cityid+"' where id="+id;
                      
                      sqls.add(sql2);
            	  }
            	  
            	 
           
            
            
           }
           
           
           for(String sql3:sqls){
        	   statement.execute(sql3);
           }
		
        }catch(Exception e){
        	e.printStackTrace();
        }

       
	}

}
