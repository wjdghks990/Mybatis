package action.photo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PhotoDao;
import db.vo.PhotoVo;

/**
 * Servlet implementation class PhotoModifyAction
 */
@WebServlet("/photo/modify.do")
public class PhotoModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		String p_title = request.getParameter("p_title");
		String p_content = request.getParameter("p_content");
		
		PhotoVo vo = new PhotoVo(p_idx,p_title, p_content);
		
		int res = PhotoDao.getInstance().update(vo);
		
		response.sendRedirect("list.do");

	}

}
