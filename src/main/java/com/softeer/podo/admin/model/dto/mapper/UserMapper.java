package com.softeer.podo.admin.model.dto.mapper;


import com.softeer.podo.admin.model.dto.user.ArrivalUserDto;
import com.softeer.podo.admin.model.dto.user.ArrivalUserListDto;
import com.softeer.podo.admin.model.dto.user.LotsUserDto;
import com.softeer.podo.admin.model.dto.user.LotsUserListDto;
import com.softeer.podo.user.model.entity.ArrivalUser;
import com.softeer.podo.user.model.entity.LotsUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

	public ArrivalUserListDto ArrivalUserListToArrivalUserListDto(List<ArrivalUser> userList) {
		List<ArrivalUserDto> arrivalUserDtoList = new ArrayList<>();
		for(ArrivalUser user : userList) {
			//reward를 제외한 내용 추가
			arrivalUserDtoList.add(
					ArrivalUserDto.builder()
							.id(user.getId())
							.name(user.getName())
							.phoneNum(user.getPhoneNum())
							.rank(user.getArrivalRank())
							.createdAt(user.getCreatedAt())
							.build()
			);
		}
		return new ArrivalUserListDto(arrivalUserDtoList);
	}

	public LotsUserListDto LotsUserListToLotsUserListDto(List<LotsUser> userList) {
		List<LotsUserDto> lotsUserDtoList = new ArrayList<>();
		for(LotsUser user : userList) {
			//reward를 제외한 내용 추가
			lotsUserDtoList.add(
					LotsUserDto.builder()
							.id(user.getId())
							.name(user.getName())
							.phoneNum(user.getPhoneNum())
							.createdAt(user.getCreatedAt())
							.reward(user.getReward())
							.build()
			);
		}
		return new LotsUserListDto(lotsUserDtoList);
	}
}
