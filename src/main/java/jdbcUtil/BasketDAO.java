package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.BasketVO;

@Repository
public class BasketDAO {

	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.BasketMapper.";
	
	public List<BasketVO> selectList(BasketVO bkv){
		return sqlsession.selectList(NameSpace+"selectList",bkv);
	}
	
	public int insert(BasketVO bkv) {
		return sqlsession.insert(NameSpace+"insert",bkv);
	}
	
	public int delete(BasketVO bkv) {
		return sqlsession.delete(NameSpace+"delete",bkv);
	}
	
	public BasketVO selectOne(BasketVO bkv) {
		return sqlsession.selectOne(NameSpace+"selectOne", bkv);
	}
	
	
}
