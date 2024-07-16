package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.SawonVo;

public class SawonDao {
		
	//SessionFactory생성하는 Mybatis객체 선언 MyBatisConnector에서 받아온다
	SqlSessionFactory factory;
	
	//single-ton : 객체1개만 생성 서비스
	static SawonDao single = null;

	
	public SawonDao() {
		//Mybatis 객체정보 얻어온다
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}

	public static SawonDao getInstance() {

		if (single == null)
			single = new SawonDao();

		return single;
	}
	
	public List<SawonVo> selectList() {

		List<SawonVo> list = null;
		
		// 1. SqlSession얻어오기(Mybatis수행객체)
		SqlSession sqlSession = factory.openSession();
		
		// 2. 작업수행					  namespace.mapperId
		list = sqlSession.selectList("sawon.sawon_list");
		
		// 3. 닫기
		sqlSession.close();
		
		return list;
	}

	public List<SawonVo> selectListFromDeptno(int deptno) {
		
		List<SawonVo> list = null;
		
		// 1. SqlSession얻어오기(Mybatis수행객체)
		SqlSession sqlSession = factory.openSession();
		
		// 2. 작업수행					  namespace.mapperId
		list = sqlSession.selectList("sawon.sawon_list_deptno",deptno);
		
		// 3. 닫기
		sqlSession.close();
		
		return list;
	}

	public List<SawonVo> selectListFromSajob(String sajob) {
		List<SawonVo> list = null;
		
		// 1. SqlSession얻어오기(Mybatis수행객체)
		SqlSession sqlSession = factory.openSession();
		
		// 2. 작업수행					  namespace.mapperId
		list = sqlSession.selectList("sawon.sawon_list_sajob",sajob);
		
		// 3. 닫기
		sqlSession.close();
		
		return list;
	}

	public List<SawonVo> selectList(Map<String, Object> map) {
		List<SawonVo> list = null;
		
		// 1. SqlSession얻어오기(Mybatis수행객체)
		SqlSession sqlSession = factory.openSession();
		
		// 2. 작업수행					  namespace.mapperId
		list = sqlSession.selectList("sawon.sawon_list_condition",map);
		
		// 3. 닫기
		sqlSession.close();
		
		return list;
	}
		
}
