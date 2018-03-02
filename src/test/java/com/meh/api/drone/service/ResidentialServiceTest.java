package com.meh.api.drone.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.meh.api.drone.enums.DroneDirectionEnum;


@RunWith(SpringRunner.class)
public class ResidentialServiceTest {
	 
	private static final String POSITION_RIGHT = DroneDirectionEnum.RIGHT.name();
	private static final String POSITION_LEFT = DroneDirectionEnum.LEFT.name();
	private static final String POSITION_DOWN = DroneDirectionEnum.DOWN.name();
	private static final String POSITION_UP = DroneDirectionEnum.UP.name();
	private static final String[] RANGE_1_URBANIZATIONS =
         {"urb7", "urb8", "urb9", "urb12", "urb14", "urb17", "urb18", "urb19"};
	private static final String[] RANGE_2_URBANIZATIONS =
         {"urb1", "urb2", "urb3", "urb4", "urb5", "urb6", "urb10", "urb11",
                 "urb15", "urb16", "urb20", "urb21", "urb22", "urb23", "urb24", "urb25"};

	@Autowired
	private ResidentialService residentialService;
	
	@MockBean
	private ResidentialDriverService residentialDriver;

	@Before
	public void setUp() {
		prepareMocks();
	}

	@Test
	public void obtenerUrbanizacionesShouldReturnRange1Urbanizations() {
		List<String> residentials = residentialService.getResidentialsByPositionDrone(38.56889, 40.511107, 1);
		assertThat(residentials).containsExactlyInAnyOrder(RANGE_1_URBANIZATIONS);
	}

	@Test
	public void obtenerUrbanizacionesShouldReturnRange2Urbanizations() {
     List<String> residentials = residentialService.getResidentialsByPositionDrone(38.56889, 40.511107, 2);
     assertThat(residentials).containsExactlyInAnyOrder(RANGE_2_URBANIZATIONS);
	}

	private void prepareMocks() {
		when(residentialDriver.getIdResidential(38.56889, 40.511107)).thenReturn("urb13");
		when(residentialDriver.getNearbyResidential("urb13", POSITION_UP)).thenReturn("urb8");
		when(residentialDriver.getNearbyResidential("urb13", POSITION_DOWN)).thenReturn("urb18");
		when(residentialDriver.getNearbyResidential("urb13", POSITION_LEFT)).thenReturn("urb12");
		when(residentialDriver.getNearbyResidential("urb13", POSITION_RIGHT)).thenReturn("urb14");
		when(residentialDriver.getNearbyResidential("urb14", POSITION_UP)).thenReturn("urb9");
		when(residentialDriver.getNearbyResidential("urb14", POSITION_DOWN)).thenReturn("urb19");
		when(residentialDriver.getNearbyResidential("urb12", POSITION_UP)).thenReturn("urb7");
		when(residentialDriver.getNearbyResidential("urb12", POSITION_DOWN)).thenReturn("urb17");
		when(residentialDriver.getNearbyResidential("urb8", POSITION_LEFT)).thenReturn("urb7");
		when(residentialDriver.getNearbyResidential("urb8", POSITION_RIGHT)).thenReturn("urb9");
		when(residentialDriver.getNearbyResidential("urb18", POSITION_LEFT)).thenReturn("urb17");
		when(residentialDriver.getNearbyResidential("urb18", POSITION_RIGHT)).thenReturn("urb19");

		when(residentialDriver.getNearbyResidential("urb8", POSITION_UP)).thenReturn("urb3");
		when(residentialDriver.getNearbyResidential("urb18", POSITION_DOWN)).thenReturn("urb23");
		when(residentialDriver.getNearbyResidential("urb12", POSITION_LEFT)).thenReturn("urb11");
		when(residentialDriver.getNearbyResidential("urb14", POSITION_RIGHT)).thenReturn("urb15");
		
		when(residentialDriver.getNearbyResidential("urb15", POSITION_UP)).thenReturn("urb10");
		when(residentialDriver.getNearbyResidential("urb10", POSITION_UP)).thenReturn("urb5");
		when(residentialDriver.getNearbyResidential("urb15", POSITION_DOWN)).thenReturn("urb20");
		when(residentialDriver.getNearbyResidential("urb20", POSITION_DOWN)).thenReturn("urb25");
		
		when(residentialDriver.getNearbyResidential("urb11", POSITION_UP)).thenReturn("urb6");
		when(residentialDriver.getNearbyResidential("urb6", POSITION_UP)).thenReturn("urb1");
		when(residentialDriver.getNearbyResidential("urb11", POSITION_DOWN)).thenReturn("urb16");
		when(residentialDriver.getNearbyResidential("urb16", POSITION_DOWN)).thenReturn("urb21");
		
		when(residentialDriver.getNearbyResidential("urb3", POSITION_LEFT)).thenReturn("urb2");
		when(residentialDriver.getNearbyResidential("urb2", POSITION_LEFT)).thenReturn("urb1");
		when(residentialDriver.getNearbyResidential("urb3", POSITION_RIGHT)).thenReturn("urb4");
		when(residentialDriver.getNearbyResidential("urb4", POSITION_RIGHT)).thenReturn("urb5");
		
		when(residentialDriver.getNearbyResidential("urb23", POSITION_LEFT)).thenReturn("urb22");
		when(residentialDriver.getNearbyResidential("urb22", POSITION_LEFT)).thenReturn("urb21");
		when(residentialDriver.getNearbyResidential("urb23", POSITION_RIGHT)).thenReturn("urb24");
		when(residentialDriver.getNearbyResidential("urb24", POSITION_RIGHT)).thenReturn("urb25");
	}
	
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public ResidentialService residentialService() {
            return new ResidentialServiceImpl();
        }
    }
 
}
