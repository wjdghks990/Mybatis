package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import db.vo.PhotoVo;

/**
 * Servlet implementation class PhotoDeleteAction
 */
@WebServlet("/photo/delete.do")
public class PhotoDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /photo/delete.do?p_idx=5
		
		// 1. parameter받기
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		// 현재 p_idx가 사용하고 있는 화일도 삭제
		// 2. PhotoVo정보 얻어온다
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		// /images/의 절대경로
		String absPath = request.getServletContext().getRealPath("/images/");
		//						(절대경로)		(삭제)파일명	
		File delFile = new File(absPath, vo.getP_filename());		
		delFile.delete();
		
		// DB delete
		int res = PhotoDao.getInstance().delete(p_idx);
				
		
		// 메인화면이동
		response.sendRedirect("list.do");
	}

}