package action.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import db.vo.MemberVo;

/**
 * Servlet implementation class MemberListAction
 */
@WebServlet("/member/list.do")
public class MemberListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 회원목록 가져오기
		List<MemberVo> list = MemberDao.getInstance().selectList();

		// request 바인딩
		request.setAttribute("list", list);
		
		// Dispatcher형식으로 호출
		String forward_page = "member_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
