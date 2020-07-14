package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.CatBoardVO;

@Repository
public class CatBoardDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.CatBoardMapper.";
	
	public List<CatBoardVO> selectList(){
		return sqlsession.selectList(NameSpace+"selectList");
	} // list
	
	public CatBoardVO selectOne(CatBoardVO bv) {
		return sqlsession.selectOne(NameSpace+"selectOne",bv);
	} // selectOne
	
	
	public int insert(CatBoardVO bv) {
		System.out.println(bv);
		return sqlsession.insert(NameSpace+"insert",bv);
	} // insert
	

} // boardDAO
