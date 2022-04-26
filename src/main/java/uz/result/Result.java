package uz.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.Model.Commands;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Result {

    private Boolean success;
    private Commands commands;
    private String message;

}
