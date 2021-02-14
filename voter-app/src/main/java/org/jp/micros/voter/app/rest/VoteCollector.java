package org.jp.micros.voter.app.rest;

import java.util.Arrays;

import org.jp.micros.voter.app.constant.AppConstant;
import org.jp.micros.voter.app.constant.StreamChoice;
import org.jp.micros.voter.app.publisher.VotePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("voter-app")
public class VoteCollector {

	@Autowired
	private VotePublisher votePublisher;

	@ApiOperation(consumes = "text/plain", httpMethod = "POST", value = "collect vote for survey")
	@PostMapping("castVote")
	public String collectVote(
			@ApiParam(allowableValues = "MEDICAL,NON_MEDICAL,COMMERCE,ART") @RequestBody final String streamName) {
		if (Arrays.stream(StreamChoice.values()).anyMatch(choice -> choice.name().equals(streamName))) {
			votePublisher.castVote(streamName);
			return AppConstant.OK;
		}
		return AppConstant.INVALID_CHOICE;
	}

}
