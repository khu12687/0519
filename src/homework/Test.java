package homework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Test extends JFrame implements ActionListener{
	JPanel panel;
	JButton bt_create,bt_color,bt;
	JTextArea area;
	int n;
	//��κ��� ���α׷��� ������ �迭������ �ݵ�� �� ũ�⸦ ���!!
	//ũ�⸦ �������� ���� ���߽� �Ѱ����� �ִ�..
	//but �ڹٽ�ũ��Ʈ�� �迭�� ũ�Ⱑ �������̴�.. �ڹپ����� ������ �迭�������� ����
	//�ڷᱸ��!!
	//�ڹٿ����� ��ü�� ��Ƽ� ó���Ҷ� ������ api�� �����ϸ�, �� api���� java.util�� ���ִ�.. �� ���̺귯����
	//������ �ڹٿ����� Collection Framework(�̹� ������� Ʋ)
	//���� �÷��� �����ӟp�� ������� �ϴ� �����ʹ� ���� ��ü�̴�!! �迭�� �ٸ����̴�
	/*
	 * �ڹپ����� ��ü�� ����� �ִ� ����� ũ�� 3������ ����..
	 * 1.�������� ��� set���� ex)���δ�����ִ� �׸�
	 * 2.�����ִ� ��� List���� ex) �����۽�,����
	 * 				�� �迭�� ������
	 * 					1)ũ�Ⱑ �������̴�!! 2)��ü���� �ٷ��
	 * 3.key(Ű)�� value(��)�� ������ �̷������� Map���� ex) Ű�������ݸ�, json {name :"ȣ����"}
	 * */
	ArrayList<JButton> list = new ArrayList<JButton>(); //ũ��� 0������, ����ó�� �þ �� �ִ�
	public Test() {
		panel = new JPanel();
		bt_create = new JButton("����");
		bt_color = new JButton("����");
		
		
		panel.setPreferredSize(new Dimension(300,300));
		panel.setBackground(Color.BLUE);
	
		setLayout(new FlowLayout());
		
		add(bt_create);
		add(bt_color);
		add(panel,BorderLayout.CENTER);
		
		setSize(300, 400);
		setVisible(true);
		
		bt_create.addActionListener(this);
		bt_color.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj =e.getSource();
		if(obj==bt_create) {
			create();
		}else if(obj==bt_color) {
			bg();
		}
	}
	public void bg() {
		for(int i=0; i<list.size(); i++) {
			//ArrayList�� ���� �� �ִ� ��ü�� �ڷ����� ����ڷ����̹Ƿ� Object������ ����ȯ�Ǿ� ����
			//���� ������ ���� �ʿ��� �ڷ������� down casting �ؾ��Ѵ�!!
			
			//���ʸ����� ����� �÷��� �����ӟp ��ü�� ����ȯ�� �ʿ���� why? ������ ������ �ڷ��� �����̹Ƿ�
			//�� �������� ���ɼ��� 0���� �̱� ������..
			JButton bt = list.get(i);
			bt.setBackground(Color.YELLOW);
		}
	}
	public void create() {
		JButton bt =new JButton(n+"��° ��ư");
		n++;
	
		panel.add(bt);
		panel.updateUI();
		list.add(bt);
	}
	
	public static void main(String[] args) {
		new Test();
	}
	
}
