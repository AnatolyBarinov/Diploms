package iiot.istok.request;


import iiot.istok.validation.ValidFileName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


@Validated
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RenameRequest {

    @ValidFileName
    String filename;

}