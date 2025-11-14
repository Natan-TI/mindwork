package com.mindwork.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindwork.dto.MoodEntryRequest;
import com.mindwork.dto.MoodEntryResponse;
import com.mindwork.entities.MoodEntry;
import com.mindwork.entities.User;
import com.mindwork.exceptions.BusinessException;
import com.mindwork.exceptions.ResourceNotFoundException;
import com.mindwork.repositories.MoodEntryRepository;
import com.mindwork.repositories.UserRepository;
import com.mindwork.services.MoodEntryService;

@Service
@Transactional
public class MoodEntryServiceImpl implements MoodEntryService {
	
	private final MoodEntryRepository moodEntryRepository;
    private final UserRepository userRepository;

    public MoodEntryServiceImpl(MoodEntryRepository moodEntryRepository,
                                UserRepository userRepository) {
        this.moodEntryRepository = moodEntryRepository;
        this.userRepository = userRepository;
    }

	@Override
	public MoodEntryResponse create(MoodEntryRequest request) {
		User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.userId()));

//        if (user.getConsentGivenAt() == null) {
//            throw new BusinessException("Usuário ainda não forneceu consentimento para coleta de bem-estar.");
//        }
        
		MoodEntry entry = new MoodEntry();
		
		entry.setUser(user);
        entry.setMood(request.mood());
        entry.setStressLevel(request.stressLevel());
        entry.setSleepHours(request.sleepHours());
        entry.setScreenTimeMinutes(request.screenTimeMinutes());
        entry.setNotes(request.notes());
        entry.setSource(request.source());
        entry.setConfidence(request.confidence());
        
        MoodEntry saved = moodEntryRepository.save(entry);
        return toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public MoodEntryResponse getByID(UUID id) {
		MoodEntry entry = moodEntryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("MoodEntry not found with id: " + id));
		
		return toResponse(entry);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MoodEntryResponse> listByUser(UUID userID) {
		return moodEntryRepository.findByUserIdOrderByCreatedAtDesc(userID)
				.stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<MoodEntryResponse> listByOrganization(UUID organizationID) {
		return moodEntryRepository.findByUserOrganizationId(organizationID, null)
				.stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public void delete(UUID id) {
		if(!moodEntryRepository.existsById(id)) {
			throw new ResourceNotFoundException("MoodEntry not found with id: " + id);
		}
		moodEntryRepository.deleteById(id);
	}
	
	private MoodEntryResponse toResponse(MoodEntry m) {
        return new MoodEntryResponse(
                m.getId(),
                m.getUser().getId(),
                m.getMood(),
                m.getStressLevel(),
                m.getSleepHours(),
                m.getScreenTimeMinutes(),
                m.getNotes(),
                m.getSource(),
                m.getConfidence(),
                m.getCreatedAt()
        );
    }

}
