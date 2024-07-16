package action.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import db.vo.MemberVo;

/**
 * Servlet implementation class MemberCheckIdAction
 */
@WebServlet("/member/check_id.do")
public class MemberCheckIdAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /member/check_id.do?mem_id=one
		
		// 0. 수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		// 1. parameter 받기
		String mem_id = request.getParameter("mem_id");
		
		// 2. mem_id에 해당되는 유저정보 검색
		MemberVo vo = MemberDao.getInstance().selectOne(mem_id);
		
		boolean bResult = (vo==null); // vo가 null이면 true(사용가능한 아이디 - 중복되는 데이터가 없다는 뜻)
		
		// 결과전송
		response.setContentType("application/json; charset=utf-8;");
		
		PrintWriter out = response.getWriter();
		
		String json = String.format("{\"result\":%b}", bResult);
		
		out.print(json);
		//response.getWriter.print(json); -> PrintWriter 생성할 필요 x
		

	}

}
