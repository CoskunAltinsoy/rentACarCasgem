package com.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.CarService;
import com.rentACar.business.requests.cars.CreateCarRequest;
import com.rentACar.business.requests.cars.UpdateCarRequest;
import com.rentACar.business.responses.cars.GetAllCarsResponse;
import com.rentACar.business.responses.cars.GetCarResponse;
import com.rentACar.core.utilities.exceptions.BusinessException;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.CarRepository;
import com.rentACar.dataAccess.abstracts.RentalRepository;
import com.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	private RentalRepository rentalRepository;

	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService
			,RentalRepository rentalRepository) {
		super();
		this.carRepository = carRepository;	
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		checkBrandCount(createCarRequest.getBrandId());
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);	
		car.setState(1);		
		carRepository.save(car);
    	return new SuccessResult("CAR.ADDED");			
	}

	@Override
	public Result delete(int id) {
		carRepository.deleteById(id);
		return new SuccessResult("CAR.DELETED");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		
        Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		
		carRepository.save(car);
		
		return new SuccessResult("CAR.UPDATED");
		
	}

	@Override
	public DataResult<List<GetAllCarsResponse>> getAll() {
		
		List<Car> cars = this.carRepository.findAll();
		List<GetAllCarsResponse> response =
				cars.stream().map(car -> this.modelMapperService.forResponse()
						.map(car, GetAllCarsResponse.class)).collect(Collectors.toList());
		    return new SuccessDataResult<List<GetAllCarsResponse>>(response,"CARS.LISTED");
	}

	@Override
	public DataResult<GetCarResponse> getById(int id) {
		Car car = this.carRepository.findById(id); 
		GetCarResponse response =  this.modelMapperService.forResponse().map(car, GetCarResponse.class);
		return new SuccessDataResult<GetCarResponse>(response,"CAR.LISTED");
	}
	
	
//	private void checkBrandCount(int id) {
//		int count = 0;
//		for (Car car :carRepository.findAll()) {
//			if(car.getBrand().getId() == id ) {
//				count++;
//			}
//		}
//		if (!(count <5)) {
//			throw new BusinessException("");
//		}
//		
//	}
	
	private void checkBrandCount(int id) {
		List<Car> results = this.carRepository.findByBrandId(id);
		if (results.size() > 5) {
			throw new BusinessException("BRAND.COUNT.CANNOT.BE.GREATER.THAN.FIVE");
		}
		
		
	}

}
