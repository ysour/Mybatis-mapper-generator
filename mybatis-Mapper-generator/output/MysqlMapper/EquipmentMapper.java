package com.test.web.mappers;

import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import com.test.web.entities.Equipment;

public interface EquipmentMapper {

	public Equipment findById(String id);
	
	public List<Equipment> listAll();

	public void insertOrUpdate(Equipment equipment);
	
	public void batchInsertOrUpdate(
			@Param("equipments") List<Equipment> equipments);

	public void delete(String id);
	
	public void batchDelete(Set<String> ids);

	public long countAll();
	
	public List<Equipment> query0(@Param("userId") String userId, @Param("orderSortName") String orderSortName, @Param("orderAsc") boolean orderAsc, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public long query1(@Param("equipmentName") String equipmentName, @Param("avatar") String avatar, @Param("name") String name);
	
	public List<Equipment> queryByEquipmentName(@Param("equipmentName") String equipmentName, @Param("orderSortName") String orderSortName, @Param("orderAsc") boolean orderAsc, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
}
