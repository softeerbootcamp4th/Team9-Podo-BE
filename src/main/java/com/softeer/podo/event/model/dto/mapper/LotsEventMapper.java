package com.softeer.podo.event.model.dto.mapper;

import com.softeer.podo.event.model.dto.LotsApplicationResponseDto;
import com.softeer.podo.event.model.dto.SenarioDto;
import com.softeer.podo.event.model.entity.TestResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LotsEventMapper {

	public LotsApplicationResponseDto TestResultToApplicationDto (TestResult testResult) {
		LotsApplicationResponseDto dto = new LotsApplicationResponseDto();
		dto.setResult(testResult.getResult());
		dto.setDescription(testResult.getDescription());
		dto.setType(testResult.getType());

		ArrayList<SenarioDto> senarioArrayList =  new ArrayList<>();
		senarioArrayList.add( new SenarioDto(testResult.getSenario1(), testResult.getSubtitle1(), testResult.getImage1()));
		senarioArrayList.add( new SenarioDto(testResult.getSenario2(), testResult.getSubtitle2(), testResult.getImage2()));
		senarioArrayList.add( new SenarioDto(testResult.getSenario3(), testResult.getSubtitle3(), testResult.getImage3()));
		dto.setSenarioList(senarioArrayList);
		return dto;
	}
}
