

package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLoginAction
 */
@WebServlet("/member/logout.do")
public class MemberLogoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		// 로그아웃 처리: 현재 로그인된 객체(user)정보를 session에서 제거
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		// 메인페이지로 이동
		response.sendRedirect("../photo/list.do");
		
	}
}
