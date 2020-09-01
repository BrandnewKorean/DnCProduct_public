package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.CatBoardNoticeVO;

@Repository
public class CatBoardNoticeDAO {
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.CatBoardNoticeMapper.";
	
	public List<CatBoardNoticeVO> selectList(){
		return sqlsession.selectList(NameSpace+"selectList");
	}
	
	public CatBoardNoticeVO selectOne(CatBoardNoticeVO bnv) {
		return sqlsession.selectOne(NameSpace+"selectOne", bnv);
	}
	
	public int countUp(CatBoardNoticeVO bnv) {
		return sqlsession.update(NameSpace+"countUp", bnv);
	}
}
