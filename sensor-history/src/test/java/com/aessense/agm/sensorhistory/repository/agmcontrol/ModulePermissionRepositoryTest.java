package com.aessense.agm.sensorhistory.repository.agmcontrol;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aessense.agm.sensorhistory.model.ModulePermission;
import com.aessense.agm.sensorhistory.repository.agmcontrol.ModulePermissionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModulePermissionRepositoryTest {
	
	@Autowired
	private ModulePermissionRepository repo;

	@Test
	public void test() {
		List<ModulePermission> perms = repo.findByCustomerIdAndModuleIdAndEnable(1001, 1, true);
		assertEquals(1, perms.size());
	}

}
