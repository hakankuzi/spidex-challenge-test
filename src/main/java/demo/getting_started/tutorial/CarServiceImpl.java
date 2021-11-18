package demo.getting_started.tutorial;

import demo.getting_started.repository.MockDataService;
import demo.getting_started.repository.MockDataServiceImpl;

import java.util.LinkedList;
import java.util.List;

public class CarServiceImpl implements CarService {

    MockDataService mockDataService = new MockDataServiceImpl();
    List<Car> carList;

    public CarServiceImpl() {
    }

    public List<Car> findAll() {
        carList = mockDataService.getMockData();
        return carList;
    }

    public List<Car> search(String keyword) {
        List<Car> result = new LinkedList<Car>();
        if (keyword == null || "".equals(keyword)) {
            result = carList;
        } else {
            for (Car c : carList) {
                if (c.getModel().toLowerCase().contains(keyword.toLowerCase())
                        || c.getMake().toLowerCase().contains(keyword.toLowerCase())
                        || c.getColour().toLowerCase().contains(keyword.toLowerCase())) {
                    result.add(c);
                }
            }
        }
        return result;
    }

}
