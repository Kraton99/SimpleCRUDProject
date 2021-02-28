package com.spring;


import static org.assertj.core.api.Assertions.assertThat;

import com.spring.controllers.SamochodController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebAppApplicationTests {


	@Autowired
	private SamochodController samochodController;

	@Test
	public void contextLoads() {
		assertThat(samochodController).isNotNull();
	}

}
