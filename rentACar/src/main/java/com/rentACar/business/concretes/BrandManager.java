package com.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.BrandService;
import com.rentACar.business.requests.brands.CreateBrandRequest;
import com.rentACar.business.requests.brands.UpdateBrandRequest;
import com.rentACar.business.responses.brands.GetAllBrandsResponse;
import com.rentACar.business.responses.brands.GetBrandResponse;
import com.rentACar.core.utilities.exceptions.BusinessException;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.BrandRepository;
import com.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {
		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		
		checkIfBrandExistByName(createBrandRequest.getName());
		//Mapping
		Brand brand =this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);	
		
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND ADDED"); 
	}

	@Override
	public Result delete(int id) {
		this.brandRepository.deleteById(id);
		return new SuccessResult("BRAND.DELETED");
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {

		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.UPDATED");
	}
	
	@Override
	public DataResult<List<GetAllBrandsResponse>> getAll() {
		
		List<Brand> brands = this.brandRepository.findAll();
		List<GetAllBrandsResponse> response =
				brands.stream().map(brand->this.modelMapperService.forResponse()
						.map(brand,GetAllBrandsResponse.class)).collect(Collectors.toList());
		    return new SuccessDataResult<List<GetAllBrandsResponse>>(response,"BRANDS.LISTED");
	}

	@Override
	public DataResult<GetBrandResponse> getById(int id) {
		Brand brand = this.brandRepository.findById(id);
	    GetBrandResponse response =	this.modelMapperService.forResponse().map(brand, GetBrandResponse.class);
		return new SuccessDataResult<GetBrandResponse>(response,"BRAND.LISTED");
	}
	
	private void checkIfBrandExistByName(String name) {
		Brand currentBrand = this.brandRepository.findByName(name);
		if (currentBrand != null) {
			throw new BusinessException("BRAND.EXISTS");
		}
	}
}
