package library.dto;


import lombok.Getter;

@Getter
public enum StatusDto {
    AVAILABLE, IN_USE, LOST, DESTROYED;
}
