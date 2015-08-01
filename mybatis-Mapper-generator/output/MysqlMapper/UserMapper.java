package com.test.web.mappers;

import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import com.test.web.entities.User;

public interface UserMapper {

	public User findById(String id);
	
	public List<User> listAll();

	public void insertOrUpdate(User user);
	
	public void batchInsertOrUpdate(
			@Param("users") List<User> users);

	public void delete(String id);
	
	public void batchDelete(Set<String> ids);

	public long countAll();
	
}
