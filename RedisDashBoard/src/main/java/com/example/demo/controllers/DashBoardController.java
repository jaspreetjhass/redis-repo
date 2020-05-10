package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.ApplicationConstant;
import com.example.demo.models.AdminToolObject;

@RestController
@RequestMapping("redis/dashBoard")
public class DashBoardController {

	@Autowired
	private HashOperations<String, String, String> hashOperations;
	private static final Logger LOGGER = LoggerFactory.getLogger(DashBoardController.class);

	@GetMapping("details")
	public List<AdminToolObject> fetchDashBoardDetails() {
		LOGGER.trace("Enter into fetchDashBoardDetails method");
		List<AdminToolObject> adminToolObjectList = new ArrayList<>();
		RedisCallback<List<AdminToolObject>> redisCallback = (redisConnection) -> {
			Cursor<byte[]> cursor = redisConnection.scan(ScanOptions.scanOptions().count(ApplicationConstant.TOTAL_KEYS)
					.match(ApplicationConstant.KEY_PATTERN).build());
			Set<String> keys = new HashSet<>();
			while (cursor.hasNext()) {
				keys.add(new String(cursor.next()));
			}

			adminToolObjectList.addAll(keys.parallelStream().map(key -> {
				Map<String, String> map = hashOperations.entries(key);
				AdminToolObject adminToolObject = AdminToolObject.builder()
						.rosettaStone(key.split(ApplicationConstant.COLON)[1])
						.requestType(String.valueOf(map.get(ApplicationConstant.REQUEST_TYPE)))
						.runningStatus(Boolean.valueOf(String.valueOf(map.get(ApplicationConstant.RUNNING_STATUS))))
						.stopTriggered(Boolean.valueOf(String.valueOf(map.get(ApplicationConstant.STOP_TRIGGERED))))
						.totalRecords(Long.valueOf(String.valueOf(map.get(ApplicationConstant.TOTAL_RECORDS))))
						.processed(Long.valueOf(String.valueOf(map.get(ApplicationConstant.PROCESSED))))
						.inProgress(Long.valueOf(String.valueOf(map.get(ApplicationConstant.IN_PROGRESS))))
						.notStarted(Long.valueOf(String.valueOf(map.get(ApplicationConstant.NOT_STARTED)))).build();
				return adminToolObject;
			}).collect(Collectors.toList()));

			return null;
		};
		hashOperations.getOperations().execute(redisCallback);
		LOGGER.trace("Exit from  fetchDashBoardDetails method with output : {} ", adminToolObjectList);
		return adminToolObjectList;
	}

	@GetMapping("incrCounter/{delta}/{rosettaStone}")
	public String increasedCounter(@PathVariable Long delta, @PathVariable String rosettaStone) {
		hashOperations.increment(ApplicationConstant.REDIS_KEY.concat(ApplicationConstant.COLON).concat(rosettaStone),
				"processed", delta);
		return "counter increased";
	}
}
