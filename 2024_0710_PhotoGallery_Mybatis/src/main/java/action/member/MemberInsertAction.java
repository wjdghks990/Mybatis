package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import db.vo.MemberVo;

/**
 * Servlet implementation class MemberInsertAction
 */
@WebServlet("/member/insert.do")
public class MemberInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//	/member/insert.do?mem_name=박정환&mem_id=asdf&mem_pwd=1234&mem_zipcode=09696&mem_addr=서울시+동작구
		
		// 0.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		// 1.parameter 받기
		String mem_name 	= request.getParameter("mem_name");
		String mem_id 	 	= request.getParameter("mem_id");
		String mem_pwd 	 	= request.getParameter("mem_pwd");
		String mem_zipcode  = request.getParameter("mem_zipcode");
		String mem_addr 	= request.getParameter("mem_addr");
		
		//2.ip받기
		String mem_ip			= request.getRemoteAddr();
		
		// 3. MemberVO 포장
		MemberVo vo = new MemberVo(mem_name, mem_id, mem_pwd, mem_zipcode, mem_addr, mem_ip);
		
		// 4. DB insert
		int res = MemberDao.getInstance().insert(vo);
		
		// 5. 목록보기
		response.sendRedirect("../photo/list.do");

	}

}