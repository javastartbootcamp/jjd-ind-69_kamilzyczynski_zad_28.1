package pl.javastart.restoffers.offer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<OfferDto> findFilteredByTitle(String title) {
        List<Offer> offers;
        if (title == null) {
            offers = offerRepository.findAll();
        } else {
            offers = offerRepository.findAllByTitleContainsIgnoreCase(title);
        }
        return offers
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public long countOffers() {
        return offerRepository.count();
    }

    public OfferDto toDto(Offer offer) {
        OfferDto dto = new OfferDto();
        dto.setId(offer.getId());
        dto.setTitle(offer.getTitle());
        dto.setDescription(offer.getDescription());
        dto.setImgUrl(offer.getImgUrl());
        dto.setPrice(offer.getPrice());
        dto.setCategory(offer.getCategory().getName());

        return dto;
    }

    public OfferDto addOffer(Offer offer) {
        offerRepository.save(offer);

        return toDto(offer);
    }

    public Optional<OfferDto> findOfferById(Long id) {
        return offerRepository.findById(id)
                .map(this::toDto);
    }

    public void deleteById(Long id) {
        offerRepository.deleteById(id);
    }
}
