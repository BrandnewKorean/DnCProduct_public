package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.CatBoardUploadVO;

@Repository
public class CatBoardUploadDAO {
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.CatBoardUploadMapper.";
	
	public List<CatBoardUploadVO> selectList(int seq){
		return sqlsession.selectList(NameSpace+"selectList", seq);
	}
	
	public int insert(CatBoardUploadVO uvo) {
		return sqlsession.insert(NameSpace+"insert", uvo);
	}
	
	public List<CatBoardUploadVO> selectAllList(){
		return sqlsession.selectList(NameSpace+"selectAllList");
	}
}
