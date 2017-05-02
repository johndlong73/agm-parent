package com.aessense.agm.sensorhistory.repository.agmcontrol;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aessense.agm.sensorhistory.model.ModulePermission;

/**
 * The repository for performing CRUD operations on ModulePermissions.
 * @author John Long
 *
 */
public interface ModulePermissionRepository extends CrudRepository<ModulePermission, Long> {
	
	List<ModulePermission> findByCustomerIdAndModuleIdAndEnable(long customerId, long moduleId, boolean enable);
}

