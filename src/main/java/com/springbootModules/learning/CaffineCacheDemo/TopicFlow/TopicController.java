package com.springbootModules.learning.CaffineCacheDemo.TopicFlow;

import lombok.NonNull;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


@RestController
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping(value = "/api/Topic")
    public void addTopic(@RequestBody TopicRequest topic)throws HttpMessageNotReadableException{
         topicService.addTopic(topic);
    }

    @PutMapping(value = "/api/Topic/{id}")
    public void updateTopic(@RequestBody TopicRequest topic, @PathVariable Long id ) {
        if(!id.equals(topic.getId()))
            throw  new RuntimeException("Record Not Found");
         topicService.updateTopic(topic, id);
       }

    @DeleteMapping(value = "api/Topic/{id}")
    public void DeleteTopic(@NonNull@PathVariable Long id) {
        topicService.deleteTopic(id);
    }

    @GetMapping(value = "api/Topic/{id}")
    public TopicRequest getTopic(@PathVariable(required = true)  Long id) {
        return topicService.getTopic(id);
    }
    }
