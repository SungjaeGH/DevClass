package com.ll.basic.domain.wiseSaying.wiseSaying.controller;

import com.ll.basic.domain.wiseSaying.wiseSaying.entity.WiseSaying;
import com.ll.basic.domain.wiseSaying.wiseSaying.service.WiseSayingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wiseSayings")
public class WiseSayingController {

    private final WiseSayingService wiseSayingService;

    @GetMapping("")
    public List<WiseSaying> getItems() {
        return wiseSayingService.findAll();
    }

    @GetMapping("/write")
    public WiseSaying write(
            // ? 뒤의 content 파라미터 값이 들어온다.
            @RequestParam(name = "content", defaultValue = "무명") String content,
            // ? 뒤의 author 파라미터 값이 들어온다.
            @RequestParam(name = "author", defaultValue = "몰라") String author
    ) {

        return wiseSayingService.write(content, author);
    }

    /* 첫번째 명언 정보 가져오기 방법 : 명언 정보를 가져올 때마다 계속 추가 해야함
    @GetMapping("/wiseSayings/1")
    public WiseSaying getItem1() {

        Optional<WiseSaying> opWiseSaying = wiseSayingService.findById(1L);

        return opWiseSaying.get();
    }

    @GetMapping("/wiseSayings/2")
    public WiseSaying getItem2() {

        Optional<WiseSaying> opWiseSaying = wiseSayingService.findById(2L);

        return opWiseSaying.get();
    }
     */

    @GetMapping("/{id}")
    public WiseSaying getItem(@PathVariable(name = "id") long id) {

        Optional<WiseSaying> opWiseSaying = wiseSayingService.findById(id);

        return opWiseSaying.get();
    }

    @GetMapping("/{id}/delete")
    public boolean deleteItem(@PathVariable(name = "id") long id) {

        return wiseSayingService.delete(id);
    }

    @GetMapping("/{id}/modify")
    public WiseSaying modifyItem(
            @PathVariable(name = "id") long id,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "author", defaultValue = "무명") String author) {

        WiseSaying wiseSaying = wiseSayingService.findById(id).get();

        wiseSayingService.modify(wiseSaying, content, author);

        return wiseSaying;
    }
}
