package EMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

import com.mysql.jdbc.ResultSetMetaData;

public class TableManage {
	public static void s(String s){
		System.out.println("s.length="+s.length());
	}

	/**
	 * @教务管理系统
	 * 对于系统中表的创建和管理
	 */
	String ID=null;
	static Connection conn = null;
	static String sql=null;
	//数据库连接信息
	static String url= "jdbc:mysql://localhost:3306/EMS?"
            + "user=root&password=111&useUnicode=true&characterEncoding=UTF8";
	public static void query(Statement stmt,String sql) throws SQLException{
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		// 获取列名  
        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();  
        for (int i = 0; i < metaData.getColumnCount(); i++) {  
            // rs数据下标从1开始  
            String columnName = metaData.getColumnName(i + 1);  
            int type = metaData.getColumnType(i + 1);  
            if (Types.INTEGER == type) {  
                // int  
            } else if (Types.VARCHAR == type) {  
                // String  
            }  
            System.out.print(columnName + "\t");  
        }  
        System.out.println();  
        // 获取数据  
        while (rs.next()) {  
            for (int i = 0; i < metaData.getColumnCount(); i++) {  
                // rs数据下标从1开始  
                System.out.print(rs.getString(i + 1) + "\t");  
            }  
            System.out.println();  
        }  
	}
	public void CreateTable(){
		
	}
	public void Stu(String ID){
		
	}
	public static void main(String[] args) throws SQLException {
		s("s");
		try {
            // 加载Mysql驱动，
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("成功加载MySQL驱动程序");

            conn = DriverManager.getConnection(url);
            System.out.println("连接建立成功");
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            System.out.println("请输入SQL语句：");
            Scanner sc=new Scanner(System.in);
            while(true){
            	sql=sc.nextLine();//从控制台接收SQL语句
            	int result;
            	if(sql.equals("#")){	//遇到#结束输入
            		break;
            	}
            	if(sql.startsWith("select")){//检测到select则进入查询函数
            		query(stmt,sql);
            	}
            	else{//否则进入修改函数
            		result = stmt.executeUpdate(sql);//executeUpdate会返回一个受影响的行数，若没有则错误
            	}
                  
            }
            
        } catch (SQLException e) {//异常抛出，当数据库连接错误、无法操作的时候会报错
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
	

}
