package homework.DB;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class TableSelectionApp extends JFrame{
	JPanel p_west;
	JTextField t_ip; //�����ϰ��� �ϴ� db������ ������ �ּ�
	Choice choice;
	JButton bt_load,bt_edit,bt_del;
	JTable table;
	JScrollPane scroll;
	int deptno; //������ ������ ���� �μ���ȣ!!
	TableModel model =null;
	
	public TableSelectionApp() {
		p_west = new JPanel();
		t_ip = new JTextField("172.30.1.24");
		choice = new Choice();
		bt_load = new JButton("Load");
		bt_del = new JButton("����");
		bt_edit = new JButton("����");
		
		table = new JTable();
		scroll = new JScrollPane(table);
		
		//��Ÿ������
		p_west.setBackground(Color.WHITE);
		p_west.setPreferredSize(new Dimension(120,400));
		p_west.add(t_ip);
		p_west.add(choice);
		p_west.add(bt_load);
		p_west.add(bt_del);
		p_west.add(bt_edit);
		
		//���̺��
		choice.add("EMP");
		choice.add("DEPT");
		choice.add("STUDENT");
		
		add(p_west, BorderLayout.WEST);
		
		add(scroll);
		
		setSize(500,400);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		bt_load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
				
			}
		});
		
		bt_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				del();
				
			}
		});
		//���̺�� ���콺������ ����
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				String value = (String)table.getValueAt(row, 0);
				deptno = Integer.parseInt(value); //Ŭ���ø��� ������ deptno�� ������
				
				System.out.println("����� ������ row��"+row+"����� ������ col��"+col);
				
				String sql="delete from dept where deptno="+value;
				System.out.println(sql);
			}
		});
	}
	
	public void load() {
		//������ ���̺��� ��µ� �� �ֵ��� ������ TableModel�� ��������!!
		//EmpTableModel, DeptTableModel, StudentTableModel
		String item = choice.getSelectedItem();
		//System.out.println(t_ip.getText()+"������"+item +"���̺����?");
		
		if(item.equals("EMP")) { //EmpTableModel ���
			model = new EmpTableModel(t_ip.getText());
		}else if(item.equals("DEPT")){
			model = new DeptTableModel(t_ip.getText());
		}else if(item.equals("STUDENT")){
			model = new StudentTableModel(t_ip.getText());
		}
		table.setModel(model);
		table.updateUI();
	}
	
	public void del() {
		//������ ������ ���ڵ带 �����Ѵ�!!
		//üũ���� 1.������ ���ڵ尡 �ִ��� ���� 2.������ ����Ȯ��
		if(deptno==0) {
			JOptionPane.showMessageDialog(this,"�����Ͻ� ���ڵ带 �����ϼ���");
			return;
		}
		//������ �ڰ��� �����...
		int result = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
		if(result ==JOptionPane.OK_OPTION) {
			System.out.println("��������!");
			//TableModel �� �̿��Ͽ� ����ó��!!
			//�� Ŭ������ ������ �����̹Ƿ�, ������ ó���ؼ��� �ȵȴ�!!
			DeptTableModel deptModel=(DeptTableModel)model;
			int row = deptModel.deletelDeptById(deptno);
			if(row==0) {
				JOptionPane.showMessageDialog(this,"��������");
			}else {
				JOptionPane.showMessageDialog(this,"��������");
				deptModel.select();
				table.updateUI();
			}
		}
	}
	public static void main(String[] args) {
		new TableSelectionApp();
	}
}
