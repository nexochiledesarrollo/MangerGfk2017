package cl.nexo.manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.dao.AccessDao;

public class SqlAccess implements AccessDao {
	
	private static final Logger logger = Logger.getLogger(SqlAccess.class);
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public ResultSet selectSql(String query){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ResultSet rs = ps.executeQuery();
			return rs;
			
		}  catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int insertUpdateDelteSql(String query){
		int result = 0;
    	Connection conn = null;
    	try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ps.executeUpdate();
			return result = 1;
			
		}  catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}
	
	
	
}
