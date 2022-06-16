package com.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.AdditionalServiceService;
import com.rentACar.business.requests.additionalServices.CreateAdditionalServiceRequest;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.AdditionalServiceItemRepository;
import com.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.rentACar.dataAccess.abstracts.RentalRepository;
import com.rentACar.entities.concretes.AdditionalService;
import com.rentACar.entities.concretes.AdditionalServiceItem;
import com.rentACar.entities.concretes.Rental;

@Service
public class AdditionalServiceManager implements AdditionalServiceService{

	private AdditionalServiceRepository additionalServiceRepository;
    private ModelMapperService modelMapperService;
    private RentalRepository rentalRepository;
    private AdditionalServiceItemRepository additionalServiceItemRepository;
   
    
    @Autowired
	public AdditionalServiceManager(AdditionalServiceRepository additionalServiceRepository,
			ModelMapperService modelMapperService,
			RentalRepository rentalRepository, AdditionalServiceItemRepository additionalServiceItemRepository){
		super();
		this.additionalServiceRepository = additionalServiceRepository;
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository;
		this.additionalServiceItemRepository = additionalServiceItemRepository;
	
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		
        AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		
		Rental rental = this.rentalRepository.findById(createAdditionalServiceRequest.getRentalId());

		AdditionalServiceItem additionalServiceItem = this.additionalServiceItemRepository.findById(createAdditionalServiceRequest.getAdditionalServiceItemId());
		
		additionalService.setTotalDay(rental.getTotalDays());
		additionalService.setTotalPrice(additionalServiceItem.getPrice() * additionalService.getTotalDay());
		
		rental.setTotalPrice(additionalService.getTotalPrice() + rental.getTotalPrice());
		
		this.additionalServiceRepository.save(additionalService);
		return new SuccessResult("ADDITIONALSERVICE.ADDED");
	
	}

	@Override
	public Result delete(int id) {
		
		this.additionalServiceRepository.deleteById(id);		
		AdditionalService additionalService = this.additionalServiceRepository.findById(id);
		Rental rental = this.rentalRepository.findById(id);
		rental.setTotalPrice(rental.getTotalPrice() - additionalService.getTotalPrice());
		return new SuccessResult("ADDITIONALSERVICE.DELETED");
	}

}
