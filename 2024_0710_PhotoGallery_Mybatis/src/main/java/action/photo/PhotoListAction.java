package action.photo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoDao;
import db.vo.PhotoVo;
import util.MyCommon;
import util.Paging;

/**
 * Servlet implementation class PhotoListAction
 */
@WebServlet("/photo/list.do")
public class PhotoListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// /photo/list.do
		// /photo/list.do?page=2
		
		int nowPage = 1;
		try {
			nowPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 게시물의 범위 계산(start/end)
		int start = (nowPage-1) * MyCommon.Photo.BLOCK_LIST + 1;
		int end   = start + MyCommon.Photo.BLOCK_LIST - 1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		
		List<PhotoVo> list = PhotoDao.getInstance().selectList(map);
		
		// 전체 게시물 수
		int rowTotal = PhotoDao.getInstance().selectRowTotal();
		
		//pageMenu만들기
		String pageMenu = Paging.getPaging("list.do", 					// pageURL
											nowPage, 					// 현재페이지
											rowTotal, 					// 전체게시물 수
											MyCommon.Photo.BLOCK_LIST, 	// 한 화면에 보여질 게시물 수
											MyCommon.Photo.BLOCK_PAGE);	// 한 화면에 보여질 페이지 수 
		
		//System.out.println(pageMenu);
		// request binding
		request.setAttribute("list", list);
		request.setAttribute("pageMenu", pageMenu);

		// Dispatcher형식으로 호출
		String forward_page = "photo_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}
