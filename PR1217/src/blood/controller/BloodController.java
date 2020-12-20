package blood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import blood.model.BloodAdcisor;

public class BloodController implements Controller{

	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//�Ķ���� �ޱ� 
		String blood =request.getParameter("blood");
		BloodAdcisor advisor=new BloodAdcisor();
		String msg=advisor.getAdvice(blood);
		
		//����� ����� �������� View�� ����ϹǷ�, �� �������� ó���ϸ� �ȵ�
		//���jsp���� �޼����� �����ַ���, ������ �޸𸮿� �ӽ������� ������ ���� �ʿ䰡 �ִ�. 
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
	}
	public String getViewPage() {
		return "/blood/blood_result.jsp";
	}
}
