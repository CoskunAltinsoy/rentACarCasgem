package com.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentACar.business.abstracts.ColorService;
import com.rentACar.business.requests.colors.CreateColorRequest;
import com.rentACar.business.requests.colors.UpdateColorRequest;
import com.rentACar.business.responses.colors.GetAllColorsResponse;
import com.rentACar.business.responses.colors.GetColorResponse;
import com.rentACar.core.utilities.mapping.ModelMapperService;
import com.rentACar.core.utilities.results.DataResult;
import com.rentACar.core.utilities.results.Result;
import com.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.dataAccess.abstracts.ColorRepository;
import com.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {

	private ColorRepository colorRepository;
	private ModelMapperService modelMapperService;
	@Autowired
	public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService) {
		super();
		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);	
		this.colorRepository.save(color);		
		return new SuccessResult("COLOR.ADDED");
	}

	@Override
	public Result delete(int id) {
		this.colorRepository.deleteById(id);
		return new SuccessResult("COLOR.DELETED");
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATED");
	}

	@Override
	public DataResult<List<GetAllColorsResponse>> getAll() {
		List<Color> colors = this.colorRepository.findAll();
		List<GetAllColorsResponse> response =
				colors.stream().map(color -> this.modelMapperService.forResponse()
						.map(color,GetAllColorsResponse.class)).collect(Collectors.toList());
		    return new SuccessDataResult<List<GetAllColorsResponse>>(response,"COLORS.LISTED");
	}

	@Override
	public DataResult<GetColorResponse> getById(int id) {
		Color color = this.colorRepository.findById(id);
	    GetColorResponse response =	this.modelMapperService.forResponse().map(color, GetColorResponse.class);
		return new SuccessDataResult<GetColorResponse>(response,"COLOR.LISTED");
	}

}
