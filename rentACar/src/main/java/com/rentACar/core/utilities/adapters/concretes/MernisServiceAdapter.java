package com.rentACar.core.utilities.adapters.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.rentACar.core.utilities.adapters.abstracts.UserCheckService;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;
@Service
public class MernisServiceAdapter implements UserCheckService{

	@Override
	public boolean checkIfUserRealPerson(Long tcNo, String firstName, String lastName, int dateOfBirth)
			throws RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
		boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula
				(tcNo, firstName, lastName, dateOfBirth);
			return result;
	}

}
