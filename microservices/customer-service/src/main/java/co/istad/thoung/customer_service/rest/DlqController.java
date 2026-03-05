package co.istad.thoung.customer_service.rest;

import co.istad.thoung.customer_service.application.config.DeadLetterProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/dlq")
@RequiredArgsConstructor
@Slf4j
public class DlqController {
    private final DeadLetterProcessor deadLetterProcessor;

    @PostMapping("/{processing-group}/any")
    public CompletableFuture<Boolean> processAny(@PathVariable("processing-group") String processingGroup){
        return deadLetterProcessor.processorAnyFor(processingGroup);
    }
}
