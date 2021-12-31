package library.backend.library.controller;


import library.backend.library.domain.Copy;
import library.backend.library.domain.Status;
import library.backend.library.domain.dto.copyDto.CopyDto;
import library.backend.library.domain.dto.copyDto.CopyStatusChangeDto;
import library.backend.library.mapper.CopyMapper;
import library.backend.library.service.CopyService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("library/copies")
public class CopyController {

    CopyMapper copyMapper;
    CopyService copyService;

    public CopyController(CopyMapper copyMapper, CopyService copyService) {
        this.copyMapper = copyMapper;
        this.copyService = copyService;
    }

    @PostMapping
    public CopyDto create(@RequestBody CopyDto copyDto) {
        Copy copy = copyMapper.mapCopyDtoToCopy(copyDto);
        return copyMapper.mapCopyToCopyDto(copyService.save(copy));
    }

    @PutMapping
    public CopyDto update(@RequestBody CopyDto copyDto) {

        Copy copy = copyMapper.mapCopyDtoToCopy(copyDto);
        return copyMapper.mapCopyToCopyDto(copyService.update(copy));
    }

    @GetMapping("/{copyId}")
    public CopyDto getOne(@PathVariable long copyId) {

        Copy copy = copyService.get(copyId);
        return copyMapper.mapCopyToCopyDto(copy);
    }

    @GetMapping
    public Set<CopyDto> getAll() {
        Set<CopyDto> copyDtos = new HashSet<>();
        copyService.getAll().forEach(copy -> copyDtos.add(copyMapper.mapCopyToCopyDto(copy)));
        return copyDtos;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable int id) {
        copyService.delete(id);
    }


    @PatchMapping("/{id}/lost")
    public CopyStatusChangeDto setLostStatus(@PathVariable long id) {

        return copyService.setStatus(id, Status.LOST);
    }

    @PatchMapping("/{id}/destroyed")
    public CopyStatusChangeDto setDestroyedStatus(@PathVariable long id) {
        return copyService.setStatus(id, Status.DESTROYED);
    }


}
