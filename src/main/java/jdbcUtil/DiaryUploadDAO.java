package jdbcUtil;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.DiaryUploadVO;

@Repository
public class DiaryUploadDAO {
	@Autowired
	private SqlSession sqlsession;
	
	private static final String NameSpace = "ex01.mappers.DiaryUploadMapper.";
	
	public List<DiaryUploadVO> selectList(DiaryUploadVO duv){
		return sqlsession.selectList(NameSpace+"selectList", duv);
	}
	
	public int insert(DiaryUploadVO duv) {
		return sqlsession.insert(NameSpace+"insert", duv);
	}
}
