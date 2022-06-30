package com.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.OrderedAdditionalServiceService;
import com.rentACar.business.requests.orderedAdditionalServices.CreateOrderedAdditionalServiceRequest;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.AdditionalServiceItemRepository;
import com.rentACar.dataAccess.abstracts.OrderedAdditionalServiceRepository;
import com.rentACar.dataAccess.abstracts.RentalRepository;
import com.rentACar.entities.concretes.OrderedAdditionalService;
import com.rentACar.entities.concretes.AdditionalServiceItem;
import com.rentACar.entities.concretes.Rental;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService{

	private OrderedAdditionalServiceRepository orderedAdditionalServiceRepository;
    private ModelMapperService modelMapperService;
    private RentalRepository rentalRepository;
    private AdditionalServiceItemRepository additionalServiceItemRepository;
   
    
    @Autowired
	public OrderedAdditionalServiceManager(OrderedAdditionalServiceRepository orderedAdditionalServiceRepository,
			ModelMapperService modelMapperService,
			RentalRepository rentalRepository, AdditionalServiceItemRepository additionalServiceItemRepository){
		super();
		this.orderedAdditionalServiceRepository = orderedAdditionalServiceRepository;
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository;
		this.additionalServiceItemRepository = additionalServiceItemRepository;
	
	}

	@Override
	public Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) {
		
		OrderedAdditionalService additionalService = this.modelMapperService.forRequest().map(createOrderedAdditionalServiceRequest, OrderedAdditionalService.class);	
		AdditionalServiceItem additionalServiceItem = this.additionalServiceItemRepository.findById(createOrderedAdditionalServiceRequest.getAdditionalServiceItemId());	
		Rental rental = this.rentalRepository.findById(createOrderedAdditionalServiceRequest.getRentalId());
		additionalService.setTotalDay(rental.getTotalDays());
        additionalService.setTotalPrice(additionalServiceItem.getPrice()*rental.getTotalDays());	
		
		this.orderedAdditionalServiceRepository.save(additionalService);
		return new SuccessResult("ORDEREDADDITIONALSERVICE.ADDED");
	
	}

	@Override
	public Result delete(int id) {
		
		this.orderedAdditionalServiceRepository.deleteById(id);				
		return new SuccessResult("ORDEREDADDITIONALSERVICE.DELETED");
	}

}
