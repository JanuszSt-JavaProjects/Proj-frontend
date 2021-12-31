package library.backend.library.mapper;

import library.backend.library.domain.Copy;
import library.backend.library.domain.dto.copyDto.CopyDto;
import library.backend.library.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class CopyMapper {

    BookRepository bookRepository;

    public CopyMapper(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Copy mapCopyDtoToCopy(CopyDto copyDto) {
        Copy copy = new Copy();

        copy.setId((int) copyDto.getId());
        copy.setSignature(copyDto.getSignature());
        copy.setStatus(copyDto.getStatus());
        return copy;
    }

    public CopyDto mapCopyToCopyDto(Copy copy) {

        CopyDto copyDto = new CopyDto();
        copyDto.setId(copy.getId());
        copyDto.setStatus(copy.getStatus());
        copyDto.setSignature(copy.getSignature());

        return copyDto;
    }
}
