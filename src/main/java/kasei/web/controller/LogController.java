package kasei.web.controller;

import kasei.springcloud.webflux.repository.entity.Log;
import kasei.springcloud.webflux.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/Log")
public class LogController {


    @Autowired
    LogService logService;




    @GetMapping("")
    public Mono<Log> getLog() {
        Log log = new Log();
        log.setTags(Stream.of("bpm", "fd").collect(Collectors.toList()));
        log.setMsg("nice");
        return Mono.just(log);
    }


    @PostMapping("")
    public Flux<Log> createLog(@RequestBody Log log){

        System.out.println(log.getMsg());

        List<Log> logs = new ArrayList<>();
        logs.add(log);
        List<Log> inserts = logService.insert(logs);


        Flux<Log> logFlux = Flux.fromIterable(inserts);
        return logFlux;
    }

}
