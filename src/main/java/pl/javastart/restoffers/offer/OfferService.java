package pl.javastart.restoffers.offer;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.category.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private OfferRepository offerRepository;
    private CategoryRepository categoryRepository;

    public OfferService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
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

    public OfferDto addOffer(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setTitle(offerDto.getTitle());
        offer.setDescription(offerDto.getDescription());
        offer.setPrice(offerDto.getPrice());
        offer.setImgUrl(offerDto.getImgUrl());
        offer.setCategory(categoryRepository.findByName(offerDto.getCategory()));
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
