package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import searchCriteria.Search;
import vo.CatBoardVO;
import vo.PageVO;

@Repository
public class CatBoardDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.CatBoardMapper.";
	
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
		System.out.println(bv);
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

} // boardDAO
