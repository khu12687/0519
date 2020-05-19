package homework.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

/*
 * �� Ŭ������ �����ؾ� �ϴ� ������?
 * ���� �� Ŭ������ ���ٸ�, �����ͺ��̽����� ���� ���� �ڵ尡 TableSelectionApp���
 * �������� ����ϰ� �Ǵ� �ڵ�� ���� ������..
 * ���� �����͸� ��ü�Ϸ��� �ص� �ش� ������ �ڵ带 �Բ� �����ؾ� �Ǵ� ��Ȳ..
 * ���� �������ڵ带 ���̻� ������� �ʰ� �б��ų��, �����ͺ��̽� ���� �ڵ���� �Բ� ������ ��������..
 * �ᱹ �ڵ带 ���ĳ��� ������ ����о߿��� ���ؾ� �� �ൿ��
 * �Ʒ��� Ŭ������ MVC ������ �������� �Ѵٸ� � ������ ���� �����ϴ°�?
 * MVC������ ����о߿��� ������� ������ �����, ���� ������� �ǹ��Ѵ�
 * �׳� ������ ����
 * Model : �����Ϳ� �� �����͸� �����ϴ� ����
 * View : ������ ����
 * Controller : Model, View�� �и������ִ� ������!!
 * */
public class EmpTableModel extends AbstractTableModel{
	ConManager conManager;
	String[][] data =null;
	String[] title = {"empno","ename","job","msg","hiredate","sal","comm","deptno"};
	
	public EmpTableModel(String ip) {
		conManager = new ConManager(ip);
		
		//���Ӱ�ü�� ���´�!! why? ������ ��� ��ü���� �����ϱ� ����
		Connection con = conManager.getConnection();
		String sql = "select * from emp";
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt =  con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=pstmt.executeQuery();
			
			rs.last(); //Ŀ���� ���ڵ��� ������������ �̵�!!
			
			data = new String[rs.getRow()][8];
			
			//�迭�� rs�����͸� �Űܽɱ� ���� Ŀ���� ���󺹱�
			rs.beforeFirst();
			
			int index=0;
			while(rs.next()) {
				String empno=Integer.toString(rs.getInt("empno"));
				String ename=rs.getString("ename");
				String job = rs.getString("job");
				String mgr = Integer.toString(rs.getInt("mgr"));
				String hiredate = rs.getString("hiredate");
				String sal = Integer.toString(rs.getInt("sal"));
				String comm = "";
				if(rs.getInt("comm")!=0) { //�����Ͱ� null�� ���
					comm=Integer.toString(rs.getInt("comm"));
				}
				String deptno = Integer.toString(rs.getInt("deptno"));
				
				//�޾��� �������� �̿��Ͽ� �迭�� ä����!!
				data[index][0]=empno;
				data[index][1]=ename;
				data[index][2]=job;
				data[index][3]=mgr;
				data[index][4]=hiredate;
				data[index][5]=sal;
				data[index][6]=comm;
				data[index][7]=deptno;
				
				index++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//db���� �ڿ� �ݳ�
			conManager.closeDB(con,pstmt,rs);
		}
	}
	//���� ������ȯ
	@Override
	public int getRowCount() {
		
		return data.length;
	}
	//���� ������ȯ
	@Override
	public int getColumnCount() {
		return 8;
	}
	
	//����ó��
	@Override
	public String getColumnName(int col) {
		return title[col];
	}

	//������ �� ������ ��ȯ
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
