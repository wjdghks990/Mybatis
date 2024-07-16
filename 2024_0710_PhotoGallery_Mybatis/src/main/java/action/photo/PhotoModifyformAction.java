package action.photo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import db.vo.PhotoVo;

/**
 * Servlet implementation class PhotoModifyformAction
 */
@WebServlet("/photo/modify_form.do")
public class PhotoModifyformAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// /photo/modify_form.do?p_idx=5
		
		// 1. parameter 받기
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));

		// 2. p_idx -> PhotoVo얻기
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		// <br> -> \n
		String p_content = vo.getP_content().replaceAll("<br>", "\n");
		vo.setP_content(p_content);
						
		// 3. reauest binding
		request.setAttribute("vo", vo);
		
		// Dispatcher형식으로 호출
		String forward_page = "photo_modify_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
