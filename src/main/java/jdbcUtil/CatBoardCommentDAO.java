package jdbcUtil;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.CatBoardCommentVO;

@Repository
public class CatBoardCommentDAO {
	
	@Autowired
	private SqlSession sqlsession;
	private static final String NameSpace = "ex01.mappers.CatBoardCommentMapper.";
	
	public ArrayList<CatBoardCommentVO> select (int seq){
		return (ArrayList)sqlsession.selectList(NameSpace+"select",seq);
	}
	
	public int insert(CatBoardCommentVO bcv) {
		return sqlsession.insert(NameSpace+"insert",bcv);
	}
}