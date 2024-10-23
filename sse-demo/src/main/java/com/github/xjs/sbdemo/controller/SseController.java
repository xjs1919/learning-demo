package com.github.xjs.sbdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class SseController {
	
	private static Logger log = LoggerFactory.getLogger(SseController.class);

	private Map<Long, SseEmitter> clientMap = new ConcurrentHashMap<>();

	private void removeClient(Long userId){
		log.info("user:{} removed", userId);
		clientMap.remove(userId);
	}

	@RequestMapping("/sse")
	public SseEmitter stream(@RequestParam("uid")Long uid)throws Exception{
		SseEmitter emitter = new SseEmitter(-1L);
		emitter.onTimeout(()->{
			removeClient(uid);
		});
		emitter.onCompletion(()->{
			removeClient(uid);
		});
		emitter.onError((error)->{
			removeClient(uid);
			log.error(error.getMessage());
		});
		log.info("user:{} connected", uid);
		clientMap.put(uid, emitter);
		return emitter;
	}

	@GetMapping("sendToAll")
	public void sendToAll() throws Exception{
		if(clientMap.size() <= 0){
			return;
		}
		for(Map.Entry<Long, SseEmitter> entry : clientMap.entrySet()){
			Long userId = entry.getKey();
			SseEmitter sseEmitter = entry.getValue();
			sseEmitter.send("userId:" + userId + ", group message");
		}
	}

	@GetMapping("sendToUser")
	public void sendToOne(@RequestParam("userId") Long userId) throws Exception{
		SseEmitter sseEmitter = clientMap.get(userId);
		if(sseEmitter == null){
			log.error("user:"+ userId + "不在线");
			return;
		}
		sseEmitter.send("userId:" + userId + ", p2p message");
	}

	@Scheduled(cron = "*/5 * * * * ?")
	public void heartbeat(){
		Set<Map.Entry<Long, SseEmitter>> entries = clientMap.entrySet();
		for(Iterator<Map.Entry<Long, SseEmitter>> iterator = entries.iterator(); iterator.hasNext();){
			Map.Entry<Long, SseEmitter> entry = iterator.next();
			try{
				entry.getValue().send(SseEmitter.event().comment("heart beat"));
			}catch(Exception e){
				log.error("心跳发送失败，删除：" + entry.getKey()+", message:" + e.getMessage());
				iterator.remove();
			}
		}
	}
}