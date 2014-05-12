package hu.bme.RPAOOITP.domain.model;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;

@MappedSuperclass
@Access(AccessType.FIELD)
public class AbstractUuidIdentifiable extends AbstractIdentifiable<UUID> {
	
	protected AbstractUuidIdentifiable() {
		super();
	}
	
	@PrePersist
	public void generateIdIfNotSpecified() {
		if (getId() == null) {
			final UUID generatedId = UUID.randomUUID();
			setId(generatedId);
		}
	}
	
	@Access(AccessType.PROPERTY)
	@Id
	@Type(type = "uuid-char")
	@Column(length = 36)
	public UUID getId() {
		return super.getId();
	}
	
	public void setId(final UUID id) {
		super.setId(id);
	}

}
