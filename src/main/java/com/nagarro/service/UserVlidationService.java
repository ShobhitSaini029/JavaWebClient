package com.nagarro.service;

import org.springframework.stereotype.Service;

import com.nagarro.apiData.GenderiseUserApiData;
import com.nagarro.apiData.NationalizeUserApiData;
import com.nagarro.entities.UserEntity;

@Service
public class UserVlidationService {
	
	public void addVerificationStatus(UserEntity userEntity, NationalizeUserApiData nationalizeUserApiData,
			GenderiseUserApiData genderiseUserApiData) {
		// Validate if nationality and gender match
		String userNationality = userEntity.getNationality();
		String userGender = userEntity.getGender();


		try {
			boolean isNationalityMatch = nationalizeUserApiData.getCountry().stream()
					.anyMatch(country -> country.getCountry_id().equalsIgnoreCase(userNationality));

			boolean isGenderMatch = genderiseUserApiData.getGender().equalsIgnoreCase(userGender);
			if (isNationalityMatch && isGenderMatch) {
				userEntity.setVerificationStatus("VERIFIED");
			} else {
				userEntity.setVerificationStatus("TO_BE_VERIFIED");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
