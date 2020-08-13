package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.ProductImageVO;

@Repository
public class ProductImageDAO {
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.ProductImageMapper.";
	
	public List<ProductImageVO> selectList(ProductImageVO piv){
		return sqlsession.selectList(NameSpace+"selectList", piv);
	}
}
