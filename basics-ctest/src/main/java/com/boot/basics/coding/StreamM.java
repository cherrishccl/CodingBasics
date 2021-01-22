package com.boot.basics.coding;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author chencl
 * @Date 2021/1/21 16:01
 * @Version 1.0
 * @Description StreamM
 */
@Slf4j
public class StreamM {

    public static void main(String[] args) {
        List<AceDTO> list = new ArrayList<>(10);
        for(int i = 0; i < 10; i++){
            list.add(new AceDTO("name" + i));
        }
        log.info("{}", list);
        AtomicInteger ai = new AtomicInteger(100);
        list.parallelStream().forEach(aceDTO -> {
            aceDTO.setName("" + ai.getAndIncrement());
        });
        log.info("{}", list);
    }

    @lombok.Data
    static class AceDTO{
        private String name;

        public AceDTO(String name) {
            this.name = name;
        }
    }
}
