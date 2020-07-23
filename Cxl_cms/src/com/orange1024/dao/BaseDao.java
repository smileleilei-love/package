package com.orange1024.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.orange1024.common.PageInfo;
import com.orange1024.util.JdbcUtil;

/**
 * 
 * @author Mr_Cxl
 * @Title BaseDao
 * @data 2020年7月17日 上午10:35:14
 * @Describe:通用的数据操作类
 */
public abstract class BaseDao {
	/**
	 * 2020年7月17日上午10:35:50
	 * @Describe: 向表中添加数据 返回数据自增涨的主键值
	 * @param sql
	 * @param param
	 * @return
	 */
	public int insert(String sql, Object... param) {
		// 1. 获取连接
		Connection conn = JdbcUtil.getConnection();
		// 2. 创建指令对象
		PreparedStatement prep = null;
		int key = 0;
		try {
			//创建指令对象  会生成主键
			prep = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			// 3.对占位符进行赋值操作
			for (int i = 0; i < param.length; i++) {
				// i+1 占位符索引 占位符是从 1 开始
				prep.setObject(i + 1, param[i]);
			}
			// 发送指令
			prep.executeUpdate();
			//获取结果
			ResultSet rs = prep.getGeneratedKeys();
			//数据指针偏移
			rs.next();
			//获取主键值
			key = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, prep);
		}
		return key;
	}
	
	/**
	 * 2020年7月17日下午2:17:31
	 * @Describe:对数据更新操作
	 * @param sql
	 * @param param
	 * @return 如果操作成功 则返回true 失败 false
	 */
	public boolean update(String sql, Object... param) {
		// 1. 获取连接
		Connection conn = JdbcUtil.getConnection();
		// 2. 创建指令对象
		PreparedStatement prep = null;
		// 受影响行数
		int size = 0;
		try {
			prep = conn.prepareStatement(sql);
			// 3.对占位符进行赋值操作
			for (int i = 0; i < param.length; i++) {
				// i+1 占位符索引 占位符是从 1 开始
				prep.setObject(i + 1, param[i]);
			}
			// 发送指令
			size = prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, prep);
		}
		return size > 0 ? true : false;
	}
	
	
	/**
	 * 
	 * 2020年7月17日下午2:20:19
	 * @Describe: 通用的查询单个对象封装
	 * @param <T>
	 * @param sql
	 * @param cls
	 * @param param
	 * @return
	 */
	public <T> T selectOne(String sql, Class<T> cls, Object... param) {
		List<T> data = this.selectList(sql, cls, param);
		if (data != null && data.size() == 1) {
			return data.get(0);
		}
		return null;
	}

	/**
	 * 2020年7月17日下午2:20:40
	 * @Describe: 通用的查询列表封装
	 * @param <T>
	 * @param sql
	 * @param cls
	 * @param param
	 * @return
	 */
	public <T> List<T> selectList(String sql, Class<T> cls, Object... param) {
		// 创建List 数据容器
		List<T> data = new ArrayList<T>();
		// 1. 获取连接
		Connection conn = JdbcUtil.getConnection();
		// 2.创建指令对象
		PreparedStatement prep = null;
		// 结果集、
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(sql);
			// 3.对占位符进行赋值操作
			for (int i = 0; i < param.length; i++) {
				// i+1 占位符索引 占位符是从 1 开始
				prep.setObject(i + 1, param[i]);
			}
			// 4.发送指令 接收查询结果
			rs = prep.executeQuery();
			// 获取数据元信息
			ResultSetMetaData metaData = rs.getMetaData();
			// 获取列个数
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				T obj = cls.newInstance();
				// 循环获取查询结果的列别名
				for (int i = 1; i <= columnCount; i++) {
					// 获取列别名
					String columnLabel = metaData.getColumnLabel(i);
					// 根据别名获取值
					Object value = rs.getObject(columnLabel);
					// 根据列名查找属性
					Field field = cls.getDeclaredField(columnLabel);
					// 忽略访问修饰符
					field.setAccessible(true);
					// 为属性赋值
					field.set(obj, value);
				}
				// 将每个数据对象 放入到容器中
				data.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, prep, rs);
		}
		// 返回数据
		return data;
	}
	
	
	/**
	 * 2020年7月17日下午2:21:03
	 * @Describe: 分页查询数据
	 * @param <T>
	 * @param sql
	 * @param cls
	 * @param page
	 * @param limit
	 * @return
	 */
	public <T> PageInfo<T> selectPage(String sql, Class<T> cls,String page,String limit) {
		//拼装查询符合条件的数据条数的sql
		Long count = selectCount(sql);
		//查询list数据 sql
		int pageOffset  = (Integer.parseInt(page) - 1) * Integer.parseInt(limit) ;
		sql = sql + " limit "+ pageOffset +","+limit;
		List<T> list = this.selectList(sql, cls);
		PageInfo<T> pageInfo = new PageInfo<T>();
		pageInfo.setCount(count);
		pageInfo.setData(list);
		return pageInfo;
		
	}
	/**
	 * 2020年7月17日下午2:21:16
	 * @Describe: 查询条数
	 * @param sql
	 * @returnLong
	 */
	private Long selectCount(String sql) {
		String countSql = " select count(1) from ("+sql+") rs";
		Connection conn = JdbcUtil.getConnection();
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(countSql);
			rs = prep.executeQuery();
			rs.next();//移动数据指针
			long count = rs.getLong(1);
			return count;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, prep, rs);
		}
		return 0L;
	};
	
}
