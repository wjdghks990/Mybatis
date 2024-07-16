package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import db.vo.MemberVo;

/**
 * Servlet implementation class MemberLoginAction
 */
@WebServlet("/member/login.do")
public class MemberLoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// /member/login.do?mem_id=one&mem_pwd=1234
		
		// 0. 수신 인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		// 1.parameter 받기
		String mem_id  = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		
		// 2.mem_id를 이용해서 MemberVo를 얻어온다
		MemberVo user = MemberDao.getInstance().selectOne(mem_id);
		
		if(user == null) { // 데이터베이스에 아이디가 없는 경우 (아이디가 틀림)
			
			//					   Session Tracking방법 중 하나(GET)
			response.sendRedirect("login_form.do?reason=fail_id");
			
			return;
		}
		
		// 비밀번호가 틀린 경우
		if(user.getMem_pwd().equals(mem_pwd)==false) {
			response.sendRedirect("login_form.do?reason=fail_pwd&mem_id=" + mem_id);
			
			return;
		}
		
		// 로그인 처리: 현재 로그인된 객체(user)정보를 session저장
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		// 현재경로 : /member/login.do
		
		// 메인페이지로 이동
		response.sendRedirect("../photo/list.do");
		

	}

}
