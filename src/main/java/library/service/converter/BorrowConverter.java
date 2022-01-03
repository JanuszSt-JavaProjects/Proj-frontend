package library.service.converter;

import library.dto.BorrowDto;
import library.dto.ConvertedBorrowDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowConverter {


    public BorrowDto convertToBorrowDto(ConvertedBorrowDto convertedBorrowDto) {
        BorrowDto borrowDto = new BorrowDto();

        borrowDto.setId(convertedBorrowDto.getId());
        borrowDto.setBookId(convertedBorrowDto.getBookId());
        borrowDto.setCopyId(convertedBorrowDto.getCopyId());
        borrowDto.setClientId(convertedBorrowDto.getClientId());
        borrowDto.setBorrowDate(LocalDate.parse(convertedBorrowDto.getBorrowDate()));

        try {
            borrowDto.setReturnDate(LocalDate.parse(convertedBorrowDto.getReturnDate()));

        } catch (Exception e) {
            borrowDto.setReturnDate(null);
        }

        return borrowDto;
    }

    public ConvertedBorrowDto convertToConvertedBDto(BorrowDto borrowDto) {
        ConvertedBorrowDto convertedBorrowDto = new ConvertedBorrowDto();

        convertedBorrowDto.setId((int) borrowDto.getId());
        convertedBorrowDto.setBookId((int) borrowDto.getBookId());
        convertedBorrowDto.setCopyId((int) borrowDto.getCopyId());
        convertedBorrowDto.setClientId((int) borrowDto.getClientId());
        convertedBorrowDto.setBorrowDate(borrowDto.getBorrowDate().toString());

        String date = borrowDto.getReturnDate() == null ? null : borrowDto.getReturnDate().toString();

        convertedBorrowDto.setReturnDate(
                date
        );
        return convertedBorrowDto;

    }


}
