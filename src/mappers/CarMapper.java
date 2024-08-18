package mappers;

import dto.CarDto;
import model.Car;

public class CarMapper {
    public CarDto toDTO(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setMake(car.getMake());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setCondition(car.getCondition());
        dto.setPrice(car.getPrice());
        return dto;
    }

    public Car toEntity(CarDto dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setMake(dto.getMake());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setCondition(dto.getCondition());
        car.setPrice(dto.getPrice());
        return car;
    }
}
