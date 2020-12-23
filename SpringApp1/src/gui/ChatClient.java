package gui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChatClient extends JFrame{
	JComponent p_north;
	JComponent t_ip;//ip작성
	JComponent bt;
	JComponent area;
	JComponent scroll;
	JComponent t_input; //메세지입력창
	
	//new하지 않으려고 getter&setter준비 
	public JComponent getP_north() {
		return p_north;
	}
	public void setP_north(JComponent p_north) {
		this.p_north = p_north;
	}
	public JComponent getT_ip() {
		return t_ip;
	}
	public void setT_ip(JComponent t_ip) {
		this.t_ip = t_ip;
	}
	public JComponent getBt() {
		return bt;
	}
	public void setBt(JComponent bt) {
		this.bt = bt;
	}
	public JComponent getArea() {
		return area;
	}
	public void setArea(JComponent area) {
		this.area = area;
	}
	public JComponent getScroll() {
		return scroll;
	}
	public void setScroll(JComponent scroll) {
		this.scroll = scroll;
	}
	public JComponent getT_input() {
		return t_input;
	}
	public void setT_input(JComponent t_input) {
		this.t_input = t_input;
	}
	
	public void init() {
		p_north.add(t_ip);
		p_north.add(bt);
		add(p_north,BorderLayout.NORTH);
		
		add(scroll);
		add(t_input,BorderLayout.SOUTH);
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
}
