package com.mindwork.dto;

import com.mindwork.entities.*;

public class Mappers {
  public static OrganizationResponse toResponse(Organization o) {
    return new OrganizationResponse(o.getId(), o.getName(), o.getCreatedAt());
  }
  public static UserResponse toResponse(User u) {
    return new UserResponse(
      u.getId(), u.getName(), u.getEmail(), u.getRole(),
      u.getOrganization().getId(), u.getCreatedAt(), u.getConsentGivenAt()
    );
  }
  public static MoodEntryResponse toResponse(MoodEntry m) {
    return new MoodEntryResponse(
      m.getId(), m.getUser().getId(), m.getMood(), m.getStressLevel(),
      m.getSleepHours(), m.getScreenTimeMinutes(), m.getNotes(),
      m.getSource(), m.getConfidence(), m.getCreatedAt()
    );
  }
}
