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
	
	public List<CatBoardCommentVO> select(int seq){
		return sqlsession.selectList(NameSpace+"select",seq);
	}
	
	public int insert(CatBoardCommentVO bcv) {
		return sqlsession.insert(NameSpace+"insert",bcv);
	}
	
}
