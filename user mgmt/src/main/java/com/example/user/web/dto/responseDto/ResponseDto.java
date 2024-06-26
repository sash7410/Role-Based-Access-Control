package com.example.user.web.dto.responseDto;

//import com.example.role.web.exception.ExceptionResponse;
import com.example.user.web.exception.ExceptionResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * Global wrapper for all Data Transfer Objects.
 *
 * @author Sashank RM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    private Object success;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
    private ExceptionResponse error;
}
