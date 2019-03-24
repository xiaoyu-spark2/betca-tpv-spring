package es.upm.miw.rest_controllers;

import es.upm.miw.documents.ArticleLine;
import es.upm.miw.dtos.input.OfferInputDto;
import es.upm.miw.dtos.output.OfferOutputDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
@RestController
@RequestMapping(OfferResource.OFFERS)
public class OfferResource {

    public static final String OFFERS = "/offers";
    public static final String OFFER_ID = "/{idOffer}";

    @GetMapping
    public List<OfferOutputDto> readAll() {
        // TODO REFACTOR in OfferController
        ArticleLine articleLine_1 = new ArticleLine("8400000000017", 20);
        ArticleLine articleLines_2 = new ArticleLine("8400000000024", 15);
        ArticleLine[] articleLines = {articleLine_1, articleLines_2};
        OfferOutputDto offerOutputDto_1 = new OfferOutputDto("1", "Prueba01", LocalDateTime.now(), articleLines);
        OfferOutputDto offerOutputDto_2 = new OfferOutputDto("2", "Prueba02", LocalDateTime.now(), articleLines);
        OfferOutputDto offerOutputDto_3 = new OfferOutputDto("3", "Prueba03", LocalDateTime.now(), articleLines);
        List<OfferOutputDto> offerOutputDtos = new ArrayList<>();
        offerOutputDtos.add(offerOutputDto_1);
        offerOutputDtos.add(offerOutputDto_2);
        offerOutputDtos.add(offerOutputDto_3);
        return offerOutputDtos;
    }

    @PostMapping
    public OfferInputDto createOffer(@Valid @RequestBody OfferInputDto offerInputDto) {
        // TODO REFACTOR in OfferController
        return offerInputDto;
    }

    @DeleteMapping(value = OFFER_ID)
    public String delete(@PathVariable String idOffer) {
        // TODO REFACTOR in OfferController. return void
        return idOffer;
    }

}