package com.softeer.podo.event.model.dto.mapper;

import com.softeer.podo.event.model.dto.LotsTypeResponseDto;
import com.softeer.podo.event.model.dto.ScenarioDto;
import com.softeer.podo.event.model.entity.TestResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LotsEventMapper {

	public LotsTypeResponseDto TestResultToApplicationDto (TestResult testResult) {
		LotsTypeResponseDto dto = new LotsTypeResponseDto();
        dto.setResultId(testResult.getId());
		dto.setResult(testResult.getResult());
		dto.setDescription(testResult.getDescription());
		dto.setType(testResult.getType());

		ArrayList<ScenarioDto> scenarioArrayList =  new ArrayList<>();
		scenarioArrayList.add( new ScenarioDto(testResult.getScenario1(), testResult.getSubtitle1(), testResult.getImage1()));
		scenarioArrayList.add( new ScenarioDto(testResult.getScenario2(), testResult.getSubtitle2(), testResult.getImage2()));
		scenarioArrayList.add( new ScenarioDto(testResult.getScenario3(), testResult.getSubtitle3(), testResult.getImage3()));
		dto.setScenarioList(scenarioArrayList);
		return dto;
	}
}
