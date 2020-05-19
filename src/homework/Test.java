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
	//대부분의 프로그래밍 언어에서는 배열생성시 반드시 그 크기를 명시!!
	//크기를 결정짓는 것은 개발시 한계점도 있다..
	//but 자바스크립트의 배열은 크기가 유동적이다.. 자바언어에서는 유동적 배열지원하지 않음
	//자료구조!!
	//자바에서는 객체를 모아서 처리할때 유용한 api를 제공하며, 이 api들은 java.util에 모여있다.. 이 라이브러리를
	//가리켜 자바에서는 Collection Framework(이미 만들어진 틀)
	//주의 컬렉션 프레임웤이 대상으로 하는 데이터는 오직 객체이다!! 배열과 다른점이다
	/*
	 * 자바언어에서는 객체를 모아져 있는 모습을 크게 3가지로 본다..
	 * 1.순서없는 모습 set구조 ex)만두담아져있는 그릇
	 * 2.순서있는 모습 List구조 ex) 프링글스,기차
	 * 				단 배열과 차이점
	 * 					1)크기가 유동적이다!! 2)객체만을 다룬다
	 * 3.key(키)와 value(값)의 쌍으로 이루어진모습 Map구조 ex) 키세스초콜릿, json {name :"호랑이"}
	 * */
	ArrayList<JButton> list = new ArrayList<JButton>(); //크기는 0이지만, 고무줄처럼 늘어날 수 있다
	public Test() {
		panel = new JPanel();
		bt_create = new JButton("생성");
		bt_color = new JButton("색상");
		
		
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
			//ArrayList에 넣을 수 있는 객체의 자료형은 모든자료형이므로 Object형으로 형변환되어 들어간다
			//따라서 끄집어 낼때 필요한 자료형으로 down casting 해야한다!!
			
			//제너릭으로 선언된 컬렉션 프레임웤 객체는 형변환이 필요없다 why? 어차피 지정한 자료형 전용이므로
			//즉 썪여있을 가능성이 0프로 이기 때문에..
			JButton bt = list.get(i);
			bt.setBackground(Color.YELLOW);
		}
	}
	public void create() {
		JButton bt =new JButton(n+"번째 버튼");
		n++;
	
		panel.add(bt);
		panel.updateUI();
		list.add(bt);
	}
	
	public static void main(String[] args) {
		new Test();
	}
	
}
