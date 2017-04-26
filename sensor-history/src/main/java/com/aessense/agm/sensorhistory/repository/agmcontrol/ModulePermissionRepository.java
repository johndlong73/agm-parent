package com.aessense.agm.sensorhistory.repository.agmcontrol;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aessense.agm.sensorhistory.model.ModulePermission;

public interface ModulePermissionRepository extends CrudRepository<ModulePermission, Long> {
	
	List<ModulePermission> findByCustomerIdAndModuleIdAndEnable(long customerId, long moduleId, boolean enable);
}

