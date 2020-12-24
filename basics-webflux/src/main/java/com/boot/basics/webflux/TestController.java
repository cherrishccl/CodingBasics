package com.boot.basics.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Author cherrishccl
 * @Date 2020/12/8 13:53
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/mono")
    public Mono<Object> mono() {
        return Mono.create(monoSink -> {
            System.out.println("创建 Mono");
            monoSink.success("hello webflux");
        }).doOnSubscribe(subscription -> {
             System.out.println(subscription);
        }).doOnNext(o -> {
            System.out.println(o);
        });
    }

    @GetMapping("flux")
    public Flux<String> flux() {
        return Flux.just("hello","webflux","spring","boot");
    }

    @GetMapping("/pub")
    public Flux<String> pub(){
        Flux<String> result = Flux
                .fromStream(IntStream.range(1, 5).mapToObj(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    return "flux data--" + i+"\n";
                }));
        return result;
    }
}
