package com.searchinner.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import com.searchinner.dao.UserDAO;
import com.searchinner.exception.MyRuntimeException;
import com.searchinner.model.Catalog;
import com.searchinner.model.User;
import com.searchinner.util.Util;

@Repository("userDAOImp")
public class UserDAOImp implements UserDAO {

	/**
	 * spring提供的jdbc操作辅助类
	 */
	private JdbcTemplate jdbcTemplate;

	@Override
	public User addUser(User user) {
		final String sql = "insert into learn_user(realName,userName,passWord,addTime,imagepath) values(?,?,?,sysdate,?)";
		String password = user.getUserName() + user.getPassWord();
		password = Util.encrypt_MD5(password);
		user.setImagePath("mgr/images/dota.jpg");
		Object obj[] = { user.getRealName(), user.getUserName(),
				password, user.getImagePath()};
		int temp = this.jdbcTemplate.update(sql, obj);

		if (temp > 0) {
			System.out.println("插入成功！");
		} else {
			throw new MyRuntimeException("注册失败");
		}
		return user;
	}

	@Override
	public User updateUser(User user) {
		String sql = "update learn_user set realName=?,passWord=?,imagepath=? where userName=?";
		/*String password = user.getUserName() + user.getPassWord();
		password = Util.encrypt_MD5(password);*/
		Object obj[] = {
				user.getRealName(),
				user.getPassWord(),
				user.getImagePath(),
				user.getUserName()};
		try{
			this.jdbcTemplate.update(sql, obj);
			System.out.println("更新成功！");
		}catch(Exception e){
			throw new MyRuntimeException("更新文章失败" ,e);
		}
		return user;
	}

	@Override
	public User getUser(String userName) {
		String sql = "select realName,userName,passWord,lastModified,isdelete,addTime,imagePath from learn_user where isdelete=0 and userName=?";// "+id;
		Object obj[] = { userName };
		User userRet = null;
		List list = jdbcTemplate.query(sql, obj,
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						User user = new User();
						user.setRealName(rs.getString("realName"));
						user.setUserName(rs
								.getString("userName"));
						user.setPassWord(rs.getString("passWord"));
						user.setLastModified(rs.getString("lastModified"));
						user.setAddTime(rs.getString("addTime"));
						user.setIsDelete(rs.getInt("isdelete"));
						user.setImagePath(rs.getString("imagePath"));
						return user;
					}

				});
		if(list != null && list.size() > 0){
			userRet = (User) list.get(0);
		}
		return userRet;
	}

	@Override
	public void deleteUser(String userName) {
		String sql = "update learn_user set isdelete =1  where userName=" + userName;
		int temp = this.jdbcTemplate.update(sql);
		if (temp > 0) {
			System.out.println("删除成功！");
		} else {
			throw new MyRuntimeException("删除失败！");
		}
	}

	@Override
	public List<User> getUsers() {
		String sql = "select NVL(tt.nums,0) as nums,u.realName,u.userName,u.imagePath from learn_user u left join ( select NVL(count(t.articleauthorid),0) as nums ,t.articleauthorid from LEARN_ARTICLE t where t.isdelete=0 group by (t.articleauthorid) ) tt  on u.username=tt.articleauthorid order by tt.nums asc";
		List<User> userList = new LinkedList<User>();
		User userRet = null;
		List list = jdbcTemplate.query(sql,
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						User user = new User();
						user.setRealName(rs.getString("realName"));
						user.setUserName(rs
								.getString("userName"));
						user.setImagePath(rs
								.getString("imagePath"));
						user.setIsDelete(rs
								.getInt("nums")); // 不想太麻烦。。再建一个viewmodel。所以用isdelete字段保存文章数
						return user;
					}

				});
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof User){
					userRet = new User();
					userRet = (User)object;
					userList.add(userRet);
				}
			}
		}
		
		return userList;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User logon(User u) {
		String sql = "select realName,userName,passWord,lastModified,isdelete,addTime,defaultCatalogid,imagePath from learn_user where isdelete=0 and userName=? and passWord=?";// "+id;
		String password = u.getUserName() + u.getPassWord();
		password = Util.encrypt_MD5(password);
		Object obj[] = { u.getUserName(),password };
		User userRet = null;
		List list = jdbcTemplate.query(sql, obj,
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						User user = new User();
						user.setRealName(rs.getString("realName"));
						user.setUserName(rs
								.getString("userName"));
						user.setPassWord(rs.getString("passWord"));
						user.setLastModified(rs.getString("lastModified"));
						user.setAddTime(rs.getString("addTime"));
						user.setIsDelete(rs.getInt("isdelete"));
						user.setDefaultCatalogid(rs.getInt("defaultCatalogid"));
						user.setImagePath(rs.getString("imagePath"));
						return user;
					}

				});
		if(list != null && list.size() > 0){
			userRet = (User) list.get(0);
		}
		return userRet;
	}

	@Override
	public void updateUserOfCatalog(User user) {
		String sql = "update learn_user set defaultCatalogid =?  where userName=?";
		Object obj[] = { user.getDefaultCatalogid(),user.getUserName() };
		try{
			this.jdbcTemplate.update(sql,obj);
		}catch(Exception e){
			throw new MyRuntimeException("更新用户分类失败");
		}
	}
}
