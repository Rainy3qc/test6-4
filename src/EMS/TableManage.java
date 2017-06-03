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
	 * @�������ϵͳ
	 * ����ϵͳ�б�Ĵ����͹���
	 */
	String ID=null;
	static Connection conn = null;
	static String sql=null;
	//���ݿ�������Ϣ
	static String url= "jdbc:mysql://localhost:3306/EMS?"
            + "user=root&password=111&useUnicode=true&characterEncoding=UTF8";
	public static void query(Statement stmt,String sql) throws SQLException{
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		// ��ȡ����  
        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();  
        for (int i = 0; i < metaData.getColumnCount(); i++) {  
            // rs�����±��1��ʼ  
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
        // ��ȡ����  
        while (rs.next()) {  
            for (int i = 0; i < metaData.getColumnCount(); i++) {  
                // rs�����±��1��ʼ  
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
            // ����Mysql������
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("�ɹ�����MySQL��������");

            conn = DriverManager.getConnection(url);
            System.out.println("���ӽ����ɹ�");
            // Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
            Statement stmt = conn.createStatement();
            System.out.println("������SQL��䣺");
            Scanner sc=new Scanner(System.in);
            while(true){
            	sql=sc.nextLine();//�ӿ���̨����SQL���
            	int result;
            	if(sql.equals("#")){	//����#��������
            		break;
            	}
            	if(sql.startsWith("select")){//��⵽select������ѯ����
            		query(stmt,sql);
            	}
            	else{//��������޸ĺ���
            		result = stmt.executeUpdate(sql);//executeUpdate�᷵��һ����Ӱ�����������û�������
            	}
                  
            }
            
        } catch (SQLException e) {//�쳣�׳��������ݿ����Ӵ����޷�������ʱ��ᱨ��
            System.out.println("MySQL��������");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
	

}
