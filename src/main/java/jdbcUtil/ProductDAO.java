package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.ProductVO;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.ProductMapper.";
	
	public List<ProductVO> selectList(ProductVO pv){
		return sqlsession.selectList(NameSpace+"selectList", pv);
	}
	
	public ProductVO selectOne(ProductVO pv) {
		return sqlsession.selectOne(NameSpace+"selectOne", pv);
	}
}
