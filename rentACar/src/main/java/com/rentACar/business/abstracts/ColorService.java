package com.rentACar.business.abstracts;

import java.util.List;

import com.rentACar.business.requests.colors.CreateColorRequest;
import com.rentACar.business.requests.colors.UpdateColorRequest;
import com.rentACar.business.responses.colors.GetAllColorsResponse;
import com.rentACar.business.responses.colors.GetColorResponse;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;

public interface ColorService {

    Result add(CreateColorRequest createColorRequest);
	Result delete(int id);
	Result update(UpdateColorRequest updateColorRequest);
	DataResult<List<GetAllColorsResponse>> getAll();
	DataResult<GetColorResponse> getById(int id);
}
