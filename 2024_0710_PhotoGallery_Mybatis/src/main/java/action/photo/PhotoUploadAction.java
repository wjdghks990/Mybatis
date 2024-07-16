package action.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PhotoDao;
import db.vo.PhotoVo;

/**
 * Servlet implementation class PhotoUploadAction
 */
// 이미지 수정용 서블릿
@WebServlet("/photo/photo_upload.do")
public class PhotoUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /photo/photo_upload.do?p_idx=5&photo=aaa.jpg
		// 파일 업로드 처리
			// String saveDir = "F:\\dev\\upload"; // upload 위치
				String webPath  = "/images/"; // 웹(URL)경로
				
				// 현재 웹어플리케이션의 전역관리객체(상대경로->절대경로)
				ServletContext application = request.getServletContext();
				//				  웹(상대)경로 -> 절대경로 구하기 (과정 꼭 필요!!!)
				String saveDir = application.getRealPath(webPath); 
				// System.out.println(saveDir); 
				int	   maxSize = 1024 * 1024 * 100; // 최대업로드크기 (100MB)
				// FileUpload객체 => MultipartRequest
				MultipartRequest mr = new MultipartRequest(
												request, 	 // request 위임
												saveDir,     // 저장위치
												maxSize, 	 // 최대업로드크기
												"utf-8", 	 // 수신인코딩
												// 화일이름이 동일할 경우 다른 이름으로 변경시키는 객체
												new DefaultFileRenamePolicy() 
												);	
				// 업로드화일명을 얻어온다
				String p_filename = "no_file";
				// mr에 의해서 업로드된 화일정보 얻어온다 // 
				File f = mr.getFile("photo");
				
				if(f!=null) { //업로드화일 존재하면
					
					p_filename = f.getName();
				}
				
				int p_idx = Integer.parseInt(mr.getParameter("p_idx"));
				
				// p_idx에 저장된 이전파일은 삭제
				PhotoVo vo = PhotoDao.getInstance().selectOne(p_idx);
				File delFile = new File(saveDir, vo.getP_filename());
				delFile.delete();
				
				//update된 파일이름 수정
				vo.setP_filename(p_filename);
				int res = PhotoDao.getInstance().update_filename(vo);
				
				// 응답처리
				response.setContentType("application/json; charset=utf-8;");
				//{"p_filename":"a.jpg"}
				String json = String.format("{\"p_filename\":\"%s\"}", p_filename);
				
				response.getWriter().print(json);			
	}

}