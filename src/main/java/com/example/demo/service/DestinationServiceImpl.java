package com.example.demo.service;

import com.example.demo.dto.DestinationDTO;
import com.example.demo.entities.Destination;
import com.example.demo.repositories.DestinationRepository;
import com.example.demo.service.DestinationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private final DestinationRepository destinationRepository;
    @Autowired
    private final ModelMapper modelMapper;


    public static DestinationDTO mapToDestinationDto(Destination destination){
        return new DestinationDTO(
                destination.getId(),
                destination.getName(),
                destination.getType()

        );
    }
    public static Destination mapToDestination(DestinationDTO destinationDto){
        return new Destination(
                destinationDto.getId(),
                destinationDto.getName(),
                destinationDto.getType()

        );
    }



    @Override
    public List<DestinationDTO> getAllDestinations() {
        List<Destination> destinations = destinationRepository.findAll();
        return destinations.stream()
                .map(destination -> modelMapper.map(destination, DestinationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DestinationDTO getDestinationById(Long id) {
        Destination destination = destinationRepository.findById(id).orElse(null);
        if (destination == null) {
            // Gérer le cas où la destination n'est pas trouvée
        }
        return modelMapper.map(destination, DestinationDTO.class);
    }

    @Override
    public DestinationDTO createDestination(DestinationDTO destinationDTO) {
        Destination destination = modelMapper.map(destinationDTO, Destination.class);
        Destination savedDestination = destinationRepository.save(destination);
        return modelMapper.map(savedDestination, DestinationDTO.class);
    }

    @Override
    public DestinationDTO updateDestination(Long id, DestinationDTO destinationDTO) {
        // Vérifier si la destination avec cet ID existe dans la base de données
        Destination existingDestination = destinationRepository.findById(id).orElse(null);
        if (existingDestination == null) {
            // Gérer le cas où la destination n'est pas trouvée
        }
        // Mettre à jour les propriétés de la destination existante avec les valeurs fournies dans le DTO
        existingDestination.setName(destinationDTO.getName());
        existingDestination.setType(destinationDTO.getType());
        // Enregistrer les modifications dans la base de données
        Destination updatedDestination = destinationRepository.save(existingDestination);
        return modelMapper.map(updatedDestination, DestinationDTO.class);
    }

    @Override
    public void deleteDestination(Long id) {
        // Vérifier si la destination avec cet ID existe dans la base de données
        Destination destination = destinationRepository.findById(id).orElse(null);
        if (destination == null) {
            // Gérer le cas où la destination n'est pas trouvée
        }
        // Supprimer la destination de la base de données
        assert destination != null;
        destinationRepository.delete(destination);
    }


}
