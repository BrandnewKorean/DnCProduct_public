package jdbcUtil;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.CatBoardHeartVO;

@Repository
public class CatBoardHeartDAO {

	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace ="ex01.mappers.CatBoardHeartMapper.";
	
	public int likeCheck(CatBoardHeartVO bhv) {
		return sqlsession.update(NameSpace+"likeCheck",bhv);
	}

	public int likeinsert(CatBoardHeartVO bhv) {
		return sqlsession.insert(NameSpace+"likeinsert",bhv);
	}

	public int likedelete(CatBoardHeartVO bhv) {
		return sqlsession.delete(NameSpace+"likedelete",bhv);
	}

	public CatBoardHeartVO selectlike(CatBoardHeartVO bhv) {
		return sqlsession.selectOne(NameSpace+"selectlike",bhv);
	}
	
}
