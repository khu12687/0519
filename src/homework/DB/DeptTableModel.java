package homework.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
/*
 * �����͸� ��� �뵵�� �迭�� �ϴٺ���, ó������ �� ũ�⸦ ����ؾ��ϴ� Ư¡ ������
 * rs.last()���� Ŀ������ �մ�� �Ѵ�..
 * ����) ���� �� ��ü�������� DB�ڵ鸵 ������ �������!!
 * 
 * JTable���� TableModel�� ������ ���, ���� �������� �����ڰ� ���� �޼���� override�ؾ� �Ѵ�
 * */
public class DeptTableModel extends AbstractTableModel{
	ConManager conManager;
	//�迭�� ����� ����������, ũ�⸦ ������� �ʾƵ� �Ǹ� ũ�Ⱑ ���������� ���� �� �� �ִ� ����Ʈ�� �̿��غ���!!
	ArrayList list;
	String ip;
	public DeptTableModel(String ip) {
		this.ip=ip;
		
		select(); //�Ʒ��� ������ �޼���ȣ��!
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return list.size(); //����Ʈ�Ǽ�
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	//�� ���� ���� �� �� �ִ��� ���θ� ��ȯ�ϴ� �޼���
	@Override
	public boolean isCellEditable(int row, int col) {
		boolean flag=true;
		if(col==0) {
			flag=false;
		}
		return flag;
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		System.out.println("row="+row+"col="+col);
		Dept dept =(Dept)list.get(row);
		
		String data = null;
		if(col==0) { //deptno
			data=Integer.toString(dept.getDeptno());
		}else if(col==1) { //dname
			data=dept.getDname();
		}else if(col==2) { //loc
			data=dept.getLoc();
		}
		return data;
	}
	
	//���� ���ֱ�!!
	/*
	 * value : ������ ������ �� ������
	 * row : ����
	 * col : ȣ��
	 * */
	@Override
	public void setValueAt(Object value, int row, int col) {
		//����Ʈ�� ����ִ� �����͸� ����!!
		Dept dept = (Dept)list.get(row); //����Ʈ�� ����ִ� VO�� ��������!!
		if(col==1) {
			dept.setDname((String)value);
		}else if(col==2) {
			dept.setLoc((String)value);
		}
	}
	
	//�����͸� �������� ������ �ʹ��� ���� �ϴ� �����̹Ƿ�, �޼���� ����!!
	public void select() {
		list = new ArrayList();
		conManager = new ConManager(ip);
		
		//���������� �ݵ�� �ʱ�ȭ�ؾ� �Ѵ�! ����)�ɹ������� �����ڰ� ���ϸ� �����Ϸ��� �ʱ�ȭ���ش�
		//���) ������ ����� ������ ���ؼ��� �ݵ�� �ʱ�ȭ �Ǿ��־���Ѵ�..
		
		Connection con = conManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="select * from dept";
		try {
			pstmt = con.prepareStatement(sql);//���� �غ�, �����������
			rs=pstmt.executeQuery();
			
			//�迭�� ���� ����, �ϳ��� ���ڵ�� �ϳ��� �ڹ��ν��Ͻ��� ��ƺ���!! ==��ü���� ��!!
			while(rs.next()) {
				//�ֺ� empty ������ �μ� �ν��Ͻ� ����
				Dept dept = new Dept(); //�μ� �ν��Ͻ�
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				list.add(dept); //����Ʈ�� �μ� �Ѱ� ���!!
			}
			System.out.println("�μ��� ������"+list.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conManager.closeDB(con, pstmt, rs);
		}
	}
	
	
	//��������
	//�����ϰ��� �ϴ� �ڴ� �޼ҵ� ȣ��� �μ���ȣ�� �Ѱܾ��Ѵ�!!
	public int deletelDeptById(int deptno) {
		int result=0; //����,���и� �Ǵ��� �� �ִ� ����!!
		//���� ����ó��!!
		Connection con = conManager.getConnection();
		
		String sql ="delete from dept where deptno="+deptno;
		System.out.println(sql);
		
		//���� ���� ��ü ���!!
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate(); //DML
			//if(result ==0) {
				//�޽��� ����� MVC�� ������ ������ View ���� ó���ϴ� ������
				//�� Ŭ������ ���� ��Ʈ�ѷ��� ����� ������ �ƴϴ�!!
				//�� �� ������.. why? ���� Ŭ������ �������� �ƴϴϱ�!!
				//JOptionPane.showConfirmDialog(this, message);
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conManager.closeDB(con, pstmt); //DML�̹Ƿ�...
		}
		return result; //ȣ���� �ڰ� ��� �˼��ֵ��� ��ȯ!!
	}
}
