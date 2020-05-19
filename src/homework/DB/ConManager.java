package homework.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * �����ͺ��̽��� ���õ� ����, ������ �ߺ��� �ڵ带 �� ��ü�� �Ͽ��� ����ϰ� �Ͽ� ���뼺�� ��������!!
 * */
public class ConManager {
	String url;
	String user ="c##java";
	String password="1234";
	public ConManager(String ip) {
		url = "jdbc:oracle:thin:@"+ip+":1521:XE";
		//����̹��ε�!!
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε强��");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("����̹� �ε����");
		}
		
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,user,password);
			if(con!=null) {
				System.out.println("���� ����");
			}else {
				System.out.println("���ӽ���");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//���� : DML
	public void closeDB(Connection con, PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//���� : Select
	public void closeDB(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
