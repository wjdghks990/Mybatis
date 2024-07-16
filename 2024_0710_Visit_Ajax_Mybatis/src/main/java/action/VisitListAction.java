package action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.MyCommon;
import util.Paging;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.VisitDao;
import db.vo.VisitVo;

/**
 * Servlet implementation class VisitListAction
 */
@WebServlet("/visit/list.do")
public class VisitListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// /visit/list.do?page=1
		// /visit/list.do?search=all&search_text=
		// /visit/list.do?search=name&search_text=길동
		// /visit/list.do?search=content&search_text=내용
		// /visit/list.do?search=name_content&search_text=길동
		
		//0.수신인코딩 설정
		request.setCharacterEncoding("utf-8");
		
		//1.parameter 받기
		String search	= request.getParameter("search");
		String search_text	= request.getParameter("search_text");
		
		if(search==null) search = "all";
		
		int nowPage = 1;
		try {
			nowPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			
		}		
		int start = (nowPage-1) * MyCommon.Visit.BLOCK_LIST +1;
		int end	  = start+MyCommon.Visit.BLOCK_LIST-1;
		
		// 검색 조건을 담을 맵
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("start", start);
		map.put("end", end);
		
		// 이름 + 내용
		if(search.equals("name_content")) {
			
			map.put("name", search_text);		
			map.put("content", search_text);	
			
		}else if(search.equals("name")) {
			// 이름
			map.put("name", search_text);		
		}else if(search.equals("content")) {
			// 내용
			map.put("content", search_text);		
		}
				
		// 방명록 데이터 가져오기
		List<VisitVo> list = VisitDao.getInstance().selectList(map);
		
		int rowTotal = VisitDao.getInstance().selectRowTotal(map);
		
		// 검색정보 필터
		String search_filter = String.format("search=%s&search_text=%s", search,search_text);
		
		//pageMenu만들기
		String pageMenu = Paging.getPaging("list.do", 					// pageURL
											search_filter,				// 검색필터
											nowPage, 					// 현재페이지
											rowTotal, 					// 전체게시물 수
											MyCommon.Visit.BLOCK_LIST, 	// 한 화면에 보여질 게시물 수
											MyCommon.Visit.BLOCK_PAGE);	// 한 화면에 보여질 페이지 수 
		
		// request binding
		request.setAttribute("list", list);
		request.setAttribute("pageMenu", pageMenu);

		// Dispatcher형식으로 호출
		String forward_page = "visit_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}
}
