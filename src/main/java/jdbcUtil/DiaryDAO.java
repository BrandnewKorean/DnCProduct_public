package jdbcUtil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.DiaryVO;

@Repository
public class DiaryDAO {
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.DiaryMapper.";
	
	public DiaryVO selectOne(DiaryVO dv) {
		return sqlsession.selectOne(NameSpace+"selectOne", dv);
	}
	
	public int insert(DiaryVO dv) {
		return sqlsession.insert(NameSpace+"insert", dv);
	}
	
	public int delete(DiaryVO dv) {
		return sqlsession.delete(NameSpace+"delete", dv);
	}
	
	public int update(DiaryVO dv) {
		return sqlsession.update(NameSpace+"update", dv);
	}
}
