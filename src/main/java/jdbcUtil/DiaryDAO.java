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
		System.out.println(dv);
		return sqlsession.selectOne(NameSpace+"selectOne", dv);
	}
}
