package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.CatBoardImageUploadVO;

@Repository
public class CatBoardImageUploadDAO {

	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace="ex01.mappers.CatBoardImageUploadMapper.";
	
	public List<CatBoardImageUploadVO> selectList(int seq){
		return sqlsession.selectList(NameSpace+"selectList",seq);
	}
	
	public int insert(CatBoardImageUploadVO uvo) {
		return sqlsession.insert(NameSpace+"insert",uvo);
	}
	
}
