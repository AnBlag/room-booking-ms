package ru.roombooking.mail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.roombooking.mail.model.dto.MailRequest;
import ru.roombooking.mail.model.dto.PreviousAndCurrentRecordTableDTO;
import ru.roombooking.mail.model.dto.RecordTableDTO;
import ru.roombooking.mail.service.mail.impl.NotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final NotificationService notificationService;

    // FIXME: 16.12.2021 Изменены url с "send" на "send-confirmation"
    @PostMapping("/send-confirmation/{roomId}")
    public void sendConfirmMessageToEmployee(@RequestBody RecordTableDTO recordTableDTO,
                                                                        @PathVariable String roomId) {
        notificationService.sendConfirmMessageToEmployee(recordTableDTO, roomId);

    }

    @PostMapping("/send-confirmation/")
    public void sendConfirmUpdateMessageToEmployee(@RequestBody PreviousAndCurrentRecordTableDTO previousAndCurrentRecordTableDTO) {
        notificationService.sendConfirmUpdateMessageToEmployee(previousAndCurrentRecordTableDTO.getPrevious(),
                        previousAndCurrentRecordTableDTO.getCurrent());
    }

    @PostMapping("/delete-confirmation/")
    public void sendConfirmDeleteMessageToEmployee(@RequestBody RecordTableDTO recordTableDTO) {
        notificationService.sendConfirmDeleteMessageToEmployee(recordTableDTO);
    }

    @PostMapping("/send")
    public void send (@RequestBody MailRequest mailRequest) {
        notificationService.send(mailRequest);
    }
}
