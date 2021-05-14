package kasei.springmvc.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ValidationDTO {
    
    @NotBlank
    private String str;
    
    @Min(value = 1, message = "min 1")
    private Integer i;
}
