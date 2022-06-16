package com.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.MaintenanceService;
import com.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.rentACar.business.responses.maintenances.GetAllMaintenancesResponse;
import com.rentACar.business.responses.maintenances.GetMaintenanceResponse;
import com.rentACar.core.utilities.exceptions.BusinessException;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.CarRepository;
import com.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.rentACar.entities.concretes.Car;
import com.rentACar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService{

	private MaintenanceRepository maintenanceRepository;
	private CarRepository carRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public MaintenanceManager(MaintenanceRepository maintenanceRepository, CarRepository carRepository
			,ModelMapperService modelMapperService) {
		super();
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {	
		
		checkCarState(createMaintenanceRequest.getCarId());
		checkDates(createMaintenanceRequest.getDateSent(),createMaintenanceRequest.getDateReturn());
		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		
		Car car = this.carRepository.findById(createMaintenanceRequest.getCarId());
		//car.setId(createMaintenanceRequest.getCarId());
		car.setState(2);
		
		maintenance.setCar(car);
		
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.ADDED");
	}

	@Override
	public Result delete(int id) {
		
		this.maintenanceRepository.deleteById(id);
		return new SuccessResult("MAINTENANCE.DELETED");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		 
		checkDates(updateMaintenanceRequest.getDateSent(),updateMaintenanceRequest.getDateReturn());
		Maintenance maintenance = this.modelMapperService.forRequest().map(updateMaintenanceRequest, Maintenance.class);
		
		Car car = this.carRepository.findById(updateMaintenanceRequest.getCarId());
		car.setId(updateMaintenanceRequest.getCarId());
		LocalDate localDate = LocalDate.now();
		if (localDate.equals(updateMaintenanceRequest.getDateReturn())) {
			car.setState(1);
		}
			
		maintenance.setCar(car);
		
		this.maintenanceRepository.save(maintenance);
		return new SuccessResult("MAINTENANCE.UPDATED");		
	}
	
	@Override
	public DataResult<List<GetAllMaintenancesResponse>> getAll() {
		
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<GetAllMaintenancesResponse> response =
				maintenances.stream().map(maintenance -> this.modelMapperService.forResponse()
						.map(maintenance, GetAllMaintenancesResponse.class)).collect(Collectors.toList());
		    return new SuccessDataResult<List<GetAllMaintenancesResponse>>(response,"MAINTENANCES.LISTED");
    }

	@Override
	public DataResult<GetMaintenanceResponse> getById(int id) {
		Maintenance maintenance = this.maintenanceRepository.findById(id);
		GetMaintenanceResponse response = this.modelMapperService.forResponse().map(maintenance, GetMaintenanceResponse.class);
		return new SuccessDataResult<GetMaintenanceResponse>(response,"MAINTENANCE.LISTED");
	}
	
	private void checkCarState(int id) {
		Car car = this.carRepository.findById(id);
		if (car.getState() != 1) {
			throw new BusinessException("MAINTENANCES.CAR.STATES.INCORRECT");
		}
		
	}
	
    private void checkDates(LocalDate dateSent, LocalDate dateReturn) {
		
		if(!(dateSent.isBefore(dateReturn))) {
			throw new BusinessException("MAINTENANCE.DATE.INCORRECT");
		}
	
	}

}
