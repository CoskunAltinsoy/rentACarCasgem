package com.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.RentalService;
import com.rentACar.business.requests.rentals.CreateRentalRequestForCorporateCustomer;
import com.rentACar.business.requests.rentals.CreateRentalRequestForIndividualCustomer;
import com.rentACar.business.requests.rentals.UpdateRentalRequestForCorporateCustomer;
import com.rentACar.business.requests.rentals.UpdateRentalRequestForIndividualCustomer;
import com.rentACar.business.responses.rentals.GetAllRentalsResponseForIndividualCustomer;
import com.rentACar.business.responses.rentals.GetRentalResponseForIndividualCustomer;
import com.rentACar.core.utilities.exceptions.BusinessException;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.CarRepository;
import com.rentACar.dataAccess.abstracts.CityRepository;
import com.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.rentACar.dataAccess.abstracts.RentalRepository;
import com.rentACar.entities.concretes.Car;
import com.rentACar.entities.concretes.City;
import com.rentACar.entities.concretes.CorporateCustomer;
import com.rentACar.entities.concretes.IndividualCustomer;
import com.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService{


	private RentalRepository rentalRepository;	
	private ModelMapperService modelMapperService;
	private CarRepository carRepository;
	//private UserCheckFindexScore userCheckFindexScore;
	private IndividualCustomerRepository individualCustomerRepository;
	private CorporateCustomerRepository corporateCustomerRepository;
	
	@Autowired
	public RentalManager(RentalRepository rentalRepository, ModelMapperService modelMapperService,
			CarRepository carRepository,/*UserCheckFindexScore userCheckFindexScore,*/ 
			IndividualCustomerRepository individualCustomerRepository, CorporateCustomerRepository corporateCustomerRepository) {
		super();
		this.rentalRepository = rentalRepository;
		this.modelMapperService = modelMapperService;
		this.carRepository = carRepository;
	//	this.userCheckFindexScore = userCheckFindexScore;
		this.individualCustomerRepository = individualCustomerRepository;
		this.corporateCustomerRepository = corporateCustomerRepository;
	}


	@Override
	public Result addForIndividualCustomer(CreateRentalRequestForIndividualCustomer createIndividualCustomerRentalRequest) {

		checkIfCarExist(createIndividualCustomerRentalRequest.getCarId());
		checkIfIndividualCustomerExist(createIndividualCustomerRentalRequest.getCustomerId());
		checkCarState(createIndividualCustomerRentalRequest.getCarId());
		checkDates(createIndividualCustomerRentalRequest.getReturnedDate(), createIndividualCustomerRentalRequest.getPickupDate());	
		Rental rental = this.modelMapperService.forRequest().map(createIndividualCustomerRentalRequest, Rental.class);		
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findById(createIndividualCustomerRentalRequest.getCustomerId());	
		Car car = this.carRepository.findById(createIndividualCustomerRentalRequest.getCarId());
		car.setState(3);
		car.setCity(rental.getReturnCityId());
		
		//checkFindexScore(car.getFindexScore(), individualCustomer.getNationalIdentity());	
        rental.setTotalDays(totalDay(createIndividualCustomerRentalRequest.getReturnedDate(), createIndividualCustomerRentalRequest.getPickupDate()));
		double totalPrice = calculateTotalPrice(createIndividualCustomerRentalRequest.getReturnedDate(), createIndividualCustomerRentalRequest.getPickupDate(),
				car.getDailyPrice(), createIndividualCustomerRentalRequest.getPickupCityId(),createIndividualCustomerRentalRequest.getReturnCityId());
		rental.setTotalPrice(totalPrice);	

		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.ADDED");
		}
	
	@Override
	public Result addForCorporateCustomer(CreateRentalRequestForCorporateCustomer createCorporateCustomerRentalRequest) {
		
		checkIfCarExist(createCorporateCustomerRentalRequest.getCarId());
		checkIfCorporateCustomerExist(createCorporateCustomerRentalRequest.getCustomerId());
		checkCarState(createCorporateCustomerRentalRequest.getCarId());
		checkDates(createCorporateCustomerRentalRequest.getReturnedDate(), createCorporateCustomerRentalRequest.getPickupDate());	
		Rental rental = this.modelMapperService.forRequest().map(createCorporateCustomerRentalRequest, Rental.class);
		Car car = this.carRepository.findById(createCorporateCustomerRentalRequest.getCarId());
		car.setState(3);
		car.setCity(rental.getReturnCityId());
		double totalPrice = calculateTotalPrice(createCorporateCustomerRentalRequest.getReturnedDate(), createCorporateCustomerRentalRequest.getPickupDate(),
				car.getDailyPrice(), createCorporateCustomerRentalRequest.getPickupCityId(),createCorporateCustomerRentalRequest.getReturnCityId());
		rental.setTotalPrice(totalPrice);
		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.ADDED");
	}

	

	@Override
	public Result delete(int id) {
		
		this.rentalRepository.deleteById(id);
		return new SuccessResult("RENTAL.DELETED");
	}

	@Override
	public Result updateForIndividualCustomer(UpdateRentalRequestForIndividualCustomer updateIndividualCustomerRentalRequest) {
		
		checkIfIndividualCustomerExist(updateIndividualCustomerRentalRequest.getCustomerId());
		checkDates(updateIndividualCustomerRentalRequest.getReturnedDate(), updateIndividualCustomerRentalRequest.getPickupDate());
		checkCarStateForUpdate(updateIndividualCustomerRentalRequest.getId());
		Rental rental = this.modelMapperService.forRequest().map(updateIndividualCustomerRentalRequest, Rental.class);
		Car car = this.carRepository.findById(updateIndividualCustomerRentalRequest.getCarId());
		car.setState(3); 
		rental.setTotalDays(totalDay(updateIndividualCustomerRentalRequest.getReturnedDate(), updateIndividualCustomerRentalRequest.getPickupDate()));
		double totalPrice = calculateTotalPrice(updateIndividualCustomerRentalRequest.getReturnedDate(), updateIndividualCustomerRentalRequest.getPickupDate(),
				car.getDailyPrice(), updateIndividualCustomerRentalRequest.getPickupCityId(),updateIndividualCustomerRentalRequest.getReturnCityId());
		rental.setTotalPrice(totalPrice);								
	    car.setCity(rental.getReturnCityId());
		rental.setCar(car);
			
		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED");		
	}
	
	@Override
	public Result updateForCorporateCustomer(UpdateRentalRequestForCorporateCustomer updateCorporateCustomerRentalRequest) {
		
	
		checkIfCorporateCustomerExist(updateCorporateCustomerRentalRequest.getCustomerId());
		checkDates(updateCorporateCustomerRentalRequest.getReturnedDate(), updateCorporateCustomerRentalRequest.getPickupDate());	
		checkCarStateForUpdate(updateCorporateCustomerRentalRequest.getId());
		Rental rental = this.modelMapperService.forRequest().map(updateCorporateCustomerRentalRequest, Rental.class);
		Car car = this.carRepository.findById(updateCorporateCustomerRentalRequest.getCarId());
		car.setState(3);
		car.setCity(rental.getReturnCityId());
		double totalPrice = calculateTotalPrice(updateCorporateCustomerRentalRequest.getReturnedDate(), updateCorporateCustomerRentalRequest.getPickupDate(),
				car.getDailyPrice(), updateCorporateCustomerRentalRequest.getPickupCityId(),updateCorporateCustomerRentalRequest.getReturnCityId());
		rental.setTotalPrice(totalPrice);
		this.rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED");
	}
		

//	@Override
//	public DataResult<List<GetAllIndividualCustomerRentalResponse>> getAll() {
//		List<Rental> rentals = this.rentalRepository.findAll();
//		List<GetAllIndividualCustomerRentalResponse> response =
//				rentals.stream().map(rental -> this.modelMapperService.forResponse()
//						.map(rental, GetAllIndividualCustomerRentalResponse.class)).collect(Collectors.toList());
//		    return new SuccessDataResult<List<GetAllIndividualCustomerRentalResponse>>(response);
//	}
//
//	@Override
//	public DataResult<GetIndividualCustomerRentalResponse> getById(int id) {
//		Rental rental = this.rentalRepository.findById(id);
//		GetIndividualCustomerRentalResponse response = this.modelMapperService.forResponse().map(rental, GetIndividualCustomerRentalResponse.class);
//		return new SuccessDataResult<GetIndividualCustomerRentalResponse>(response,"RENTAL.LISTED");
//	}
//	
	private void checkCarState(int id) {
		Car car = this.carRepository.findById(id);
		if (car.getState()!=1){
			throw new BusinessException("CAR.NOTAVAILABLE");
		}
		
	}
	
	private void checkCarStateForUpdate(int id) {
		Rental rental = this.rentalRepository.findById(id);
		Car car = rental.getCar();
	    car.setState(1);
		
	}
	
	private void checkDates(LocalDate returnedDate, LocalDate pickupDate) {
		
		if(!(pickupDate.isBefore(returnedDate))) {
			throw new BusinessException("RENTALDATE.INCORRECT");
		}
	
	}
	
//	private void checkFindexScore(int findexScore, String nationalIdentity) {
//		int calculatedFindexScore = this.userCheckFindexScore.checkUserFindexScore(nationalIdentity);
//		if (!(calculatedFindexScore > findexScore)) {
//			throw new BusinessException("LOWFINDEXSCORE");
//		}
//	}
	
	private int totalDay(LocalDate returnDate, LocalDate pickupDate) {
		return (int) ChronoUnit.DAYS.between(pickupDate, returnDate);
	}
	
	private double calculateTotalPrice(LocalDate returnDate, LocalDate pickupDate,double dailyPrice, int pickupId, int returnId) {
		if (pickupId == returnId) {
			double totalPrice = (totalDay(returnDate, pickupDate) * dailyPrice);
			return totalPrice;
			
		}else {
			double totalPrice = (totalDay(returnDate, pickupDate) * dailyPrice) +750;
			return totalPrice;
		}	
	}
	
	private void checkIfCarExist(int id) {
		Car car = this.carRepository.findById(id);
		if (car == null) {
			throw new BusinessException("CAR.DOESNOT.EXIST");
		}		
	}
	private void checkIfIndividualCustomerExist(int id) {
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findById(id);
		if (individualCustomer == null) {
			throw new BusinessException("INDIVIDUALCUSTOMER.DOESNOT.EXIST");
		}
	}
	private void checkIfCorporateCustomerExist(int id) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(id);
		if (corporateCustomer == null) {
			throw new BusinessException("CORPORATECUSTOMER.DOESNOT.EXIST");
		}
	}
	
}
