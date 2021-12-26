package library.backend.readingRoom.NationalLibrary.controller;


import library.backend.readingRoom.NationalLibrary.domain.NatLibBookDto;
import library.backend.readingRoom.NationalLibrary.service.NatLibService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("reading-room/national-library")
@RestController
public class NatLibController {

    @Autowired
    NatLibService natLibService;


    @GetMapping
    public List<NatLibBookDto> getList(@RequestParam(required = false) String author,
                                       @RequestParam(required = false) String title) {

        return natLibService.getBooks(author, title);

    }
}
