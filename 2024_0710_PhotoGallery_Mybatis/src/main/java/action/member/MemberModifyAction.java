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
 * Servlet implementation class MemberModifyAction
 */
@WebServlet("/member/modify.do")
public class MemberModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /member/modify.do?mem_idx=2&mem_name=일길동&mem_id=one&mem_pwd=1234&mem_zipcode=00000&mem_addr=서울시+관악구&mem_grade=일반
		
		request.setCharacterEncoding("utf-8");
		int    mem_idx  	= Integer.parseInt(request.getParameter("mem_idx"));
		String mem_name 	= request.getParameter("mem_name");
		String mem_id		= request.getParameter("mem_id");
		String mem_pwd		= request.getParameter("mem_pwd");
		String mem_zipcode	= request.getParameter("mem_zipcode");
		String mem_addr		= request.getParameter("mem_addr");
		String mem_grade	= request.getParameter("mem_grade");
		
		MemberVo vo = new MemberVo(mem_idx, mem_name, mem_id, mem_pwd, mem_zipcode, mem_addr, mem_grade);
		
		int res = MemberDao.getInstance().update(vo);
		
		HttpSession session = request.getSession();
		MemberVo loginUser = (MemberVo) session.getAttribute("user");
		
		if(loginUser.getMem_idx()==mem_idx) {
	
			// 로그인상태정보
			MemberVo user = MemberDao.getInstance().selectOne(mem_idx);
			
			// session.removeAttribute("user"); 불필요한 작업
			// scope내에 저장방식 Map형식: key / value
			//						  동일한 key로 저장하면 수정처리된다
			session.setAttribute("user", user);
		}
			
		response.sendRedirect("list.do");		
	}

}
