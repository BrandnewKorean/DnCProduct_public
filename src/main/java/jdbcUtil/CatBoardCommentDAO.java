package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.CatBoardCommentVO;

@Repository
public class CatBoardCommentDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.CatBoardCommentMapper.";
	
	
	public int delete(CatBoardCommentVO bcv) {
		return sqlsession.delete(NameSpace+"delete",bcv);
	}
	
	public CatBoardCommentVO selectOne(CatBoardCommentVO bcv) {
		return sqlsession.selectOne(NameSpace+"selectOne",bcv);
	}
	
	
	public List<CatBoardCommentVO> selectList(int seq){
		return sqlsession.selectList(NameSpace+"selectList",seq);
	}
	
	public int insert(CatBoardCommentVO bcv) {
		return sqlsession.insert(NameSpace+"insert",bcv);
	}
	
	public int update(CatBoardCommentVO bcv) {
		return sqlsession.update(NameSpace+"update",bcv);
	}
	
}
