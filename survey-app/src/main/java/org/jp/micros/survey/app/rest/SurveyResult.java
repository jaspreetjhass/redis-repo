package org.jp.micros.survey.app.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jp.micros.survey.app.constant.AppConstant;
import org.jp.micros.survey.app.constant.StreamChoice;
import org.jp.micros.survey.app.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("survey-app")
public class SurveyResult {

	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, String> redisTemplate;

	private final Function<String, String> function = (s) -> {
		String streamChoice = null;
		if (s.contains(StreamChoice.NON_MEDICAL.name()))
			streamChoice = StreamChoice.NON_MEDICAL.name();
		else if (s.contains(StreamChoice.MEDICAL.name()))
			streamChoice = StreamChoice.MEDICAL.name();
		else if (s.contains(StreamChoice.COMMERCE.name()))
			streamChoice = StreamChoice.COMMERCE.name();
		else if (s.contains(StreamChoice.ARTS.name()))
			streamChoice = StreamChoice.ARTS.name();
		return streamChoice;
	};

	@GetMapping("survey")
	public List<Survey> showResult() {
		List<Survey> surveyList = new ArrayList<>();
		final Map<Object, Object> surveyMap = redisTemplate.opsForHash().entries(AppConstant.VOTING);
		if (!CollectionUtils.isEmpty(surveyMap)) {
			final long totalVote = Long.valueOf(surveyMap.get(AppConstant.TOTAL).toString());
			final List<Object> streamChoiceList = surveyMap.keySet().parallelStream()
					.filter(key -> !key.equals(AppConstant.TOTAL)).collect(Collectors.toList());
			log.info("survey result is displayed");
			surveyList = streamChoiceList.parallelStream().map(surveyKey -> {
				return Survey.builder().stream(function.apply(surveyKey.toString()))
						.percentage(Double.valueOf(surveyMap.get(surveyKey).toString()) / totalVote * 100).build();
			}).collect(Collectors.toList());
		}
		return surveyList;
	}

}
