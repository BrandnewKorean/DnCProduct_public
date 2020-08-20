package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import searchCriteria.StoreSearch;
import vo.CatStoreVO;

@Repository
public class CatStoreDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.CatStoreMapper.";
	
	public List<CatStoreVO> selectList(CatStoreVO cs){
		return sqlsession.selectList(NameSpace+"selectList", cs);
	}
	
	public int searchRowCount(StoreSearch search) {
		return sqlsession.selectOne(NameSpace+"searchRowCount", search);
	}
	
	public List<CatStoreVO> searchList(StoreSearch search){
		return sqlsession.selectList(NameSpace+"searchList", search);
	}
	
}
