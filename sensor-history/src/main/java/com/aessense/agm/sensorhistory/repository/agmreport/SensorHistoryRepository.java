package com.aessense.agm.sensorhistory.repository.agmreport;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.aessense.agm.sensorhistory.model.SensorHistory;
import com.aessense.agm.sensorhistory.model.SensorHistoryKey;

public interface SensorHistoryRepository extends CrudRepository<SensorHistory, SensorHistoryKey>{
	
	@Query("select h from SensorHistory h where h.deviceId = ?1 and h.type in (?2) and h.created between ?3 and ?4")
	List<SensorHistory> findByDeviceIdAndTypeBetweenStartAndEnd(int deviceId, int[] type, Date start, Date end );
	
	@Query("select h from SensorHistory h where h.deviceId = ?1 and h.created between ?2 and ?3")
	List<SensorHistory> findByDeviceIdBetweenStartAndEnd(int deviceId, Date start, Date end );
	
	@Query("select h from SensorHistory h where h.deviceId = ?1 and h.type = ?2 and h.sensorIndex = ?3 and h.created between ?4 and ?5 ORDER BY h.created")
	List<SensorHistory> findByDeviceIdAndTypeAndIndexBetweenStartAndEnd(int deviceId, int type, int index, Date start, Date end );
}
