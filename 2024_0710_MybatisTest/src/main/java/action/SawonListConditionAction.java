package action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.SawonDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.SawonVo;

/**
 * Servlet implementation class SawonListAction
 */
@WebServlet("/sawon/list_condition.do")
public class SawonListConditionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// /sawon/list.do?deptno=0&sajob=all
		// /sawon/list.do?deptno=0&sajob=부장
		// /sawon/list.do?deptno=10&sajob=사원
		
		request.setCharacterEncoding("utf-8");
		
		int deptno = 0;
		
		try {
			deptno = Integer.parseInt(request.getParameter("deptno"));
		} catch (Exception e) {
			
		}
		
		int hire_year_period = 0;
		
		try {
			hire_year_period = Integer.parseInt(request.getParameter("hire_year_period"));
		} catch (Exception e) {
			
		}
		
		String sajob = request.getParameter("sajob");
		if(sajob==null) sajob="all";
		
		String sasex = request.getParameter("sasex");
		if(sasex==null) sasex="all";
		
		
		// 검색할 조건을 전달할 Map
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(hire_year_period != 0) {	// 전체가 아니면
			map.put("hire_year_period", hire_year_period);
		}
		
		if(deptno != 0) {	// 전체가 아니면
			map.put("deptno", deptno);
		}
		
		if(!sajob.equals("all")) { // 전체가 아니면
			map.put("sajob", sajob);
		}
		
		if(!sasex.equals("all")) {
			map.put("sasex", sasex);
		}
		
		
		// 사원목록 가져오기
		List<SawonVo> list = SawonDao.getInstance().selectList(map);

		request.setAttribute("list",list);		
		
		// Dispatcher형식으로 호출
		String forward_page = "sawon_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}