
package hu.bme.RPAOOITP.components;

import lombok.Getter;

import com.vaadin.ui.FormLayout;

public class GenericFormLayout<DTO extends Object> extends FormLayout implements GenericLayout<DTO> {
	
	@Getter
	private final DTO data;
	
	public GenericFormLayout( final DTO dto ) {
		super();
		this.data = dto;
		setSpacing( true );
	}
	
}
