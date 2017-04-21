package cl.nexo.manager.access.dao;

import java.sql.ResultSet;

public interface AccessDao {

	public ResultSet selectSql(String query);

	public int insertUpdateDelteSql(String query);

}
