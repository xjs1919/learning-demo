package com.xjs1919.mybatis.entity.chapter_2_6;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jiashuai.xujs
 * @date 2022/3/7 10:40
 */
public class EnumAbleTypeHandler<E extends Enum<E> & EnumAble> extends BaseTypeHandler<E> {

    private Class<E> type;

    public EnumAbleTypeHandler(Class<E> type){
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            Object value = parameter.getValue();
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
                throw new RuntimeException("unsupported [value] type of enum");
            }
        } else {
            ps.setObject(i, parameter.getValue(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return toEnum(s);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        return toEnum(s);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        return toEnum(s);
    }

    private <E extends Enum<E> & EnumAble> E toEnum(String valueString){
        if(valueString == null || valueString.length() <= 0){
            return null;
        }
        E[] enums = (E[])type.getEnumConstants();
        for(E enumInstance : enums){
            if(valueString.equals(enumInstance.getValue().toString())){
                return enumInstance;
            }
        }
        return null;
    }
}
