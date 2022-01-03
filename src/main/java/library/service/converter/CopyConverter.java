package library.service.converter;

import library.dto.ConvertedCopyDto;
import library.dto.CopyDto;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class CopyConverter {

   public CopyDto convertToCopyDto(ConvertedCopyDto convertedCopyDto){
       CopyDto copyDto = new CopyDto();
       copyDto.setBookId(convertedCopyDto.getBookId());
       copyDto.setId(convertedCopyDto.getId());
       copyDto.setSignature(convertedCopyDto.getSignature());
       copyDto.setStatus(convertedCopyDto.getStatusDto());

       return copyDto;
   }

    public ConvertedCopyDto convertToConvertedCopyDto(CopyDto copyDto){
       ConvertedCopyDto ccDto = new ConvertedCopyDto();
       ccDto.setStatusDto(copyDto.getStatus());
       ccDto.setSignature(copyDto.getSignature());
       ccDto.setId((int) copyDto.getId());
       ccDto.setBookId((int) copyDto.getBookId());
       return ccDto;
    }

}
