package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import db.vo.VisitVo;
import service.MyBatisConnector;

public class VisitDao {
	
	SqlSessionFactory factory;

	// single-ton pattern : 객체 1개만 생성해서 이용하자
	static VisitDao single = null;

	public static VisitDao getInstance() {

		//없으면 생성해라
		if (single == null)
			single = new VisitDao();

		return single;
	}

	// 외부에서 객체생성하지 말아라...
	private VisitDao() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	//목록조회
	public List<VisitVo> selectList() {

		List<VisitVo> list = null;

		// 1.SqlSession얻어오기
		SqlSession sqlSession = factory.openSession(); // Connection획득
		
		// 2.작업수행
		list = sqlSession.selectList("visit.visit_list");
		
		// 3.닫기 conn.close()과정포함
		sqlSession.close();
		
		return list;
	} // end - selectList()
	
	public List<VisitVo> selectList(Map<String, Object> map) {
		
		List<VisitVo> list = null;

		// 1.SqlSession얻어오기
		SqlSession sqlSession = factory.openSession(); // Connection획득
		
		// 2.작업수행
		list = sqlSession.selectList("visit.visit_list_condition",map);
		
		// 3.닫기 conn.close()과정포함
		sqlSession.close();
		
		return list;
	}
	

	public int insert(VisitVo vo) {
		// TODO Auto-generated method stub

		int res = 0;
		
		// 1.SqlSession얻어오기
		SqlSession sqlSession = factory.openSession(); // Connection획득
		
		// 2.작업수행
		res = sqlSession.insert("visit.visit_insert",vo);
		if(res==1)
			sqlSession.commit();
				
		// 3.닫기 conn.close()과정포함
		sqlSession.close();
		

		return res;

	}//end:insert()

	public int delete(int idx) {
		// TODO Auto-generated method stub

		int res = 0;		
		// 1.SqlSession얻어오기						true <- auto commint 옵션
		SqlSession sqlSession = factory.openSession(true); // Connection획득
		
		// 2.작업수행
		res = sqlSession.delete("visit.visit_delete",idx);
				
		// 3.닫기 conn.close()과정포함
		sqlSession.close();
		
		return res;

	}//end:delete()

	// 일부만 조회
	public VisitVo selectOne(int idx) {

		VisitVo vo = null;
		
		// 1.SqlSession얻어오기						true <- auto commint 옵션
		SqlSession sqlSession = factory.openSession(true); // Connection획득
		
		// 2.작업수행
		vo = sqlSession.selectOne("visit.visit_one",idx);
				
		// 3.닫기 conn.close()과정포함
		sqlSession.close();

		return vo;
	}

	public int update(VisitVo vo) {
		// TODO Auto-generated method stub

		int res = 0;
		              
		// 1.SqlSession얻어오기						true <- auto commint 옵션
		SqlSession sqlSession = factory.openSession(true); // Connection획득
		
		// 2.작업수행
		res = sqlSession.update("visit.visit_update",vo);
				
		// 3.닫기 conn.close()과정포함
		sqlSession.close();

		return res;

	}//end:update()

	public int selectRowTotal(Map<String, Object> map) {
		int total = 0;
		
		//1.SqlSession 얻어오기
		SqlSession sqlSession = factory.openSession();

		//2.작업수행
		total = sqlSession.selectOne("visit.visit_row_total",map);

		//3.닫기
		sqlSession.close();
		
		return total;
	}

}
