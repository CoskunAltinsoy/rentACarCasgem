package com.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.AdditionalServiceItemService;
import com.rentACar.business.requests.additionalServiceItems.CreateAdditionalServiceItemRequest;
import com.rentACar.business.requests.additionalServiceItems.UpdateAdditionalServiceItemRequest;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.AdditionalServiceItemRepository;
import com.rentACar.entities.concretes.AdditionalServiceItem;

@Service
public class AdditionalServiceItemManager implements AdditionalServiceItemService{

	private AdditionalServiceItemRepository additionalServiceItemRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalServiceItemManager(AdditionalServiceItemRepository additionalServiceItemRepository,
			ModelMapperService modelMapperService) {
		super();
		this.additionalServiceItemRepository = additionalServiceItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		AdditionalServiceItem additionalServiceItem = this.modelMapperService.forRequest().map(createAdditionalServiceItemRequest, AdditionalServiceItem.class);
		this.additionalServiceItemRepository.save(additionalServiceItem);
		return new SuccessResult("ADDITIONALSERVICE.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.additionalServiceItemRepository.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		AdditionalServiceItem additionalServiceItem = this.modelMapperService.forRequest().map(updateAdditionalServiceItemRequest, AdditionalServiceItem.class);
		this.additionalServiceItemRepository.save(additionalServiceItem);
	    return new SuccessResult();
	}
}
