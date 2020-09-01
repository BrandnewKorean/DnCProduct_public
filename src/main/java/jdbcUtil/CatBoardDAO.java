package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import searchCriteria.Search;
import vo.CatBoardHeartVO;
import vo.CatBoardVO;
import vo.PageVO;

@Repository
public class CatBoardDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.CatBoardMapper.";
	
	public int updatecomments(int seq) {
		return sqlsession.update(NameSpace+"updatecomments",seq);
	}
	
	public int searchRowCount(Search search) {
		return sqlsession.selectOne(NameSpace+"searchRowCount",search);
	}
	
	public List<CatBoardVO> searchList(Search search){
		return sqlsession.selectList(NameSpace+"searchList",search);
	}
	
	public List<CatBoardVO> criList(Search search){
		return sqlsession.selectList(NameSpace+"criList",search);
	}
	
	public List<CatBoardVO> selectList(){
		return sqlsession.selectList(NameSpace+"selectList");
	} // list
	
	public CatBoardVO selectOne(CatBoardVO bv) {
		return sqlsession.selectOne(NameSpace+"selectOne",bv);
	} // selectOne
	
	
	public int insert(CatBoardVO bv) {
		return sqlsession.insert(NameSpace+"insert",bv);
	} // insert
	
	public int countUp(CatBoardVO bv) {
		return sqlsession.update(NameSpace+"countUp",bv);
	}
	
	public int update(CatBoardVO bv) {
		return sqlsession.update(NameSpace+"update",bv);
	}
	
	public int delete(CatBoardVO bv) {
		return sqlsession.delete(NameSpace+"delete",bv);
	}
	
	public int totalRowCount() {
		return sqlsession.selectOne(NameSpace+"totalRowCount");
	}
	
	
	public PageVO<CatBoardVO> pageList(PageVO<CatBoardVO> pvo) {
		pvo.setTotalCount(totalRowCount());
		pvo.setList(sqlsession.selectList(NameSpace+"pageList",pvo));
		return pvo;
	}
	
	public int insertseq() {
		return sqlsession.selectOne(NameSpace+"insertseq");
	}
	public int likeCheck(CatBoardHeartVO bhv) {
		return sqlsession.update(NameSpace+"likeCheck",bhv);
	}
	
	public int likeinsert(CatBoardHeartVO bhv) {
		return sqlsession.insert(NameSpace+"likeinsert",bhv);
	}
	
	public CatBoardHeartVO selectlike(CatBoardHeartVO bhv) {
		return sqlsession.selectOne(NameSpace+"selectlike", bhv);
	}
	
	public int likedelete(CatBoardHeartVO bhv) {
		return sqlsession.delete(NameSpace+"likedelete", bhv);
	}

} // boardDAO
