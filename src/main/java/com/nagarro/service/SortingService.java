package com.nagarro.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.entities.UserEntity;

@Service
public class SortingService {

	public void sortByAgeEven(List<UserEntity> sublist) {
		Comparator<UserEntity> ageComparator = Comparator.comparingInt(UserEntity::getAge);
		Comparator<UserEntity> oddEvenComparator = Comparator.comparingInt(user -> user.getAge() % 2);


		// Combine the comparators to sort by odd/even first and then by age
		Comparator<UserEntity> combinedComparator = oddEvenComparator.thenComparing(ageComparator);

		// Sort the sublist using the combined comparator
		Collections.sort(sublist, combinedComparator);
	}

	public void sortByAgeOdd(List<UserEntity> sublist) {

		Comparator<UserEntity> ageComparator = Comparator.comparingInt(UserEntity::getAge);
		Comparator<UserEntity> oddEvenComparator = Comparator.comparingInt(UserEntity -> {
			if (UserEntity.getAge() % 2 != 0) {
				return 0;
			} else {
				return 1;
			}

		});

		// Combine the comparators to sort by odd/even first and then by age
		Comparator<UserEntity> combinedComparator = oddEvenComparator.thenComparing(ageComparator);

		// Sort the sublist using the combined comparator
		Collections.sort(sublist, combinedComparator);
//		Collections.reverse(sublist);
	}

	public void sortByNameEven(List<UserEntity> sublist) {
		// Create a comparator for sorting by the length of the name
		Comparator<UserEntity> nameLengthComparator = Comparator.comparingInt(User -> User.getName().length());

		// Create a comparator for sorting by odd/even parity of name length
		Comparator<UserEntity> oddEvenNameComparator = Comparator.comparingInt(user -> user.getName().length() % 2);

		// Combine the comparators to sort by odd/even first and then by name length
		Comparator<UserEntity> combinedComparator = oddEvenNameComparator.thenComparing(nameLengthComparator);

		// Sort the sublist using the combined comparator
		Collections.sort(sublist, combinedComparator);
	}

	public void sortByNameOdd(List<UserEntity> sublist) {
		// Create a comparator for sorting by the length of the name
		Comparator<UserEntity> nameLengthComparator = Comparator.comparingInt(user -> user.getName().length());

		// Create a comparator for sorting by odd/even parity of name length
		Comparator<UserEntity> oddEvenNameComparator = Comparator.comparingInt(user -> user.getName().length() % 2);

		// Combine the comparators to sort by odd/even first and then by name length
		Comparator<UserEntity> combinedComparator = oddEvenNameComparator.thenComparing(nameLengthComparator);

		// Sort the sublist using the combined comparator
		Collections.sort(sublist, combinedComparator);
		Collections.reverse(sublist);
	}
}
