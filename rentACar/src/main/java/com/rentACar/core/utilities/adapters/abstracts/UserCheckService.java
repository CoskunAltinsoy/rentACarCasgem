package com.rentACar.core.utilities.adapters.abstracts;

import java.rmi.RemoteException;


public interface UserCheckService {
	boolean checkIfUserRealPerson(Long tcNo, String firstName, String lastName, int dateOfBirth) throws RemoteException;
}
