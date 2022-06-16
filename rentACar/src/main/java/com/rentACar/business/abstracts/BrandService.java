package com.rentACar.business.abstracts;

import java.util.List;

import com.rentACar.business.requests.brands.CreateBrandRequest;
import com.rentACar.business.requests.brands.UpdateBrandRequest;
import com.rentACar.business.responses.brands.GetAllBrandsResponse;
import com.rentACar.business.responses.brands.GetBrandResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;

public interface BrandService {

	Result add(CreateBrandRequest createBrandRequest);
	Result delete(int id);
	Result update(UpdateBrandRequest updateBrandRequest);
    DataResult<List<GetAllBrandsResponse>> getAll();
    DataResult<GetBrandResponse> getById(int id);
}
