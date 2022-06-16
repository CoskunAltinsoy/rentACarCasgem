package com.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.RentalService;
import com.rentACar.business.requests.rentals.CreateRentalRequest;
import com.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.rentACar.business.responses.rentals.GetAllRentalsResponse;
import com.rentACar.business.responses.rentals.GetRentalResponse;
import com.rentACar.core.utilities.exceptions.BusinessException;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.CarRepository;
import com.rentACar.dataAccess.abstracts.CityRepository;
import com.rentACar.dataAccess.abstracts.RentalRepository;
import com.rentACar.entities.concretes.Car;
import com.rentACar.entities.concretes.City;
import com.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{


	private RentalRepository rentalRepository;	
	private ModelMapperService modelMapperService;
	private CarRepository carRepository;
	private CityRepository cityRepository;
	
	@Autowired
	public RentalManager(RentalRepository rentalRepository, ModelMapperService modelMapperService,
			CarRepository carRepository, CityRepository cityRepository) {
		super();
		this.rentalRepository = rentalRepository;
		this.modelMapperService = modelMapperService;
		this.carRepository = carRepository;
		this.cityRepository = cityRepository;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		
		City pickupCityId = this.cityRepository.findById(createRentalRequest.getPickupCityId());
		City returnCityId = this.cityRepository.findById(createRentalRequest.getReturnCityId());
		
		checkCarState(createRentalRequest.getCarId()) ;
		checkDates(createRentalRequest.getReturnedDate(), createRentalRequest.getPickupDate());	
		
		Car car = this.carRepository.findById(createRentalRequest.getCarId());
	    //car.setId(createRentalRequest.getCarId());
		car.setState(3);
		
		rental.setPickupCityId(pickupCityId);
		rental.setReturnCityId(returnCityId);
		 
		int totalDays = (rental.getReturnedDate().getDayOfYear() - rental.getPickupDate().getDayOfYear());
	    rental.setTotalDays(totalDays);
		rental.setTotalPrice((totalDays * car.getDailyPrice()));	
				
		if (!(rental.getPickupCityId().equals(rental.getReturnCityId()))) {
			rental.setTotalPrice(rental.getTotalPrice() + 750);
			}
		
		car.setCity(returnCityId);
		 
		rental.setCar(car);
				
		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.ADDED");
		}

	@Override
	public Result delete(int id) {
		
		this.rentalRepository.deleteById(id);
		return new SuccessResult("RENTALL.DELETED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		checkDates(updateRentalRequest.getReturnedDate(), updateRentalRequest.getPickupDate());
		Car car = this.carRepository.findById(updateRentalRequest.getCarId());
		
		LocalDate localDate = LocalDate.now();
		if (localDate.equals(rental.getReturnedDate())) {
			car.setState(1);
			}
		
		//if ((rental.getPickupCityId().equals(rental.getReturnCityId()))) {
		  //  rental.setTotalPrice(rental.getTotalPrice() - 750);
			//}
		 
		int totalDays = (rental.getReturnedDate().getDayOfYear() - rental.getPickupDate().getDayOfYear());
		rental.setTotalDays(totalDays);
	    rental.setTotalPrice((totalDays * car.getDailyPrice()));					
				
	   
		rental.setCar(car);
			
		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED");
			
	}

	@Override
	public DataResult<List<GetAllRentalsResponse>> getAll() {
		List<Rental> rentals = this.rentalRepository.findAll();
		List<GetAllRentalsResponse> response =
				rentals.stream().map(rental -> this.modelMapperService.forResponse()
						.map(rental, GetAllRentalsResponse.class)).collect(Collectors.toList());
		    return new SuccessDataResult<List<GetAllRentalsResponse>>(response,"RENTALS.LISTED");
	}

	@Override
	public DataResult<GetRentalResponse> getById(int id) {
		Rental rental = this.rentalRepository.findById(id);
		GetRentalResponse response = this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
		return new SuccessDataResult<GetRentalResponse>(response,"RENTAL.LISTED");
	}
	
	private void checkCarState(int id) {
		Car car = this.carRepository.findById(id);
		if (!(car.getState() == 1)) {
			throw new BusinessException("RENTALSTATE.INCORRECT");
		}
		
	}
	
	private void checkDates(LocalDate returnedDate, LocalDate pickupDate) {
		
		if(!(pickupDate.isBefore(returnedDate))) {
			throw new BusinessException("RENTALDATE.INCORRECT");
		}
	
	}
}
