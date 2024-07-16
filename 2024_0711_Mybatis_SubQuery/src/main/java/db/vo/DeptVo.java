package db.vo;

import java.util.List;

public class DeptVo {
	
	int deptno;
	String dname;
	String loc;
	
	// 사원 목록을 저장할 객체
	List<SawonVo> sa_list;
	
	public List<SawonVo> getSa_list() {
		return sa_list;
	}
	public void setSa_list(List<SawonVo> sa_list) {
		this.sa_list = sa_list;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
