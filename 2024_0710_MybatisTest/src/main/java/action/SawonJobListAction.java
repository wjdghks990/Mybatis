package action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.SawonVo;

import java.io.IOException;
import java.util.List;

import dao.SawonDao;

/**
 * Servlet implementation class SawonListAction
 */
@WebServlet("/sawon/list_job.do")
public class SawonJobListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getParameter("utf-8");
		
		// /sawon/list_job.do?sajob=["부장"||"과장"||"대리"||"사원"||"알바"]
		String sajob = request.getParameter("sajob");
		
		if(sajob==null || sajob.isEmpty()) {
			sajob = "all";
		}
		
		// 사원목록 가져오기
		List<SawonVo> list = null;
			
		if(sajob.equals("all")) {			
			list = SawonDao.getInstance().selectList();
		}	
		else {
		 // 직급별 조회
			list = SawonDao.getInstance().selectListFromSajob(sajob);
		}

		request.setAttribute("list",list);		
		
		// Dispatcher형식으로 호출
		String forward_page = "sawon_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}