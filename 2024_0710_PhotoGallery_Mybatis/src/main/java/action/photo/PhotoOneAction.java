package action.photo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.PhotoDao;
import db.vo.PhotoVo;

/**
 * Servlet implementation class PhotoOneAction
 */
@WebServlet("/photo/photo_one.do")
public class PhotoOneAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /photo/photo_one.do?p_idx=5
		
		// 1. 파라미터 받기
		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		
		// 2.p_idx에 해당되는 PhotoVo정보 얻어온다
		PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
		
		// 응답처리
		response.setContentType("application/json; charset=utf-8");
		
		// VO -> JSON 객체 생성(필요없는건 안넘겨도 됨)
		JSONObject json = new JSONObject();
		json.put("p_idx", 		vo.getP_idx());
		json.put("p_title",		vo.getP_title());
		json.put("p_content", 	vo.getP_content());
		json.put("p_filename", 	vo.getP_filename());
		json.put("p_regdate",	vo.getP_regdate());
		json.put("p_ip", 		vo.getP_ip());
		json.put("mem_idx", 	vo.getMem_idx());
		json.put("mem_name", 	vo.getMem_name());
		
		response.getWriter().print(json.toString());
		

	}

}
