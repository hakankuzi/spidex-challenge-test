package demo.getting_started.repository;

import demo.getting_started.tutorial.Car;

import java.util.List;

public interface MockDataService {

    List<Car> getMockData();

    void addCar(Car car);

    void removeCar(Car car);
}
