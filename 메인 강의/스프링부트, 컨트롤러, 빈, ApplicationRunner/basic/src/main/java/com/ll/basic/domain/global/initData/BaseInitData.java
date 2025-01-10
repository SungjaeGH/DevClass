package com.ll.basic.domain.global.initData;

import com.ll.basic.domain.wiseSaying.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final WiseSayingService wiseSayingService;

    @Bean
    public ApplicationRunner BaseInitDataApplicationRunner() {
        return args -> {
            wiseSayingService.write("초기 내용 1", "초기 작기1");
            wiseSayingService.write("초기 내용 2", "초기 작기2");
            wiseSayingService.write("초기 내용 3", "초기 작기3");
        };
    }

    @Bean
    public List<Integer> ages() {
        return List.of(10, 20, 30, 40, 50);
    }
}
