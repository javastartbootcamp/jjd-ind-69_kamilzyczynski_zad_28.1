package pl.javastart.restoffers.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/offers")
@RestController
public class OfferRestController {

    private OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping()
    public List<OfferDto> getAllOffers(@RequestParam(required = false) String title) {
        return offerService.findFilteredByTitle(title);
    }

    @GetMapping("/count")
    public long getOffersQuantity() {
        return offerService.countOffers();
    }

    @PostMapping()
    public OfferDto addOffer(@RequestBody Offer offer) {
        return offerService.addOffer(offer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable Long id) {
        Optional<OfferDto> offerOptional = offerService.findOfferById(id);
        return offerOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteOfferById(@PathVariable Long id) {
        offerService.deleteById(id);
    }
}
