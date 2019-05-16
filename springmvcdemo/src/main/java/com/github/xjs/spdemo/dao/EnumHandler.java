package com.github.xjs.spdemo.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.github.xjs.spdemo.enums.BaseEnum;
import com.github.xjs.spdemo.enums.EnumFactory;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年5月14日 下午2:44:47<br/>
 * 参考：https://blog.csdn.net/u014044812/article/details/78258730 
 */
@Alias("EnumHandler")
public class EnumHandler<R extends BaseEnum<V>,V> extends BaseTypeHandler<R> {

	private Class<R> type;

	public EnumHandler(Class<R> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, R parameter, JdbcType jdbcType) throws SQLException {
		if (jdbcType == null) {
			V value = parameter.getValue();
			if(value instanceof Integer || value instanceof Short || value instanceof Character || value instanceof Byte){
				ps.setInt(i, (Integer)value);
			}else if(value instanceof String){
				ps.setString(i, (String)value);
			}else if(value instanceof Boolean){
				ps.setBoolean(i, (Boolean)value);
			}else if(value instanceof Long){
				ps.setLong(i, (Long)value);
			}else if(value instanceof Double){
				ps.setDouble(i, (Double)value);
			}else if(value instanceof Float){
				ps.setFloat(i, (Float)value);
			}else{
				throw new RuntimeException("unsupported [id] type of enum");
			}
		} else {
			ps.setObject(i, parameter.getValue(), jdbcType.TYPE_CODE); // see r3589
		}
	}

	@Override
	public R getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String s = rs.getString(columnName);
		return toEnum(s);
	}

	@Override
	public R getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String s = rs.getString(columnIndex);
		return toEnum(s);
	}

	@Override
	public R getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String s = cs.getString(columnIndex);
		return toEnum(s);
	}
	
	private R toEnum(String value){
		return EnumFactory.convertByStringValue(type, value);
	}
}
