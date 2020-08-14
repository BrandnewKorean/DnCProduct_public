package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.CatStoreVO;

@Repository
public class CatStoreDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.CatStoreMapper.";
	
	public List<CatStoreVO> selectList(CatStoreVO cs){
		return sqlsession.selectList(NameSpace+"selectList", cs);
	}
}
