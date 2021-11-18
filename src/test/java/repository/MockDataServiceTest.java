package repository;

import demo.getting_started.repository.MockDataService;
import demo.getting_started.repository.MockDataServiceImpl;
import demo.getting_started.tutorial.Car;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MockDataServiceTest {

    MockDataService mockDataService = new MockDataServiceImpl();

    @Test
    public void shouldGetMockData() {
        List<Car> carList = mockDataService.getMockData();
        assertTrue(carList.size() != 0);
    }

    @Test
    public void shouldAddCarToList() {
        List<Car> carList = mockDataService.getMockData();
        Car car = new Car();
        car.setColour("testColour");
        car.setPreview("testPreview");
        car.setDescription("testDescription");
        car.setPrice(123);
        car.setMake("testMake");
        car.setModel("testModel");
        mockDataService.addCar(car);
        assertTrue(carList.contains(car));
    }

    @Test
    public void shouldRemoveCarFromList() {
        List<Car> carList = mockDataService.getMockData();
        Car car = carList.stream().filter(x -> x.getModel().equals("Primera")).findFirst().get();
        mockDataService.removeCar(car);
        assertFalse(carList.contains(car));
    }

}
